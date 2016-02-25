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
public class ALocalVar  implements AbstractTree, AbstractExpression, AbstractLeaf{
  
  private AbstractType type;
  private String representation;

  public ALocalVar(AbstractType type, String representation) {
    this.type = type;
    this.representation = representation;
  }
  
  public ALocalVar(String representation) {
    this.type = AbstractType.OBJECT;
    this.representation = representation;
  }

  @Override
  public void testType() {
    // nothing to do
  }
  
  @Override
  public String toString(){
    return this.representation;
  }

  @Override
  public AbstractType getType() {
    return this.type;
  }
  
}
