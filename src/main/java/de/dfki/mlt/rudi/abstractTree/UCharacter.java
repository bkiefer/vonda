/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

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
  public void testType() {
    // everything okay
  }

  @Override
  public String getType() {
    return "char";
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
