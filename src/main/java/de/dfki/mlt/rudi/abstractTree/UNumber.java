/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * this is either an int or a float
 *
 * @author Anna Welker
 */
public class UNumber extends RTLeaf{

  String value;

  public UNumber(String value) {
    this.value = value;
  }

  @Override
  public String getType() {
    // for the moment, we assume we don't get longs or doubles here
    if(this.value.contains(".")){
      return "float";
    } else {
      return "int";
    }
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
