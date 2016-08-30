/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import static de.dfki.mlt.rudi.abstractTree.AGrammarFile.out;
import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;
import de.dfki.mlt.rudi.abstractTree.statements.AIfStatement;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author anna
 */
public class AGrammarRule implements AbstractTree{

  // label comment if_statement

  private String label;
  private String classname;
  private ACommentBlock comment;
  private AIfStatement ifstat;
  protected static Writer out;

  public AGrammarRule(String label, ACommentBlock comment,AIfStatement ifstat) {
    this.label = label;
    this.ifstat = ifstat;
    this.comment = comment;
    this.classname = label.substring(0, 1).toUpperCase() + label.substring(1);
  }

  @Override
  public void generate(Writer out) throws FileNotFoundException, IOException{
    out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(GrammarMain.getOutputDirectory() + classname + ".java")));
    out.append("public class " + classname + "{\n"
            + "  public void process(){");
    comment.generate(out);
    ifstat.generate(out);
    out.append("\n  }\n}");
  }

  @Override
  public void testType() {
    this.ifstat.testType();
  }

}
