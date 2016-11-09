/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * all classes that can be created by the ParseTreeVisitor should implement this
 * interface, for it is the return type of the visit methods
 *
 * @author Anna Welker
 */
public abstract class RudiTree {

  int[] positions;

  /**
   * visitor method
   */
  public abstract void visit(RudiVisitor v);

  public RudiTree setPosition(ParserRuleContext context) {
    positions = new int[] {
      context.getStart().getTokenIndex(),
      context.getStop().getTokenIndex()
    };
    return this;
  }
}
