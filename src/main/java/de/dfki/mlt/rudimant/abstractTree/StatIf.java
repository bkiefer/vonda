/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

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
  // if the boolexp is no boolexp, type visitor should set this to the correct
  // way of testing the existance of boolexp
  String isTrue;

  /**
   * if there is no else case, set statblockElse to null
   *
   * @param conditionString
   * @param condition the condition
   * @param statblockIf the if block
   * @param statblockElse the else block if existing
   * @param position
   */
  public StatIf(String conditionString, RTExpression condition, RTStatement statblockIf,
          RTStatement statblockElse) {
    this.condition = condition;
    this.statblockIf = statblockIf;
    this.statblockElse = statblockElse;
    this.conditionString = conditionString;
  }

  @Override
  public void visit(RudiVisitor v) {
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
