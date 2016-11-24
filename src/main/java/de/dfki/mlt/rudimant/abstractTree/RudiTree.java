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
   * ParserRuleContext. [0] = start of TokenIndex, [1] = stop of TokenIndex
   */
  public int[] positions;

  /**
   * visitor method
   */
  public abstract void visit(RudiVisitor v);

  public void visitWithComments(VGenerationVisitor v) {
    int firstPos = this.positions[0];
    checkComments(v, firstPos);
    this.visit(v);
    int endPos = this.positions[1];
    checkComments(v, endPos);
  }

  private void checkComments(VGenerationVisitor v, int firstPos) {
      while (!v.collectedTokens.isEmpty() && v.collectedTokens.get(0).getTokenIndex() < firstPos) {
        String comment = v.collectedTokens.get(0).getText();
        // Deal with java code
        if(comment.startsWith("/*@")){
          comment = comment.substring(3, comment.length()-3);
        }
        v.out.append(comment);
        v.collectedTokens.remove();
      }
    }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param context The ParserRuleContext.
   * @return RudiTree
   */
  public RudiTree setPosition(ParserRuleContext context) {
    positions = new int[]{
      context.getStart().getTokenIndex(),
      context.getStop().getTokenIndex()
    };
    return this;
  }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param tn The TerminalNode
   * @return RudiTree
   */
  public RudiTree setPosition(TerminalNode tn) {
    positions = new int[]{
      tn.getSymbol().getTokenIndex(),
      tn.getSymbol().getTokenIndex()
    };
    return this;
  }

  public abstract Iterable<? extends RudiTree> getDtrs();
}
