/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.Writer;

/**
 *
 * @author anna
 */
public class AImport implements AbstractTree{

  private String text;

  public AImport(String text){
    this.text = text;
  }

  @Override
  public void testType() {
    //nothing to do
  }

  @Override
  public void generate(Writer out){
    return "import " + this.text + ";";
  }

}
