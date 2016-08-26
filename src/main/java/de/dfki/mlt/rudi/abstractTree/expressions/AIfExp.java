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
  
  private AbstractExpression bool;
  private AbstractExpression ifexp;
  private AbstractExpression thenexp;
  
  public AIfExp(AbstractExpression bool, AbstractExpression ifexp, AbstractExpression thenexp){
    this.bool = bool;
    this.ifexp = ifexp;
    this.thenexp = thenexp;
  }

  @Override
  public void testType() {
    ((AbstractTree)bool).testType();
    ((AbstractTree)ifexp).testType();
    ((AbstractTree)thenexp).testType();
    assert(bool.getType().equals("boolean"));
    assert(ifexp.getType().equals(thenexp.getType()));
  }

  @Override
  public String getType() {
    return ifexp.getType();
  }
  
  @Override
  public String toString(){
    return this.bool + " ? " + this.ifexp + " : " + this.thenexp;
  }
  
}
