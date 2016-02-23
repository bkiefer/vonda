/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.expressions;

import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.AbstractType;
import Versuch2.abstractTree.leaves.ACommentBlock;

/**
 *
 * @author anna
 */
public class AArithmeticExp  implements AbstractTree, AbstractExpression{
  
  private AbstractType type;
  private AbstractExpression left;
  private AbstractExpression right;
  private String operator;
  private boolean minus;

  /**
   * if the expression consists of only one part, set right and operator to null
   * @param left the left part
   * @param right the right part
   * @param operator the operator in between
   * @param minus set true if there is a minus in front of the arithmetic
   */
  public AArithmeticExp(AbstractExpression left,
          AbstractExpression right, String operator, boolean minus) {
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.minus = minus;
  }

  @Override
  public void testType() {
    // TODO: test sth
    this.type = AbstractType.OBJECT;
  }
  
  @Override
  public String toString(){
    String ret = "";
    if (this.minus){
      ret += "-";
    }
    if(this.right != null){
      return ret + "(" + this.left + this.operator + this.right
              + ")";
    }
    return ret + this.left;
  }

  @Override
  public AbstractType getType() {
    return this.type;
  }
  
}
