/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * class representing a simple value like a String, an int or null
 * @author Anna Welker
 */
public class USingleValue extends RTLeaf {

//  residing in RTLeaf:
//  String content;
//  String type;
  
  public USingleValue(String representation, String type){
    this.content = representation;
    this.type = type;
  }

  @Override
  public String getType() {
    return this.type;
  }

  /**
   * note: this represents values like strings, ints, floats,..
   * whose type is known at creation time and therefore shouldn't be changed
   * @param to 
   */
  @Override
  public void setType(String to) {
    this.type = to;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
}
