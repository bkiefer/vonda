/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.returnManagement;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * represents a single grammar rule located in the .rudi file; each rule
 * will be transformed in a separate .java class (i.e., a separate .java file)
 * @author Anna Welker
 */
public class GrammarRule implements RudiTree{

  // label comment if_statement

  private String label;
  private String classname;
  private UCommentBlock comment;
  private StatIf ifstat;
  protected static Writer out;

  public GrammarRule(String label, UCommentBlock comment,StatIf ifstat) {
    this.label = label;
    this.ifstat = ifstat;
    this.comment = comment;
    this.classname = label.substring(0, 1).toUpperCase() + label.substring(1);
  }

  @Override
  public void generate(Writer out) throws FileNotFoundException, IOException{
    String returnType = "void";
    if(returnManagement.shouldAddReturnto(label)){
      returnType = "String";
    }
    out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(GrammarMain.getOutputDirectory() + classname + ".java")));
    Mem.addAndEnterNewEnvironment(Mem.getCurrentDepth() + 1);
    out.append("public class " + classname + "{\n"
            + "  public " + returnType + "process(){");
    comment.generate(out);
    ifstat.generate(out);
    out.append("\n\t return;\n  }\n}");
    Mem.leaveEnvironment();
  }

  @Override
  public void testType() {
    this.ifstat.testType();
  }

  @Override
  public void returnManaging() {
    returnManagement.enterRule(label);
    ifstat.returnManaging();
    returnManagement.leaveRule();
  }

}
