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
  
  private AbstractTree bool;
  private AbstractTree ifexp;
  private AbstractTree thenexp;
  
  public AIfExp(AbstractTree bool, AbstractTree ifexp, AbstractTree thenexp){
    this.bool = bool;
    this.ifexp = ifexp;
    this.thenexp = thenexp;
  }

  @Override
  public void testType() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getType() {
    // TODO: geht das genauer?
    return "Object";
  }
  
  @Override
  public String toString(){
    return this.bool + " ? " + this.ifexp + " : " + this.thenexp;
  }
  
}
