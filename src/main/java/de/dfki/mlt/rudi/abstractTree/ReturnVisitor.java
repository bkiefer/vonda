/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.ReturnManagement;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class ReturnVisitor implements RudiVisitor {

  private ReturnManagement _rm;

  public ReturnVisitor(ReturnManagement rm_ini ) throws Exception {
    _rm = rm_ini;
  }

  @Override
  public void visitNode(ExpAbstractWrapper node ) throws Exception {
    node.exp.visit(this);
  }

  @Override
  public void visitNode(RudiTree node ) throws Exception {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node ) throws Exception {

  }

  @Override
  public void visitNode(ExpAssignment node ) throws Exception {

  }

  @Override
  public void visitNode(ExpBoolean node ) throws Exception {

  }

  @Override
  public void visitNode(ExpDialogueAct node ) throws Exception {

  }

  @Override
  public void visitNode(ExpIf node ) throws Exception {

  }

  @Override
  public void visitNode(ExpLambda node ) throws Exception {

  }

  @Override
  public void visitNode(GrammarFile node ) throws Exception {
    for (RudiTree t : node.rules ) {
      t.visit(this);
    }
  }

  @Override
  public void visitNode(GrammarRule node ) throws Exception {
    _rm.enterRule(node.label);
    node.ifstat.visit(this);
    _rm.leaveRule();
}

  @Override
  public void visitNode(StatAbstractBlock node ) throws Exception {
    for (RudiTree stat : node.statblock ) {
      stat.visit(this);
    }
  }

  @Override
  public void visitNode(StatDoWhile node ) throws Exception {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor1 node ) throws Exception {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor2 node ) throws Exception {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor3 node ) throws Exception {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFunDef node ) throws Exception {

  }

  @Override
  public void visitNode(StatIf node ) throws Exception {
    node.statblockIf.visit(this);
    if (node.statblockElse != null ) {
      node.statblockElse.visit(this);
    }
  }

  @Override
  public void visitNode(StatImport node ) throws Exception {
    // for the moment, let's just say we cannot return from an import to a superclass
    // of the importing class
  }

  @Override
  public void visitNode(StatMethodDeclaration node ) throws Exception {
    node.block.visit(this);
  }

  @Override
  public void visitNode(StatPropose node ) throws Exception {

  }

  @Override
  public void visitNode(StatReturn node ) throws Exception {
    if (_rm.isExistingRule(node.lit) ) {
      _rm.foundReturnTo(node.lit);
      node.toRet = null;
    }
  }

  @Override
  public void visitNode(StatSetOperation node ) throws Exception {

  }

  @Override
  public void visitNode(StatTimeout node ) throws Exception {

  }

  @Override
  public void visitNode(StatVarDef node ) throws Exception {

  }

  @Override
  public void visitNode(StatWhile node ) throws Exception {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(UCharacter node ) throws Exception {

  }

  @Override
  public void visitNode(UComment node ) throws Exception {

  }

  @Override
  public void visitNode(UCommentBlock node ) throws Exception {

  }

  @Override
  public void visitNode(UFieldAccess node ) throws Exception {

  }

  @Override
  public void visitNode(UFuncCall node ) throws Exception {

  }

  @Override
  public void visitNode(UNull node ) throws Exception {

  }

  @Override
  public void visitNode(UNumber node ) throws Exception {

  }

  @Override
  public void visitNode(UString node ) throws Exception {

  }

  @Override
  public void visitNode(UVariable node ) throws Exception {

  }

  @Override
  public void visitNode(UWildcard node ) throws Exception {

  }

  @Override
  public void visitNode(UnaryBoolean node ) throws Exception {

  }

}
