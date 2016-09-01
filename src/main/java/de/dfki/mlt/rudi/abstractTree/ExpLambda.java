/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * just to be able to deal with lambda expressions if someone should use them,
 * but there is nothing like type checking implemented yet
 *
 * @author Anna Welker
 */
public class ExpLambda implements RudiTree, RTExpression {

  String exp;

  public ExpLambda(String exp) {
    this.exp = exp;
  }

  @Override
  public void testType() {
    // nothing to do for now
  }

  @Override
  public String getType() {
    // TODO: what's the type of a lambda expression?
    return "Object";
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
