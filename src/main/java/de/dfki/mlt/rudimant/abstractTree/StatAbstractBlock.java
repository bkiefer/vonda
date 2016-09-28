/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;
import java.util.Objects;


/**
 * a class to represent a set of statements, might have curly braces or just be
 * an abstract object for grouping (without showing that grouping in the output)
 *
 * @author Anna Welker
 */
public class StatAbstractBlock implements RTStatement, RudiTree {

  List<RudiTree> statblock;
  final boolean braces;

  public StatAbstractBlock(List<RudiTree> statblock, boolean braces) {
    this.statblock = statblock;
    this.braces = braces;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 43 * hash + Objects.hashCode(this.statblock);
    hash = 43 * hash + (this.braces ? 1 : 0);
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
    final StatAbstractBlock other = (StatAbstractBlock) obj;
    if (this.braces != other.braces) {
      return false;
    }
    if (!Objects.equals(this.statblock, other.statblock)) {
      return false;
    }
    return true;
  }

  
}
