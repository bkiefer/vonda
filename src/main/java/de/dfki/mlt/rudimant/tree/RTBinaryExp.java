/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

/**
 * a special kind of the RudiTree is an expression; expressions can have types
 *
 * @author Anna Welker
 */
public abstract class RTBinaryExp extends RTExpression {
  RTExpression left;
  RTExpression right;

  String operator;

  public Iterable<? extends RudiTree> getDtrs() {
    return Arrays.asList(
        (right != null)
        ? new RudiTree[]{ left, right }
        : new RudiTree[]{ left });
  }

  public void propagateType(String upperType) {
    if (type != null) {
      logger.error("Why didn't this type percolate up? " + fullexp + " " + type);
      return;
    }
    type = upperType;
    right.propagateType(upperType);
    left.propagateType(upperType);
  }
}
