/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

/**
 * representation of sth like a += b where a is a set and b should be added
 * (also works with -, i.e. remove)
 *
 * @author Anna Welker
 */
public class StatSetOperation extends RTStatement {

  RTExpression left;
  boolean add;
  RTExpression right;

  public StatSetOperation(RTExpression l, boolean a, RTExpression r) {
    left = l;
    add = a;
    right = r;
  }

  @Override
  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { left, right };
    return Arrays.asList(dtrs);
  }
}
