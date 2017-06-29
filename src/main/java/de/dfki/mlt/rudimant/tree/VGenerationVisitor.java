/*
 * To change license header, choose License Headers in Project Properties.
 * To change template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;
import static de.dfki.mlt.rudimant.Utils.*;

import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.Type;

/**
 * visitor generates the java code
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VGenerationVisitor implements RTStringVisitor, RTStatementVisitor {

  public static Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  RudimantCompiler out;
  private RudimantCompiler rudi;
  private Mem mem;
  private VConditionLogVisitor condV;
  LinkedList<Token> collectedTokens;

  // activate bool to get double escaped String literals
  private boolean escape = false;

  // flag to tell the if if is a real rule if (contains the condition that was calculated)
  private String ruleIf = null;

  public VGenerationVisitor(RudimantCompiler r, LinkedList<Token> tokens) {
    rudi = r;
    out = r;
    mem = rudi.getMem();
    condV = new VConditionLogVisitor(this);
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

  boolean replaceLastWithFuncall = false;

  @Override
  public String visitNode(ExpAssignment node) {
    String ret = "";
    if (node.declaration) {
      ret += (node.type.convertRdfType().get_name());
    }
    ret += (' ');
    ExpUPropertyAccess pa = null;
    if (node.left instanceof ExpUFieldAccess) {
      ExpUFieldAccess acc = (ExpUFieldAccess) node.left;
      RudiTree lastPart = acc.parts.get(acc.parts.size() - 1);
      if (lastPart instanceof ExpUPropertyAccess) {
        pa = (ExpUPropertyAccess) lastPart;
      }
      // don't print the last field since is will be replaced by a set...(a, b)
      replaceLastWithFuncall = pa != null;
      ret += node.left.visitWithSComments(this);
      if (replaceLastWithFuncall) {
        if (node.right instanceof ExpUSingleValue &&
            ((ExpUSingleValue)node.right).content.equals("null")) {
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
            && !node.type.get_name().equals(node.right.getType().get_name())
            && !(node.right instanceof ExpNew)) {
      // then there is either sth wrong here, what would at least have resulted
      // in warnings in type testing, or it is possible to cast the right part
      ret += "(" + node.type.convertRdfType().get_name() + ") ";
    }
    ret += node.right.visitWithSComments(this);
    if (pa != null) {
      ret += ")"; // close call to setValue()
    }
    return ret;
  }

  @Override
  public String visitNode(ExpBoolean node) {
    String ret = "";
    if (node.operator != null && node.operator.contains("(")) {
      // other operator, is it sth. like "exists("
      if (! mem.getToplevelInstance().equalsIgnoreCase(mem.getClassName())) {
        ret += mem.getToplevelInstance() + ".";
      }
      ret += node.operator;
      ret += node.left.visitWithSComments(this);
      if (node.right != null) {
        ret += ", ";
        ret += node.right.visitWithSComments(this);
      }
      ret += ")";
    } else {
      if (node.right != null) {
        ret += "(";
        ret += node.left.visitWithSComments(this);
        ret += " " + node.operator + " ";
        ret += node.right.visitWithSComments(this);
        ret += ")";
      } else {
        if (null != node.operator) {
          ret += node.operator;
        }
        ret += node.left.visitWithSComments(this);
      }
    }
    return ret;
  }

  @Override
  public String visitNode(ExpCast node) {
    return "((" + node.type.convertRdfType().get_name() + ")"
        + visitNode(node.expression) + ")";
  }

  public String visitDaToken(RTExpression exp) {
    String ret = "";
    if (exp instanceof ExpUSingleValue
        && "String".equals(((ExpUSingleValue) exp).type.get_name())) {
      String s = ((ExpUSingleValue) exp).visitStringV(this);
      if (! s.startsWith("\"")) {
        ret += "\"" + s + "\"";
      } else
        ret += s;
    } else {
      ret +=  visitNode(exp);
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
    String ret = "(" + node.parameters.get(0);
    for(int i = 1; i < node.parameters.size(); i++){
      ret += ", " + node.parameters.get(i);
    }
    ret += ") -> ";
    // is the rare occasion where a "statement", namely a StatAbstractBlock can
    // be inside an expression, because the body of a lambda expresssion can be
    // a block.
    // Therefore, prevent it from printing directly to out
    Writer old = out.out;
    out.out = new StringWriter();
    if (node.body instanceof RTExpression)
      ((RTExpression)node.body).visitVoidV(this);
    else { // this must be an AbstractBlock
      assert(node.body instanceof StatAbstractBlock);
      ((StatAbstractBlock)node.body).visit(this);
    }
    ret += out.out.toString();
    out.out = old;
    return ret;
  }

  @Override
  public String visitNode(ExpNew node) {
    String ret = "";
    if (node.construct != null) {
      ret += "new ";
      ret += node.construct.visitStringV(this);
    } else {
      if(!mem.getClassName().toLowerCase().equals(
          mem.getToplevelInstance().toLowerCase())){
        ret += mem.getToplevelInstance() + ".";
      }
      ret += "_proxy.getClass(\""
              + mem.getProxy().getClass(node.type.get_name())
              + "\").getNewInstance(";
      if(!mem.getClassName().toLowerCase().equals(
              mem.getToplevelInstance().toLowerCase())){
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
      // is a toplevel rule and will be converted to a method
      out.append("public boolean " + node.label + "(");
      out.append("){\n");
      ruleIf = printRuleLogger(node.label, node.ifstat.condition);
      out.append(node.label + ":\n");
      node.ifstat.visitWithComments(this);
      out.append("return false; \n}\n");
    } else {
      // is a sublevel rule and will get an if to determine whether it
      // should be executed
      out.append("//Rule " + node.label + "\n");
      ruleIf = printRuleLogger(node.label, node.ifstat.condition);
      out.append(node.label + ":\n");
      node.ifstat.visitWithComments(this);
    }
    mem.leaveRule();
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    if (node.braces) {
      // when entering a statement block, we need to create a new local
      // environment
      out.append("{");
    }
    for (RudiTree stat : node.statblock) {
      stat.visitWithComments(this);
    }
    if (node.braces) {
      out.append("}");
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
       .append(node.varType.convertRdfType().get_name())
       .append(" ").append(var);
    out.append(" = (").append(node.varType.convertRdfType().get_name())
       .append(")").append(var).append("_outer;\n");
    node.statblock.visitWithComments(this);
    out.append("}");
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
    out.append("}");
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
    out.append(node.listType.convertRdfType().get_name()).append(' ')
       .append(node.variableName);
    if (node.listType.get_name().startsWith("List")) {
      out.append(" = new ArrayList<>();");
    } else if (node.listType.get_name().startsWith("Set")) {
      out.append(" = new HashSet<>();");
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
    out.append(node.visibility + " ");
    out.append(node.return_type.convertRdfType().get_name() + " ");
    out.append(node.name + "(");
    for (int i = 0; i < node.parameters.size(); i++) {
      if (i != 0) {
        out.append(", ");
      }
      out.append(node.partypes.get(i).convertRdfType().get_name()
              + " " + node.parameters.get(i));
    }
    out.append(")\n");
    node.block.visitWithComments(this);
  }

  @Override
  public void visitNode(StatPropose node) {
    if(!mem.getClassName().toLowerCase().equals(
            mem.getToplevelInstance().toLowerCase())){
      out.append(mem.getToplevelInstance()).append(".");
    }
    out.append("propose(");
    node.arg.visitWithComments(this);
    out.append(",");
    if(!mem.getClassName().toLowerCase().equals(
            mem.getToplevelInstance().toLowerCase())){
      out.append(mem.getToplevelInstance()).append(".");
    }
    out.append("new Proposal() {public void run()\n");
    node.block.visitWithComments(this);
    out.append("});\n");
  }

  @Override
  public void visitNode(StatReturn node) {
    if (node.returnExp == null) {
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
      out.append("return ");
      node.returnExp.visitWithComments(this);
      out.append(";\n");
    }
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
  public String visitNode(ExpUFieldAccess node) {
    String ret = "";
    int to = node.parts.size();
    // don't print the last field if is in an assignment rather than an
    // access, which means that a set method is generated.
    if (replaceLastWithFuncall) {
      --to;
    }

    // changed the direction of the for loop; should be enough
    for (int i = to - 1; i > 0; i--) {
      if (node.parts.get(i) instanceof ExpUPropertyAccess) {
        ExpUPropertyAccess pa = (ExpUPropertyAccess) node.parts.get(i);
        String cast = pa.getType().convertRdfType().get_name();
        // TODO: what about long, double, ... ??
        if ("int".equals(cast))
          cast = "Integer";
        else
          cast = capitalize(cast);
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
      if (currentPart instanceof ExpUPropertyAccess) {
        ExpUPropertyAccess pa = (ExpUPropertyAccess) currentPart;
        // then we are in the case that is actually an rdf operation
        if (DIALOGUE_ACT_TYPE.equals(currentType.get_name())) {
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
  public String visitNode(ExpUFuncCall node) {
    String ret = "";
    if (node.realOrigin != null &&
    		(node.calledUpon == null ||
    		node.calledUpon.get_name().isEmpty())) {
      ret += lowerCaseFirst(node.realOrigin) + ".";
    }
    if(node.newexp){
      ret += node.type.convertRdfType().get_name() + "(";
    } else {
      ret += node.content + "(";
    }
    for (int i = 0; i < node.exps.size(); i++) {
      ret += node.exps.get(i).visitWithSComments(this);
      if (i != node.exps.size() - 1) {
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
  public String visitNode(ExpUSingleValue node) {
    if ("String".equals(node.type) && escape) {
      // properly escape if needed
      return "\\" + node.content.substring(0, node.content.length() - 1) + "\\\" ";
    }
    return node.content;
  }

  @Override
  public String visitNode(ExpUVariable node) {
    String realOrigin = mem.getVariableOriginClass(node.fullexp);
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
    if (bool_exp instanceof ExpUSingleValue && "boolean".equals(bool_exp.getType().get_name())) {
      return ((ExpUSingleValue) bool_exp).content;
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
    if(!mem.getClassName().toLowerCase().equals(
            mem.getToplevelInstance().toLowerCase())){
      out.append(mem.getToplevelInstance()).append(".");
    }
    out.append("shouldLog(\"" + rule + "\")){\n");
    // do all that logging
    out.append("Map<String, Boolean> " + rule + " = new LinkedHashMap<>();\n");

    LinkedHashMap<String, String> logging;
      out.append(rule + ".put(\"" + stringEscape(bool_exp.fullexp) + "\", "
          + condV.getLastBool() + ");\n");
    if(out.logRudi()){
      logging = rudiLook;
    } else {
      logging = realLook;
    }
    for (String var : logging.keySet()) {
      out.append(rule + ".put(\"" + stringEscape(logging.get(var)) + "\", " + var + ");\n");
    }
    if(!mem.getClassName().toLowerCase().equals(
            mem.getToplevelInstance().toLowerCase())){
      out.append(mem.getToplevelInstance()).append(".");
    }
    out.append("logRule(" + rule + ", \"" + rule + "\", \""
            + mem.getClassName() + "\");\n");

    out.append("}\n");
    collectingCondition = false;
    //return (String) expnames[expnames.length - 1];
    return condV.getLastBool();
  }

}
