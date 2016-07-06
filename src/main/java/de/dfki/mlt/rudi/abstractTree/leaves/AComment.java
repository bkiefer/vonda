/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.abstractTree.*;

/**
 * class that handles comments, please don't use this as one side in an expression!
 * @author anna
 */
public class AComment implements AbstractTree, AbstractStatement, AbstractExpression, AbstractLeaf{

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
  public String toString(){
    return this.comment;
  }

  @Override
  public String getType() {
    return null;
  }

  @Override
  public void testType() {
    // no type to be tested
  }
}
