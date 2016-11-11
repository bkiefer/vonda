/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * all classes that can be created by the ParseTreeVisitor should implement this
 * interface, for it is the return type of the visit methods
 *
 * @author Anna Welker
 */
public abstract class RudiTree {

  /**
   * positions contains the start and stop positions of a Token according to its
   * ParserRuleContext.
   * [0] = start of TokenIndex
   * [1] = stop of TokenIndex
   */
  public int[] positions;

  /**
   * visitor method
   */
  public abstract void visit(RudiVisitor v);

  /**
   * setPosition is used to store the start and stop position of a Token
   * given its ParserRuleContext.
   * @param context the ParserRuleContext.
   * @return RudiTree
   */
  public RudiTree setPosition(ParserRuleContext context) {
    positions = new int[]{
      context.getStart().getTokenIndex(),
      context.getStop().getTokenIndex()
    };
    return this;
  }

  public RudiTree setPosition(TerminalNode tn) {
    positions = new int[]{
      tn.getSymbol().getTokenIndex(),
      tn.getSymbol().getTokenIndex()
    };
    return this;
  }
}
