/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.ArrayList;
import java.util.Objects;

/**
 * type_spec VARIABLE LPAR ( type_spec VARIABLE (COMMA type_spec VARIABLE)* )?
 * RPAR SEMICOLON = only to get the types of this function into memory
 *
 * @author Anna Welker
 */
public class StatFunDef extends RTStatement {

  String funcname;
  String type;
  ArrayList<String> parameters;
  ArrayList<String> parameterTypes;
  String position;

  public StatFunDef(String type, String funcname, ArrayList<String> parameters,
          ArrayList<String> parameterTypes, String position) {
    this.type = type;
    this.funcname = funcname;
    this.position = position;
    this.parameters = parameters;
    this.parameterTypes = parameterTypes;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 19 * hash + Objects.hashCode(this.funcname);
    hash = 19 * hash + Objects.hashCode(this.type);
    hash = 19 * hash + Objects.hashCode(this.parameters);
    hash = 19 * hash + Objects.hashCode(this.parameterTypes);
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
    final StatFunDef other = (StatFunDef) obj;
    if (!Objects.equals(this.funcname, other.funcname)) {
      return false;
    }
    if (!Objects.equals(this.type, other.type)) {
      return false;
    }
    if (!Objects.equals(this.parameters, other.parameters)) {
      return false;
    }
    if (!Objects.equals(this.parameterTypes, other.parameterTypes)) {
      return false;
    }
    return true;
  }

}
