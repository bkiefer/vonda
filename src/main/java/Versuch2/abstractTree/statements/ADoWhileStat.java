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
public class ADoWhileStat implements AbstractStatement, AbstractTree{
  
  private AbstractTree condition;
  private AbstractBlock statblock;

  public ADoWhileStat(AbstractTree condition, AbstractBlock statblock) {
    this.condition = condition;
    this.statblock = statblock;
  }

  @Override
  public void testType() {
    // no types for statements
  }
  
  @Override
  public String toString(){
    String ret1 = "do " + statblock;
    String log = GrammarMain.context.getLog();
    String ret2 = "while (" + condition + ");\n";
    // remember to insert the log to log the condition
    return ret1.substring(0, ret1.length() - 2) + log + "}" + ret2;
  }
  
}
