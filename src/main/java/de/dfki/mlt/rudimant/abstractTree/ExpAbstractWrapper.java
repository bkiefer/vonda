/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

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

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 37 * hash + Objects.hashCode(this.commentbefore);
    hash = 37 * hash + Objects.hashCode(this.exp);
    hash = 37 * hash + Objects.hashCode(this.commentafter);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ExpAbstractWrapper other = (ExpAbstractWrapper) obj;
    if (!Objects.equals(this.commentbefore, other.commentbefore)) {
      return false;
    }
    if (!Objects.equals(this.exp, other.exp)) {
      return false;
    }
    return Objects.equals(this.commentafter, other.commentafter);
  }
}
