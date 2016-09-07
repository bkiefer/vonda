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
public class VReturnVisitor implements RudiVisitor {

  private ReturnManagement _rm;

  public VReturnVisitor(ReturnManagement rm_ini ) {
    _rm = rm_ini;
  }

  @Override
  public void visitNode(ExpAbstractWrapper node ) {
    node.exp.visit(this);
  }

  @Override
  public void visitNode(RudiTree node ) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node ) {

  }

  @Override
  public void visitNode(ExpAssignment node ) {

  }

  @Override
  public void visitNode(ExpBoolean node ) {

  }

  @Override
  public void visitNode(ExpDialogueAct node ) {

  }

  @Override
  public void visitNode(ExpFuncOnObject node) {

  }

  @Override
  public void visitNode(ExpIf node ) {

  }

  @Override
  public void visitNode(ExpLambda node ) {

  }

  @Override
  public void visitNode(GrammarFile node ) {
    for (RudiTree t : node.rules ) {
      t.visit(this);
    }
  }

  @Override
  public void visitNode(GrammarRule node ) {
    _rm.enterRule(node.label);
    node.ifstat.visit(this);
    _rm.leaveRule();
}

  @Override
  public void visitNode(StatAbstractBlock node ) {
    for (RudiTree stat : node.statblock ) {
      stat.visit(this);
    }
  }

  @Override
  public void visitNode(StatDoWhile node ) {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor1 node ) {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor2 node ) {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor3 node ) {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFunDef node ) {

  }

  @Override
  public void visitNode(StatIf node ) {
    node.statblockIf.visit(this);
    if (node.statblockElse != null ) {
      node.statblockElse.visit(this);
    }
  }

  @Override
  public void visitNode(StatImport node ) {
    // for the moment, let's just say we cannot return from an import to a superclass
    // of the importing class
  }

  @Override
  public void visitNode(StatMethodDeclaration node ) {
    node.block.visit(this);
  }

  @Override
  public void visitNode(StatPropose node ) {

  }

  @Override
  public void visitNode(StatReturn node ) {
    if (_rm.isExistingRule(node.lit) ) {
      _rm.foundReturnTo(node.lit);
      node.toRet = null;
    }
    // TODO: if the return has no literal, we want to escape from the innermost rule...
    node.curRuleLabel = _rm.getCurrentRule();
  }

  @Override
  public void visitNode(StatSetOperation node ) {

  }

  @Override
  public void visitNode(StatTimeout node ) {

  }

  @Override
  public void visitNode(StatVarDef node ) {

  }

  @Override
  public void visitNode(StatWhile node ) {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(UCharacter node ) {

  }

  @Override
  public void visitNode(UComment node ) {

  }

  @Override
  public void visitNode(UCommentBlock node ) {

  }

  @Override
  public void visitNode(UFieldAccess node ) {

  }

  @Override
  public void visitNode(UFuncCall node ) {

  }

  @Override
  public void visitNode(UNull node ) {

  }

  @Override
  public void visitNode(UNumber node ) {

  }

  @Override
  public void visitNode(UString node ) {

  }

  @Override
  public void visitNode(UVariable node ) {

  }

  @Override
  public void visitNode(UWildcard node ) {

  }

  @Override
  public void visitNode(UnaryBoolean node ) {

  }

}
