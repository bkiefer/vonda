/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudi.abstractTree.expressions.AAssignment;
import de.dfki.mlt.rudi.abstractTree.expressions.ABooleanExp;
import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;

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
  private String currentRule;
  private int currentBool;

  public AFor1Stat(AAssignment assignment, ABooleanExp condition,
          AbstractExpression arithmetic, AbstractBlock statblock) {
    this.assignment = assignment;
    this.condition = condition;
    this.arithmetic = arithmetic;
    this.statblock = statblock;
    this.currentRule = GrammarMain.context.getCurrentRule();
    this.currentBool = GrammarMain.context.getCurrentBool();
  }

  @Override
  public void testType() {
    // no types for statements
  }

  @Override
  public String generate(Writer out) {
    String ret0 = "if(this.whatToLog.get(\"" + this.currentRule + 
            "\").contains(" + this.currentBool + ")){";
    if (arithmetic != null) {
      String ret1 = "for (" + assignment + "; " + condition + "; " + arithmetic
              + ") ";
      String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
              GrammarMain.context.getLog() +
              "boolLogger.info(\"------------------------------------------------\\n\");\n";
      String ret2 = statblock.generate(null).substring(1);
      return ret0 + "if(!(" + this.condition + ")){" + log + "}}" +
              ret1 + "{" + ret0 + log + "}" + ret2;
    }
    String ret1 = "for ( " + assignment + "; " + condition + ";"
            + ") " + statblock;
    String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
            GrammarMain.context.getLog() +
            "boolLogger.info(\"------------------------------------------------\\n\");\n";
    String ret2 = statblock.generate(null).substring(1);
    return ret0 + "if(!(" + this.condition + ")){" + log + "}}" +
            ret1 + "{" + ret0 + log + "}" + ret2;
  }

}
