/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * class representing a character
 *
 * @author Anna Welker
 */
public class UCharacter extends RTLeaf {

  String content;

  public UCharacter(String content) {
    this.content = content;
  }

  @Override
  public String getType() {
    return "char";
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 47 * hash + Objects.hashCode(this.content);
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
    final UCharacter other = (UCharacter) obj;
    if (!Objects.equals(this.content, other.content)) {
      return false;
    }
    return true;
  }

  
}
