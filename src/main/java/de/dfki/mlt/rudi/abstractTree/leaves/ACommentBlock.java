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
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author anna
 */
public class ACommentBlock extends AbstractLeaf implements AbstractStatement {

  List<AComment> comments;

  public ACommentBlock(List<AComment> comments) {
    this.comments = comments;
  }

  @Override
  public void generate(Writer out) throws IOException {
    for (AComment c : comments) {
      c.generate(out);
      out.append("\n");
    }
  }

  @Override
  public void testType() {
  }

  @Override
  public String getType() { return null; }

}
