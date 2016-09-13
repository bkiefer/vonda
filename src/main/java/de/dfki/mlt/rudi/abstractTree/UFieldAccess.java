/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.mlt.rudi.Mem;
import java.util.ArrayList;
import de.dfki.lt.hfc.db.rdfProxy.*;
import java.util.Objects;
import org.apache.thrift.TException;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class UFieldAccess extends RTLeaf {

  /**
   * Client-field, taken from client/HfcDbClient.java, more information under
   * server/HfcDbService.Client.java
   */
  HfcDbService.Client _client;

  String type;
  ArrayList<String> representation;
  boolean asked = false;

  public UFieldAccess(ArrayList<String> representation, HfcDbService.Client client) {
    this.representation = representation;
    this._client = client;
  }

  public String getType(Mem mem) {
    return this.type;
  }

  /**
   * ask the ontology about the type of this object
   *
   * @param rudi
   * @return the type of this object
   * @throws org.apache.thrift.TException
   */
  public String askChristophe(Mem mem) throws TException {
    asked = true;
    if(_client == null){
      return "Object";
    }
    // first element of representation is type
    // everything else specifies the wanted predicate information
    String typ = mem.getVariableType(representation.get(0));
    String result = RdfClass.getPredicateType(typ,
            representation.subList(1, representation.size()), _client);
    // TODO: result = <xsd:string>
    return result;

  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + Objects.hashCode(this.representation);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final UFieldAccess other = (UFieldAccess) obj;
    if (!Objects.equals(this.representation, other.representation)) {
      return false;
    }
    return true;
  }

  
}
