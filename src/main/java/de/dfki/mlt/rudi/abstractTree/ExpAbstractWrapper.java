/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTExpression;

/**
 * major intent of this class is to be able to deal with comments in every
 * position
 *
 * @author Anna Welker
 */
public class ExpAbstractWrapper implements RudiTree, RTExpression{

  UCommentBlock commentbefore;
  RudiTree exp;
  UCommentBlock commentafter;

  public ExpAbstractWrapper(UCommentBlock commentbefore, RudiTree exp, UCommentBlock commentafter) {
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
    return ((RTExpression) exp).getType();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
