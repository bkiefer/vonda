/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author anna
 */
public class AIfStatement implements AbstractStatement, AbstractTree{

  private AbstractTree condition;
  private AbstractBlock statblockIf;
  private AbstractBlock statblockElse;
  private String currentRule;

  /**
   * if there is no else case, set statblockElse to null
   * @param condition the condition
   * @param statblockIf the if block
   * @param statblockElse  the else block if existing
   */
  public AIfStatement(AbstractTree condition, AbstractBlock statblockIf,
          AbstractBlock statblockElse, String position) {
    this.condition = condition;
    this.statblockIf = statblockIf;
    this.statblockElse = statblockElse;
    this.currentRule = position;
  }

  @Override
  public void testType() {
    // no types for statements
  }

  @Override
  public void generate(Writer out) throws IOException{
    if (this.statblockElse != null){
      out.append("if (");
      condition.generate(out);
      out.append(") ");
      statblockIf.generate(out);
      out.append("else");
      statblockElse.generate(out);
    }


    /*String ret0 = "if(this.whatToLog.get(\"" + this.currentRule +
            "\").contains(" + this.currentBool + ")){";
    if (this.statblockElse != null){
      String ret1 = "if (" + condition + ") ";
      String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
              GrammarMain.context.getLog() +
              "boolLogger.info(\"------------------------------------------------\\n\");\n";
      String ret2 = statblockIf.generate(null).substring(1) + " else ";
      String ret3 = statblockElse.generate(null).substring(1);

      return ret0 + "if(!(" + this.condition + ")){" + log + "}}"
              + ret1 + "{" + ret0 + log  + "}" + ret2 + "{ " + log + ret3;
    }
    String ret1 = "if (" + condition + ") {";
    String log = "boolLogger.info(\"------------------------------------------------\\n\");\n" +
              GrammarMain.context.getLog() +
              "boolLogger.info(\"------------------------------------------------\\n\");\n";
    return ret0 + "if(!(" + this.condition + ")){" + log + "}}" +
            ret1 + ret0 + log + "}" +
            statblockIf.generate(null).substring(1) + "\n";*/
  }
}
