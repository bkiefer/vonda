/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import java.util.List;

import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import java.io.IOException;
import java.io.Writer;

/**
 * a class to represent a set of statements, might have curly braces or just be
 * an abstract object for grouping (without showing that grouping in the output)
 *
 * @author Anna Welker
 */
public class AbstractBlock implements AbstractStatement, AbstractTree {

  private List<AbstractTree> statblock;
  private final boolean braces;
  private int environmentPosition;

  public AbstractBlock(List<AbstractTree> statblock, boolean braces) {
    this.statblock = statblock;
    this.braces = braces;
  }

  @Override
  public void testType() {
  }

  @Override
  public void generate(Writer out) throws IOException {
    String stats = "";
    if (braces) {
      // when entering a statement block, we need to create a new local environment
      this.environmentPosition = Mem.addAndEnterNewEnvironment(Mem.getCurrentDepth() + 1);
      out.append("{");
    }
    for (AbstractTree stat : statblock) {
      if (stat instanceof AbstractExpression) {
        stat.generate(out);
        out.append(";\n");
        break;
      }
      stat.generate(out);
    }
    if (braces) {
      out.append("}");
      Mem.leaveEnvironment();
    }
  }

  @Override
  public void returnManaging() {
    for (AbstractTree stat : statblock) {
      stat.returnManaging();
    }
  }
}
