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
public class AReturnStat implements AbstractStatement, AbstractTree{
  
  private AbstractTree toRet;
  
  public AReturnStat(){
    this.toRet = null;
  }
  
  public AReturnStat(AbstractTree exp){
    this.toRet = exp;
  }
  @Override
  public String toString(){
    if(this.toRet == null){
      return "return;\n";
    }
    return "return " + this.toRet + ";\n";
  }

  @Override
  public void testType() {
    // test return type??
  }
  
}
