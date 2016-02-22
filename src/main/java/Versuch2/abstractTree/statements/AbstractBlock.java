/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;
import java.util.List;

/**
 *
 * @author anna
 */
public class AbstractBlock implements AbstractStatement, AbstractTree{
  
  private List<AbstractStatement> statblock;
  private final boolean braces;

  public AbstractBlock(List<AbstractStatement> statblock, boolean braces) {
    this.statblock = statblock;
    this.braces = braces;
  }

  @Override
  public void testType() {
  }
  
  @Override
  public String toString(){
    String stats = "";
    for (AbstractStatement stat : statblock){
      stats += stat.toString() + "\n";
    }
    if(braces){
      stats = "{" + stats + "}\n";
    }
    return stats;
  }
  
}
