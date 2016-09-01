/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;

// TODO: test whether variable exists?

/**
 * representation of a variable
 *
 * @author Anna Welker
 */
public class UVariable extends RTLeaf {

  String type;
  String representation;
  String origin;

  public UVariable(String type, String representation, String origin) {
    this.type = type;
    this.representation = representation;
    this.origin = origin;
  }

  public UVariable(String representation, String origin) {
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
  public String getType() {
    return Mem.getVariableType(representation);
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
