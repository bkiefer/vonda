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

import static de.dfki.mlt.rudimant.compiler.Constants.*;
import static de.dfki.mlt.rudimant.compiler.Utils.*;

import java.io.Writer;
import java.util.*;

//import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.compiler.*;

/**
 * visitor generates the java code
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VisitorGeneration implements RudiVisitor {

  public static Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  SilentWriter out;
  private Mem mem;

  LinkedList<Token> collectedTokens;

  boolean whatToLog;

  // activate bool to get double escaped String literals
  private boolean escape = false;

  // to count the base terms when generating code for logging the rules
  int baseTerm;

  // The active rule info when generating logging info for rules
  private RuleInfo activeInfo = null;


  public VisitorGeneration(Writer o, Mem m, boolean logHow, LinkedList<Token> tokens) {
    out = new SilentWriter(o);
    mem = m;
    whatToLog = logHow;
    //condV = new VisitorConditionLog(this);
    collectedTokens = tokens;
  }

  public static String removeJavaBrackets(String c){
    // Deal with java code
    if (c.startsWith("/*@")) {
      c = c.substring(3, c.length() - 3);
    }
    return c;
  }

  protected void checkComments(Position firstPos) {
    Iterator<Token> it = collectedTokens.iterator();
    Token next;
    boolean content = false;
    while (it.hasNext()
        && (next = it.next()).getStart().getCharpos() < firstPos.getCharpos()) {
      it.remove();
      String comment = next.getText();
      comment = removeJavaBrackets(comment);
      if (!comment.trim().isEmpty()) {
        out.append(comment);
        content = true;
      }
    }
    if (content) out.append('\n');
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
      out.append(mem.getToplevelInstance()).append(".");
  }


  @Override
  public void visitNode(RudiTree node) {
    node.visitWithComments(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    if (node.right == null) {
      // unary operator
      // TODO: ENCAPSULATE THIS INTO TWO FUNCTIONS: isPrefixOperator() and
      // isPostFixOperator()
      if ("-".equals(node.operator) || "!".equals(node.operator)) {
        out.append(node.operator);
      }
      out.append('(');
      node.left.visitWithComments(this);
      // something like .isEmpty(), which is a postfix operator
      if (node.operator.endsWith(")")) {
        out.append(node.operator);
      }
    } else {
      out.append('(');
      node.left.visitWithComments(this);
      out.append(node.operator);
      node.right.visitWithComments(this);
      if (node.operator.endsWith("(")) {
        out.append(')');
      }
    }
    out.append(')');
  }

  /** If this is true, when generating a ExpFieldAccess we will not
   *  generate an RDF access for the last part, which will be realized
   *  by a method call.  This is highly, EXTREMELY, dangerous and error
   *  prone, but currently we don't have a better idea.
   */
  boolean replaceLastWithFuncall = false;

  @Override
  public void visitNode(ExpAssignment node) {
    ExpPropertyAccess pa = null;
    if (node.left instanceof ExpFieldAccess) {
      ExpFieldAccess acc = (ExpFieldAccess) node.left;
      RudiTree lastPart = acc.parts.get(acc.parts.size() - 1);
      if (lastPart instanceof ExpPropertyAccess) {
        pa = (ExpPropertyAccess) lastPart;
      }
      // don't print the last field since is will be replaced by a set...(a, b)
      replaceLastWithFuncall = pa != null;
      node.left.visitWithComments(this);
      if (replaceLastWithFuncall) {
        if (node.right instanceof ExpSingleValue &&
            ((ExpSingleValue)node.right).content.equals("null")) {
          replaceLastWithFuncall = false;
          out.append(".clearValue(" + pa.getPropertyName() + ")");
          return;
        }
        // NO: out.append(functional ? ".setSingleValue(" : ".setValue(");
        out.append(".setValue(");  // always right!
        out.append(pa.getPropertyName());
        out.append(", ");
      } else {
        out.append(" = ");
      }
      replaceLastWithFuncall = false;
    } else {
      node.left.visitWithComments(this);
      out.append(" = ");
    }
    if (node.type != null) {
      if (node.type.needsCast(node.right.getType())
          && !(node.right instanceof ExpNew)) {
        // then there is either sth wrong here, what would at least have resulted
        // in warnings in type testing, or it is possible to cast the right part
        out.append("(" + node.type.toJava() + ") ");
      }
    }
    node.right.visitWithComments(this);
    if (pa != null) {
      out.append(")"); // close call to setValue()
    }
  }


  private static Type assessTypes(Type left, Type right) {
    if (right.isNull()) return right;
    if (left.isNull()) return left;
    if (right.isString() && ! left.isString()) {
      Type h = right; right = left; left = h;
    }
    if (left.isString()
        && (right.isDialogueAct() || right.isRdfType()))
      return right;
    return left;
  }

  private void generateAndMassageType(RTExpression node,
      Type resultType, String operator) {
    // collectTerms is guaranteed to be false here!
    if (node.type.isString()) {
      if (resultType.isDialogueAct()) {
        out.append("new DialogueAct(");
        node.visitWithComments(this);
        out.append(")");
        return;
      }
      if (resultType.isRdfType() && ! "==".equals(operator)) {
        node.visitWithComments(this);
        out.append(".getClazz()");
        return;
      }
    }
    // TODO: MAYBE THIS MUST BE GENERALIZED TO OTHER JAVA TYPES THAN STRING
    // THAT CAN BE CONVERTED AUTOMATICALLY FROM XSD TYPES
    if (!node.type.isDialogueAct()
        && node.type.isRdfType()
        && resultType.isRdfType()) {
      if (node instanceof ExpVariable
          && ((ExpVariable) node).content.startsWith("\"")) {
        // TODO: ADD WRAPPER CLASS ACCESS
        Type[] args = { new Type("String") };
        String orig = mem.getFunctionOrigin("getRdfClass", Arrays.asList(args));
        if (orig != null)
          out.append(lowerCaseFirst(orig)).append(".");
        out.append("getRdfClass(");
        node.visitWithComments(this);
        out.append(")");
        return;
      } else {
        if (! "==".equals(operator)) {
          node.visitWithComments(this);
          out.append(".getClazz()");
          return;
        }
      }
    }
    node.visitWithComments(this);
  }

  static Map<String, String> rdfOpMap = new HashMap<>();
  static Map<String, String> dialOpMap = new HashMap<>();
  static Map<String, String> striOpMap = new HashMap<>();
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
  }

  private String massageOperator(String operator, Type resultType) {
    if (resultType.isDialogueAct()) return dialOpMap.get(operator);
    if (resultType.isRdfType()) return rdfOpMap.get(operator);
    if (resultType.isString()) return striOpMap.get(operator);
    return operator;
  }

  private void massageTest(RTExpression node) {
    // collectTerms is guaranteed to be false here!
    Type type = node.type;
    if (node instanceof ExpFieldAccess) {
      ExpFieldAccess fa = (ExpFieldAccess)node;
      RTExpression nextToLast = fa.parts.get(fa.parts.size() - 2);
      if (nextToLast.type.isDialogueAct()) {
        boolean oldrep = replaceLastWithFuncall;
        replaceLastWithFuncall = true;
        fa.visitWithComments(this);
        out.append(".hasSlot(\"" +
            ((RTExpLeaf)(fa.parts.get(fa.parts.size() - 1))).content + "\")");
        replaceLastWithFuncall = oldrep;
        return;
      }
    }
    if (type.isBool()) {
      node.visitWithComments(this);
    } else if (type.isPODType()) {
      node.visitWithComments(this); out.append(" != 0");
    } else if (type.isCollection() || type.isString() || type.isNumber()) {
      Type[] args = { new Type("Object") };
      String orig = mem.getFunctionOrigin("exists", Arrays.asList(args));
      orig = orig == null ? "" : lowerCaseFirst(orig) + ".";
      out.append(orig + "exists(");
      node.visitWithComments(this);
      out.append(")");
    } else {
      node.visitWithComments(this);
      out.append(" != null");
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
      out.append("! (");
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
    out.append(pref);
    this.generateAndMassageType(node.right, resultType, operator);
    out.append(suff);
    if ("!=".equals(node.operator)) out.append(')');
  }

  private boolean handleRuleLogging(RudiTree node) {
    if (node instanceof ExpAssignment) {
        // then there must be parenthesis around this
        out.append("(");
        return true;
    }
    if (activeInfo == null) return false;
    if (node instanceof ExpBoolean) {
      ExpBoolean n = (ExpBoolean)node;
      if (! n.synthetic && n.operator != null && isBooleanOperator(n.operator))
        return false;
    }
    out.append("(")
    .append(activeInfo.resultVarName())
    .append('[').append(Integer.toString(baseTerm++)).append("] = ");
    activeInfo = null;
    return true;
  }

  /** Treat the special case where we prepare for rule logging in this function
   *  and then delegate to the "real" generation method
   */
  private void visitExpBoolChild(RudiTree node) {
    RuleInfo info = activeInfo;
    boolean closeParen = handleRuleLogging(node);
    node.visitWithComments(this);
    if (closeParen) out.append(')');
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
  public void visitNode(ExpBoolean node) {
    RuleInfo info = activeInfo;
    boolean closeParen = handleRuleLogging(node);
    if (node.right != null) { // binary expression?
      if (isComparisonOperator(node.operator)
          && !node.left.type.isPODType() && !node.right.type.isPODType()) {
        massageComparison(node);
      } else {
        visitExpBoolChild(node.left);
        out.append(" " + node.operator + " ");
        visitExpBoolChild(node.right);
      }
    } else { // unary boolean expression
      if (null == node.operator) {
        // activeInfo is guaranteed to be null here!
        node.left.visitWithComments(this);
      } else if (node.operator.equals("<>")) {
        // marker for generation, to probably wrap the right tests around?
        // activeInfo is guaranteed to be null here!
        massageTest(node.left);
      } else {
        out.append(node.operator + '(');
        visitExpBoolChild(node.left);
        out.append(')');
      }
    }
    if (closeParen) out.append(")");
    // reset activeInfo to state when entering this method
    activeInfo = info;
  }

  @Override
  public void visitNode(ExpCast node) {
    out.append("((" + node.type.toJava() + ")");
    visitNode(node.expression);
    out.append(")");
  }

  public void visitDaToken(RTExpression exp) {
    visitNode(exp);
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    out.append("new DialogueAct(");
    visitDaToken(node.daType);
    out.append(", ");
    visitDaToken(node.proposition);
    for (int i = 0; i < node.exps.size(); i += 2) {
      out.append(", ");
      visitDaToken(node.exps.get(i));
      out.append(", ");
      visitDaToken(node.exps.get(i + 1));
    }
    out.append(")");
  }

  @Override
  public void visitNode(ExpConditional node) {
    out.append("(");
    node.boolexp.visitWithComments(this);
    out.append(" ? ");
    node.thenexp.visitWithComments(this);
    out.append(" : ");
    node.elseexp.visitWithComments(this);
    out.append(')');
  }

  @Override
  public void visitNode(ExpLambda node) {
    mem.enterEnvironment(node);
    out.append("(" + node.parameters.get(0));
    for(int i = 1; i < node.parameters.size(); i++){
      out.append(", " + node.parameters.get(i));
    }
    out.append(") -> ");
    node.body.visitWithComments(this);
    mem.leaveEnvironment(node);
  }

  @Override
  public void visitNode(ExpNew node) {
    if (node.params != null) {
      out.append("new ");
      if (node.type.isArray()) {
        out.append(node.type.getInnerType().toJava()).append('[');
      } else {
        out.append(node.type.toJava()).append('(');
      }
      boolean first = true;
      for(RTExpression param : node.params) {
        if (! first) {
          out.append(", ");
        } else first = false;
        param.visitWithComments(this);
      }
      out.append(node.type.isArray() ? ']' : ')');
    } else {
      RdfClass clz = node.type.getRdfClass();
      String clazz = (clz == null ? node.type.toJava() : clz.toString());
      accessTopLevelInstance();
      out.append("_proxy.getClass(\"").append(clazz)
         .append("\").getNewInstance(");
      accessTopLevelInstance();
      out.append("DEFNS)");
    }
  }

  @Override
  public void visitNode(StatGrammarRule node) {
    if (node.toplevel) {
      mem.enterEnvironment(node);
      // is a top level rule and will be converted to a method
      out.append("public int " + node.label + "(){\n");
    } else {
      // a sub-level rule: ordinary <if>
      out.append("// Rule ").append(node.label).append("\n");
    }
    // generate output for rule logging. This is the "toplevel", because
    // activeInfo is non-Null, the visit call to ifNode.condition will generate
    // the appropriate assignments of baseterms to the bool array.
    activeInfo = mem.enterRule(node.ruleId);
    String varName = activeInfo.resultVarName();
    out.append("boolean[] ").append(varName).append(" = new boolean[")
       .append(Integer.toString(activeInfo.noBaseTerms() + 1))
       .append("];\n");
    StatIf ifNode = node.ifstat;
    // first assign final result, then call log function, then execute if
    out.append(varName).append("[0] = ");

    baseTerm = 1;
    ifNode.condition.visitWithComments(this);
    out.append(";\n");
    accessTopLevelInstance();
    out.append("logRule(").append(Integer.toString(activeInfo.getId()))
       .append(", ").append(varName).append(");\n");
    out.append(node.label + ":\n");
    out.append("if (").append(varName).append("[0])");
    activeInfo = null;  // no more rule logging code from here on

    ifNode.statblockIf.visitWithComments(this);
    out.append("\n");
    if (ifNode.statblockElse != null) {
      out.append("else ");
      ifNode.statblockElse.visitWithComments(this);
    }
    if (node.toplevel) {
      out.append("\nreturn 0; \n}\n");
      mem.leaveEnvironment(node);
    }
    mem.leaveRule();
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    if (node.braces) {
      // when entering a statement block, we need to enter the local environment
      out.append("{\n");
      mem.enterEnvironment(node);
    }
    for (RudiTree stat : node.statblock) {
      stat.visitWithComments(this);
    }
    if (node.braces) {
      out.append("\n}\n");
      mem.leaveEnvironment(node);
    }
  }

  @Override
  public void visitNode(StatFor1 node) {
    out.append("for ( ");
    node.initialization.visitWithComments(this);
    //out.append("; ");
    node.condition.visitWithComments(this);
    out.append(";");
    if (node.increment != null) {
      node.increment.visitWithComments(this);
    }
    out.append(")");
    node.statblock.visitWithComments(this);
  }

  @Override
  public void visitNode(StatFor2 node) {
    out.append("for (Object ");
    node.var.visitWithComments(this);
    out.append("_outer : ");
    node.initialization.visitWithComments(this);
    out.append(") { ").append(node.varType.toJava()).append(" ");
    node.var.visitWithComments(this);
    out.append(" = (").append(node.varType.toJava()).append(")");
    node.var.visitWithComments(this);
    out.append("_outer;\n");
    node.statblock.visitWithComments(this);
    out.append("\n}\n");
  }

  @Override
  public void visitNode(StatFor3 node) {
    out.append("for (Object[] o : ");
    node.initialization.visitWithComments(this);
    out.append(") {");
    int count = 0;
    for (String s : node.variables) {
      out.append("\nObject " + s + " = o[" + count++ + "]");
    }
    node.statblock.visitWithComments(this);
    out.append("\n}");
  }

  @Override
  public void visitNode(StatIf node) {
    out.append("if (");
    node.condition.visitWithComments(this);
    out.append(") ");
    node.statblockIf.visitWithComments(this);
    out.append("\n");
    if (node.statblockElse != null) {
      out.append("else ");
      node.statblockElse.visitWithComments(this);
    }
  }

  @Override
  public void visitNode(ExpListLiteral node) {
    if (node.type.isArray()) {
      out.append("{");
      boolean first = true;
      for (RTExpression e : node.objects) {
        if (! first) out.append(", ");
        else first = false;
        e.visitWithComments(this);
      }
      out.append("}");
    } else {
      out.append(" new ").append(node.type.toConcreteCollection()).append("()");
    }

    /*
    // This functionality is now in other form in visitNode(StatVarDef node)
    if (! node.objects.isEmpty()) {
      out.append("{{");
      for (RTExpression e : node.objects) {
        out.append("add("); visitNode(e); out.append(");\n");
      }
      out.append("}}");
    }*/
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    if (node.block == null) {
      return;
    }
    mem.enterEnvironment(node);
    out.append(node.visibility + " ");
    out.append(node.return_type.toJava() + " ");
    out.append(node.name + "(");
    for (int i = 0; i < node.parameters.size(); i++) {
      if (i != 0) {
        out.append(", ");
      }
      out.append(node.partypes.get(i).toJava() + " " + node.parameters.get(i));
    }
    out.append(")\n");
    node.block.visitWithComments(this);
    mem.leaveEnvironment(node);
  }

  @Override
  public void visitNode(StatPropose node) {
    accessTopLevelInstance();
    out.append("propose(");
    node.arg.visitWithComments(this);
    out.append(",");
    accessTopLevelInstance();
    out.append("new Proposal() {public void run()\n");
    node.block.visitWithComments(this);
    out.append("});\n");
  }

  /** newTimeout("label", time, new Proposal(public void run(){ block }))
   *  if the label ist null, then this is a "behaviour finished" triggered
   *  timeout.
   */
  @Override
  public void visitNode(StatTimeout node) {
    accessTopLevelInstance();
    if (node.label == null) {
      out.append("lastBehaviourTrigger(");
    } else {
      out.append("newTimeout(");
      node.label.visitWithComments(this);
      out.append(",");
    }
    node.time.visitWithComments(this);
    out.append(",");
    accessTopLevelInstance();
    out.append("new Proposal() {public void run()\n");
    node.block.visitWithComments(this);
    out.append("});\n");
  }

  @Override
  public void visitNode(StatReturn node) {
    switch (node.command) {
    case "break":
    case "return":
      out.append(node.command).append(' ');
      if (node.returnExp != null)
        node.returnExp.visitWithComments(this);
      if (node.ruleLabel != null)
        out.append(node.ruleLabel);
      out.append(";\n");
      break;
    case "cancel":
      out.append("return ").append(CANCEL_LOCAL).append(";\n");
      break;
    case "cancel_all":
      out.append("return ").append(CANCEL_GLOBAL).append(";\n");
      break;
    case "continue":
      out.append(node.command).append(";\n");
      break;
    default: logger.error("Wrong return command: {}", node.command); break;
    }
    // }
  }


  @Override
  public void visitNode(StatSetOperation node) {
    node.left.visitWithComments(this);
    if (node.add) {
      out.append(".add(");
    } else {
      out.append(".remove(");
    }
    node.right.visitWithComments(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatVarDef node) {
    if (node.isDefinition) { // Definition of var?
      if (node.varIsFinal)
        out.append("final ");
      out.append(node.type.toJava()).append(" ");
    }
    if (node.toAssign != null) {
      node.toAssign.visitWithComments(this);
    } else {
      out.append(node.variable);
    }
    out.append(";");
    // TODO: IF WE CHANGE OUR MIND ABOUT THE INITIALIZATION OF COLLECTIONS,
    // THIS IS THE PLACE TO PUT IT:
    /**/
    if (node.toAssign != null &&
        ((ExpAssignment)node.toAssign).right instanceof ExpListLiteral) {
      ExpListLiteral listNode =
          (ExpListLiteral)((ExpAssignment)node.toAssign).right;
      if (!listNode.type.isArray() && ! listNode.objects.isEmpty()) {
        for (RTExpression e : listNode.objects) {
          out.append(node.variable).append(".add(");
          visitNode(e);
          out.append(");\n");
        }
      }
    }
  }

  /** Top-level field (variable) definition: only spit out definition! */
  @Override
  public void visitNode(StatFieldDef node) {
    out.append(node.visibility).append(node.varDef.varIsFinal ? " final ": " ")
       .append(node.varDef.type.toJava()).append(" ")
       .append(node.varDef.variable).append(";");
  }

  @Override
  public void visitNode(StatWhile node) {
    if (node.isWhileDo()) {
      out.append("while (");
      node.condition.visitWithComments(this);
      out.append(")");
      node.block.visitWithComments(this);
    } else {
      out.append("do");
      node.block.visitWithComments(this);
      out.append("while (");
      node.condition.visitWithComments(this);
      out.append(");");
    }
  }

  @Override
  public void visitNode(StatSwitch node) {
    out.append("switch (");
    node.condition.visitWithComments(this);
    out.append(")");
    node.block.visitWithComments(this);
  }

  @Override
  public void visitNode(ExpFieldAccess node) {
    int to = node.parts.size();
    // don't print the last field if is in an assignment rather than an
    // access, which means that a set method is generated.
    if (replaceLastWithFuncall) {
      --to;
    }

    // changed the direction of the for loop; should be enough
    // for (int i = to - 1; i > 0; i--) {
    for (int i = to - 1; i >= 0; i--) {
      if (node.parts.get(i) instanceof ExpPropertyAccess) {
        ExpPropertyAccess pa = (ExpPropertyAccess) node.parts.get(i);
        String cast = pa.getType().toJava();
        // cast = capitalize(cast);
        //out.append("((" + cast + ")";
        out.append("((");
        out.append((!pa.functional) ? "Set<Object>" : cast);
        out.append(")");
      }  else if (node.parts.get(i).getType().castRequired()) {
        out.append("((");
        out.append(node.parts.get(i).getType().toJava());
        out.append(")");
      }
    }
    node.parts.get(0).visitWithComments(this);
    if (node.parts.get(0).getType().castRequired()) out.append(")");
    Type currentType = ((RTExpression) node.parts.get(0)).type;
    for (int i = 1; i < to; i++) {
      RTExpression currentPart = node.parts.get(i);
      if (currentPart instanceof ExpPropertyAccess) {
        ExpPropertyAccess pa = (ExpPropertyAccess) currentPart;
        // then we are in the case that is actually an rdf operation
        if (currentType.isDialogueAct()) {
          out.append(".getValue(");
        } else {
          out.append(pa.functional ? ".getSingleValue(" : ".getValue(");
        }
        out.append(pa.getPropertyName());
        out.append("))");
        currentType = pa.type;
      } else {
        out.append(".");
        currentPart.visitWithComments(this);
        if (node.parts.get(i).getType().castRequired()) out.append(")");
        currentType = ((RTExpression) currentPart).type;
      }
    }
  }

  @Override
  public void visitNode(ExpFuncCall node) {
    if (node.realOrigin != null && node.calledUpon == null) {
      out.append(lowerCaseFirst(node.realOrigin) + ".");
    }
    int start = 0;
    if (node.content.charAt(0) == '.') {
      assert(node.params.size() > 0);
      node.params.get(0).visitWithComments(this);
      start = 1;
    }
    if (node.newexp){
      out.append(node.type.toJava() + "(");
    } else {
      out.append(node.content + "(");
    }
    for (int i = start; i < node.params.size(); i++) {
      node.params.get(i).visitWithComments(this);
      if (i != node.params.size() - 1) {
        out.append(", ");
      }
    }
    out.append(")");
  }

  @Override
  public void visitNode(StatExpression rt) {
    rt.expression.visitWithComments(this);
    out.append(";\n");
  }

  @Override
  public void visitNode(ExpSingleValue node) {
    if (node.type.isString()) {
      if (node.content.indexOf('"') != 0)
        node.content = "\"" + node.content + "\"";
      if (escape) {
        // properly escape if needed
        out.append("\\")
           .append(node.content.substring(0, node.content.length() - 1))
           .append("\\\" ");
        return;
      }
    }
    out.append(node.content);
  }

  @Override
  public void visitNode(ExpVariable node) {
    String realOrigin = mem.getVariableOriginClass(node.content);
    if (realOrigin != null) {
      out.append(lowerCaseFirst(realOrigin) + "." );
    }
    out.append(node.content);
  }

  String stringEscape(String in) {
    return in.replaceAll("\\\"", "\\\\\"");
  }

  @Override
  public void visitNode(ExpArrayAccess node) {
    node.array.visitWithComments(this);
    out.append("[");
    node.index.visit(this);
    out.append("]");
  }
}
