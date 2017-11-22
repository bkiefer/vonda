/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * class representing a simple value like a String, an int or null
 * @author Anna Welker
 */
public class ExpSingleValue extends RTExpLeaf {

  public ExpSingleValue(String representation, String type){
    this.content = representation;
    this.type = new Type(type);
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
