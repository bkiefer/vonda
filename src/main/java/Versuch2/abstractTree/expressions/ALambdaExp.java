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

/**
 *
 * @author anna
 */
public class ALambdaExp implements AbstractTree, AbstractExpression{
  
  private String exp;

  public ALambdaExp(String exp) {
    this.exp = exp;
  }

  @Override
  public String toString(){
    return exp;
  }
  
  @Override
  public void testType() {
    // nothing to do for now
  }

  @Override
  public AbstractType getType() {
    // TODO: what's the type of a lambda expression?
    return AbstractType.OBJECT;
  }
  
  
}
