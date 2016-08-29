/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import java.util.List;

import de.dfki.mlt.rudi.abstractTree.*;

/**
 *
 * @author anna
 */
public class AFunctAccess extends AbstractLeaf{

  private String type;
  private String representation;
  private List<AbstractExpression> exps;

  public AFunctAccess(String type, String representation, List<AbstractExpression> exps) {
    this.type = type;
    this.representation = representation;
    this.exps = exps;
  }

  @Override
  public void testType() {
    // nothing to do
  }

  @Override
  public String generate(Writer out){
    String args = "";
    for(int i = 0; i < this.exps.size(); i++){
      args += this.exps.get(i);
      if(i != this.exps.size() -1){
        args += ", ";
      }
    }
    return this.representation + "(" + args + ")";
  }

  @Override
  public String getType() {
    return this.type;
  }
}
