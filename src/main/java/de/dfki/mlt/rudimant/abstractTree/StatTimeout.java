/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * class representing a timeout
 *
 * @author Anna Welker
 */
public class StatTimeout implements RTStatement, RudiTree {

  long time;
  String name;
  RudiTree statblock;

  public StatTimeout(String name, long time, RudiTree statblock) {
    this.time = time;
    this.name = name;
    this.statblock = statblock;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 79 * hash + (int) (this.time ^ (this.time >>> 32));
    hash = 79 * hash + Objects.hashCode(this.name);
    hash = 79 * hash + Objects.hashCode(this.statblock);
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
    final StatTimeout other = (StatTimeout) obj;
    if (this.time != other.time) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.statblock, other.statblock)) {
      return false;
    }
    return true;
  }

}
