/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

import de.dfki.mlt.rudimant.Type;

/**
 * A Java-like cast
 * @author pal
 */
public class ExpCast extends RTExpression {

  // the java object creation as a function call
  RTExpression expression;

  /**
   *
   * @param t set to the type that the exp is casted to
   * @param exp set to the expression that is casted
   */
  public ExpCast(String t, RTExpression exp){
    type = new Type(t);
    expression = exp;
  }

  @Override
  public void propagateType(Type upperType) {
    // TODO: does this make sense here????
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

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { expression };
    return Arrays.asList(dtrs);
  }


}
