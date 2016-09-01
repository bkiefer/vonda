/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.ExpBoolean;
import de.dfki.mlt.rudi.abstractTree.UCommentBlock;
import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTStatement;

/**
 * representing a while statement
 *
 * @author Anna Welker
 */
public class StatWhile implements RTStatement, RudiTree {

  RudiTree condition;
  StatAbstractBlock statblock;
  String currentRule;

  public StatWhile(RudiTree condition, StatAbstractBlock statblock, String position) {
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

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
