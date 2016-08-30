/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.abstractTree.*;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author anna
 */
public class ANumber extends AbstractLeaf{

  private String value;

  public ANumber(String value) {
    this.value = value;
  }

  @Override
  public void testType() {
    // everything okay
  }

  @Override
  public void generate(Writer out) throws IOException{
    out.append(this.value);
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
}
