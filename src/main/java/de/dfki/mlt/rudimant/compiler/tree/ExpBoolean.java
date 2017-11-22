/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * this is a boolean expression; might also be a subsumes relation (but will in
 * sum always be a boolean)
 *
 * @author Anna Welker
 */
public class ExpBoolean extends RTBinaryExp {
  // if this is true, it means that this ExpBoolean has been created during
  // code generation, and does not directly come from parsing
  public boolean synthetic;

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

  /**
   * if the expression consists of only one part, set right and operator to null
  *
  * @param fullexp the String representation of the whole expression
  * @param left left part
  * @param right right part
  * @param operator operator in between
  */
 public ExpBoolean(RTExpression left,
         RTExpression right, String operator, boolean synth) {
   this(left, right, operator);
   synthetic = synth;
 }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
