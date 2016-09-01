/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

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
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
