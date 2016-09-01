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
public interface RudiVisitor <T> {

  T visitNode(RudiTree node);

  T visitNode(ExpAbstractWrapper node);

  T visitNode(ExpArithmetic node);

  T visitNode(ExpAssignment node);

  T visitNode(ExpBoolean node);

  T visitNode(ExpDialogueAct node);

  T visitNode(ExpIf node);

  T visitNode(ExpLambda node);

  T visitNode(GrammarFile node);

  T visitNode(GrammarRule node);

  T visitNode(StatAbstractBlock node);

  T visitNode(StatDoWhile node);

  T visitNode(StatFor1 node);

  T visitNode(StatFor2 node);

  T visitNode(StatFor3 node);

  T visitNode(StatFunDef node);

  T visitNode(StatIf node);

  T visitNode(StatImport node);

  T visitNode(StatMethodDeclaration node);

  T visitNode(StatPropose node);

  T visitNode(StatReturn node);

  T visitNode(StatSetOperation node);

  T visitNode(StatTimeout node);

  T visitNode(StatVarDef node);

  T visitNode(StatWhile node);

  T visitNode(UCharacter node);

  T visitNode(UComment node);

  T visitNode(UCommentBlock node);

  T visitNode(UFieldAccess node);

  T visitNode(UFuncCall node);

  T visitNode(UNull node);

  T visitNode(UNumber node);

  T visitNode(UString node);

  T visitNode(UVariable node);

  T visitNode(UWildcard node);

  T visitNode(UnaryBoolean node);

}