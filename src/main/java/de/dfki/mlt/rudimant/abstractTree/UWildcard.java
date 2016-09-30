/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * a wildcard...
 *
 * @author Anna Welker
 */
public class UWildcard extends RTLeaf {

  String type;

  @Override
  public String getType() {
    return "Object"; // ???
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public void setType(String to) {
    this.type = to;
  }
}
