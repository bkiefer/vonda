/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.expressions;

import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;
import java.io.IOException;
import java.io.Writer;

/**
 * major intent of this class is to be able to deal with comments in every
 * position
 *
 * @author Anna Welker
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
  public void generate(Writer out) throws IOException{
    commentbefore.generate(out);
    exp.generate(out);
    commentafter.generate(out);
  }

  @Override
  public void testType() {
    this.exp.testType();
  }

  @Override
  public String getType() {
    return ((AbstractExpression) exp).getType();
  }

  @Override
  public void returnManaging() {
    exp.returnManaging();
  }
}
