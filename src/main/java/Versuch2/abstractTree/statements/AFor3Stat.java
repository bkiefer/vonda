/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;

/**
 * FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block
 * @author anna
 */
public class AFor3Stat extends AbstractStatement implements AbstractTree{

  @Override
  public void testType() {
    // no types for statements
  }
  
  @Override
  public String toString(){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
