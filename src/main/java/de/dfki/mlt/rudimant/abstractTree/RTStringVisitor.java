/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 *
 * @author anna
 * a visitor interface that allows to create a RudiTree visitor where the
 * single visits can return Strings
 */
public interface RTStringVisitor {
  
  String visitNode(RudiTree node);
   
  String visitNode(RTExpression node);

  String visitNode(ExpArithmetic node);

  String visitNode(ExpAssignment node);

  String visitNode(ExpBoolean node);

  String visitNode(ExpDialogueAct node);

  String visitNode(ExpConditional node);

  String visitNode(ExpLambda node);
  
  String visitNode(ExpNew node);

  String visitNode(GrammarFile node);

  String visitNode(GrammarRule node);

  String visitNode(StatAbstractBlock node);

  String visitNode(StatDoWhile node);

  String visitNode(StatFor1 node);

  String visitNode(StatFor2 node);

  String visitNode(StatFor3 node);

  String visitNode(StatIf node);

  String visitNode(StatImport node);

  String visitNode(StatListCreation node);

  String visitNode(StatMethodDeclaration node);

  String visitNode(StatPropose node);

  String visitNode(StatReturn node);

  String visitNode(StatSetOperation node);

  String visitNode(StatSwitch node);

  String visitNode(StatVarDef node);

  String visitNode(StatWhile node);

  String visitNode(UFieldAccess node);

  String visitNode(UFuncCall node);

  String visitNode(USingleValue node);

  String visitNode(UVariable node);

  String visitNode(UWildcard node);
}
