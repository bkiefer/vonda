/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * class that handles comments, please don't use this as one side in an expression!
 * @author Anna Welker
 */
public class UComment extends RTLeaf implements RTStatement {

  String comment;
  boolean containsClassName;

  public UComment(String comment) {
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
  public String getType() {
    return null;
  }

  @Override
  public void testType() {
    // no type to be tested
  }
  
  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
