/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import java.util.List;

import de.dfki.mlt.rudi.abstractTree.*;
import java.io.IOException;
import java.io.Writer;

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
  public void generate(Writer out) throws IOException{
    out.append(this.representation + "(");
    for(int i = 0; i < this.exps.size(); i++){
      this.exps.get(i).generate(out);
      if(i != this.exps.size() -1){
        out.append(", ");
      }
    }
    out.append(")");
  }

  @Override
  public String getType() {
    return this.type;
  }
}
