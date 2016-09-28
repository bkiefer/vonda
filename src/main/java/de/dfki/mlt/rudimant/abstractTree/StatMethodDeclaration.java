/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import de.dfki.mlt.rudimant.Mem;
import java.util.ArrayList;
import java.util.Objects;

/**
 * class that allows the implementation of methods in a .rudi file; we are not
 * sure whether this should still be possible
 * @author Anna Welker
 */
public class StatMethodDeclaration implements RudiTree {

  String visibility;
  String return_type;
  String name;
  ArrayList<String> parameters;
  ArrayList<String> partypes;
  RudiTree block;
  String position;

  public StatMethodDeclaration(String visibility, String return_type, String name,
          ArrayList<String> parameters, ArrayList<String> partypes,
          RudiTree block, String position) {
    this.visibility = visibility;
    this.return_type = return_type;
    this.name = name;
    this.parameters = parameters;
    this.partypes = partypes;
    this.block = block;
    this.position = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 53 * hash + Objects.hashCode(this.visibility);
    hash = 53 * hash + Objects.hashCode(this.return_type);
    hash = 53 * hash + Objects.hashCode(this.name);
    hash = 53 * hash + Objects.hashCode(this.parameters);
    hash = 53 * hash + Objects.hashCode(this.partypes);
    hash = 53 * hash + Objects.hashCode(this.block);
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
    final StatMethodDeclaration other = (StatMethodDeclaration) obj;
    if (!Objects.equals(this.visibility, other.visibility)) {
      return false;
    }
    if (!Objects.equals(this.return_type, other.return_type)) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.parameters, other.parameters)) {
      return false;
    }
    if (!Objects.equals(this.partypes, other.partypes)) {
      return false;
    }
    if (!Objects.equals(this.block, other.block)) {
      return false;
    }
    return true;
  }

  
}
