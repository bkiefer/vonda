/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.leaves;

import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractLeaf;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.AbstractType;

/**
 *
 * @author anna
 */
public class AString implements AbstractTree, AbstractExpression, AbstractLeaf{
  
  private String content;

  public AString(String content) {
    this.content = content;
  }

  @Override
  public void testType() {
    // everything okay
  }
  
  @Override
  public String toString(){
    return "\"" + this.content + "\"";
  }

  @Override
  public AbstractType getType() {
    return AbstractType.STRING;
  }
  
}
