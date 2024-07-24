/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.common.ErrorInfo.ErrorType.*;
import static de.dfki.mlt.rudimant.compiler.Constants.*;
import static de.dfki.mlt.rudimant.compiler.Utils.*;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.compiler.*;

/**
 * visitor generates the java code
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VisitorGeneration implements RudiVisitor {

  Writer _out;
  private Mem mem;

  // activate bool to get double escaped String literals
  private boolean escape = false;

  // to count the base terms when generating code for logging the rules
  int baseTerm;

  // The active rule info when generating logging info for rules
  private RuleInfo activeInfo = null;


  public VisitorGeneration(Writer o, Mem m) {
    _out = o;
    mem = m;
  }

  public VisitorGeneration genNL() {
    return gen(System.lineSeparator());
  }

  public VisitorGeneration gen(char c) {
    try {
      _out.append(c);
    } catch (IOException ex) {
      throw new WriterException(ex);
    }
    return this;
  }

  public VisitorGeneration gen(CharSequence c) {
    try {
      _out.append(c);
    } catch (IOException ex) {
      throw new WriterException(ex);
    }
    return this;
  }

  public VisitorGeneration gen(int i) {
    try {
      _out.append(Integer.toString(i));
    } catch (IOException ex) {
      throw new WriterException(ex);
    }
    return this;
  }

  public VisitorGeneration gen(boolean b, char c) {
    if (b) gen(c); return this;
  }

  public VisitorGeneration gen(boolean b, CharSequence c) {
    if (b) gen(c); return this;
  }

  public VisitorGeneration gen(boolean b, RudiTree c) {
    if (b) gen(c); return this;
  }

  /**
   * Handle parentheses properly for expressions
   * @param v
   */
  public VisitorGeneration gen(RudiTree rt) {
    if (! rt.comments.isEmpty()) genNL();
    for (Token tok: rt.comments) {
      gen(tok.content());
    }
    gen(rt._parens, "(");
    rt.visit(this);
    gen(rt._parens, ")");
    return this;
  }

  /** If this method is called in a generated class that is *not* the top level
   *  class, the topLevelInstanceName and a dot will be output to guarantee
   *  that functions, constants, and fields that are provided by the top level
   *  agent object are accessed in the right way.
   *
   *  So in principle, this could also output "this." in case it's the top level
   */
  private void accessTopLevelInstance() {
    if(mem.isNotToplevelClass())
      gen(mem.getToplevelInstance()).gen(".");
  }

  private static final String[] PREFIXOPS = { "-", "!", "--", "++" };
  static { Arrays.sort(PREFIXOPS); }

  private boolean isPrefixOperator(String op) {
    return op.endsWith("(") || Arrays.binarySearch(PREFIXOPS, op) >= 0;
  }

  private boolean isPostfixOperator(String op) {
    return "---".equals(op) || "+++".equals(op) || op.endsWith(")");
  }

  private String getPostfixOperator(String op) {
    char c = op.charAt(0);
    return c == '+' || c == '-' ? op.substring(0, 2) : op;
  }

  public void genLocation(RudiTree node) {
    gen("// Line " + node.location.getBegin().getLine() + " in rudi\n");
  }

  @Override
  public void visit(RudiTree node) {
    gen(node);
  }

  @Override
  public void visit(ExpArithmetic node) {
    if (node.right == null) {
      if ((node.operator.startsWith("++") || node.operator.startsWith("--"))
          && node.left instanceof ExpFieldAccess) {
        ExpFieldAccess acc = (ExpFieldAccess)node.left;
        RudiTree lastPart = acc.parts.get(acc.parts.size() - 1);
        if (lastPart instanceof ExpPropertyAccess) {
          ExpPropertyAccess pa = (ExpPropertyAccess) lastPart;
          switch (node.operator) {
          case "++": pa.acc= ExpPropertyAccess.Access.incr; break;
          case "+++": pa.acc= ExpPropertyAccess.Access.pincr; break;
          case "--": pa.acc= ExpPropertyAccess.Access.decr; break;
          case "---": pa.acc= ExpPropertyAccess.Access.pdecr; break;
          }
        }
        gen(node.left);
      } else {
        // unary operator
        gen(isPrefixOperator(node.operator), node.operator);
        gen(node.left);
        // something like .isEmpty(), a++, which is a postfix operator
        if (isPostfixOperator(node.operator)) {
          gen(getPostfixOperator(node.operator));
        }
      }
    } else {
      gen(node.left).gen(node.operator).gen(node.right);
      gen((node.operator.endsWith("(")), ')');
    }
  }

  @Override
  public void visit(ExpAssignment node) {
    ExpPropertyAccess pa = null;
    if (node.left instanceof ExpFieldAccess) {
      ExpFieldAccess acc = (ExpFieldAccess) node.left;
      RudiTree lastPart = acc.parts.get(acc.parts.size() - 1);
      if (lastPart instanceof ExpPropertyAccess) {
        pa = ((ExpPropertyAccess) lastPart).copy();
        if (node.right instanceof ExpLiteral &&
            ((ExpLiteral)node.right).content.equals("null")) {
          pa.acc = ExpPropertyAccess.Access.clearValue;
        } else {
          pa.acc = ExpPropertyAccess.Access.setValue;
          pa.secondArg = node.right;
        }
        acc.parts.set(acc.parts.size() - 1, pa);
        gen(node.left);
        return;
      }
    }

    gen(node.left).gen(" = ");
    if (node.type.needsCast(node.right.getType())
        && !(node.right instanceof ExpNew)) {
      // then there is either sth wrong here, what would at least have resulted
      // in warnings in type testing, or it is possible to cast the right part
      gen('(').gen(node.type.toJava()).gen(") ");
    }
    gen(node.right);
  }


  private static Type assessTypes(Type left, Type right) {
    if (right.isNull()) return right;
    if (left.isNull()) return left;
    if (right.isString() && ! left.isString()) {
      Type h = right; right = left; left = h;
    }
    if (left.isString() && (right.isRdfType() || right.isDialogueAct()))
      return right;
    return left;
  }

  private void generateAndMassageType(RTExpression node,
      Type resultType, String operator) {
    // collectTerms is guaranteed to be false here!
    if (node.type.isString()) {
      if (resultType.isDialogueAct()) {
        gen("new DialogueAct(").gen(node).gen(")");
        return;
      }
      if (resultType.isRdfType() && ! "==".equals(operator)) {
        gen(node).gen(".getClazz()");
        return;
      }
    }
    // TODO: MAYBE THIS MUST BE GENERALIZED TO OTHER JAVA TYPES THAN STRING
    // THAT CAN BE CONVERTED AUTOMATICALLY FROM XSD TYPES
    if (node.type.isRdfType()) {
      if (node instanceof ExpIdentifier
          && !mem.variableExists(((ExpIdentifier)node).content)) {
        String orig = mem.getFunctionOrigin("getRdfClass", null, "String");
        if (orig != null)
          gen(lowerCaseFirst(orig)).gen(".");
        gen("getRdfClass(\"").gen(node).gen("\")");
        return;
      } else {
        if (! "==".equals(operator)) {
          gen(node).gen(".getClazz()");
          return;
        }
      }
    }
    gen(node);
  }

  static Map<String, String> rdfOpMap = new HashMap<>();
  static Map<String, String> dialOpMap = new HashMap<>();
  static Map<String, String> striOpMap = new HashMap<>();
  static Map<String, String> objOpMap = new HashMap<>();
  static {
    String[] rdf = { "==", ".equals()",
        "<=", ".isSubclassOf()",
        "<", ".isTrueSubclassOf()",
        ">=", ".isSuperclassOf()",
        ">", ".isTrueSuperclassOf()",
    };
    for(int i = 0; i < rdf.length; i += 2) {
      rdfOpMap.put(rdf[i], rdf[i+1]);
    }
    String[] dial = { "==", ".equals()",
        "<=", ".subsumes()",
        "<", ".strictlySubsumes()",
        ">=", ".isSubsumedBy()",
        ">", ".isStrictlySubsumedBy()",
    };
    for(int i = 0; i < dial.length; i += 2) {
      dialOpMap.put(dial[i], dial[i+1]);
    }
    String[] stri = { "==", ".equals()",
        "<=", ".compareTo() <= 0",
        "<", ".compareTo() < 0",
        ">=", ".compareTo() >= 0",
        ">", ".compareTo() > 0"
    };
    for(int i = 0; i < stri.length; i += 2) {
      striOpMap.put(stri[i], stri[i+1]);
    }
    String[] obj = { "==", "==",
        "<=", "instanceof",
        "<", "instanceof",
        ">=", ".class.isInstance()",
        ">", ".class.isInstance()"
    };
    for(int i = 0; i < obj.length; i += 2) {
      objOpMap.put(obj[i], obj[i+1]);
    }
  }

  private String massageOperator(String operator, Type resultType) {
    if (resultType.isDialogueAct()) return dialOpMap.get(operator);
    if (resultType.isRdfType()) return rdfOpMap.get(operator);
    if (resultType.isString()) return striOpMap.get(operator);
    if (resultType.isPODType()) return operator;
    return objOpMap.get(operator);
  }

  private void massageTest(RTExpression node) {
    // collectTerms is guaranteed to be false here!
    Type type = node.type;
    if (node instanceof ExpFieldAccess) {
      ExpFieldAccess fa = (ExpFieldAccess)node;
      RTExpression nextToLast = fa.parts.get(fa.parts.size() - 2);
      if (nextToLast.type.isDialogueAct()) {
        RTExpression last = fa.parts.get(fa.parts.size() - 1);
        if (last instanceof ExpPropertyAccess) {
          ExpPropertyAccess acc = ((ExpPropertyAccess)last);
          ExpPropertyAccess.Access orig = acc.acc;
          acc.acc = ExpPropertyAccess.Access.hasSlot;
          gen(fa);
          acc.acc = orig;
          return;
        }
      }
    }
    if (type.isBool()) {
      gen(node);
    } else if (type.isPODType()) {
      gen(node).gen(" != 0");
    } else if (type.isCollection() || type.isString() || type.isNumberContainer()
        || type.isBoolContainer() || type.isDialogueAct()) {
      // We "know" this is an "Agent" method
      String orig = mem.getFunctionOrigin("exists", null, "Object");
      orig = orig == null ? "" : lowerCaseFirst(orig) + ".";
      gen(orig);
      gen("exists(").gen(node).gen(")");
    } else {
      gen(node).gen(" != null");
    }
  }

  /** Massage comparisons if necessary
   * What combinations are we expecting?
   * POD vs POD --> keep operator
   * POD vs Container --> container may be null! Ignore for now?
   * String vs String --> i.compareTo(j) < 0 etc.
   * String vs DialogueAct --> convert String and apply DA vs DA (isSubsumed, etc.)
   * String vs Rdf --> convert to RdfClass and use isSub/SuperclassOf
   * DA vs DA, DA vs String --> isSubsumed and the like.
   * Rdf == Rdf --> equals
   */
  private void massageComparison(ExpBoolean node) {
    String operator = node.operator;
    if (operator.equals("!=")) {
      operator = "==";
      gen("! (");
    }
    // TODO: THERE'S A SPECIAL CASE IF BOTH ARE RDF AND OPERATOR IS ==
    // THEN, IT SHOULD JUST BE a.equals(b)
    Type resultType = assessTypes(node.left.type, node.right.type);
    String newOp = massageOperator(operator, resultType);
    int lastClosing = newOp.lastIndexOf(')');
    String pref, suff;
    if (lastClosing >= 0) {
      pref = newOp.substring(0, lastClosing);
      suff = newOp.substring(lastClosing);
    } else {
      pref = " " + newOp + " ";
      suff = "";
    }
    this.generateAndMassageType(node.left, resultType, operator);
    gen(pref);
    this.generateAndMassageType(node.right, resultType, operator);
    gen(suff);
    gen("!=".equals(node.operator), ')');
  }

  private boolean handleRuleLogging(RudiTree node) {
    if (node instanceof ExpAssignment) {
      // then there must be parenthesis around this
      gen("(");
      return true;
    }
    if (activeInfo == null) return false;
    if (node instanceof ExpBoolean) {
      ExpBoolean n = (ExpBoolean)node;
      if (! n.synthetic && n.operator != null && isBooleanOperator(n.operator))
        return false;
    }
    gen("(").gen(activeInfo.resultVarName()).gen('[').gen(baseTerm++).gen("] = ");
    activeInfo = null;
    return true;
  }

  /** Treat the special case where we prepare for rule logging in this function
   *  and then delegate to the "real" generation method
   */
  private void visitExpBoolChild(RudiTree node) {
    RuleInfo info = activeInfo;
    boolean closeParen = handleRuleLogging(node);
    gen(node).gen(closeParen, ')');
    activeInfo = info;
  }

  /** In case activeInfo is not null and ruleIfSuspended is false, we have to
   *  check if the operator is one of &&, || or !, in which case we add the
   *  operator to the infix representation in the rule info and proceed
   *  collecting base terms.
   *
   *  Otherwise, we have to do some things:
   *   1. add the base term to the rule info
   *   2. set ruleIfSuspended to true before descending
   */
  @Override
  public void visit(ExpBoolean node) {
    RuleInfo info = activeInfo;
    boolean closeParen = handleRuleLogging(node);
    if (node.right != null) { // binary expression?
      if (isComparisonOperator(node.operator)
          && !node.left.type.isPODType() && !node.right.type.isPODType()) {
        massageComparison(node);
      } else {
        visitExpBoolChild(node.left);
        gen(' ').gen(node.operator).gen(' ');
        visitExpBoolChild(node.right);
      }
    } else { // unary boolean expression
      if (null == node.operator) {
        // activeInfo is guaranteed to be null here!
        gen(node.left);
      } else if (node.operator.equals("<>")) {
        // marker for generation, to probably wrap the right tests around?
        // activeInfo is guaranteed to be null here!
        massageTest(node.left);
      } else {
        gen(node.operator + '(');
        visitExpBoolChild(node.left);
        gen(')');
      }
    }
    gen(closeParen, ")");
    // reset activeInfo to state when entering this method
    activeInfo = info;
  }

  @Override
  public void visit(ExpCast node) {
    // "(${type.toJava()})${expression}"
    gen("(").gen(node.type.toJava()).gen(")").gen(node.expression);
  }

  @Override
  public void visit(ExpDialogueAct node) {
    // "new DialogueAct($[daType], $[proposition] ${{(e, f) : exps}, $[e], $[f]})"
    gen("new DialogueAct(").gen(node.daType).gen(", ").gen(node.proposition);
    for (int i = 0; i < node.exps.size(); i += 2) {
      gen(", ").gen(node.exps.get(i)).gen(", ").gen(node.exps.get(i + 1));
    }
    gen(")");
  }

  @Override
  public void visit(ExpConditional node) {
    // "($[boolexp] ? $[thenexp] : $[elseexp])"
    gen("(").gen(node.boolexp).gen(" ? ").gen(node.thenexp).gen(" : ").gen(node.elseexp).gen(')');
  }

  @Override
  public void visit(ExpLambda node) {
    mem.enterEnvironment(node);
    // "(${{p : parameters[0:-1]}${p}, }${parameters[-1]}) -> $[body]"
    gen("(" + node.parameters.get(0));
    for(int i = 1; i < node.parameters.size(); i++){
      gen(", " + node.parameters.get(i));
    }
    gen(") -> ").gen(node.body);
    mem.leaveEnvironment(node);
  }

  @Override
  public void visit(ExpNew node) {
    if (node.params != null) {
      gen("new ");
      if (node.type.isArray()) {
        gen(node.type.getInnerType().toJava()).gen('[');
      } else {
        gen(node.type.toJava()).gen('(');
      }
      boolean first = true;
      for(RTExpression param : node.params) {
        if (! first) {
          gen(", ");
        } else first = false;
        gen(param);
      }
      gen(node.type.isArray() ? ']' : ')');
    } else {
      RdfClass clz = node.type.getRdfClass();
      String clazz = (clz == null ? node.type.toJava() : clz.toString());
      accessTopLevelInstance();
      gen("_proxy.getClass(\"").gen(clazz).gen("\").getNewInstance(");
      accessTopLevelInstance();
      gen("DEFNS)");
    }
  }

  @Override
  public void visit(StatGrammarRule node) {
    if (node.toplevel) {
      mem.enterEnvironment(node);
      // is a top level rule, generate a block to shield variables
      gen("// Rule ").gen(node.label).gen("\n{");
    } else {
      // a sub-level rule: ordinary <if>
      gen("// Rule ").gen(node.label).gen("\n");
    }
    genLocation(node);
    // generate output for rule logging. This is the "toplevel", because
    // activeInfo is non-Null, the visit call to ifNode.condition will generate
    // the appropriate assignments of baseterms to the bool array.
    activeInfo = mem.enterRule(node.ruleId);
    String varName = activeInfo.resultVarName();
    gen("boolean[] ").gen(varName)
       .gen(" = new boolean[").gen(activeInfo.noBaseTerms() + 1).gen("];\n");
    StatIf ifNode = node.ifstat;
    // first assign final result, then call log function, then execute if
    gen(varName).gen("[0] = ");

    baseTerm = 1;
    gen(ifNode.condition).gen(";\n");
    accessTopLevelInstance();
    gen("logRule(").gen(activeInfo.getId()).gen(", ").gen(varName).gen(");\n");
    gen(node.label + ":\n").gen("if (").gen(varName).gen("[0])");
    activeInfo = null;  // no more rule logging code from here on

    gen(ifNode.statblockIf).gen("\n");
    if (ifNode.statblockElse != null) {
      gen("else ").gen(ifNode.statblockElse);
    }
    gen("// Rule ").gen(node.label).gen(" end\n");
    if (node.toplevel) {
      gen("\n}\n");
      mem.leaveEnvironment(node);
    }
    mem.leaveRule();
  }

  @Override
  public void visit(StatAbstractBlock node) {
    if (node.braces) {
      // when entering a statement block, we need to enter the local environment
      gen("{\n");
      mem.enterEnvironment(node);
    }
    genLocation(node);
    for (RudiTree stat : node.statblock) {
      gen(stat);
    }
    if (node.braces) {
      gen("\n}\n");
      mem.leaveEnvironment(node);
    }
  }

  @Override
  public void visit(StatFor1 node) {
    // for ($[initialization]$[condition];$[increment]) $[statblock]
    genLocation(node);
    gen("for ( ").gen(node.initialization).gen(node.condition).gen(";");
    if (node.increment != null) {
      gen(node.increment);
    }
    gen(")").gen(node.statblock);
  }

  @Override
  public void visit(StatFor2 node) {
    // for (Object $[var]_outer : $[initialization]) {
    //   ${varType.toJava()} $[var] = (${varType.toJava()})$[var]_outer;
    //   $[statblock]
    // }
    genLocation(node);
    gen("for (Object ").gen(node.var).gen("_outer : ").gen(node.initialization).gen(") { ");
    gen(node.varType.toJava()).gen(" ").gen(node.var)
      .gen(" = (").gen(node.varType.toJava()).gen(")").gen(node.var).gen("_outer;\n");
    gen(node.statblock).gen("\n}\n");
  }

  @Override
  public void visit(StatFor3 node) {
    // for (Object[] o : $[initialization]) {
    //   ${{s : variables} ${s} = o[${s?index}]\n};
    //   $[statblock]
    // }
    genLocation(node);
    gen("for (Object[] o : ").gen(node.initialization).gen(") {");
    int count = 0;
    for (String s : node.variables) {
      gen("\nObject ").gen(s).gen(" = o[").gen(count++).gen("]");
    }
    gen(node.statblock).gen("\n}");
  }

  @Override
  public void visit(StatIf node) {
    // if ($[condition]) $[statblockIf]
    // ${{statblockElse??}else $[statblockElse]}
    genLocation(node);
    gen("if (").gen(node.condition).gen(") ").gen(node.statblockIf).gen("\n");
    if (node.statblockElse != null) {
      gen("else ").gen(node.statblockElse);
    }
  }

  @Override
  public void visit(ExpListLiteral node) {
    gen(" new ");
    if (node.type.isArray()) {
      gen(node.type.getInnerType().toJava()).gen("[]{");
      boolean first = true;
      for (RTExpression e : node.objects) {
        if (! first) gen(", ");
        else first = false;
        gen(e);
      }
      gen("}");
    } else {
      gen(node.type.toConcreteCollection()).gen("()");
    }

    /*
    // This functionality is now in other form in visit(StatVarDef node)
    if (! node.objects.isEmpty()) {
      gen("{{");
      for (RTExpression e : node.objects) {
        gen("add("); visit(e); gen(");\n");
      }
      gen("}}");
    }*/
  }

  @Override
  public void visit(StatMethodDeclaration node) {
    if (node.block == null) {
      return;
    }
    genLocation(node);
    mem.enterEnvironment(node);
    // return type
    gen(node.visibility).gen(' ').gen(node.function_type.getReturnedType().toJava())
    .gen(" ").gen(node.name).gen("(");
    // must be a "vonda" method, so no class member argument!
    Iterator<Type> paramTypes = node.function_type.getParameterTypes();
    for (int j = 0; j < node.parameters.size(); ++j) {
      gen((j != 0), ", ");
      gen(paramTypes.next().toJava() + " " + node.parameters.get(j));
    }
    gen(")\n").gen(node.block);
    mem.leaveEnvironment(node);
  }

  @Override
  public void visit(StatPropose node) {
    genLocation(node);
    accessTopLevelInstance();
    gen("propose(").gen(node.arg).gen(",");
    accessTopLevelInstance();
    gen("new Proposal() {public void run()\n").gen(node.block).gen("});\n");
  }

  /** newTimeout("label", time, new Proposal(public void run(){ block }))
   *  if the label ist null, then this is a "behaviour finished" triggered
   *  timeout.
   */
  @Override
  public void visit(StatTimeout node) {
    genLocation(node);
    accessTopLevelInstance();
    if (node.label.getType().isDialogueAct()) {
      gen("behaviourTimeout(");
    } else {
      gen("newTimeout(");
    }
    gen(node.label).gen(",").gen(node.time).gen(",");
    accessTopLevelInstance();
    gen("new Proposal() {public void run()\n").gen(node.block).gen("});\n");
  }

  @Override
  public void visit(StatReturn node) {
    switch (node.command) {
    case "break":
    case "return":
      gen(node.command).gen(' ');
      gen((node.returnExp != null), node.returnExp);
      gen((node.ruleLabel != null), node.ruleLabel);
      gen(";\n");
      break;
    case "cancel":
      gen("return ").gen(CANCEL_LOCAL).gen(";\n");
      break;
    case "cancel_all":
      gen("return ").gen(CANCEL_GLOBAL).gen(";\n");
      break;
    case "continue":
      gen(node.command).gen(";\n");
      break;
    default:
      // this can only happen when VondaGrammar is wrong
      mem.registerError("Wrong return command: " + node.command,
          node.getLocation(), ERROR);
    }
  }


  @Override
  public void visit(StatSetOperation node) {
    gen(node.left);
    // is this an arithmetic operation rather than a set operation?
    if (node.right == null) {
      gen(";");
    } else {
      if (node.add) {
        gen(".add(");
      } else {
        gen(".remove(");
      }
      gen(node.right).gen(");");
    }
  }

  @Override
  public void visit(StatVarDef node) {
    if (node.isDefinition) { // Definition of var?
      gen((node.varIsFinal), "final ");
      gen(node.type.toJava()).gen(" ");
    }
    if (node.toAssign != null) {
      gen(node.toAssign);
    } else {
      gen(node.variable);
    }
    gen(";");
    // TODO: IF WE CHANGE OUR MIND ABOUT THE INITIALIZATION OF COLLECTIONS,
    // THIS IS THE PLACE TO PUT IT:
    /**/
    if (node.toAssign != null &&
        ((ExpAssignment)node.toAssign).right instanceof ExpListLiteral) {
      ExpListLiteral listNode =
          (ExpListLiteral)((ExpAssignment)node.toAssign).right;
      if (!listNode.type.isArray() && ! listNode.objects.isEmpty()) {
        for (RTExpression e : listNode.objects) {
          gen(node.variable).gen(".add(");
          visit(e);
          gen(");\n");
        }
      }
    }
  }

  /** Top-level field (variable) definition: only spit out definition! */
  @Override
  public void visit(StatFieldDef node) {
    // Don't print if this is only info about a field of some other class
    // TODO: Normally we shouldn't need this because all this info should go
    //       into wrapper class rudi...
    if (node.varDef.type.isField()) return;
    gen(node.visibility).gen(node.varDef.varIsFinal ? " final ": " ")
       .gen(node.varDef.type.toJava()).gen(" ")
       .gen(node.varDef.variable).gen(";");
  }

  @Override
  public void visit(StatWhile node) {
    genLocation(node);
    if (node.isWhileDo()) {
      gen("while (").gen(node.condition).gen(")").gen(node.block);
    } else {
      gen("do").gen(node.block).gen("while (").gen(node.condition).gen(");");
    }
  }

  @Override
  public void visit(StatSwitch node) {
    genLocation(node);
    gen("switch (").gen(node.condition).gen(")").gen(node.block);
  }

  @Override
  public void visit(ExpFieldAccess node) {
    RTExpression currentPart = node.parts.get(0);
    gen(currentPart);
    Type currentType = currentPart.type;
    for (int i = 1; i < node.parts.size(); i++) {
      currentPart = node.parts.get(i);
      if (currentPart instanceof ExpPropertyAccess) {
        ExpPropertyAccess pa = (ExpPropertyAccess) currentPart;
        // then we are in the case that is actually an RDF operation
        if (currentType.isDialogueAct()) pa.functional = false;
        gen('.').gen(pa.getFunctionName()).gen('(');
        gen(pa.getPropertyName());
        if (pa.secondArg != null) {
          gen(", ").gen(pa.secondArg);
        }
        gen(')');
        currentType = pa.type;
      } else {
        gen(".").gen(currentPart);
        currentType = currentPart.type;
      }
    }
  }

  @Override
  public void visit(ExpPropertyAccess node) {
    throw new UnsupportedOperationException("Should not be visited");
  }

  @Override
  public void visit(ExpFuncCall node) {
    if (node.realOrigin != null && node.calledUpon == null) {
      gen(lowerCaseFirst(node.realOrigin)).gen('.');
    }
    int start = 0;
    if (node.content.charAt(0) == '.') {
      assert(node.params.size() > 0);
      gen(node.params.get(0));
      start = 1;
    }
    if (node.newexp){
      gen(node.type.toJava());
    } else {
      gen(node.content);
    }
    gen('(');
    for (int i = start; i < node.params.size(); i++) {
      gen(node.params.get(i));
      if (i != node.params.size() - 1) {
        gen(", ");
      }
    }
    gen(')');
  }

  @Override
  public void visit(StatExpression rt) {
    gen(rt.expression).gen(";\n");
  }

  @Override
  public void visit(ExpLiteral node) {
    String content = node.content;
    if (node.type.isString()) {
      if (content.indexOf('"') != 0)
        content = "\"" + content + "\"";
      if (escape) {
        // properly escape if needed
        gen('\\').gen(content.substring(0, content.length() - 1))
           .gen("\\\" ");
        return;
      }
    }
    gen(content);
  }

  @Override
  public void visit(ExpIdentifier node) {
    if (node.type.castRequired()) {
      gen("((").gen(node.type.toJava()).gen(')');
    }
    String realOrigin = mem.getVariableOriginClass(node.content);
    if (realOrigin != null) {
      gen(lowerCaseFirst(realOrigin)).gen('.');
    }
    gen(node.content);
    gen(node.type.castRequired(), ')');
  }

  String stringEscape(String in) {
    return in.replaceAll("\\\"", "\\\\\"");
  }

  @Override
  public void visit(ExpArrayAccess node) {
    gen(node.array).gen("[").gen(node.index).gen("]");
  }
}
