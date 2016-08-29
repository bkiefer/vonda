/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;

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
  public String generate(Writer out){
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
