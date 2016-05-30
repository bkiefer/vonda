/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.abstractTree.*;

/**
 *
 * @author anna
 */
public class ANumber  implements AbstractTree, AbstractExpression, AbstractLeaf{
  
  private String value;

  public ANumber(String value) {
    this.value = value;
  }

  @Override
  public void testType() {
    // everything okay
  }
  
  @Override
  public String toString(){
    return this.value;
  }

  @Override
  public AbstractType getType() {
    // every int can be a float
    return AbstractType.FLOAT;
  }
}
