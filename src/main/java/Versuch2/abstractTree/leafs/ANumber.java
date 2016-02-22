/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.leafs;

import Versuch2.abstractTree.AbstractLeaf;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.AbstractType;

/**
 *
 * @author anna
 */
public class ANumber  implements AbstractTree, AbstractLeaf{
  
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
