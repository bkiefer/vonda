/*
 * To change license header, choose License Headers in Project Properties.
 * To change template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Utils.*;

import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.SilentWriter;
import de.dfki.mlt.rudimant.Type;

/**
 * visitor generates the java code
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VisitorGeneration implements RTStringVisitor, RTStatementVisitor {

  public static Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  SilentWriter out;
  private Mem mem;
  private VisitorConditionLog condV;
  LinkedList<Token> collectedTokens;

  boolean whatToLog;

  // activate bool to get double escaped String literals
  private boolean escape = false;

  // flag to tell the if if is a real rule if (contains the condition that was calculated)
  private String ruleIf = null;

  public VisitorGeneration(Writer o, Mem m, boolean logHow, LinkedList<Token> tokens) {
    out = new SilentWriter(o);
    mem = m;
    whatToLog = logHow;
    condV = new VisitorConditionLog(this);
    collectedTokens = tokens;
  }

  @Override
  public void visitNode(RTStatement node) {
    node.visitWithComments(this);
  }

  @Override
  public String visitNode(RTExpression node) {
    return node.visitWithSComments(this);
  }

  @Override
  public String visitNode(ExpArithmetic node) {
    String ret = "";
    if (node.right == null) {
      // unary operator
      // TODO: ENCAPSULATE THIS INTO TWO FUNCTIONS: isPrefixOperator() and
      // isPostFixOperator()
      if ("-".equals(node.operator) || "!".equals(node.operator)) {
        ret += node.operator;
      }
      ret += '(' + node.left.visitWithSComments(this);
      // something like .isEmpty(), which is a postfix operator
      if (node.operator.endsWith(")")) {
        ret += node.operator;
      }
    } else {
      ret += '(' + node.left.visitWithSComments(this);
      ret += node.operator;
      ret += node.right.visitWithSComments(this);
      if (node.operator.endsWith("(")) {
        ret += ')';
      }
    }
    ret += ')';
    return ret;
  }

  /** If this is true, when generating a ExpFieldAccess we will not
   *  generate an RDF access for the last part, which will be realized
   *  by a method call.  This is highly, EXTREMELY, dangerous and error
   *  prone, but currently we don't have a better idea.
   */
  boolean replaceLastWithFuncall = false;

  @Override
  public String visitNode(ExpAssignment node) {
    String ret = "";
    if (node.declaration) {
      ret += node.type;
    }
    ret += ' ';
    ExpPropertyAccess pa = null;
    if (node.left instanceof ExpFieldAccess) {
      ExpFieldAccess acc = (ExpFieldAccess) node.left;
      RudiTree lastPart = acc.parts.get(acc.parts.size() - 1);
      if (lastPart instanceof ExpPropertyAccess) {
        pa = (ExpPropertyAccess) lastPart;
      }
      // don't print the last field since is will be replaced by a set...(a, b)
      replaceLastWithFuncall = pa != null;
      ret += node.left.visitWithSComments(this);
      if (replaceLastWithFuncall) {
        if (node.right instanceof ExpSingleValue &&
            ((ExpSingleValue)node.right).content.equals("null")) {
          replaceLastWithFuncall = false;
          return ret + ".clearValue(" + pa.getPropertyName() + ")";
        }
        // NO: out.append(functional ? ".setSingleValue(" : ".setValue(");
        ret += ".setValue(";  // always right!
        ret += pa.getPropertyName();
        ret += ", ";
      } else {
        ret += " = ";
      }
      replaceLastWithFuncall = false;
    } else {
      ret += node.left.visitWithSComments(this);
      ret += " = ";
    }
    if (node.type != null
        && !node.type.needsCast(node.right.getType())
        && !(node.right instanceof ExpNew)) {
      // then there is either sth wrong here, what would at least have resulted
      // in warnings in type testing, or it is possible to cast the right part
      ret += "(" + node.type + ") ";
    }
    ret += node.right.visitWithSComments(this);
    if (pa != null) {
      ret += ")"; // close call to setValue()
    }
    return ret;
  }


  private static Type assessTypes(Type left, Type right) {
    if (right.isString() && ! left.isString()) {
      Type h = right; right = left; left = h;
    }
    if (left.isString()
        && (right.isDialogueAct() || right.isRdfType()))
      return right;
    return left;
  }

  private String generateAndMassageType(RTExpression node,
      Type resultType, String operator) {
    if (node.type.isString()) {
      if (resultType.isDialogueAct())
        return "new DialogueAct(" + node.visitWithSComments(this) + ")";
      if (resultType.isRdfType() && ! "==".equals(operator))
        return node.visitWithSComments(this) + ".getClazz()";
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
        orig = orig == null ? "" : lowerCaseFirst(orig) + ".";
        return orig + "getRdfClass(" + node.visitWithSComments(this) + ")";
      } else {
        if (! "==".equals(operator))
          return node.visitWithSComments(this) + ".getClazz()";
      }
    }
    return node.visitWithSComments(this);
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

  private String massageTest(RTExpression node) {
    Type type = node.type;
    if (node instanceof ExpFieldAccess) {
      ExpFieldAccess fa = (ExpFieldAccess)node;
      RTExpression nextToLast = fa.parts.get(fa.parts.size() - 2);
      if (nextToLast.type.isDialogueAct()) {
        boolean oldrep = replaceLastWithFuncall;
        replaceLastWithFuncall = true;
        String ret = fa.visitWithSComments(this) + ".hasSlot(\"" +
            fa.parts.get(fa.parts.size() - 1).fullexp + "\")";
        replaceLastWithFuncall = oldrep;
        return ret;
      }
    }
    if (type.isBool()) {
      return node.visitWithSComments(this);
    }
    if (type.isPODType()) {
      return node.visitWithSComments(this) + " != 0";
    }
    if (type.isCollection() || type.isString() || type.isNumber()) {
      Type[] args = { new Type("Object") };
      String orig = mem.getFunctionOrigin("exists", Arrays.asList(args));
      orig = orig == null ? "" : lowerCaseFirst(orig) + ".";
      return  orig + "exists(" + node.visitWithSComments(this) + ")";
    }
    return node.visitWithSComments(this) + " != null";
  }

  @Override
  public String visitNode(ExpBoolean node) {
    String ret = "";
    if (node.right != null) {
      /* What combinations are we expecting?
       * POD vs POD --> keep operator
       * POD vs Container --> container may be null! Ignore for now?
       * String vs String --> i.compareTo(j) < 0 etc.
       * String vs DialogueAct --> convert String and apply DA vs DA (isSubsumed, etc.)
       * String vs Rdf --> convert to RdfClass and use isSub/SuperclassOf
       * DA vs DA, DA vs String --> isSubsumed and the like.
       * Rdf == Rdf --> equals
       */
      ret += "(";
      if (isComparisonOperator(node.operator)
          && !node.left.type.isPODType() && !node.right.type.isPODType()) {
        String operator = node.operator;
        if (operator.equals("!=")) {
          operator = "==";
          ret += "! (";
        }
        // TODO: THERE'S A SPECIAL CASE IF BOTH ARE RDF AND OPERATOR IS ==
        // THEN, IT SHOULD JUST BE a.equals(b)
        Type resultType = assessTypes(node.left.type, node.right.type);
        String newOp = massageOperator(operator, resultType);
        int lastClosing = newOp.lastIndexOf(')');
        String pref = (lastClosing >= 0)
            ? newOp.substring(0, lastClosing)
            : " " + newOp + " ";
        String suff =
            (lastClosing >= 0) ? newOp.substring(lastClosing) : "";
        ret += this.generateAndMassageType(node.left, resultType, operator);
        ret += pref;
        ret += this.generateAndMassageType(node.right, resultType, operator);
        ret += suff;
        ret += ("!=".equals(node.operator)) ? ")" : "";
      } else {
        ret += node.left.visitWithSComments(this);
        ret += " " + node.operator + " ";
        ret += node.right.visitWithSComments(this);
      }
      ret += ")";
    } else {
      if (null != node.operator) {
        // marker for generation, to probably wrap the right tests around?
        if (node.operator.equals("<>")) {
          ret += massageTest(node.left);
        } else {
          ret += node.operator + '(';
          ret += node.left.visitWithSComments(this) + ')';
        }
      } else {
        ret += node.left.visitWithSComments(this);
      }
    }
    return ret;
  }

  @Override
  public String visitNode(ExpCast node) {
    return "((" + node.type + ")" + visitNode(node.expression) + ")";
  }

  public String visitDaToken(RTExpression exp) {
    String ret;
    if (exp instanceof ExpSingleValue
        && ((ExpSingleValue) exp).type.isString()) {
      String s = ((ExpSingleValue) exp).visitStringV(this);
      if (! s.startsWith("\"")) {
        ret = "\"" + s + "\"";
      } else
        ret = s;
    } else {
      ret = visitNode(exp);
    }
    return ret;
  }

  @Override
  public String visitNode(ExpDialogueAct node) {
    String ret = "new DialogueAct(";
    ret += visitDaToken(node.daType);
    ret += ", ";
    ret += visitDaToken(node.proposition);
    for (int i = 0; i < node.exps.size(); i += 2) {
      ret += ", ";
      ret += visitDaToken(node.exps.get(i));
      ret += ", ";
      ret += visitDaToken(node.exps.get(i + 1));
    }
    ret += ")";
    return ret;
  }

  @Override
  public String visitNode(ExpConditional node) {
    String ret = "(";
    ret += node.boolexp.visitWithSComments(this);
    ret += " ? ";
    ret += node.thenexp.visitWithSComments(this);
    ret += " : ";
    ret += node.elseexp.visitWithSComments(this);
    ret += ')';
    return ret;
  }

  @Override
  public String visitNode(ExpLambda node) {
    mem.enterEnvironment(node);
    String ret = "(" + node.parameters.get(0);
    for(int i = 1; i < node.parameters.size(); i++){
      ret += ", " + node.parameters.get(i);
    }
    ret += ") -> ";
    // is the rare occasion where a "statement", namely a StatAbstractBlock can
    // be inside an expression, because the body of a lambda expresssion can be
    // a block.
    // Therefore, prevent it from printing directly to out
    SilentWriter old = out;
    StringWriter inner = new StringWriter();
    out = new SilentWriter(inner);
    if (node.body instanceof RTExpression)
      ((RTExpression)node.body).visitVoidV(this);
    else { // this must be an AbstractBlock
      assert(node.body instanceof StatAbstractBlock);
      ((StatAbstractBlock)node.body).visit(this);
    }
    ret += inner.toString();
    out = old;
    mem.leaveEnvironment(node);
    return ret;
  }

  @Override
  public String visitNode(ExpNew node) {
    String ret = "";
    if (node.construct != null) {
      ret += "new ";
      ret += node.construct.visitStringV(this);
    } else {
      // TODO: MAKE A FUNCTION FOR THIS, OR KICK IT! WHAT'S THE MEANING?
      if(mem.isNotToplevelClass()) {
        ret += mem.getToplevelInstance() + ".";
      }
      ret += "_proxy.getClass(\""
              + node.type.getRdfClass()
              + "\").getNewInstance(";
      // SEE ABOVE
      if(mem.isNotToplevelClass()) {
        ret += mem.getToplevelInstance() + ".";
      }
      ret += "DEFNS)";
    }
    return ret;
  }

  @Override
  public void visitNode(StatGrammarRule node) {
    mem.enterRule(node.label);
    if (node.toplevel) {
      mem.enterEnvironment(node);
      // is a top level rule and will be converted to a method
      out.append("public boolean " + node.label + "(");
      out.append("){\n");
    } else {
      // is a sub-level rule and will get an if to determine whether it
      // should be executed
      out.append("// Rule " + node.label + "\n");
    }
    ruleIf = printRuleLogger(node.label, node.ifstat.condition);
    out.append(node.label + ":\n");
    node.ifstat.visitWithComments(this);
    if (node.toplevel) {
      out.append("\nreturn false; \n}\n");
      mem.leaveEnvironment(node);
    }
    mem.leaveRule();
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    if (node.braces) {
      // when entering a statement block, we need to create a new local
      // environment
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
    out.append("; ");
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
    String var = node.var.visitWithSComments(this);
    out.append(var).append("_outer : ");
    node.initialization.visitWithComments(this);
    out.append(") { ")
       .append(node.varType.toString())
       .append(" ").append(var);
    out.append(" = (").append(node.varType.toString())
       .append(")").append(var).append("_outer;\n");
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
    if (ruleIf != null) {
      out.append("if (" + ruleIf + ") ");
//      out.append("if (shouldLog(\"" + node.currentRule + "\") ? wholeCondition : ");
      ruleIf = null;
    } else {
      out.append("if (");
      node.condition.visitWithComments(this);
      out.append(") ");
    }
    node.statblockIf.visitWithComments(this);
    out.append("\n");
    if (node.statblockElse != null) {
      out.append("else ");
      node.statblockElse.visitWithComments(this);
    }
  }

  @Override
  public void visitNode(StatListCreation node) {
    out.append(node.listType.toString()).append(' ')
       .append(node.variableName);
    if (node.listType.toString().startsWith("List")) {
      out.append(" = new ArrayList<>();\n");
    } else if (node.listType.toString().startsWith("Set")) {
      out.append(" = new HashSet<>();\n");
    }
    if(node.objects.isEmpty()) {
      return;
    }
    for (RTExpression e : node.objects) {
      out.append(node.variableName + ".add(");
      out.append(visitNode(e));
      out.append(");\n");
    }
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    if (node.block == null) {
      return;
    }
    mem.enterEnvironment(node);
    out.append(node.visibility + " ");
    out.append(node.return_type + " ");
    out.append(node.name + "(");
    for (int i = 0; i < node.parameters.size(); i++) {
      if (i != 0) {
        out.append(", ");
      }
      out.append(node.partypes.get(i) + " " + node.parameters.get(i));
    }
    out.append(")\n");
    node.block.visitWithComments(this);
    mem.leaveEnvironment(node);
  }

  private void topLevel() {
    if(mem.isNotToplevelClass()){
      out.append(mem.getToplevelInstance()).append(".");
    }
  }

  @Override
  public void visitNode(StatPropose node) {
    topLevel();
    out.append("propose(");
    node.arg.visitWithComments(this);
    out.append(",");
    topLevel();
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
    topLevel();
    if (node.label == null) {
      out.append("lastBehaviourTrigger(");
    } else {
      out.append("newTimeout(");
      node.label.visitWithComments(this);
      out.append(",");
    }
    node.time.visitWithComments(this);
    out.append(",");
    topLevel();
    out.append("new Proposal() {public void run()\n");
    node.block.visitWithComments(this);
    out.append("});\n");
  }

  @Override
  public void visitNode(StatReturn node) {
    /* if (node.returnExp == null) {
      // not in any rule, stop all rule processing
      if (mem.getCurrentRule() == null) {
        out.append("return true;\n");
      } else {
        out.append("break " + mem.getCurrentRule() + ";\n");
      }
    } else if (mem.isExistingRule(node.returnExp.fullexp)) {
      out.append("break " + node.returnExp.fullexp + ";\n");
    } else if (node.returnExp.fullexp.equals(mem.getClassName())) {
      // explicitely cancel all rule processing specifying "return <Class>;"
      out.append("return true;\n");
    } else {
    */
      out.append("return ");
      if (node.returnExp != null)
        node.returnExp.visitWithComments(this);
      out.append(";\n");
    // }
  }

  @Override
  public void visitNode(StatBreak node) {
    out.append("break " + node.toLeave + ";");
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
    // no generation here
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
  public String visitNode(ExpFieldAccess node) {
    String ret = "";
    int to = node.parts.size();
    // don't print the last field if is in an assignment rather than an
    // access, which means that a set method is generated.
    if (replaceLastWithFuncall) {
      --to;
    }

    // changed the direction of the for loop; should be enough
    for (int i = to - 1; i > 0; i--) {
      if (node.parts.get(i) instanceof ExpPropertyAccess) {
        ExpPropertyAccess pa = (ExpPropertyAccess) node.parts.get(i);
        String cast = pa.getType().toString();
        // cast = capitalize(cast);
        //ret += "((" + cast + ")";
        ret += "((";
        ret += (!pa.functional) ? "Set<Object>" : cast;
        ret += ")";
      }
    }
    ret += node.parts.get(0).visitWithSComments(this);
    Type currentType = ((RTExpression) node.parts.get(0)).type;
    for (int i = 1; i < to; i++) {
      RTExpression currentPart = node.parts.get(i);
      if (currentPart instanceof ExpPropertyAccess) {
        ExpPropertyAccess pa = (ExpPropertyAccess) currentPart;
        // then we are in the case that is actually an rdf operation
        if (currentType.isDialogueAct()) {
          ret += ".getValue(";
        } else {
          ret += pa.functional ? ".getSingleValue(" : ".getValue(";
        }
        ret += pa.getPropertyName();
        ret += "))";
        currentType = pa.type;
      } else {
        ret += ".";
        ret += currentPart.visitStringV(this);
        currentType = ((RTExpression) currentPart).type;
      }
    }
    return ret;
  }

  @Override
  public String visitNode(ExpFuncCall node) {
    String ret = "";
    if (node.realOrigin != null &&
        (node.calledUpon == null || node.calledUpon.isUnspecified())) {
      ret += lowerCaseFirst(node.realOrigin) + ".";
    }
    if (node.newexp){
      ret += node.type + "(";
    } else {
      ret += node.content + "(";
    }
    for (int i = 0; i < node.params.size(); i++) {
      ret += node.params.get(i).visitWithSComments(this);
      if (i != node.params.size() - 1) {
        ret += ", ";
      }
    }
    ret += ")";
    return ret;
  }

  @Override
  public void visitNode(StatExpression rt) {
    rt.expression.visitWithComments(this);
    out.append(";\n");
  }

  @Override
  public String visitNode(ExpSingleValue node) {
    if (node.type.isString()) {
      if (node.content.indexOf('"') != 0)
        node.content = "\"" + node.content + "\"";
      if (escape)
        // properly escape if needed
        return "\\" + node.content.substring(0, node.content.length() - 1) + "\\\" ";
    }
    return node.content;
  }

  @Override
  public String visitNode(ExpVariable node) {
    String realOrigin = mem.getVariableOriginClass(node.content);
    if (realOrigin != null) {
      return lowerCaseFirst(realOrigin) + "." + node.content;
    } else {
      return node.content;
    }
  }

  boolean collectingCondition = false;

  String stringEscape(String in) {
    return in.replaceAll("\\\"", "\\\\\"");
  }
  /**
   * creates and prints the logging method of the given rule
   *
   * @param rule
   */
  private String printRuleLogger(String rule, RTExpression bool_exp) {

    // TODO BK: bool_exp can be a simple expression, in which case it
    // has to be turned into a comparison with zero, null or a call to
    // the has(...) method
    if (bool_exp instanceof ExpSingleValue && bool_exp.getType().isBool()) {
      return ((ExpSingleValue) bool_exp).content;
    }
    collectingCondition = true;

    // remembers how the expressions looked (for logging)
    LinkedHashMap<String, String> realLook = new LinkedHashMap<>();
    LinkedHashMap<String, String> rudiLook = new LinkedHashMap<>();

    condV.newInit(rule, realLook, rudiLook);
    String result = condV.visitNode(bool_exp);
    for (String s : realLook.keySet()) {
      out.append("boolean " + s + " = false;\n");
    }
    out.append(result);

    out.append("if (");
    topLevel();
    out.append("shouldLog(\"" + rule + "\")){\n");
    // do all that logging
    out.append("Map<String, Boolean> " + rule + " = new LinkedHashMap<>();\n");

    LinkedHashMap<String, String> logging;
    out.append(rule + ".put(\"" + stringEscape(bool_exp.fullexp) + "\", "
        + condV.getLastBool() + ");\n");
    if(whatToLog){
      logging = rudiLook;
    } else {
      logging = realLook;
    }
    for (String var : logging.keySet()) {
      out.append(rule + ".put(\"" + stringEscape(logging.get(var)) + "\", " + var + ");\n");
    }
    topLevel();
    out.append("logRule(" + rule + ", \"" + rule + "\", \""
            + mem.getClassName() + "\");\n");

    out.append("}\n");
    collectingCondition = false;
    return condV.getLastBool();
  }

}
