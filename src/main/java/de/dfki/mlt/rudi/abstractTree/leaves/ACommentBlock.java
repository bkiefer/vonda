/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import java.util.List;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.AbstractLeaf;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;

/**
 *
 * @author anna
 */
public class ACommentBlock extends AbstractLeaf implements AbstractStatement {

  List<AComment> comments;
  private boolean containsClassName;
  private boolean printClassName;

  public ACommentBlock(List<AComment> comments) {
    this.comments = comments;
    this.containsClassName = false;
    for (AComment c : comments) {
      if (!(c.generate(null).startsWith("//") || c.generate(null).startsWith("/*"))) {
        if (c.containsClassName()) {
          this.containsClassName = true;
        }
      }
    }
  }

  public void setPrintClassName() {
    this.printClassName = true;
  }

  public boolean containsClassName() {
    return this.containsClassName;
  }

  @Override
  public String generate(Writer out) {
    String ret = "";
    if (this.printClassName) {
      for (AComment c : comments) {
        ret += "\n" + c;
        if (c.containsClassName()) {
          ret += "\n" + GrammarMain.context.afterClassName();
        }
      }
      return ret;
    }
    for (AComment c : comments) {
      ret += "\n" + c;
    }
    return ret;
  }

  @Override
  public void testType() {
  }

  @Override
  public String getType() { return null; }

}
