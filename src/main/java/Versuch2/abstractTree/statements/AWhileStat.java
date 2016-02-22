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
public class AWhileStat implements AbstractStatement, AbstractTree{
  
  private ABooleanExp condition;
  private AbstractBlock statblock;

  public AWhileStat(ABooleanExp condition, AbstractBlock statblock) {
    this.condition = condition;
    this.statblock = statblock;
  }

  @Override
  public void testType() {
    // no types for statements
  }
  
  @Override
  public String toString(){
    return "while (" + this.condition + ") {\n" + statblock + "}\n";
  }
}
