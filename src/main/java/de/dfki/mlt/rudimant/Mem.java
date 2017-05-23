/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.abstractTree.ExpUSingleValue;
import de.dfki.mlt.rudimant.abstractTree.ExpUVariable;

/**
 * this is rudimants memory, used for type checking
 *
 * @author Anna Welker
 */
public class Mem {

  private static Logger logger = LoggerFactory.getLogger(Mem.class);

  // to remember those labels that get an own method
  public int ruleNumber = 1;
  private HashMap<String, HashMap<String, Integer>> ruleNums = new HashMap<>();

  final private Deque<Environment> environment;

  private Environment current;

  // every toplevel rule might use variables of super rules from the super file
  private String curRule;
  private String curClass;
  private String curTopRule;
  private HashMap<String, List<String>> neededClasses = new HashMap<>();

  // remember the toplevel rules and imports in the correct order
  private Map<String, List<String>> rulesAndImports;

  private RdfProxy _proxy;

  // to be set by grammarfile if a toplevel rule is expected
  public boolean ontop = false;

  /**
   * to be able to not go down into environments for our different
   * initialization files
   **/
  boolean initializing = false;

  // the rudi file that represents the Agent
  private String upperRudi;

  public Mem(RdfProxy proxy) {
    environment = new ArrayDeque<>();
    rulesAndImports = new HashMap<>();
    _proxy = proxy;
    Type.setProxy(proxy);
    current = new Environment();
    // enter our very first environment,
    this.enterEnvironment();
  }

  public void setToplevelFile(String name) {
    upperRudi = name;
  }

  public String getToplevelInstance() {
    return upperRudi.substring(0, 1).toLowerCase() + upperRudi.substring(1);
  }

  public RdfProxy getProxy() {
    return _proxy;
  }

  /**
   * ATTENTION!! This method is only to be used to set a classname for testing
   * purposes!!!!!!!!!!!!!!
   * @param name
   */
  public void setClassName(String name){
    this.curClass = name;
  }

  /**
   * enter a new (imported) class. please store the old values of
   * getcurrentClass() and getCurrentRule in your visitor and use leaveClass()
   * to give them back to the memory after the new class has been handled
   *
   * @param classname of the new class
   */
  public void enterClass(String classname) {
    this.curClass = classname;
    this.curRule = classname;
    if (ruleNums.get(classname) == null) {
      this.ruleNums.put(classname, new HashMap<String, Integer>());
      this.neededClasses.put(classname, new ArrayList<String>());
      this.rulesAndImports.put(classname, new ArrayList<String>());
    }
    this.needsClass(classname, this.upperRudi);
  }

  /**
   * get the name of the current class
   *
   * @return
   */
  public String getClassName() {
    if(this.curClass != null){
      return this.curClass;
    } else {
      return this.upperRudi;
    }
  }

  /**
   * get the name of the current rule
   *
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

  /** Add a function/method declaration, optionally with return and parameter
   *  types. If the types are not known, it's assumed they are null.
   *
   * @param funcname The name of the function
   * @param functype the return type of the function, or null
   * @param partypes the parameter types of the function's parameters
   * @param origin first element class, second rule origin
   */
  public void addFunction(String funcname, String functype, String calledUpon,
          List<String> partypes, String origin) {
    if(funcname.equals("equals") || funcname.equals("startsWith")
            || funcname.equals("endsWith") || funcname.equals("substring")
            || funcname.equals("info")){
      origin = null;
    } else if(initializing){
      origin = upperRudi;
    }
    this.current.addFunction(funcname, functype, calledUpon, partypes, origin, this);
  }

  public String getFunctionOrigin(String funcname, List<String> partypes){
    return current.getFunctionOrigin(funcname, partypes, this);
  }

  public boolean existsFunction(String funcname,
          List<String> partypes) {
    return current.existsFunction(funcname, partypes, this);
  }

  /**
   * returns null if there is no such function
   *
   * @param funcname the name of the function
   * @return its return type or null
   */
  public String getFunctionRetType(String funcname, String calledUpon, List<String> partypes) {
    return current.getFunctionRetType(funcname, calledUpon, partypes, this);
  }

  /**
   *
   * @param variable
   * @param type
   * @param origin first element class, second rule origin
   * @return
   */
  public boolean addVariableDeclaration(String variable, String type, String origin) {
    if (current.containsKey(variable)) {
      return false;
    }
    if(initializing){
      origin = upperRudi;
    }
    type = Type.checkRdf(type);
    current.put(variable, type, origin);
    logger.trace("Add var {}:{} [{}]", environment.size(), variable, type);
    return true;
  }

  public boolean variableExists(String variable) {
    return (current.containsKey(variable));
  }

  /**
   * get the class the given variable is located
   *
   * @param variable a variable
   * @return the class it came from, null if not declared
   */
  public String getVariableOriginClass(String variable) {
    return current.getOrigin(variable);
  }

  /**
   * get the type of the given variable
   *
   * @param variable a variable
   * @return the variable's type
   */
  public String getVariableType(String variable) {
    return current.get(variable);
  }

  /**
   * adds a new Environment with the given depth
   */
  public void enterEnvironment() {
    logger.trace("Enter level {}", environment.size());
    environment.push(current);
    current = current.deepCopy();
  }

  public void leaveEnvironment() {
    logger.trace("Leave level {}", environment.size());
    // restore the values in actual that we changed
    current = environment.isEmpty() ? null : environment.pop();
  }

  /**
   * add the rule to the memory
   *
   * @param rule
   * @param toplevel
   * @return the rule's number, to be used in bitwise if markers
   */
  public void addRule(String rule) {
    if (ontop) {
      ontop = false;
      this.rulesAndImports.get(this.curClass).add(rule);
      this.ruleNumber = 1;
      curTopRule = rule;
    } else {
      this.ruleNumber *= 2;
    }
    this.ruleNums.get(curClass).put(rule, ruleNumber);
    curRule = rule;
    this.neededClasses.put(rule, new ArrayList<String>());
  }

  public void addImport(String importName, String conargs) {
    String importClassName = importName.substring(0, 1).toUpperCase() + importName.substring(1);
    this.rulesAndImports.get(this.curClass).add(importClassName + " "
            + importName + " = new " + importClassName + "(");
  }

  /**
   * not only toplevel rules, but also import calls
   *
   * @param classname
   * @return
   */
  public List<String> getToplevelCalls(String classname) {
    return this.rulesAndImports.get(classname);
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
   * tell the memory to remember that the given rule/class needs an instance of
   * the given class
   *
   * @param rule a rule or class
   * @param ruleclass the class needed
   */
  public void needsClass(String rule, String ruleclass) {
    if(this.curClass.toLowerCase().equals(ruleclass.toLowerCase())
            || this.neededClasses.get(rule).contains(ruleclass)){
      return;
    }
    this.neededClasses.get(rule).add(ruleclass);
  }

  public List<String> getNeededClasses(String ruleOrClass) {
    return this.neededClasses.get(ruleOrClass);
  }

  public boolean isExistingRule(String rule) {
    return ruleNums.get(this.curClass).containsKey(rule);
  }

  public ExpUSingleValue degradeToString(ExpUVariable variable){
    return new ExpUSingleValue(variable.toString(), "String");
  }
}
