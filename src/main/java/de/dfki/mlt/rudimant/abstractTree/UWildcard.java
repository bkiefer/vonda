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
public class UWildcard extends RTExpLeaf {

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }
}
