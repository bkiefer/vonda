/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * class that handles comments, please don't use this as one side in an
 * expression!
 *
 * @author Anna Welker
 */
public class UComment extends RTLeaf {

  String comment;
  boolean containsClassName;
  String type;

  public UComment(String comment) {
    containsClassName = false;
    if (comment.contains("public class ")) {
      containsClassName = true;
    }
    this.comment = comment;
  }

  public boolean containsClassName() {
    return this.containsClassName;
  }

  @Override
  public String getType() {
    return null;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 29 * hash + Objects.hashCode(this.comment);
    hash = 29 * hash + (this.containsClassName ? 1 : 0);
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
    final UComment other = (UComment) obj;
    if (this.containsClassName != other.containsClassName) {
      return false;
    }
    if (!Objects.equals(this.comment, other.comment)) {
      return false;
    }
    return true;
  }

  @Override
  public void setType(String to) {
    this.type = to;
  }
}
