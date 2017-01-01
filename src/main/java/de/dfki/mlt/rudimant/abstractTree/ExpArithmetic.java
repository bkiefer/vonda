/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * this is an arithmetic expression with an operator, two expressions left and
 * right, and eventually if this is no complex term but a single number there is
 * a - in front of it
 *
 * @author Anna Welker
 */
public class ExpArithmetic extends RTBinaryExp {

  /**
   * if the expression consists of only one part, set right and operator to null
   *
   * @param left the left part
   * @param right the right part
   * @param operator the operator in between
   * @param minus set true if there is a minus in front of the arithmetic
   */
  public ExpArithmetic(String full,
      RTExpression left, RTExpression right, String operator) {
    this.fullexp = full;
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.type = left.getType();   
  }
 
  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
