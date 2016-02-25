/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

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
  }

  @Override
  public void testType() {
    // no types for statements
  }
  
  @Override
  public String toString(){
    if (this.statblockElse != null){
      return "if (" + condition + ") \n" + statblockIf
              + " else \n" + statblockElse;
    }
    return "if (" + condition + ") \n" + statblockIf + "\n";
  }
}
