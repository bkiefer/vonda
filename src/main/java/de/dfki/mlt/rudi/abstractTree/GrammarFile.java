/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.List;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.Mem;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * class that represents a top-level file that was handed over to GrammarMain (=
 * the root of the whole tree)
 *
 * @author Anna Welker
 */
public class GrammarFile implements RudiTree {

  // imports* (comment grammar_rule | method_declaration | statement )* comment
  List<RudiTree> rules;
  static Writer out;
  String classname;

  public GrammarFile(List<RudiTree> rules) {
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
    //let the return guy do its work
    returnManaging();
    //boolean foundClassName = false;
    Mem.addAndEnterNewEnvironment(Mem.getCurrentDepth() + 1);
    out.append("public class " + classname + "{\n"
            + "  public void process(){");
    for (RudiTree r : rules) {
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
    Mem.leaveEnvironment();
  }

  @Override
  public void testType() {
    if (GrammarMain.checkTypes()) {
      for (RudiTree t : this.rules) {
        t.testType();
      }
    }
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
