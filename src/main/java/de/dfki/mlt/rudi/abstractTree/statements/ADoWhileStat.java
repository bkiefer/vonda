/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;

/**
 *
 * @author anna
 */
public class ADoWhileStat implements AbstractStatement, AbstractTree{
  
  private AbstractTree condition;
  private AbstractBlock statblock;
  private String currentRule;
  private int currentBool;

  public ADoWhileStat(AbstractTree condition, AbstractBlock statblock) {
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
            "\").contains(" + this.currentBool + ")){";
    String ret1 = "do " + statblock;
    String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
            GrammarMain.context.getLog() +
            "boolLogger.info(\"------------------------------------------------\\n\");\n";
    String ret2 = "while (" + condition + ");\n";
    // remember to insert the log to log the condition
    return ret1.substring(0, ret1.length() - 2) + ret0 + log + "}}" + ret2;
  }
  
}
