/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 *
 * @author anna
 */
public class StatIf implements RTStatement, RudiTree{

  RTExpression condition;
  StatAbstractBlock statblockIf;
  StatAbstractBlock statblockElse;
  String currentRule;
  String conditionString;
  // if the boolexp is no boolexp, type visitor should set this to the correct
  // way of testing the existance of boolexp
  String isTrue;

  /**
   * if there is no else case, set statblockElse to null
   * @param conditionString
   * @param condition the condition
   * @param statblockIf the if block
   * @param statblockElse  the else block if existing
   * @param position
   */
  public StatIf(String conditionString, RTExpression condition, StatAbstractBlock statblockIf,
          StatAbstractBlock statblockElse) {
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
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + Objects.hashCode(this.statblockIf);
    hash = 67 * hash + Objects.hashCode(this.statblockElse);
    hash = 67 * hash + Objects.hashCode(this.conditionString);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StatIf other = (StatIf) obj;
    if (!Objects.equals(this.conditionString, other.conditionString)) {
      return false;
    }
    if (!Objects.equals(this.statblockIf, other.statblockIf)) {
      return false;
    }
    if (!Objects.equals(this.statblockElse, other.statblockElse)) {
      return false;
    }
    return true;
  }


}
