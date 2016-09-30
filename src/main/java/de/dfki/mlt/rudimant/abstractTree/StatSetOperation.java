/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * representation of sth like a += b where a is a set and b should be added
 * (also works with -, i.e. remove)
 *
 * @author Anna Welker
 */
public class StatSetOperation implements RTStatement, RudiTree {

  RudiTree left;
  boolean add;
  RudiTree right;

  public StatSetOperation(RudiTree left, boolean add, RudiTree right) {
    this.left = left;
    this.add = add;
    this.right = right;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + Objects.hashCode(this.left);
    hash = 17 * hash + (this.add ? 1 : 0);
    hash = 17 * hash + Objects.hashCode(this.right);
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
    final StatSetOperation other = (StatSetOperation) obj;
    if (this.add != other.add) {
      return false;
    }
    if (!Objects.equals(this.left, other.left)) {
      return false;
    }
    if (!Objects.equals(this.right, other.right)) {
      return false;
    }
    return true;
  }

}
