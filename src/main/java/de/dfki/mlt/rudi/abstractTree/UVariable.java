/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;
import java.util.Objects;

// TODO: test whether variable exists?

/**
 * representation of a variable
 *
 * @author Anna Welker
 */
public class UVariable extends RTLeaf {

  String type;
  String representation;
  String originClass;
  // set to the class whose attribute this variable is; null if the variable
  // is declared in originClass
  String realOrigin;
  boolean isRdfClass;
  

  public UVariable(String type, String representation, String originClass, 
          String originTRule) {
    this.type = type;
    this.representation = representation;
    this.originClass = originClass;
  }

  public UVariable(String representation, String originClass, String originTRule
          ) {
    //this.type = "Object";
    this.representation = representation;
    this.originClass = originClass;
  }

  @Override
  public String toString(){
    return this.representation;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.representation);
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
    final UVariable other = (UVariable) obj;
    if (!Objects.equals(this.representation, other.representation)) {
      return false;
    }
    return true;
  }

  
}
