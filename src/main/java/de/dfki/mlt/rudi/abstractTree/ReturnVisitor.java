/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class ReturnVisitor implements RudiVisitor {

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    visitNode(node.exp);
  }

  @Override
  public void visitNode(RudiTree node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpArithmetic node) {

  }

  @Override
  public void visitNode(ExpAssignment node) {

  }

  @Override
  public void visitNode(ExpBoolean node) {

  }

  @Override
  public void visitNode(ExpDialogueAct node) {

  }

  @Override
  public void visitNode(ExpIf node) {

  }

  @Override
  public void visitNode(ExpLambda node) {

  }

  @Override
  public void visitNode(GrammarFile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(GrammarRule node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    for (RudiTree stat : node.statblock) {
      visitNode(stat);
    }
  }

  @Override
  public void visitNode(StatDoWhile node) {
    visitNode(node.statblock);
  }

  @Override
  public void visitNode(StatFor1 node) {
    visitNode(node.statblock);
  }

  @Override
  public void visitNode(StatFor2 node) {
    visitNode(node.statblock);
  }

  @Override
  public void visitNode(StatFor3 node) {
    visitNode(node.statblock);
  }

  @Override
  public void visitNode(StatFunDef node) {

  }

  @Override
  public void visitNode(StatIf node) {
    visitNode(node.statblockIf);
    if (node.statblockElse != null) {
      visitNode(node.statblockElse);
    }
  }

  @Override
  public void visitNode(StatImport node) {
    // for the moment, let's just say we cannot return from an import to a superclass
    // of the importing class
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    visitNode(node.block);
  }

  @Override
  public void visitNode(StatPropose node) {

  }

  @Override
  public void visitNode(StatReturn node) {
    if (ReturnManagement.isExistingRule(node.lit)) {
      ReturnManagement.foundReturnTo(node.lit);
      node.toRet = null;
    }
  }

  @Override
  public void visitNode(StatSetOperation node) {

  }

  @Override
  public void visitNode(StatTimeout node) {

  }

  @Override
  public void visitNode(StatVarDef node) {

  }

  @Override
  public void visitNode(StatWhile node) {
    visitNode(node.statblock);
  }

  @Override
  public void visitNode(UCharacter node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UComment node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UCommentBlock node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UFieldAccess node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UFuncCall node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UNull node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UNumber node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UString node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UVariable node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UWildcard node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
