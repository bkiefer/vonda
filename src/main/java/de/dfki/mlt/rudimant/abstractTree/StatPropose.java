/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * represents a propose statement, creating a proposal
 *
 * @author Anna Welker
 */
public class StatPropose extends RTStatement {

  RTExpression arg;
  StatAbstractBlock block;

  public StatPropose(RTExpression arg, StatAbstractBlock block) {
    this.arg = arg;
    this.block = block;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + Objects.hashCode(this.arg);
    hash = 83 * hash + Objects.hashCode(this.block);
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
    final StatPropose other = (StatPropose) obj;
    if (!Objects.equals(this.arg, other.arg)) {
      return false;
    }
    if (!Objects.equals(this.block, other.block)) {
      return false;
    }
    return true;
  }

}
