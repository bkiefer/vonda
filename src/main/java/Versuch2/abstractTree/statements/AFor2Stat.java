/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.abstractTree.AbstractLeaf;
import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.leafs.ALocalVar;

/**
 * FOR LPAR VARIABLE COLON exp RPAR loop_statement_block
 * @author anna
 */
public class AFor2Stat extends AbstractStatement implements AbstractTree{
  
  private ALocalVar var;
  private AbstractLeaf exp;
  private AbstractStatement[] statblock;

  @Override
  public void testType() {
    // somehow test return type of exp & variable?!
    // currently we will always assume that type of var is object
  }
  
  @Override
  public String toString(){
    return "for (Object " + var + ": " + exp + ") {\n" + printStatBlock(statblock) + "}\n";
  }
}
