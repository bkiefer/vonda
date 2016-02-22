/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.expressions;

import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.AbstractType;

/**
 *
 * @author anna
 */
public class AArithmeticExp  implements AbstractTree, AbstractExpression{
  
  private AbstractType type;
  private AbstractExpression left;
  private AbstractExpression right;

  /**
   * if the expression consists of only one part, set right and operator to null
   * @param left
   * @param right
   * @param operator 
   */
  public AArithmeticExp(AbstractExpression left, AbstractExpression right, String operator) {
    this.left = left;
    this.right = right;
    this.operator = operator;
  }
  private String operator;

  @Override
  public void testType() {
    // TODO: test sth
    this.type = AbstractType.OBJECT;
  }
  
  @Override
  public String toString(){
    if(this.right != null){
      return "(" + this.left + this.operator + this.right + ")";
    }
    return this.left.toString();
  }

  @Override
  public AbstractType getType() {
    return this.type;
  }
  
}
