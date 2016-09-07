/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

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
  // if the boolexp is no boolexp, type visitor should set this to the correct
  // way of testing the existance of boolexp
  String isTrue;

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
}
