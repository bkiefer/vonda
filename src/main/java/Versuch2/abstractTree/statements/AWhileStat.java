/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.GrammarMain;
import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.expressions.ABooleanExp;
import Versuch2.abstractTree.leaves.ACommentBlock;

/**
 *
 * @author anna
 */
public class AWhileStat implements AbstractStatement, AbstractTree{
  
  private AbstractTree condition;
  private AbstractBlock statblock;

  public AWhileStat(AbstractTree condition, AbstractBlock statblock) {
    this.condition = condition;
    this.statblock = statblock;
  }

  @Override
  public void testType() {
    // no types for statements
  }
  
  @Override
  public String toString(){
    String ret1 = "while (" + this.condition + ")";
    String log = GrammarMain.context.getLog();
    String ret2 = statblock.toString().substring(1);
    return ret1 + "{" + log + ret2;
  }
}
