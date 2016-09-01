/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

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
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
