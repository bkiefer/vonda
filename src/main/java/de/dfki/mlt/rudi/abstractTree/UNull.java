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
 * represents the null object
 *
 * @author Anna Welker
 */
public class UNull extends RTLeaf {

  @Override
  public void testType() {
    // nothing to do
  }

  @Override
  public String getType() {
    return "Object";   // for you can assign null to anything
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
