/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.expressions;

import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;

/**
 *
 * @author anna
 */
public class AIfExp implements AbstractTree, AbstractExpression{

  private AbstractExpression boolexp;
  private AbstractExpression thenexp;
  private AbstractExpression elseexp;

  public AIfExp(AbstractExpression bool, AbstractExpression thenexp, AbstractExpression elseexp){
    this.boolexp = bool;
    this.thenexp = thenexp;
    this.elseexp = elseexp;
  }

  @Override
  public void testType() {
    ((AbstractTree)boolexp).testType();
    ((AbstractTree)thenexp).testType();
    ((AbstractTree)elseexp).testType();
    assert(boolexp.getType().equals("boolean"));
    assert(thenexp.getType().equals(elseexp.getType()));
  }

  @Override
  public String getType() {
    return thenexp.getType();
  }

  @Override
  public String generate(Writer out){
    return this.boolexp + " ? " + this.thenexp + " : " + this.elseexp;
  }

}
