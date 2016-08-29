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
public class AGlobalVar extends AbstractLeaf{

  private String type;
  private String representation;

  public AGlobalVar(String type, String representation) {
    this.type = type;
    this.representation = representation;
  }

  @Override
  public void testType() {
    // nothing to do
  }

  @Override
  public String generate(Writer out){
    return this.representation;
  }

  @Override
  public String getType() {
    return this.type;
  }
}
