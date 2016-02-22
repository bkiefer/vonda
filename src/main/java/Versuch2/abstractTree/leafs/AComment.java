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
public class AComment implements AbstractLeaf{

  private String comment;

  public AComment(String comment) {
    this.comment = comment;
  }
  
  @Override
  public String toString(){
    return this.comment;
  }

  @Override
  public AbstractType getType() {
    return AbstractType.NONE;
  }
}
