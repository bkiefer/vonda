/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.expressions;

import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.AbstractType;
import Versuch2.abstractTree.leaves.ACommentBlock;
import java.util.List;

/**
 *
 * @author anna
 */
public class ALiteralOrGraphExp  implements AbstractTree, AbstractExpression{

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment
  
  private String litGraph;
  private List<AbstractExpression> exps;

  public ALiteralOrGraphExp(String litGraph, List<AbstractExpression> exps) {
    this.litGraph = litGraph.substring(1, litGraph.length());
    this.exps = exps;
  }
  
  @Override
  public void testType() {
    // no type testing here
  }
  
  @Override
  public String toString(){
    String ret = "new DialogueAct(\"" + litGraph + "\"(";
    // TODO: magically arrange exps
    throw new UnsupportedOperationException("Magic not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractType getType() {
    return AbstractType.MAGIC;
  }
  
}
