/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import java.io.IOException;
import java.io.Writer;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 * 
 * @author Anna Welker
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
  public void generate(Writer out) throws IOException{
    if(this.toRet == null){
      out.append("return;\n");
    }
    out.append("return ");
    this.toRet.generate(out);
    out.append(";\n");
  }

  @Override
  public void testType() {
    // test return type??
  }

}
