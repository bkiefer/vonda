/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Arrays;

/**
 * represents a propose statement, creating a proposal
 *
 * @author Anna Welker
 */
public class StatPropose extends RTStatement {

  RTExpression arg;
  StatAbstractBlock block;

  public StatPropose(RTExpression arg, StatAbstractBlock block) {
    this.arg = arg;
    this.block = block;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { arg, block };
    return Arrays.asList(dtrs);
  }
}
