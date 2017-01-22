/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;

/**
 * this is either a variable declaration, or an assignment of a variable to a
 * new value. Most of the type checking rudimant currently does happens here.
 *
 * @author Anna Welker
 */
public class ExpAssignment extends RTExpression {

  RTExpression left; // can be either a UVariable or a Field access
  RTExpression right;
  boolean declaration;
  String position;

  public ExpAssignment(String full, RTExpression left, RTExpression right,
          String position) {
    fullexp = full;
    this.left = left;
    this.right = right;
    this.declaration = false;
    this.position = position;
  }

  /** This is called in case this is a combined variable declaration with
   * initial assignment
   * @param actualType the declared type
   * @param left
   * @param right
   * @param position
   */
  public ExpAssignment(String full, String actualType, RTExpression left, RTExpression right,
          String position) {
    this(full, left, right, position);
    this.declaration = true;
    this.type = actualType;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { left, right };
    return Arrays.asList(dtrs);
  }

  public void propagateType(String upperType) {
    if (type != null) {
      logger.error("Why didn't this type percolate up? " + fullexp + " " + type);
      return;
    }
    type = upperType;
    right.propagateType(upperType);
  }
}
