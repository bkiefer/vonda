/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;
import java.util.Objects;

/**
 * this is used to represent a set of comments
 *
 * @author Anna Welker
 */
public class UCommentBlock extends RTStatement {

  List<UComment> comments;

  public UCommentBlock(List<UComment> comments) {
    this.comments = comments;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 53 * hash + Objects.hashCode(this.comments);
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
    final UCommentBlock other = (UCommentBlock) obj;
    if (!Objects.equals(this.comments, other.comments)) {
      return false;
    }
    return true;
  }

}
