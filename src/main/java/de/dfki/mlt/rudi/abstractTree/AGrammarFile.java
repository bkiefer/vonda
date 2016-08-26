/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.List;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;

/**
 *
 * @author anna
 */
public class AGrammarFile implements AbstractTree {

  // (comment grammar_rule)* comment
  private List<AbstractTree> rules;

  public AGrammarFile(List<AbstractTree> rules) {
    this.rules = rules;
  }

  @Override
  public String toString() {
    String ret = "";
    boolean foundClassName = false;
    for (AbstractTree r : rules) {
      // Don't forget to insert text after class name as specified in context
      if (r instanceof ACommentBlock && foundClassName == false) {
        if (((ACommentBlock) r).containsClassName()) {
          ((ACommentBlock) r).setPrintClassName();
          foundClassName = true;
        }
      }
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
