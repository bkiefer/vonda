/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

/**
 *
 * @author anna
 */
public class StatIf extends RTStatement {

  RTExpression condition;
  RTStatement statblockIf;
  RTStatement statblockElse;
  String currentRule;
  String conditionString;

  /**
   * if there is no else case, set statblockElse to null
   *
   * @param conditionString
   * @param condition the condition
   * @param statblockIf the if block
   * @param statblockElse the else block if existing
   * @param position
   */
  public StatIf(String condString, RTExpression cond, RTStatement statIf,
          RTStatement statElse) {
    condition = cond;
    statblockIf = statIf;
    statblockElse = statElse;
    conditionString = condString;
  }

  @Override
  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    return Arrays.asList(
        (statblockElse != null)
        ? new RudiTree[]{ condition, statblockIf, statblockElse }
        : new RudiTree[]{ condition, statblockIf });
  }
}
