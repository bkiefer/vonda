/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 *
 * @author anna
 */
public class StatSwitch extends RTStatement {

  RTExpression condition;
  StatAbstractBlock switchBlock;

  /**
   * if there is no else case, set statblockElse to null
   *
   * @param condition   the condition
   * @param block       the block containing the case blocks
   */
  public StatSwitch(RTExpression condition, StatAbstractBlock block) {
    this.condition = condition;
    this.switchBlock = block;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
