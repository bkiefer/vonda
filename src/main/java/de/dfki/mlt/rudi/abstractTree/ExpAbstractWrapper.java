/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * major intent of this class is to be able to deal with comments in every
 * position
 *
 * @author Anna Welker
 */
public class ExpAbstractWrapper implements RudiTree, RTExpression{

  UCommentBlock commentbefore;
  RudiTree exp;
  UCommentBlock commentafter;

  public ExpAbstractWrapper(UCommentBlock commentbefore, RudiTree exp, UCommentBlock commentafter) {
    this.commentbefore = commentbefore;
    this.exp = exp;
    this.commentafter = commentafter;
  }

  @Override
  public String getType() {
    return ((RTExpression) exp).getType();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
