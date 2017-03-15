/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public interface RTStringVisitor {

  public void visitNode(RudiTree node);

  public String visitNode(RTExpression node);

  public String visitNode(ExpArithmetic node);

  public String visitNode(ExpAssignment node);

  public String visitNode(ExpBoolean node);

  public String visitNode(ExpCast node);

  public String visitNode(ExpDialogueAct node);

  public String visitNode(ExpConditional node);

  public String visitNode(ExpLambda node);

  public String visitNode(ExpNew node);

  public void visitNode(GrammarFile node);

  public void visitNode(GrammarRule node);

  public void visitNode(StatAbstractBlock node);

  public void visitNode(StatDoWhile node);

  public void visitNode(StatFor1 node);

  public void visitNode(StatFor2 node);

  public void visitNode(StatFor3 node);

  public void visitNode(StatIf node);

  public void visitNode(UImport node);

  public void visitNode(StatListCreation node);

  public void visitNode(StatMethodDeclaration node);

  public void visitNode(StatPropose node);

  public void visitNode(StatReturn node);

  public void visitNode(StatSetOperation node);

  public void visitNode(StatSwitch node);

  public void visitNode(StatVarDef node);

  public void visitNode(StatWhile node);

  public String visitNode(UFieldAccess node);

  public String visitNode(UFuncCall node);

  public String visitNode(USingleValue node);

  public String visitNode(UVariable node);

  public String visitNode(UWildcard node);
}
