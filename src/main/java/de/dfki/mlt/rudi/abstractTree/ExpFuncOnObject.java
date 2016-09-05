/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.List;

/**
 * represents an application of a function like in a.equals(b)
 * @author Anna Welker, anna.welker@dfki.de
 */
public class ExpFuncOnObject implements RTExpression{

  RTExpression on;
  RTExpression funccall;

  /**
   *
   * @param on the object before the dot
   * @param representation the name of the function as a string
   * @param exps a list of the given arguments
   */
  public ExpFuncOnObject(RTExpression on, RTExpression funccall) {
    this.on = on;
    this.funccall = funccall;
  }
  @Override
  public String getType() {
    return this.funccall.getType();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
