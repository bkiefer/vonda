/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

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
    RudiTree[] dtrs = { left, right };
    return Arrays.asList(dtrs);
  }
}
