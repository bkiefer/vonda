/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree;

/**
 *
 * @author anna
 */
public abstract class AbstractStatement {
  
  protected String printStatBlock(AbstractStatement[] statementBlock){
    String stats = "";
    for (AbstractStatement stat : statementBlock){
      stats += stat.toString() + "\n";
    }
    return stats;
  }
}
