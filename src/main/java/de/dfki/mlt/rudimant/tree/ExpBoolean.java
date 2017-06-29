/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import de.dfki.mlt.rudimant.Type;

/**
 * this is a boolean expression; might also be a subsumes relation (but will in
 * sum always be a boolean)
 *
 * @author Anna Welker
 */
public class ExpBoolean extends RTBinaryExp {

  /**
   * if the expression consists of only one part, set right and operator to null
   *
   * @param fullexp the String representation of the whole expression
   * @param left left part
   * @param right right part
   * @param operator operator in between
   */
  public ExpBoolean(RTExpression left,
          RTExpression right, String operator) {
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.type = new Type("boolean");
  }

  @Override
  public void visit(RTExpressionVisitor v) {
    v.visitNode(this);
  }

  /**
   * if we are an expression but this method is called, we should write to out;
   * it means that the instance calling us must be a statement
   * @param v
   */
  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.out.append(v.visitNode(this));
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }
}
