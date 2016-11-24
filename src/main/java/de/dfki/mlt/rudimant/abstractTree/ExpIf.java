/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;

/**
 * this is an if expression, i.e., something like a ? b : c
 *
 * @author Anna Welker
 */
public class ExpIf extends RTExpression {

  RTExpression boolexp;
  RTExpression thenexp;
  RTExpression elseexp;
  String fullexp;

  public ExpIf(String fullexp, RTExpression bool, RTExpression thenexp,
          RTExpression elseexp) {
    this.boolexp = bool;
    this.thenexp = thenexp;
    this.elseexp = elseexp;
    this.fullexp = fullexp;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { boolexp, thenexp, elseexp };
    return Arrays.asList(dtrs);
  }
}
