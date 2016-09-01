/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTStatement;

/**
 * represents a do ... while statement
 *
 * @author Anna Welker
 */
public class StatDoWhile implements RTStatement, RudiTree{

  RudiTree condition;
  StatAbstractBlock statblock;
  String currentRule;

  public StatDoWhile(RudiTree condition, StatAbstractBlock statblock, String position) {
    this.condition = condition;
    this.statblock = statblock;
    this.currentRule = position;
  }

  @Override
  public void testType() {
    // no types for statements
  }

  @Override
  public void generate(Writer out) throws IOException{
    out.append("do");
    statblock.generate(out);
    out.append("while (");
    condition.generate(out);
    out.append(");");


    /*String ret0 = "if(this.whatToLog.get(\"" + this.currentRule +
            "\").contains(" + this.currentBool + ")){";
    String ret1 = "do " + statblock;
    String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
            GrammarMain.context.getLog() +
            "boolLogger.info(\"------------------------------------------------\\n\");\n";
    String ret2 = "while (" + condition + ");\n";
    // remember to insert the log to log the condition
    return ret1.substring(0, ret1.length() - 2) + ret0 + log + "}}" + ret2;*/
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
