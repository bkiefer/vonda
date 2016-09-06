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
  private HashMap<String, ArrayList<String>> variableOrigin = new HashMap<>();
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
  private String curTopRule;
  private HashMap<String, HashSet<String>> neededClasses = new HashMap<>();

  public Mem() {
    environment = new ArrayList<>();
    actualValues = new HashMap<>();
    variableOrigin = new HashMap<>();
    positionAtm = -1;
    depthAtm = -1;
  }

  /**
   * enter a new (imported) class. please store the old values of getcurrentClass()
   * and getCurrentRule in your visitor and use leaveClass() to give them back to the memory
   * after the new class has been handled
   * @param classname of the new class
   */
  public void enterClass(String classname) {
    this.curClass = classname;
    this.curRule = classname;
    if (ruleNums.get(classname) == null) {
      this.ruleNums.put(classname, new HashMap<String, Integer>());
      this.neededClasses.put(classname, new HashSet<String>());
    }

  }

  /**
   * get the name of the current class
   * @return
   */
  public String getClassName() {
    return this.curClass;
  }

  /**
   * get the name of the current rule
   * @return
   */
  public String getCurrentRule() {
    return this.curRule;
  }

  public String getCurrentTopRule() {
    if (this.curTopRule == null) {
      return this.curClass;
    }
    return this.curTopRule;
  }

  public void leaveClass(String oldClassName, String oldCurRule, String oldCurTRule) {
    if (oldClassName != null) {
      this.curClass = oldClassName;
      if (oldCurRule != null) {
        this.curRule = oldCurRule;
        this.curTopRule = oldCurTRule;
      } else {
        this.curRule = oldClassName;
        this.curTopRule = oldClassName;
      }
    }
  }

  /**
   *
   * @param funcname
   * @param functype
   * @param partypes
   * @param origin first element class, second rule origin
   */
  public void addFunction(String funcname, String functype,
          ArrayList<String> partypes, ArrayList<String> origin) {
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

  /**
   *
   * @param variable
   * @param type
   * @param origin first element class, second rule origin
   * @return
   */
  public boolean addElement(String variable, String type, ArrayList<String> origin) {
    //environment.get(positionAtm).put(variable, type);
    if (actualValues.containsKey(variable)) {
      //System.out.println("rudi thought about " + variable);
      // if this variable was introduced elsewhere, it could already have been overridden here;
      // if we just introduced it in this toplevel rule, it shouldn't be
      // redeclared, too
      if (environment.get(positionAtm).overrides(variable)){
              //|| this.getVariableOriginTRule(variable).equals(this.curRule)) {
        return false;
      }
      // we overwrite it
      environment.get(positionAtm).override(variable, actualValues.get(variable),
              type, variableOrigin.get(variable));
    } else {
      // we add it
      environment.get(positionAtm).put(variable, type);
    }
    variableOrigin.put(variable, origin);
    actualValues.put(variable, type);
    return true;
  }

  public boolean existsVariable(String variable) {
    return (actualValues.containsKey(variable));
  }

  /**
   * get the class the given variable is located
   *
   * @param variable a variable
   * @return the class it came from, null if not declared
   */
  public String getVariableOriginClass(String variable) {
    //System.out.println(variable);
    if(variableOrigin.get(variable) == null){
      // then the variable was never declared, the type checking should throw sth
      return null;
    }
    return variableOrigin.get(variable).get(0);
  }

  /**
   * get the rule the given variable is located
   *
   * @param variable a variable
   * @return the toplevel rule it came from
   */
  public String getVariableOriginTRule(String variable) {
    return variableOrigin.get(variable).get(1);
  }

  /**
   * get the type of the given variable
   *
   * @param variable a variable
   * @return the variable's type
   */
  public String getVariableType(String variable) {
    return actualValues.get(variable);
  }

  /**
   * adds a new Environment with the given depth
   *
   * @return the position in memory where the environment is stored
   */
  public int addAndEnterNewEnvironment() {
    depthAtm += 1;
    environment.add(new Environment(depthAtm));
    return ++positionAtm;
  }

  public void leaveEnvironment() {
    // restore the values in actual that we changed
    environment.get(positionAtm).restoreOld(this);
    this.depthAtm--;
  }

  public void eraseLocalV(String variable) {
    actualValues.remove(variable);
    variableOrigin.remove(variable);
  }

  public void restoreLocalV(String variable, String type, ArrayList<String> origin) {
    actualValues.put(variable, type);
    variableOrigin.put(variable, origin);
  }

  public void addRdf(String variable) {
    rdfs.add(variable);
  }

  public boolean isRdf(String variable) {
    return rdfs.contains(variable);
  }

  /**
   * add the rule to the memory
   * @param rule
   * @return the rule's number, to be used in bitwise if markers
   */
  public int addRule(String rule, boolean toplevel) {
    if (toplevel) {
      this.ruleNumber = 1;
      curTopRule = rule;
    } else {
      this.ruleNumber *= 2;
    }
    this.ruleNums.get(curClass).put(rule, ruleNumber);
    curRule = rule;
    this.neededClasses.put(rule, new HashSet<String>());
    return this.ruleNumber;
  }

  /**
   * return all toplevel rules contained in the given class
   * @param classname
   * @return
   */
  public Set<String> getTopLevelRules(String classname) {
    HashSet<String> rs = new HashSet<>();
    for (String k : this.ruleNums.get(classname).keySet()) {
      if (this.ruleNums.get(classname).get(k) == 1) {
        rs.add(k);
      }
    }
    return rs;
  }

  /**
   * tell the memory to remember that the given rule/class needs an instance of the given class
   * @param rule a rule or class
   * @param ruleclass the class needed
   */
  public void needsClass(String rule, String ruleclass) {
    //System.out.println(ruleclass);
    this.neededClasses.get(rule).add(ruleclass);
  }

  public Set<String> getNeededClasses(String ruleOrClass) {
    return this.neededClasses.get(ruleOrClass);
  }

  public boolean isExistingRule(String rule) {
    return ruleNums.containsKey(rule);
  }
}
