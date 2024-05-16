package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Iterator;

import de.dfki.mlt.rudimant.compiler.Type;

public class TreeViewVisitor implements RudiVisitor {
  public String result;

  @Override
  public void visit(RudiTree node) {
    result = "";
  }

  @Override
  public void visit(StatGrammarRule node) {
    result = node.label + ":";
  }

  @Override
  public void visit(StatAbstractBlock node) {
    result = node.braces ? "{ _ }" : "block";
  }

  @Override
  public void visit(StatFor1 node) {
    result = "for";
  }

  @Override
  public void visit(StatFor2 node) {
    result = "for";
  }

  @Override
  public void visit(StatFor3 node) {
    result = "for";
  }

  @Override
  public void visit(StatIf node) {
    result = "if";
  }

  @Override
  public void visit(StatMethodDeclaration md) {
    Iterator<Type> partypes = md.function_type.getParameterTypes();
    Type calledUpon = md.function_type.getClassOf();

    result = "def " + md.function_type.getReturnedType().getRep() + " " +
        (calledUpon == null ? "" : calledUpon.getRep() + ". ")
        + md.name + '(';
    for (int j = 0; j < md.parameters.size(); ++j) {
      if (j != 0) result += ", ";
      result += partypes.next().getRep() + " " + md.parameters.get(j);
    }
    result += ")";
  }

  @Override
  public void visit(StatPropose node) {
    result = "propose";
  }

  @Override
  public void visit(StatTimeout node) {
    result = "timeout";
  }

  @Override
  public void visit(StatReturn node) {
    result = "return";
  }

  @Override
  public void visit(StatSetOperation node) {
    result = node.add ? "+=" : "-=";
  }

  @Override
  public void visit(StatSwitch node) {
    result = "switch";
  }

  @Override
  public void visit(StatVarDef node) {
    result = (node.isDefinition ? "def " : "")
        + (node.varIsFinal ? "final " : "") + node.variable
        + " [" + node.type.getRep() + "]";
  }

  @Override
  public void visit(StatFieldDef node) {
    result = node.visibility != null ? node.visibility : "";
  }

  @Override
  public void visit(StatWhile node) {
    result = "while";
  }

  @Override
  public void visit(StatExpression node) {
    result = "SE";
  }

  @Override
  public void visit(ExpArithmetic node) {
    result = node.operator != null ? node.operator : "";
  }

  @Override
  public void visit(ExpArrayAccess node) {
    result = "[ ]";
  }

  @Override
  public void visit(ExpAssignment node) {
    result = ":=";
  }

  @Override
  public void visit(ExpBoolean node) {
    result = node.operator != null ? node.operator : "";
  }

  @Override
  public void visit(ExpCast node) {
    result = "ISA(" + node.type + ")";
  }

  @Override
  public void visit(ExpDialogueAct node) {
    result = "DialAct";
  }

  @Override
  public void visit(ExpConditional node) {
    result = "_?_:_";
  }

  @Override
  public void visit(ExpLambda node) {
    result = "lambda";
  }

  @Override
  public void visit(ExpListLiteral node) {
    result = "{...}";
  }

  @Override
  public void visit(ExpNew node) {
    result = "new";
  }

  @Override
  public void visit(ExpFieldAccess node) {
    result = "FieldAcc";
  }

  @Override
  public void visit(ExpFuncCall node) {
    result = (node.calledUpon != null ? node.calledUpon.get_name() : "")
        + node.content + "()";
  }

  @Override
  public void visit(ExpLiteral node) {
    result = node.content;
  }

  @Override
  public void visit(ExpIdentifier node) {
    result = node.content;
  }

  @Override
  public void visit(ExpPropertyAccess epa) {
    result = epa.content + ":" + epa.acc.name();
  }

}
