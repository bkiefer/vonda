/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import de.dfki.mlt.rudimant.Type;

/**
 * representation of a variable
 *
 * @author Anna Welker
 */
public class ExpVariable extends RTExpLeaf {

  private ExpVariable(String representation, Type t) {
    content = representation;
    type = t;
  }

  public ExpVariable(String type, String representation) {
    this(representation, new Type(type));
  }

  public ExpVariable(String representation) {
    this(representation, Type.getNoType());
  }

  @Override
  public String toString() {
    return this.content;
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
  public void visitVoidV(VisitorGeneration v) {
    v.out.append(v.visitNode(this));
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }

  public void propagateType(Type upperType) {
    // it's not an error if the type is null
    type = upperType;
  }
}