/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * this is an if expression, i.e., something like a ? b : c
 *
 * @author Anna Welker
 */
public class ExpIf implements RudiTree, RTExpression{

  RTExpression boolexp;
  RTExpression thenexp;
  RTExpression elseexp;

  String fullexp;

  public ExpIf(String fullexp, RTExpression bool, RTExpression thenexp,
          RTExpression elseexp){
    this.boolexp = bool;
    this.thenexp = thenexp;
    this.elseexp = elseexp;
    this.fullexp = fullexp;
  }

  @Override
  public String getType() {
    return thenexp.getType();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 37 * hash + Objects.hashCode(this.fullexp);
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
    final ExpIf other = (ExpIf) obj;
    if (!Objects.equals(this.fullexp, other.fullexp)) {
      return false;
    }
    return true;
  }

  
}
