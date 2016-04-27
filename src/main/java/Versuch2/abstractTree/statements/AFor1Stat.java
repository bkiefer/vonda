/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.GrammarMain;
import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.expressions.AAssignment;
import Versuch2.abstractTree.expressions.ABooleanExp;
import Versuch2.abstractTree.leaves.ACommentBlock;

/**
 * FOR LPAR assignment SEMICOLON exp SEMICOLON exp? RPAR loop_statement_block
 *
 * @author anna
 */
public class AFor1Stat implements AbstractStatement, AbstractTree {

  private AAssignment assignment;
  private ABooleanExp condition;
  private AbstractExpression arithmetic;
  private AbstractBlock statblock;

  public AFor1Stat(AAssignment assignment, ABooleanExp condition,
          AbstractExpression arithmetic, AbstractBlock statblock) {
    this.assignment = assignment;
    this.condition = condition;
    this.arithmetic = arithmetic;
    this.statblock = statblock;
  }

  @Override
  public void testType() {
    // no types for statements
  }

  @Override
  public String toString() {
    if (arithmetic != null) {
      String ret1 = "for (" + assignment + "; " + condition + "; " + arithmetic
              + ") ";
      String log = GrammarMain.context.getLog();
      String ret2 = statblock.toString().substring(1);
      return ret1 + "{" + log + ret2;
    }
    String ret1 = "for ( " + assignment + "; " + condition + ";"
            + ") " + statblock;
    String log = GrammarMain.context.getLog();
    String ret2 = statblock.toString().substring(1);
    return ret1 + "{" + log + ret2;
  }

}
