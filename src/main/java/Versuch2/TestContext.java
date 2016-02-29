/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2;

import Versuch1.RobotContext;
import Versuch1.Type;
import Versuch2.abstractTree.AbstractType;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author anna
 */
public class TestContext implements RobotContext{

  @Override
  public String beforeClassName() {
    return "/**this is the license header\n**/\n\n";
  }

  @Override
  public String afterClassName() {
    return ("  // GameData is an RDF class proxy in java code\n"
              + "  // TODO: The type of game must specified, can not be inferred, same for currentUser\n"
              + "  // and speech act\n"
              + "  // TODO: what about relational properties aka multi-valued features\n"
              + "  // TODO: possibly gamePlayed must be a custom method / ASK query\n\n"
              + "  GameData game;"
              + "\n"
              + "  // User is an RDF class proxy in java code"
              + "\n"
              + "  User currentUser;"
              + "\n\n  // This must be a real class"
              + "\n"
              + "  GameLogic gameLogic;"
              + "\n\n  // SpeechActs, RDF proxies"
              + "\n"
              + "  DialogueAct myLastSA, currentSA;"
              + "\n\n"
              + "  private void computeProxies() "
              + "{\n"
              + "    // TODO: ... missing definition generating this code\n"
              + "  }\n\n"
              + "  public void mainLoop() {\n"
              + "    computeProxies();");
  }

  @Override
  public String atEndOfFile() {
    return "}\n";
  }

  @Override
  public boolean isGlobalVariable(String variable) {
    // for the moment, assume the user knows what he's doing
    if(variableMap.containsKey(variable)){
      return true;
    }
    if(Pattern.matches("([A-Z]|_)+", variable)){
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
    if(variableMap.containsKey(variable)){
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
    if (fieldMap.containsKey(access)){
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
  public boolean testFunctionArguments(List<Type> arguments) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type getFunctionReturn(String functionname) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean existsFieldAccess(String access) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type getFieldType(String access) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getFullVariableName(String variable) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
