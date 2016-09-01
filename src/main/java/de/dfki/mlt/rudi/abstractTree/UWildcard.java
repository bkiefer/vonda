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
 * a wildcard...
 *
 * @author Anna Welker
 */
public class UWildcard extends RTLeaf{

  @Override
  public void testType() {
    // do nothing
  }

  @Override
  public String getType() {
    return "Object"; // ???
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
