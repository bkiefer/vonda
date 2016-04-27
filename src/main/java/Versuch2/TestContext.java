/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2;

import Versuch2.abstractTree.AbstractType;
import java.io.IOException;
import java.util.HashMap;
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
  private boolean log;
  private String boolLog;

  public TestContext(boolean log) {
    this.log = log;
    this.boolLog = "";
  }

  @Override
  public void doLog(String toLog) {
    if (!this.log) {
      return;
    }
    this.boolLog += "boolLogger.info(" + toLog + ");\n";
  }
  
  @Override
  public String getLog(){
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
            + "import java.util.logging.SimpleFormatter;";
  }

  @Override
  public String afterClassName() {
    return ("\n"
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

  private static HashMap<String, AbstractType> variableMap = new HashMap<String, AbstractType>();

  {
    variableMap.put("myLastSA", AbstractType.MAGIC);
    variableMap.put("currentSA", AbstractType.MAGIC);
    variableMap.put("lastSA", AbstractType.MAGIC);
    variableMap.put("initiated", AbstractType.STRING);
    variableMap.put("startSession", AbstractType.STRING);
    variableMap.put("game", AbstractType.OBJECT);
    variableMap.put("Inform", AbstractType.OBJECT);
    variableMap.put("Confirm", AbstractType.OBJECT);
    variableMap.put("_pendingTask", AbstractType.OBJECT);
    variableMap.put("MAX_WAIT_FOR_TABLET", AbstractType.FLOAT);
    variableMap.put("currentTime", AbstractType.FLOAT);
    variableMap.put("POINTS_TO_ME", AbstractType.BOOL);
    variableMap.put("I_MYSELF", AbstractType.OBJECT);
    variableMap.put("inSession", AbstractType.OBJECT);
    variableMap.put("questionAsked", AbstractType.BOOL);
    variableMap.put("Question", AbstractType.OBJECT);
    variableMap.put("Answers", AbstractType.OBJECT);
    variableMap.put("Solution", AbstractType.OBJECT);
    variableMap.put("turnFinished", AbstractType.BOOL);
    variableMap.put("answerGiven", AbstractType.OBJECT);
    variableMap.put("no", AbstractType.OBJECT);
    variableMap.put("proposed", AbstractType.OBJECT);
    variableMap.put("yes", AbstractType.OBJECT);
  }

  @Override
  public AbstractType getVariableType(String variable) {
    if (variableMap.containsKey(variable)) {
      return variableMap.get(variable);
    }
    throw new UnsupportedOperationException("I don't know this variable: " + variable);
  }

  private static HashMap<String, AbstractType> fieldMap = new HashMap<String, AbstractType>();

  {
    fieldMap.put("game.status", AbstractType.STRING);
    fieldMap.put("game.name", AbstractType.STRING);
    fieldMap.put("game.maxTries", AbstractType.INT);
    fieldMap.put("game.activeParticipant", AbstractType.BOOL);
    fieldMap.put("game.lastMove.Question", AbstractType.OBJECT);
    fieldMap.put("game.lastMove.Question.explanationGiven", AbstractType.OBJECT);
    fieldMap.put("game.lastMove.answerCorrect", AbstractType.BOOL);
    fieldMap.put("game.lastMove.tries", AbstractType.INT);
    fieldMap.put("game.lastMove.explanationGiven", AbstractType.OBJECT);
    fieldMap.put("game.tablet.currentOrientation", AbstractType.OBJECT);
    fieldMap.put("game.tablet.supposedOrientation", AbstractType.OBJECT);
    fieldMap.put("game.tablet.isAvailable", AbstractType.BOOL);
    fieldMap.put("game.tablet.orientation", AbstractType.OBJECT);

    fieldMap.put("gameLogic.startSession", AbstractType.BOOL);
    fieldMap.put("gameLogic.isTurnBased", AbstractType.BOOL);
    fieldMap.put("gameLogic.newRound", AbstractType.BOOL);

    fieldMap.put("currentUser.gameTypePlayed", AbstractType.BOOL);
    fieldMap.put("currentUser.id", AbstractType.STRING);

    fieldMap.put("currentSA.hasActor", AbstractType.BOOL);
    fieldMap.put("currentSA.frame", AbstractType.OBJECT);
    fieldMap.put("currentSA.type", AbstractType.OBJECT);
    fieldMap.put("currentSA.what", AbstractType.OBJECT);

    fieldMap.put("_pendingTask.frame", AbstractType.OBJECT);
    fieldMap.put("_pendingTask.arg", AbstractType.OBJECT);

    fieldMap.put("last.time", AbstractType.FLOAT);
  }

  @Override
  public AbstractType getFieldAccessType(String access) {
    if (fieldMap.containsKey(access)) {
      return fieldMap.get(access);
    }
    throw new UnsupportedOperationException("I don't know this field: " + access);
  }

  @Override
  public AbstractType getFunctionAccessType(String access) {
    // could be anything
    return AbstractType.OBJECT;
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
