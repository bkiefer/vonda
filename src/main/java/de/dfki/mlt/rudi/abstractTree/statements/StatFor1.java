/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.expressions.ExpAssignment;
import de.dfki.mlt.rudi.abstractTree.expressions.ExpBoolean;
import de.dfki.mlt.rudi.abstractTree.leaves.UCommentBlock;
import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTStatement;
import de.dfki.mlt.rudi.abstractTree.RTExpression;

/**
 * FOR LPAR assignment SEMICOLON exp SEMICOLON exp? RPAR loop_statement_block
 * = a 'normal' for statement containing three ;
 *
 * @author Anna Welker
 */
public class StatFor1 implements RTStatement, RudiTree {

  private ExpAssignment assignment;
  private ExpBoolean condition;
  private RTExpression arithmetic;
  private StatAbstractBlock statblock;
  private String currentRule;

  public StatFor1(ExpAssignment assignment, ExpBoolean condition,
          RTExpression arithmetic, StatAbstractBlock statblock, String position) {
    this.assignment = assignment;
    this.condition = condition;
    this.arithmetic = arithmetic;
    this.statblock = statblock;
    this.currentRule = position;
  }

  @Override
  public void testType() {
    // no types for statements
  }

  @Override
  public void generate(Writer out) throws IOException {
    // the assignment will add the variable to the memory!!
    out.append("for ( ");
    assignment.generate(out);
    out.append("; ");
    condition.generate(out);
    out.append(";");
    if(arithmetic != null){
      arithmetic.generate(out);
    }
    out.append(");");
    statblock.generate(out);



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
  }

  @Override
  public void returnManaging() {
    statblock.returnManaging();
  }
}
