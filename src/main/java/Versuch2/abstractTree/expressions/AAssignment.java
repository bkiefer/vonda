/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.expressions;

import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.AbstractType;
import Versuch2.abstractTree.leafs.ALocalVar;

/**
 *
 * @author anna
 */
public class AAssignment  implements AbstractTree, AbstractExpression{
  
  private AbstractType type;
  private ALocalVar left;
  private AbstractExpression right;
  private boolean declaration;

  public AAssignment(ALocalVar left, AbstractExpression right, boolean declaration) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
  }

  @Override
  public void testType() {
    // TODO: test, look into memory, differ between String s = "" and s = ""
    this.type = AbstractType.OBJECT;
  }
  
  @Override
  public String toString(){
    if (this.declaration){
      return this.type + this.left.toString() + " = " + this.right;
    }
    return this.left + " = " + this.right;
  }

  @Override
  public AbstractType getType() {
    return this.type;
  }
  
}
