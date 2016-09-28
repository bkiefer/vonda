/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * representing a while statement
 *
 * @author Anna Welker
 */
public class StatWhile implements RTStatement, RudiTree {

  RTExpression condition;
  StatAbstractBlock statblock;
  String currentRule;
  // if the boolexp is no boolexp, type visitor should set this to the correct
  // way of testing the existance of boolexp
  String isTrue;

  public StatWhile(RTExpression condition, StatAbstractBlock statblock, String position) {
    this.condition = condition;
    this.statblock = statblock;
    this.currentRule = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 79 * hash + Objects.hashCode(this.condition);
    hash = 79 * hash + Objects.hashCode(this.statblock);
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
    final StatWhile other = (StatWhile) obj;
    if (!Objects.equals(this.condition, other.condition)) {
      return false;
    }
    if (!Objects.equals(this.statblock, other.statblock)) {
      return false;
    }
    return true;
  }

  
}
