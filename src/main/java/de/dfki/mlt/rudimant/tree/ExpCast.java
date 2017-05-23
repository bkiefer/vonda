/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

/**
 * Either used to encode a normal java creation using new or to create a new
 * rdf object
 * @author pal
 */
public class ExpCast extends RTExpression {

  // the java object creation as a function call
  RTExpression construct;

  /**
   *
   * @param toCreate set to the rdf object class whose type should be created
   *                  or to null if this is a java creation
   * @param construct set to the function call that represents the java creation
   *                  (to null if no java creation)
   */
  public ExpCast(String t, RTExpression con){
    type = t;
    construct = con;
  }

  @Override
  public void propagateType(String upperType) {
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
    RudiTree[] dtrs = { construct };
    return Arrays.asList(dtrs);
  }


}
