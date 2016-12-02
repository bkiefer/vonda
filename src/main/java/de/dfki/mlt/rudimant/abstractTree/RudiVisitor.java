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

  void visitNode(RTExpression node);

  void visitNode(ExpArithmetic node);

  void visitNode(ExpAssignment node);

  void visitNode(ExpBoolean node);

  void visitNode(ExpDialogueAct node);

  void visitNode(ExpIf node);

  void visitNode(ExpLambda node);
  
  void visitNode(ExpNew node);

  void visitNode(GrammarFile node);

  void visitNode(GrammarRule node);

  void visitNode(StatAbstractBlock node);

  void visitNode(StatDoWhile node);

  void visitNode(StatFor1 node);

  void visitNode(StatFor2 node);

  void visitNode(StatFor3 node);

  void visitNode(StatIf node);

  void visitNode(StatImport node);

  void visitNode(StatListCreation node);

  void visitNode(StatMethodDeclaration node);

  void visitNode(StatPropose node);

  void visitNode(StatReturn node);

  void visitNode(StatSetOperation node);

  void visitNode(StatSwitch node);

  void visitNode(StatVarDef node);

  void visitNode(StatWhile node);

  void visitNode(UFieldAccess node);

  void visitNode(UFuncCall node);

  void visitNode(USingleValue node);

  void visitNode(UVariable node);

  void visitNode(UWildcard node);
}
