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
 * just to be able to deal with lambda expressions if someone should use them,
 * but there is nothing like type checking implemented yet
 *
 * @author Anna Welker
 */
public class ALambdaExp implements AbstractTree, AbstractExpression{

  private String exp;

  public ALambdaExp(String exp) {
    this.exp = exp;
  }

  @Override
  public void generate(Writer out) throws IOException{
    out.append(exp);
  }

  @Override
  public void testType() {
    // nothing to do for now
  }

  @Override
  public String getType() {
    // TODO: what's the type of a lambda expression?
    return "Object";
  }


}
