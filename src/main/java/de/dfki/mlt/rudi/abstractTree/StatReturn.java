/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.ReturnManagement;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 *
 * @author Anna Welker
 */
public class StatReturn implements RTStatement, RudiTree {

  RudiTree toRet;
  String lit;

  public StatReturn() {
    this.toRet = null;
  }

  /**
   * give me the expression after 'return' as well as the String it is, so if this
   * is a return to a rule I can transform accordingly
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

}
