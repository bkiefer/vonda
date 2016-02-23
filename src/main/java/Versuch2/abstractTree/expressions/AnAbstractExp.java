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
public class AnAbstractExp implements AbstractTree, AbstractExpression{
  
  private ACommentBlock commentbefore;
  private AbstractTree exp;
  private ACommentBlock commentafter;

  public AnAbstractExp(ACommentBlock commentbefore, AbstractTree exp, ACommentBlock commentafter) {
    this.commentbefore = commentbefore;
    this.exp = exp;
    this.commentafter = commentafter;
  }
  
  @Override
  public String toString(){
    return commentbefore.toString() + exp + commentafter.toString();
  }

  @Override
  public void testType() {
  }

  @Override
  public AbstractType getType() {
    return null;
  }
  
}
