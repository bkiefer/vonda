/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.abstractTree.*;
import java.io.IOException;
import java.io.Writer;

/**
 * class that handles comments, please don't use this as one side in an expression!
 * @author Anna Welker
 */
public class AComment extends AbstractLeaf implements AbstractStatement {

  private String comment;
  private boolean containsClassName;

  public AComment(String comment) {
    containsClassName = false;
    if(comment.contains("public class ")){
      containsClassName = true;
    }
    this.comment = comment;
  }

  public boolean containsClassName(){
    return this.containsClassName;
  }

  @Override
  public void generate(Writer out) throws IOException{
    out.append(this.comment);
  }

  @Override
  public String getType() {
    return null;
  }

  @Override
  public void testType() {
    // no type to be tested
  }

  @Override
  public void returnManaging() {
    // nothing to do
  }
}
