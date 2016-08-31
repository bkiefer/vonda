/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.*;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import de.dfki.lt.hfc.db.rdfProxy.*;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class AFieldAccess  extends AbstractLeaf{

  private String type;
  private ArrayList<String> representation;
  private boolean asked = false;

  public AFieldAccess(ArrayList<String> representation) {
    this.representation = representation;
  }

  @Override
  public void testType() {
    // nothing to do
  }

  @Override
  public void generate(Writer out) throws IOException{
    if(!asked){
      this.type = askChristophe();
    }
    out.append("Rdf here\n");
    //out.append(this.representation);
  }

  @Override
  public String getType() {
    if(!asked){
      this.type = askChristophe();
    }
    return this.type;
  }

  /**
   * ask the ontology about the type of this object
   * @return the type of this object
   */
  public String askChristophe(){
    asked = true;
    String typ = Mem.getVariableType(representation.get(0));

  }

  @Override
  public void returnManaging() {
    // nothing to do
  }
}
