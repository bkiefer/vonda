/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;
import java.util.Objects;

/**
 * represents an application of a function like in a.equals(b)
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class ExpFuncOnObject extends RTExpression {

  RTExpression on;
  RTExpression funccall;
  String look;
  /**
   *
   * @param on the object before the dot
   * @param representation the name of the function as a string
   * @param exps a list of the given arguments
   */
  public ExpFuncOnObject(RTExpression on, RTExpression funccall, String look) {
    this.on = on;
    this.funccall = funccall;
    this.look = look;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + Objects.hashCode(this.on);
    hash = 17 * hash + Objects.hashCode(this.funccall);
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
    final ExpFuncOnObject other = (ExpFuncOnObject) obj;
    if (!Objects.equals(this.on, other.on)) {
      return false;
    }
    if (!Objects.equals(this.funccall, other.funccall)) {
      return false;
    }
    return true;
  }
}
