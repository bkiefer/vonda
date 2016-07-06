/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.expressions;

import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;

/**
 *
 * @author anna
 */
public class AArithmeticExp  implements AbstractTree, AbstractExpression{
  
  private String type;
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
    this.type = left.getType();
  }

  @Override
  public void testType() {
    // TODO: test somehow right and left
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
  public String getType() {
    return this.type;
  }
  
}
