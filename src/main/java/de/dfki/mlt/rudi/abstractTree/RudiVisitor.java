/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public interface RudiVisitor {

  void visitNode(RudiTree node) throws Exception;

  void visitNode(ExpAbstractWrapper node) throws Exception;

  void visitNode(ExpArithmetic node) throws Exception;

  void visitNode(ExpAssignment node) throws Exception;

  void visitNode(ExpBoolean node) throws Exception;

  void visitNode(ExpDialogueAct node) throws Exception;

  void visitNode(ExpIf node) throws Exception;

  void visitNode(ExpLambda node) throws Exception;

  void visitNode(GrammarFile node) throws Exception;

  void visitNode(GrammarRule node) throws Exception;

  void visitNode(StatAbstractBlock node) throws Exception;

  void visitNode(StatDoWhile node) throws Exception;

  void visitNode(StatFor1 node) throws Exception;

  void visitNode(StatFor2 node) throws Exception;

  void visitNode(StatFor3 node) throws Exception;

  void visitNode(StatFunDef node) throws Exception;

  void visitNode(StatIf node) throws Exception;

  void visitNode(StatImport node) throws Exception;

  void visitNode(StatMethodDeclaration node) throws Exception;

  void visitNode(StatPropose node) throws Exception;

  void visitNode(StatReturn node) throws Exception;

  void visitNode(StatSetOperation node) throws Exception;

  void visitNode(StatTimeout node) throws Exception;

  void visitNode(StatVarDef node) throws Exception;

  void visitNode(StatWhile node) throws Exception;

  void visitNode(UCharacter node) throws Exception;

  void visitNode(UComment node) throws Exception;

  void visitNode(UCommentBlock node) throws Exception;

  void visitNode(UFieldAccess node) throws Exception;

  void visitNode(UFuncCall node) throws Exception;

  void visitNode(UNull node) throws Exception;

  void visitNode(UNumber node) throws Exception;

  void visitNode(UString node) throws Exception;

  void visitNode(UVariable node) throws Exception;

  void visitNode(UWildcard node) throws Exception;

  void visitNode(UnaryBoolean node) throws Exception;

}
