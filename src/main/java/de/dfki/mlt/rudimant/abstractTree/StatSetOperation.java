/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;

/**
 * representation of sth like a += b where a is a set and b should be added
 * (also works with -, i.e. remove)
 *
 * @author Anna Welker
 */
public class StatSetOperation extends RTStatement {

  RudiTree left;
  boolean add;
  RudiTree right;

  public StatSetOperation(RudiTree left, boolean add, RudiTree right) {
    this.left = left;
    this.add = add;
    this.right = right;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { left, right };
    return Arrays.asList(dtrs);
  }
}
