/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.Objects;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 *
 * @author Anna Welker
 */
public class StatReturn implements RTStatement, RudiTree {

  RudiTree toRet;
  String lit;
  String curRuleLabel;

  public StatReturn() {
    this.toRet = null;
  }

  /**
   * give me the expression after 'return' as well as the String it is, so if this
   * is a return to a rule I can transform accordingly
   * @param exp
   * @param lit
   */
  public StatReturn(RudiTree exp, String lit) {
    this.toRet = exp;
    this.lit = lit;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 79 * hash + Objects.hashCode(this.toRet);
    hash = 79 * hash + Objects.hashCode(this.lit);
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
    final StatReturn other = (StatReturn) obj;
    if (!Objects.equals(this.lit, other.lit)) {
      return false;
    }
    if (!Objects.equals(this.toRet, other.toRet)) {
      return false;
    }
    return true;
  }

  
}
