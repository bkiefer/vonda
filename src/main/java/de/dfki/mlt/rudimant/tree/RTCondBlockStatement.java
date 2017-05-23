/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

/**
 * A statement that has a condition and a block: while, do..while, switch
 *
 * @author Anna Welker
 */
public abstract class RTCondBlockStatement extends RTStatement {
  RTExpression condition;
  RTStatement block;

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { condition, block };
    return Arrays.asList(dtrs);
  }
}
