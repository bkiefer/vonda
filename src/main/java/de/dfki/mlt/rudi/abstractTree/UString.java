/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.abstractTree.*;
import java.io.IOException;
import java.io.Writer;

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
  public void testType() {
    // everything okay
  }

  @Override
  public String getType() {
    return "String";
  }

  @Override
  public void returnManaging() {
    // nothing to do
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
