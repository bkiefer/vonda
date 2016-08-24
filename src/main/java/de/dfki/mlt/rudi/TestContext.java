/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;


/**
 *
 * @author anna
 */
public class TestContext implements RobotContext {

  private Logger boolLogger;
  private FileHandler fh;
  private static boolean log;
  private String boolLog;
  private String currentRule;
  private int lastBool;
  private Set<String> rules;

  public TestContext(boolean log) {
    this.log = log;
    this.boolLog = "";
    this.rules = new HashSet<String>();
  }

  @Override
  public String getCurrentRule() {
    return this.currentRule;
  }

  @Override
  public int getCurrentBool() {
    return ++this.lastBool;
  }

  @Override
  public void setCurrentRule(String rule) {
    this.currentRule = rule;
    this.lastBool = 0;
    this.rules.add(rule);
  }

  @Override
  public void doLog(String toLog) {
    if (!this.log) {
      return;
    }
    this.boolLog += "boolLogger.info(" + toLog + ");\n";
  }

  @Override
  public String getLog() {
    String x = this.boolLog;
    this.boolLog = "";
    return x;
  }

  @Override
  public String beforeClassName() {
    return "/**this is the license header\n**/\n\n\n"
            + "import java.io.IOException;\n"
            + "import java.util.logging.FileHandler;\n"
            + "import java.util.logging.Logger;\n"
            + "import java.util.logging.SimpleFormatter;\n"
            + "import java.util.HashMap;\n"
            + "import java.util.HashSet;\n"
            + "import java.util.Set;\n";
  }

  @Override
  public String afterClassName() {
    String ret1 = "// if you want to log a boolean expression, please enter its location here\n"
            + "// (0 is default and will not match anything)\n"
            + "whatToLog = new HashMap<String, Set<Integer>>();\n";
    for (String r : this.rules) {
      ret1 += "whatToLog.put(\"" + r + "\", new HashSet<Integer>());\n"
              + "whatToLog.get(\"" + r + "\").add(0);\n";
    }
    return ("\n"
            + "  private static HashMap<String, Set<Integer>> whatToLog;\n"
            + "{\n" + ret1 + "}\n"
            + "  private Logger boolLogger;\n"
            + "  private FileHandler fh;\n"
            + "{    boolLogger = Logger.getLogger(\"BoolLog\");\n"
            + "    try {\n"
            + "      // This block configure the logger with handler and formatter  \n"
            + "      fh = new FileHandler(\"src/test/BooleanExpressionLog\");\n"
            + "      boolLogger.addHandler(fh);\n"
            + "      SimpleFormatter formatter = new SimpleFormatter();\n"
            + "      fh.setFormatter(formatter);\n"
            + "      // the following statement is used to log any messages  \n"
            + "      boolLogger.info(\"this is the logger of rudimant booleans\");\n"
            + "    } catch (SecurityException | IOException e) {\n"
            + "      e.printStackTrace();\n"
            + "    }"
            + "}\n");
  }

  @Override
  public String atEndOfFile() {
    return "\n";
  }

  @Override
  public boolean isGlobalVariable(String variable) {
    // for the moment, assume the user knows what he's doing
    if (variableMap.containsKey(variable)) {
      return true;
    }
    if (Pattern.matches("([A-Z]|_)+", variable)) {
      return false;
    }
    return true;
  }

  private static HashMap<String, String> variableMap = new HashMap<String, String>();

  {
    variableMap.put("myLastSA", "magic");
    variableMap.put("currentSA", "magic");
    variableMap.put("lastSA", "magic");
    variableMap.put("initiated", "String");
    variableMap.put("startSession", "String");
    variableMap.put("game", "Object");
    variableMap.put("Inform", "Object");
    variableMap.put("Confirm", "Object");
    variableMap.put("_pendingTask", "Object");
    variableMap.put("MAX_WAIT_FOR_TABLET", "float");
    variableMap.put("currentTime", "float");
    variableMap.put("POINTS_TO_ME", "boolean");
    variableMap.put("I_MYSELF", "Object");
    variableMap.put("inSession", "Object");
    variableMap.put("questionAsked", "boolean");
    variableMap.put("Question", "Object");
    variableMap.put("Answers", "Object");
    variableMap.put("Solution", "Object");
    variableMap.put("turnFinished", "boolean");
    variableMap.put("answerGiven", "Object");
    variableMap.put("no", "Object");
    variableMap.put("proposed", "Object");
    variableMap.put("yes", "Object");
  }

  @Override
  public String getVariableType(String variable) {
    if (variableMap.containsKey(variable)) {
      return variableMap.get(variable);
    }
    throw new UnsupportedOperationException("I don't know this variable: " + variable);
  }

  private static HashMap<String, String> fieldMap = new HashMap<String, String>();

  {
    fieldMap.put("game.status", "boolean");
    fieldMap.put("game.name", "String");
    fieldMap.put("game.maxTries", "int");
    fieldMap.put("game.activeParticipant", "boolean");
    fieldMap.put("game.lastMove.Question", "Object");
    fieldMap.put("game.lastMove.Question.explanationGiven", "Object");
    fieldMap.put("game.lastMove.answerCorrect", "boolean");
    fieldMap.put("game.lastMove.tries", "int");
    fieldMap.put("game.lastMove.explanationGiven", "Object");
    fieldMap.put("game.tablet.currentOrientation", "Object");
    fieldMap.put("game.tablet.supposedOrientation", "Object");
    fieldMap.put("game.tablet.isAvailable", "boolean");
    fieldMap.put("game.tablet.orientation", "Object");

    fieldMap.put("gameLogic.startSession", "boolean");
    fieldMap.put("gameLogic.isTurnBased", "boolean");
    fieldMap.put("gameLogic.newRound", "boolean");

    fieldMap.put("currentUser.gameTypePlayed", "boolean");
    fieldMap.put("currentUser.id", "String");

    fieldMap.put("currentSA.hasActor", "boolean");
    fieldMap.put("currentSA.frame", "Object");
    fieldMap.put("currentSA.type", "Object");
    fieldMap.put("currentSA.what", "Object");

    fieldMap.put("_pendingTask.frame", "Object");
    fieldMap.put("_pendingTask.arg", "Object");

    fieldMap.put("last.time", "float");
  }

  @Override
  public String getFieldAccessType(String access) {
    if (fieldMap.containsKey(access)) {
      return fieldMap.get(access);
    }
    throw new UnsupportedOperationException("I don't know this field: " + access);
  }

  @Override
  public String getFunctionAccessType(String access) {
    // could be anything
    return "Object";
  }
  
  ////////////////////////////////////////////////////////////////////////////
  /////////////////// following methods still needed? ////////////////////////
  ////////////////////////////////////////////////////////////////////////////
  @Override
  public boolean existsFieldAccess(String access) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getFullVariableName(String variable) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


}
