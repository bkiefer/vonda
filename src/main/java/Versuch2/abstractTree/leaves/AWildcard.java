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
public class AWildcard  implements AbstractTree, AbstractExpression, AbstractLeaf{

  @Override
  public void testType() {
    // do nothing
  }
  
  @Override
  public String toString(){
    return "this.wildcard";   // wildcard is a local variable in resulting class
  }

  @Override
  public AbstractType getType() {
    return AbstractType.OBJECT; // ???
  }
}
