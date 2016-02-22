/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.expressions.AAssignment;
import Versuch2.abstractTree.expressions.ABooleanExp;

/**
 * FOR LPAR assignment SEMICOLON exp SEMICOLON exp? RPAR loop_statement_block
 * @author anna
 */
public class AFor1Stat extends AbstractStatement implements AbstractTree{
  
  private AAssignment assignment;
  private ABooleanExp condition;
  private AbstractExpression arithmetic;
  private AbstractStatement[] statblock;

  @Override
  public void testType() {
    // no types for statements
  }
  
  @Override
  public String toString(){
    if (arithmetic != null){
      return "for (" + assignment + "; " + condition + "; " + arithmetic
              + ") {\n" + printStatBlock(statblock) + "}\n";
    }
    return "for ( "+ assignment + "; " + condition + ";"
              + ") {\n" + printStatBlock(statblock) + "}\n";
  }
  
}
