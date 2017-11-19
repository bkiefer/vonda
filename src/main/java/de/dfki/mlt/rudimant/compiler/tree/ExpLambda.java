/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Arrays;
import java.util.List;

import de.dfki.mlt.rudimant.compiler.Environment;
import de.dfki.mlt.rudimant.compiler.Type;

/**
 * just to be able to deal with lambda expressions if someone should use them,
 * but there is nothing like type checking implemented yet
 *
 * @author Anna Welker
 */
public class ExpLambda extends RTExpLeaf implements RTBlockNode {

  List<String> parameters;
  Type parType;
  RudiTree body;

  public ExpLambda(List<String> args, RudiTree b) {
    parameters = args;
    body = b;
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

  public Iterable<? extends RudiTree> getDtrs() {
    StringBuilder sb = new StringBuilder();
    sb.append('(').append(parameters.get(0));
    for (int i = 1; i < parameters.size(); ++i)
      sb.append(',').append(parameters.get(i));
    sb.append(")");
    String typeString = parType == null ? "<null>" : parType.getRep();
    RudiTree[] dtrs = { new ExpSingleValue(sb.toString(), typeString), body };
    return Arrays.asList(dtrs);
  }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings, _parentBindings;

  public void setBindings(Environment parent, Environment local) {
    _parentBindings = parent; _localBindings = local;
  }

  public Environment getBindings() { return _localBindings; }

  public Environment getParentBindings() { return _parentBindings; }
}
