/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

/**
 * this is a conditional expression, i.e., something like a ? b : c
 *
 * @author Anna Welker
 */
public class ExpConditional extends RTExpression {

  RTExpression boolexp;
  RTExpression thenexp;
  RTExpression elseexp;

  // TODO: WHY IS THE FIRST NOT EXPBOOLEAN (AS LONG AS WE KEEP THAT)
  public ExpConditional(RTExpression bool, RTExpression thn,
          RTExpression els) {
    boolexp = bool;
    thenexp = thn;
    elseexp = els;
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

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { boolexp, thenexp, elseexp };
    return Arrays.asList(dtrs);
  }

  public void propagateType(String upperType) {
    if (type != null) {
      logger.error("Why didn't this type percolate up? " + fullexp + " " + type);
      return;
    }
    thenexp.propagateType(upperType);
    elseexp.propagateType(upperType);
  }

}
