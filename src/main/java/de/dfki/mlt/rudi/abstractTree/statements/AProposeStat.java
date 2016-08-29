/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author anna
 */
public class AProposeStat implements AbstractStatement, AbstractTree {

  private AbstractExpression arg;
  private AbstractBlock block;

  public AProposeStat(AbstractExpression arg, AbstractBlock block) {
    this.arg = arg;
    this.block = block;
  }

  @Override
  public void testType() {
    // TODO: arg should be a string expression
  }

  @Override
  public void generate(Writer out) throws IOException {
    out.append("propose(");
    arg.generate(out);
    out.append(", new Proposal() {public void run()\n");
    block.generate(out);
    out.append("});");
  }
}
