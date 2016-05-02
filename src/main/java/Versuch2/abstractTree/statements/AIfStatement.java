/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.GrammarMain;
import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;

/**
 *
 * @author anna
 */
public class AIfStatement implements AbstractStatement, AbstractTree{
  
  private AbstractTree condition;
  private AbstractBlock statblockIf;
  private AbstractBlock statblockElse;
  private String currentRule;
  private int currentBool;

  /**
   * if there is no else case, set statblockElse to null
   * @param condition the condition
   * @param statblockIf the if block
   * @param statblockElse  the else block if existing
   */
  public AIfStatement(AbstractTree condition, AbstractBlock statblockIf, 
          AbstractBlock statblockElse) {
    this.condition = condition;
    this.statblockIf = statblockIf;
    this.statblockElse = statblockElse;
    this.currentRule = GrammarMain.context.getCurrentRule();
    this.currentBool = GrammarMain.context.getCurrentBool();
  }

  @Override
  public void testType() {
    // no types for statements
  }
  
  @Override
  public String toString(){
    String ret0 = "if(this.whatToLog.get(\"" + this.currentRule + 
            "\").contains(" + this.currentBool + "){";
    if (this.statblockElse != null){
      String ret1 = "if (" + condition + ") ";
      String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
              GrammarMain.context.getLog() +
              "boolLogger.info(\"------------------------------------------------\\n\");\n";
      String ret2 = statblockIf.toString().substring(1) + " else ";
      String ret3 = statblockElse.toString().substring(1);
      
      return ret0 + "if(!(" + this.condition + ")){" + log + "}}"
              + ret1 + "{" + ret0 + log  + "}" + ret2 + "{ " + log + ret3;
    }
    String ret1 = "if (" + condition + ") {";
    String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
              GrammarMain.context.getLog() +
              "boolLogger.info(\"------------------------------------------------\\n\");\n";
    return ret0 + "if(!(" + this.condition + ")){" + log + "}}" +
            ret1 + ret0 + log + "}" +
            statblockIf.toString().substring(1) + "\n";
  }
}
