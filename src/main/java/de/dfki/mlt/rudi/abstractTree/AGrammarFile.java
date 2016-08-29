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
  protected static Writer writer;

  public AGrammarFile(List<AbstractTree> rules) {
    this.rules = rules;
  }

  public void print(String classname, String outDir) throws FileNotFoundException {
    writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(outDir + classname + ".java")));
  }

  @Override
  public String toString() {
    String ret = "";
    //boolean foundClassName = false;
    for (AbstractTree r : rules) {
      // Don't forget to insert text after class name as specified in context
      // Edit: no one wants to use this anyway
      /*if (r instanceof ACommentBlock && foundClassName == false) {
        if (((ACommentBlock) r).containsClassName()) {
          ((ACommentBlock) r).setPrintClassName();
          foundClassName = true;
        }
      }*/
      ret += r;
    }
    return ret;
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
