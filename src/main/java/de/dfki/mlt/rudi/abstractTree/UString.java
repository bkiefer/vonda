/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.Objects;

/**
 * a String
 *
 * @author Anna Welker
 */
public class UString extends RTLeaf {

  String content;

  public UString(String content) {
    this.content = content;
  }

  @Override
  public String getType() {
    return "String";
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + Objects.hashCode(this.content);
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
    final UString other = (UString) obj;
    if (!Objects.equals(this.content, other.content)) {
      return false;
    }
    return true;
  }

  
}
