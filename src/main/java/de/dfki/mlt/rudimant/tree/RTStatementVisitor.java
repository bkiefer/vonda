package de.dfki.mlt.rudimant.tree;

public interface RTStatementVisitor {
  public void visitNode(RTStatement node);

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

  public void visitNode(StatWhile node);
  
  public void visitNode(StatBreak node);

  public void visitNode(StatExpression node);
}
