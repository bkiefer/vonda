/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * this is rudimants memory, used for type checking
 *
 * @author Anna Welker
 */
public class Mem {

  private List<Environment> environment = new ArrayList<>();
  private HashMap<String, String> actualValues = new HashMap<>();
  private HashMap<String, String> variableOrigin = new HashMap<>();
  private int positionAtm = -1;
  private int depthAtm = -1;
  private HashSet<String> rdfs = new HashSet<>();

  // as this is only a way to provide mem with information about the types of
  // imported java functions, we probably don't need local namespaces
  // functions are not implemented locally in this version
  private HashMap<String, String> functionTypes = new HashMap<>();
  private HashMap<String, ArrayList<String>> functionParTypes = new HashMap<>();

  public Mem() {
    environment = new ArrayList<>();
    actualValues = new HashMap<>();
    variableOrigin = new HashMap<>();
    positionAtm = -1;
    depthAtm = -1;
  }

  public int getCurrentDepth() {
    return depthAtm;
  }

  public void addFunction(String funcname, String functype,
          ArrayList<String> partypes, String origin){
    functionTypes.put(funcname, functype);
    functionParTypes.put(funcname, partypes);
    // we may need this later, it doesn't harm us now
    variableOrigin.put(funcname, origin);
  }

  public boolean existsFunction(String funcname,
          ArrayList<String> partypes){
    if(!functionTypes.containsKey(funcname)){
      return false;
    }
    return (partypes.equals(functionParTypes.get(funcname)));
  }

  /**
   * returns null if there is no such function
   * @param funcname the name of the function
   * @return its return type or null
   */
  public String getFunctionRetType(String funcname){
    // TODO: we could also identify the function by the parameter types, is this
    // necessary?
    return functionTypes.get(funcname);
  }

  public void addElement(String variable, String type, String origin) {
    //environment.get(positionAtm).put(variable, type);
    if (actualValues.containsKey(variable)) {
      // we overwrite it
      environment.get(positionAtm).override(variable, actualValues.get(variable),
              type, variableOrigin.get(variable));
    } else {
      // we add it
      environment.get(positionAtm).put(variable, type);
    }
    variableOrigin.put(variable, origin);
    actualValues.put(variable, type);
  }

  public void decreaseDepth() {
    depthAtm--;
  }

  public boolean existsVariable(String variable) {
    return (actualValues.containsKey(variable));
  }

  /**
   * get the rule the given variable is located
   *
   * @param variable a variable
   * @return the rule it came from
   */
  public String getVariableOrigin(String variable) {
    return variableOrigin.get(variable);
  }

  /**
   * get the type of the given variable
   *
   * @param variable a variable
   * @return the variable's type
   */
  public String getVariableType(String variable) {
    return actualValues.get(variable);
    /*Environment actual = environment.get(positionAtm);
    if (!actual.containsKey(variable)) {
      int depth = actual.getDepth();
      int position = positionAtm;
      while (position >= 0) {
        if (!actual.containsKey(variable)) {
          actual = environment.get(position--);
          while (!actual.isVisibleFrom(depth) && position > 0) {
            actual = environment.get(position--);
          }
        }
        else{
          break;
        }
      }
    }
    return actual.get(variable);*/
  }

  /**
   * adds a new Environment with the given depth
   *
   * @param depth the depth the environment is supposed to lie on
   * @return the position in memory where the environment is stored
   */
  public int addAndEnterNewEnvironment(int depth) {
    environment.add(new Environment(depth));
    depthAtm = depth;
    return ++positionAtm;
  }

  public void enterNextEnvironment() {
    positionAtm++;
    depthAtm = environment.get(positionAtm).getDepth();
  }

  /*
  public Environment getFollowingEnvironment() {
    this.depthAtm = this.environment.get(++this.positionAtm).getDepth();
    return this.environment.get(this.positionAtm);
  }
   */
  public void leaveEnvironment() {
    // restore the values in actual that we changed
    environment.get(positionAtm).restoreOld(this);
    decreaseDepth();
    // this is no longer needed because it's handled in add&Enter
//    this.positionAtm++;
//    this.depthAtm = this.environment.get(this.positionAtm).getDepth();
  }

  public void eraseLocalV(String variable) {
    actualValues.remove(variable);
    variableOrigin.remove(variable);
  }

  public void restoreLocalV(String variable, String type, String origin) {
    actualValues.put(variable, type);
    variableOrigin.put(variable, origin);
  }

  public void addRdf(String variable){
    rdfs.add(variable);
  }

  public boolean isRdf(String variable){
    return rdfs.contains(variable);
  }
}
