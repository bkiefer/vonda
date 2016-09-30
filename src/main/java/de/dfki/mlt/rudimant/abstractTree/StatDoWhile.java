/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * represents a do ... while statement
 *
 * @author Anna Welker
 */
public class StatDoWhile implements RTStatement, RudiTree {

  RTExpression condition;
  StatAbstractBlock statblock;
  String currentRule;
  // if the boolexp is no boolexp, type visitor should set this to the correct
  // way of testing the existance of boolexp
  String isTrue;

  public StatDoWhile(RTExpression condition, StatAbstractBlock statblock, String position) {
    this.condition = condition;
    this.statblock = statblock;
    this.currentRule = position;
  }

  /*String ret0 = "if(this.whatToLog.get(\"" + this.currentRule +
            "\").contains(" + this.currentBool + ")){";
    String ret1 = "do " + statblock;
    String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
            GrammarMain.context.getLog() +
            "boolLogger.info(\"------------------------------------------------\\n\");\n";
    String ret2 = "while (" + condition + ");\n";
    // remember to insert the log to log the condition
    return ret1.substring(0, ret1.length() - 2) + ret0 + log + "}}" + ret2;*/
  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 19 * hash + Objects.hashCode(this.condition);
    hash = 19 * hash + Objects.hashCode(this.statblock);
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
    final StatDoWhile other = (StatDoWhile) obj;
    if (!Objects.equals(this.condition, other.condition)) {
      return false;
    }
    if (!Objects.equals(this.statblock, other.statblock)) {
      return false;
    }
    return true;
  }

}
