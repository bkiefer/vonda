/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author anna
 */
public class ExampleContext implements RobotContext{

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
    return "\n  }\n";
  }

  @Override
  public boolean testFunctionArguments(List<Type> arguments) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type getFunctionReturn(String functionname) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  private static HashMap<String, Type> fieldMap = new HashMap<String, Type>();
  {
    fieldMap.put("game.status", Type.STRING);
    fieldMap.put("initiated", Type.STRING);
  }
  
  @Override
  public boolean existsFieldAccess(String access) {
    return fieldMap.containsKey(access);
  }

  @Override
  public Type getFieldType(String access) {
    if(existsFieldAccess(access)){
      return fieldMap.get(access);
    }
    return null;
  }
  
}
