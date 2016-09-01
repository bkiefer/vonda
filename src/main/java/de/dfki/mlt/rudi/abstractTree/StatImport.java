/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.Mem;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * represents an import statement; each import statement will cause the imported
 * .rudi file to be parsed and translated. While parsing the imported file, rudimant
 * will remember the variables from the original file, but the namespace of the
 * imported file will not be visible from the original file thereafter.
 * @author Anna Welker
 */
public class StatImport implements RudiTree{

  // IMPORT VARIABLE SEMICOLON

  String text;

  public StatImport(String text){
    this.text = text;
  }

  @Override
  public void testType() {
    //nothing to do
  }

  @Override
  public void generate(Writer out) throws IOException{
    System.out.println("Processing import " + text);
    Mem.addAndEnterNewEnvironment(Mem.getCurrentDepth() + 1);
    out.append(text + ".process()");
    GrammarMain.processFile(new File(GrammarMain.getInputDirectory() + text + ".rudi"));
    Mem.leaveEnvironment();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
