/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * this is either an int or a float
 *
 * @author Anna Welker
 */
public class UNumber extends RTLeaf {

  String value;
  String type;

  public UNumber(String value) {
    this.value = value;
  }

  @Override
  public String getType() {
    // for the moment, we assume we don't get longs or doubles here
    if (this.value.contains(".")) {
      return "float";
    } else {
      return "int";
    }
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 47 * hash + Objects.hashCode(this.value);
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
    final UNumber other = (UNumber) obj;
    if (!Objects.equals(this.value, other.value)) {
      return false;
    }
    return true;
  }

  @Override
  public void setType(String to) {
    this.type = to;
  }
}
