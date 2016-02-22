/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.expressions.ABooleanExp;

/**
 *
 * @author anna
 */
public class AIfStatement implements AbstractStatement, AbstractTree{
  
  private ABooleanExp condition;
  private AbstractBlock statblockIf;
  private AbstractBlock statblockElse;

  /**
   * if there is no else case, set statblockElse to null
   * @param condition
   * @param statblockIf
   * @param statblockElse 
   */
  public AIfStatement(ABooleanExp condition, AbstractBlock statblockIf, AbstractBlock statblockElse) {
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
      return "if (" + condition + ") {\n" + statblockIf
              + "} else {\n" + statblockElse;
    }
    return "if (" + condition + ") {\n" + statblockIf + "}\n";
  }
}
