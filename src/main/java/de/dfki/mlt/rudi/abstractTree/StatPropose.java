/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * represents a propose statement, creating a proposal
 *
 * @author Anna Welker
 */
public class StatPropose implements RTStatement, RudiTree {

  RTExpression arg;
  StatAbstractBlock block;

  public StatPropose(RTExpression arg, StatAbstractBlock block) {
    this.arg = arg;
    this.block = block;
  }

  @Override
  public void testType() {
    // TODO: arg should be a string expression
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
