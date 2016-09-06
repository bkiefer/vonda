/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

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

  /**
   * if there is no else case, set statblockElse to null
   * @param condition the condition
   * @param statblockIf the if block
   * @param statblockElse  the else block if existing
   */
  public StatIf(String conditionString, RTExpression condition, StatAbstractBlock statblockIf,
          StatAbstractBlock statblockElse, String position) {
    this.condition = condition;
    this.statblockIf = statblockIf;
    this.statblockElse = statblockElse;
    this.currentRule = position;
    this.conditionString = conditionString;
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

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
