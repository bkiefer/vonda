/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.AbstractLeaf;
import java.io.IOException;
import java.io.Writer;

// TODO: test whether variable exists?

/**
 * representation of a variable
 * 
 * @author Anna Welker
 */
public class AVariable extends AbstractLeaf {

  private String type;
  private String representation;
  private String origin;

  public AVariable(String type, String representation, String origin) {
    this.type = type;
    this.representation = representation;
    this.origin = origin;
  }

  public AVariable(String representation, String origin) {
    //this.type = "Object";
    this.representation = representation;
    this.origin = origin;
  }

  @Override
  public String toString(){
    return this.representation;
  }

  @Override
  public void testType() {
    // nothing to do
  }

  @Override
  public void generate(Writer out) throws IOException{
    if(!origin.equals(Mem.getVariableOrigin(representation)))
      origin += ".";
    out.append(origin + representation);
  }

  @Override
  public String getType() {
    return Mem.getVariableType(representation);
  }

}
