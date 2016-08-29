/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.abstractTree.*;

/**
 *
 * @author anna
 */
public class ALocalVar extends AbstractLeaf {

  private String type;
  private String representation;
  private String origin;

  public ALocalVar(String type, String representation, String origin) {
    this.type = type;
    this.representation = representation;
    this.origin = origin;
  }

  public ALocalVar(String representation, String origin) {
    this.type = "Object";
    this.representation = representation;
    this.origin = origin;
  }

  @Override
  public void testType() {
    // nothing to do
  }

  @Override
  public String generate(Writer out){
    if(!origin.equals(""))
      origin += ".";
    return origin + representation;
  }

  @Override
  public String getType() {
    return this.type;
  }

}
