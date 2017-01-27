/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Collections;

/**
 * type_spec VARIABLE SEMICOLON = only to get the type of this variable into
 * memory
 *
 * @author Anna Welker
 */
public class StatVarDef extends RTStatement {

  String variable;
  String type;
  String position;

  public StatVarDef(String type, String variable, String position) {
    this.type = type;
    this.variable = variable;
    this.position = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }

  public String toString() { return type + " " + variable +";"; }

  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }
}
