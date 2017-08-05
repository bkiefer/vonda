/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;
import java.util.Collections;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 *
 * @author Anna Welker
 */
public class StatBreak extends RTStatement {

  String toLeave = "";

  public StatBreak() {
  }

  /**
   * give me the expression after 'return' as well as the String it is, so if
   * this is a return to a rule I can transform accordingly
   *
   * @param exp
   * @param lit
   */
  public StatBreak(String exp) {
    toLeave = exp;
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
  public void visitVoidV(VisitorGeneration v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    // if (toLeave == null) 
      return Collections.emptyList();
    // return Arrays.asList(new RudiTree[]{ toLeave });
  }
}
