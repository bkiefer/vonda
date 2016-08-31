/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudi.abstractTree.expressions.ABooleanExp;
import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;
import java.io.IOException;
import java.io.Writer;

/**
 * representing a while statement
 *
 * @author Anna Welker
 */
public class AWhileStat implements AbstractStatement, AbstractTree {

  private AbstractTree condition;
  private AbstractBlock statblock;
  private String currentRule;

  public AWhileStat(AbstractTree condition, AbstractBlock statblock, String position) {
    this.condition = condition;
    this.statblock = statblock;
    this.currentRule = position;
  }

  @Override
  public void testType() {
    // no types for statements
  }

  @Override
  public void generate(Writer out) throws IOException {
    out.append("while (");
    this.condition.generate(out);
    out.append(")");
    statblock.generate(out);
  }

  @Override
  public void returnManaging() {
    statblock.returnManaging();
  }
}
