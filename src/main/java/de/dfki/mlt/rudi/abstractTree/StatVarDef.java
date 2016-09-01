/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * type_spec VARIABLE SEMICOLON
 * = only to get the type of this variable into memory
 *
 * @author Anna Welker
 */
public class StatVarDef implements RTStatement, RudiTree{

  String variable;
  String type;
  String position;

  public StatVarDef(String type, String variable, String position){
    this.type = type;
    this.variable = variable;
    this.position = position;
  }

  @Override
  public void testType() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
