/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Arrays;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * this is either a variable declaration, or an assignment of a variable to a
 * new value. Most of the type checking rudimant currently does happens here.
 *
 * @author Anna Welker
 */
public class ExpAssignment extends RTExpression {

  RTExpression left; // can be either a UVariable or a Field access
  RTExpression right;

  public ExpAssignment(RTExpression l, RTExpression r) {
    left = l;
    right = r;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
  public boolean leftIsVariable() {
    return left instanceof ExpVariable;
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { left, right };
    return Arrays.asList(dtrs);
  }

  public void propagateType(Type upperType) {
    if (type != null && ! type.isUnspecified()) {
      logger.error("Why didn't this type percolate up? " + fullexp + " " + type);
      return;
    }
    type = upperType;
    right.propagateType(upperType);
  }
}
