/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.Objects;

/**
 * FOR LPAR assignment SEMICOLON exp SEMICOLON exp? RPAR loop_statement_block
 * = a 'normal' for statement containing three ;
 *
 * @author Anna Welker
 */
public class StatFor1 implements RTStatement, RudiTree {

  ExpAssignment assignment;
  ExpBoolean condition;
  RTExpression arithmetic;
  StatAbstractBlock statblock;

  String currentRule;

  public StatFor1(ExpAssignment assignment, ExpBoolean condition,
          RTExpression arithmetic, StatAbstractBlock statblock, String position) {
    this.assignment = assignment;
    this.condition = condition;
    this.arithmetic = arithmetic;
    this.statblock = statblock;
    this.currentRule = position;
  }

    /*String ret0 = "if(this.whatToLog.get(\"" + this.currentRule +
            "\").contains(" + this.currentBool + ")){";
    if (arithmetic != null) {
      String ret1 = "for (" + assignment + "; " + condition + "; " + arithmetic
              + ") ";
      String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
              GrammarMain.context.getLog() +
              "boolLogger.info(\"------------------------------------------------\\n\");\n";
      String ret2 = statblock.generate(null).substring(1);
      return ret0 + "if(!(" + this.condition + ")){" + log + "}}" +
              ret1 + "{" + ret0 + log + "}" + ret2;
    }
    String ret1 = "for ( " + assignment + "; " + condition + ";"
            + ") " + statblock;
    String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
            GrammarMain.context.getLog() +
            "boolLogger.info(\"------------------------------------------------\\n\");\n";
    String ret2 = statblock.generate(null).substring(1);
    return ret0 + "if(!(" + this.condition + ")){" + log + "}}" +
            ret1 + "{" + ret0 + log + "}" + ret2;*/

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.assignment);
    hash = 97 * hash + Objects.hashCode(this.condition);
    hash = 97 * hash + Objects.hashCode(this.arithmetic);
    hash = 97 * hash + Objects.hashCode(this.statblock);
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
    final StatFor1 other = (StatFor1) obj;
    if (!Objects.equals(this.assignment, other.assignment)) {
      return false;
    }
    if (!Objects.equals(this.condition, other.condition)) {
      return false;
    }
    if (!Objects.equals(this.arithmetic, other.arithmetic)) {
      return false;
    }
    if (!Objects.equals(this.statblock, other.statblock)) {
      return false;
    }
    return true;
  }

  
}
