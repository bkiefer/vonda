/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import java.io.File;
import java.io.IOException;
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
  public void generate(Writer out) throws IOException{
    out.append(text + ".process()");
    GrammarMain.processFile(new File(GrammarMain.getOutputDirectory() + text + ".rudi"));
  }

}
