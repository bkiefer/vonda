package de.dfki.mlt.rudimant.compiler.tree;

public interface RudiVisitor {

  public void visitNode(RudiTree node);

  // Statements
  public void visitNode(StatGrammarRule node);

  public void visitNode(StatAbstractBlock node);

  public void visitNode(StatFor1 node);

  public void visitNode(StatFor2 node);

  public void visitNode(StatFor3 node);

  public void visitNode(StatIf node);

  public void visitNode(StatListCreation node);

  public void visitNode(StatMethodDeclaration node);

  public void visitNode(StatPropose node);

  public void visitNode(StatTimeout node);

  public void visitNode(StatReturn node);

  public void visitNode(StatSetOperation node);

  public void visitNode(StatSwitch node);

  public void visitNode(StatVarDef node);

  public void visitNode(StatFieldDef node);

  public void visitNode(StatWhile node);

  public void visitNode(StatExpression node);

  // Expressions
  public void visitNode(ExpArithmetic node);

  public void visitNode(ExpAssignment node);

  public void visitNode(ExpBoolean node);

  public void visitNode(ExpCast node);

  public void visitNode(ExpDialogueAct node);

  public void visitNode(ExpConditional node);

  public void visitNode(ExpLambda node);

  public void visitNode(ExpNew node);

  public void visitNode(ExpFieldAccess node);

  public void visitNode(ExpFuncCall node);

  public void visitNode(ExpSingleValue node);

  public void visitNode(ExpVariable node);
}
