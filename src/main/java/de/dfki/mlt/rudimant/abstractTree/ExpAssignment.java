/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * this is either a variable declaration, or an assignment of a variable to a
 * new value. Most of the type checking rudimant currently does happens here.
 *
 * @author Anna Welker
 */
public class ExpAssignment extends RTExpression {

  RudiTree left; // can be either a UVariable or a Field access
  RTExpression right;
  boolean declaration;
  String actualType;
  String position;

  public ExpAssignment(RudiTree left, RTExpression right,
          String position) {
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
   * @param declaration
   * @param position
   */
  public ExpAssignment(String actualType, RudiTree left, RTExpression right,
          String position) {
    this.left = left;
    this.right = right;
    this.declaration = true;
    this.actualType = actualType;
    this.position = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }


}
