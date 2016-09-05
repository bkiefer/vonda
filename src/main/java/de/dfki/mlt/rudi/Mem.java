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
import java.util.Set;

/**
 * this is rudimants memory, used for type checking
 *
 * @author Anna Welker
 */
public class Mem {

  // to remember those labels that get an own method
  public int ruleNumber = 1;
  private HashMap<String, HashMap<String, Integer>> ruleNums = new HashMap<>();

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

  // every toplevel rule might use variables of super rules from the super file
  private String curRule;
  private String curClass;
  public int xtImport = 0;
  private HashMap<String, HashSet<String>> neededClasses = new HashMap<>();

  public Mem() {
    environment = new ArrayList<>();
    actualValues = new HashMap<>();
    variableOrigin = new HashMap<>();
    positionAtm = -1;
    depthAtm = -1;
  }

  public void enterClass(String classname) {
    this.curClass = classname;
    this.curRule = classname;
    if (ruleNums.get(classname) == null) {
      this.ruleNums.put(classname, new HashMap<String, Integer>());
      this.neededClasses.put(classname, new HashSet<String>());
    }

  }

  public String getClassName() {
    return this.curClass;
  }

  public String getCurrentRule() {
    return this.curRule;
  }

  public void leaveClass(String oldClassName, String oldCurRule) {
    if (oldClassName != null) {
      this.curClass = oldClassName;
      if (oldCurRule != null) {
        this.curRule = oldCurRule;
      } else {
        this.curRule = oldClassName;
      }
    }
  }

  public int getCurrentDepth() {
    return depthAtm;
  }

  public void addFunction(String funcname, String functype,
          ArrayList<String> partypes, String origin) {
    functionTypes.put(funcname, functype);
    functionParTypes.put(funcname, partypes);
    // we may need this later, it doesn't harm us now
    variableOrigin.put(funcname, origin);
  }

  public boolean existsFunction(String funcname,
          ArrayList<String> partypes) {
    if (!functionTypes.containsKey(funcname)) {
      return false;
    }
    return (partypes.equals(functionParTypes.get(funcname)));
  }

  /**
   * returns null if there is no such function
   *
   * @param funcname the name of the function
   * @return its return type or null
   */
  public String getFunctionRetType(String funcname) {
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
   * @return the position in memory where the environment is stored
   */
  public int addAndEnterNewEnvironment() {
    depthAtm = getCurrentDepth() + 1;
    environment.add(new Environment(depthAtm));
    return ++positionAtm;
  }

  public void enterNextEnvironment() {
    depthAtm = getCurrentDepth() + 1;
    positionAtm++;
  }

  public void goBackToBeginning() {
    positionAtm = -1;
  }

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

  public void addRdf(String variable) {
    rdfs.add(variable);
  }

  public boolean isRdf(String variable) {
    return rdfs.contains(variable);
  }

  public void addRule(String rule) {
    if (this.depthAtm == xtImport) {
      this.ruleNumber = 1;
    } else {
      this.ruleNumber *= 2;
    }
    this.ruleNums.get(curClass).put(rule, ruleNumber);
    curRule = rule;
    this.neededClasses.put(rule, new HashSet<String>());
  }

  public Set<String> getTopLevelRules(String classname) {
    HashSet<String> rs = new HashSet<>();
    for (String k : this.ruleNums.get(classname).keySet()) {
      if (isTopLevel(k)) {
        rs.add(k);
      }
    }
    return rs;
  }

  public boolean isTopLevel(String rule) {
    if (rule.equals(curClass)) {
      return false;
    }
    int i = ruleNums.get(curClass).get(rule);
    return i == 1;
  }

  public int getRuleNumber(String rule) {
    return ruleNums.get(curClass).get(rule);
  }

  public void needsClass(String ruleclass) {
    System.out.println(ruleclass);
    this.neededClasses.get(curRule).add(ruleclass);
  }

  public Set<String> getNeededClasses(String rule) {
    return this.neededClasses.get(rule);
  }

  public boolean isExistingRule(String rule) {
    return ruleNums.containsKey(rule);
  }
}
