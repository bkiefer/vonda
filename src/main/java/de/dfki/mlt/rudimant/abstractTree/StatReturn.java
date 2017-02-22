/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;
import java.util.Collections;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 *
 * @author Anna Welker
 */
public class StatReturn extends RTStatement {

  RudiTree toRet;
  String lit;
  String curRuleLabel;

  public StatReturn() {
    this.toRet = null;
  }

  /**
   * give me the expression after 'return' as well as the String it is, so if
   * this is a return to a rule I can transform accordingly
   *
   * @param exp
   * @param lit
   */
  public StatReturn(RudiTree exp, String lit) {
    this.toRet = exp;
    this.lit = lit;
  }

  @Override
  public void visit(RudiVisitor v) {
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
    if (toRet == null) return Collections.emptyList();
    return Arrays.asList(new RudiTree[]{ toRet });
  }
}
