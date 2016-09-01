/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.List;

import de.dfki.mlt.rudi.abstractTree.RTLeaf;
import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RTStatement;

/**
 * this is used to represent a set of comments
 *
 * @author Anna Welker
 */
public class UCommentBlock extends RTLeaf implements RTStatement {

  List<UComment> comments;

  public UCommentBlock(List<UComment> comments) {
    this.comments = comments;
  }

  @Override
  public void generate(Writer out) throws IOException {
    for (UComment c : comments) {
      c.generate(out);
      out.append("\n");
    }
  }

  @Override
  public void testType() {
  }

  @Override
  public String getType() { return null; }

  @Override
  public void returnManaging() {
    // nothing to do
  }
}
