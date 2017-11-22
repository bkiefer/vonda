/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Arrays;
import java.util.Collections;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 *
 * @author Anna Welker
 */
public class StatReturn extends RTStatement {

  String command;
  RTExpression returnExp;
  String ruleLabel;

  /**
   * give me the expression after 'return' as well as the String it is, so if
   * this is a return to a rule I can transform accordingly
   *
   * @param exp
   * @param lit
   */
  public StatReturn(RTExpression exp) {
    command = "return";
    returnExp = exp;
    ruleLabel = null;
  }

  /** A break with label */
  public StatReturn(String cmd, String label) {
    command = cmd;
    ruleLabel = label;
    returnExp = null;
  }

  /** A break without label, a cancel or cancelAll */
  public StatReturn(String cmd) {
    command = cmd;
    ruleLabel = null;
    returnExp = null;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public String toString() {
    String result = command;
    if (ruleLabel != null) result = result + " " + ruleLabel;
    if (returnExp != null) result = result + " " + returnExp.toString();
    return result;
  }

  public Iterable<? extends RudiTree> getDtrs() {
    if (returnExp == null) return Collections.emptyList();
    return Arrays.asList(new RudiTree[]{ returnExp });
  }
}
