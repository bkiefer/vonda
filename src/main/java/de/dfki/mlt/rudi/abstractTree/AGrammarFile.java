/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.List;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import static java.lang.System.out;

/**
 *
 * @author anna
 */
public class AGrammarFile implements AbstractTree {

  // (comment grammar_rule)* comment
  private List<AbstractTree> rules;
  protected static Writer out;
  private String classname;

  public AGrammarFile(List<AbstractTree> rules) {
    this.rules = rules;
  }

  public void print(String classname) throws FileNotFoundException, IOException {
    this.classname = classname;
    out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(GrammarMain.getOutputDirectory() + classname + ".java")));
    this.generate(out);
  }

  @Override
  public void generate(Writer out) throws IOException {
    //boolean foundClassName = false;
    out.append("public class " + classname + "{\n"
            + "  public void process(){");
    for (AbstractTree r : rules) {
      // Don't forget to insert text after class name as specified in context
      // Edit: no one wants to use this anyway
      /*if (r instanceof ACommentBlock && foundClassName == false) {
        if (((ACommentBlock) r).containsClassName()) {
          ((ACommentBlock) r).setPrintClassName();
          foundClassName = true;
        }
      }*/
      r.generate(out);
    }
    out.append("  }\n}");
    out.close();
  }

  @Override
  public void testType() {
    if (GrammarMain.checkTypes()) {
      for (AbstractTree t : this.rules) {
        t.testType();
      }
    }
  }
}
