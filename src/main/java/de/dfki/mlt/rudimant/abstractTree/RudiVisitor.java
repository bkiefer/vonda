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
public interface RudiVisitor {

  public void visitNode(RudiTree node);

  public void visitNode(RTExpression node);

  public void visitNode(ExpArithmetic node);

  public void visitNode(ExpAssignment node);

  public void visitNode(ExpBoolean node);

  public void visitNode(ExpCast node);

  public void visitNode(ExpDialogueAct node);

  public void visitNode(ExpConditional node);

  public void visitNode(ExpLambda node);

  public void visitNode(ExpNew node);

  public void visitNode(GrammarFile node);

  public void visitNode(GrammarRule node);

  public void visitNode(StatAbstractBlock node);

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

  public void visitNode(UFieldAccess node);

  public void visitNode(UFuncCall node);

  public void visitNode(USingleValue node);

  public void visitNode(UVariable node);
}
