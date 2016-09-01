/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.*;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import de.dfki.lt.hfc.db.rdfProxy.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class UFieldAccess  extends RTLeaf{

    /**
   * Client-field, taken from client/HfcDbClient.java, more information
   * under server/HfcDbService.Client.java
   */
  protected HfcDbService.Client _client;


  private String type;
  private ArrayList<String> representation;
  private boolean asked = false;

  public UFieldAccess(ArrayList<String> representation, HfcDbService.Client client) {
    this.representation = representation;
    this._client = client;
  }

  @Override
  public void testType() {
    // nothing to do
  }

  @Override
  public void generate(Writer out) throws IOException{
    if(!asked){
      try {
        this.type = askChristophe();
      } catch (TException ex) {
        Logger.getLogger(UFieldAccess.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    out.append("Rdf here\n");
    //out.append(this.representation);
  }

  @Override
  public String getType(){
    if(!asked){
      try {
        this.type = askChristophe();
      } catch (TException ex) {
        Logger.getLogger(UFieldAccess.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return this.type;
  }

  /**
   * ask the ontology about the type of this object
   * @return the type of this object
   * @throws org.apache.thrift.TException
   */
  public String askChristophe() throws TException {
    asked = true;
    // first element of representation is type
    // everything else specifies the wanted predicate information
    String typ = Mem.getVariableType(representation.get(0));
    String result = RdfClass.getPredicateType(typ,
            representation.subList(1, representation.size()), _client);
    // TODO: result = <xsd:string>
    return result;

  }

  @Override
  public void returnManaging() {
    // nothing to do
  }
}
