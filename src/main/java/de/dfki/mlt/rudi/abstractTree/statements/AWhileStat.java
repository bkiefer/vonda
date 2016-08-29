/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudi.abstractTree.expressions.ABooleanExp;
import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;

/**
 *
 * @author anna
 */
public class AWhileStat implements AbstractStatement, AbstractTree{
  
  private AbstractTree condition;
  private AbstractBlock statblock;
  private String currentRule;
  private int currentBool;

  public AWhileStat(AbstractTree condition, AbstractBlock statblock) {
    this.condition = condition;
    this.statblock = statblock;
    this.currentRule = GrammarMain.context.getCurrentRule();
    this.currentBool = GrammarMain.context.getCurrentBool();
  }

  @Override
  public void testType() {
    // no types for statements
  }
  
  @Override
  public String generate(Writer out){
    String ret0 = "if(this.whatToLog.get(\"" + this.currentRule + 
            "\").contains(" + this.currentBool + "){";
    String ret1 = "while (" + this.condition + ")";
    String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
            GrammarMain.context.getLog() +
            "boolLogger.info(\"------------------------------------------------\\n\");\n";
    String ret2 = statblock.generate(null).substring(1);
    return ret0 + "if(!(" + this.condition + ")){" + log + "}}"
            + ret1 + "{\nif(this.whatToLog.get(\"" + this.currentRule + 
            "\").contains(" + this.currentBool + "){" + log + "}" + ret2;
  }
}
