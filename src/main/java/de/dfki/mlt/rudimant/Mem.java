/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Utils.*;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.tree.ExpUSingleValue;
import de.dfki.mlt.rudimant.tree.ExpUVariable;

/**
 * this is rudimants memory, used for type checking
 *
 * @author Anna Welker
 */
public class Mem {

  private static Logger logger = LoggerFactory.getLogger(Mem.class);

  /** A stack of active class environments, representing the files that are
   *  currently processed.
   *
   *  When processing starts, the ClassEnv for the first source file must be
   *  added, then the Agent.rudi must be processed as if it was part of this
   *  file, and then normal processing commences.
   */
  final private Deque<ClassEnv> classes;

  /** A stack of the active variable / function scopes */
  final private Deque<Environment> environment;

  private RdfProxy _proxy;

  // to be set by grammarfile if a toplevel rule is expected
  public boolean ontop = false;

  /**
   * to be able to not go down into environments for our different
   * initialization files
   **/
  //boolean initializing = false;

  // the rudi file that represents the Agent
  private String upperRudi;

  public Mem(RdfProxy proxy) {
    environment = new ArrayDeque<>();
    classes = new ArrayDeque<>();
    _proxy = proxy;
    Type.setProxy(proxy);
    // current = new Environment();
    // enter our very first environment,
    // enterEnvironment();
  }

  public void setToplevelFile(String name) {
    upperRudi = name;
  }

  public String getToplevelInstance() {
    return lowerCaseFirst(upperRudi);
  }

  public RdfProxy getProxy() {
    return _proxy;
  }

  /**
   * ATTENTION!! This method is only to be used to set a classname for testing
   * purposes!!!!!!!!!!!!!!
   * @param name
   *
  public void setClassName(String name){
    classes.push(new ClassEnv(name));// curClass = name;
  }*/

  /** Get the current ClassEnv (the one treated now) */
  private ClassEnv curClass() {
    return classes.peekFirst();
  }

  /**
   * enter a new (imported) class. please store the old values of
   * getcurrentClass() and getCurrentRule in your visitor and use leaveClass()
   * to give them back to the memory after the new class has been handled
   *
   * @param classname of the new class
   */
  public void enterClass(String classname) {
    enterEnvironment();
    classes.push(new ClassEnv(classname, environment.size()));
    // curRule = classname;
    curClass().needsClass(upperRudi);
  }

  /**
   * get the name of the current class
   *
   * @return
   */
  public String getClassName() {
    if(! classes.isEmpty()){
      return curClass().getName();
    } else {
      return upperRudi;
    }
  }

  public void leaveClass() {
    classes.pop();
    leaveEnvironment();
  }
  
  public boolean isActiveRule(String name) {
	return curClass().isActiveRule(name);
  }

  public boolean enterRule(String name) {
    // TODO: The condition must be: We're in the topmost
    // environment of a file. This is not the nicest solution
    return curClass().enterRule(name)
        && environment.size() == curClass().getClassEnvironment();
  }

  public void leaveRule() { curClass().leaveRule(); }

  public String getCurrentRule() { return curClass().getCurrentRule(); }

  /*
  private static final Set<String> KNOWN_FUNCTIONS =
      new HashSet<>(Arrays.asList(new String[] {
          "equals", "startsWith", "endsWith", "substring", "info"
      }));
   */
  /** Add a function/method declaration, optionally with return and parameter
   *  types. If the types are not known, it's assumed they are null.
   *
   * @param funcname The name of the function
   * @param functype the return type of the function, or null
   * @param partypes the parameter types of the function's parameters
   * @param origin first element class, second rule origin
   */
  public void addFunction(String funcname, String functype, String calledUpon,
          List<String> partypes) {
    String origin = null;
    //if(! KNOWN_FUNCTIONS.contains(funcname)) {
    origin = getClassName();
    //}
    current().addFunction(funcname, functype, calledUpon, partypes, origin, this);
  }

  public String getFunctionOrigin(String funcname, List<String> partypes){
    String origin = current().getFunctionOrigin(funcname, partypes, this);
    if (origin != null && ! origin.equals(getClassName())) {
      curClass().needsClass(origin);
      return origin;
    }
    return null;
  }

  public boolean existsFunction(String funcname,
          List<String> partypes) {
    return current().existsFunction(funcname, partypes, this);
  }

  /**
   * returns null if there is no such function
   *
   * @param funcname the name of the function
   * @return its return type or null
   */
  public String getFunctionRetType(String funcname, String calledUpon, List<String> partypes) {
    return current().getFunctionRetType(funcname, calledUpon, partypes, this);
  }

  /**
   *
   * @param variable
   * @param type
   * @param origin first element class, second rule origin
   * @return
   */
  public boolean addVariableDeclaration(String variable, String type) {
    if (current().isVarDefined(variable)) {
      return false;
    }
    // TODO: GET RID OF INITIALIZING IF POSSIBLE
    String origin = (classes.size() == 1) ? upperRudi : getClassName();
    type = Type.checkRdf(type);
    current().put(variable, type, origin);
    logger.trace("Add var {}:{} [{}]", environment.size(), variable, type);
    return true;
  }

  public boolean variableExists(String variable) {
    return (current().isVarDefined(variable));
  }

  /**
   * get the class the given variable is located
   *
   * @param variable a variable
   * @return the class it came from, null if not declared
   */
  public String getVariableOriginClass(String variable) {
    String origin = current().getOrigin(variable);
    if (origin != null && ! origin.equals(getClassName())) {
      curClass().needsClass(origin);
      return origin;
    }
    return null;
  }

  /**
   * get the type of the given variable
   *
   * @param variable a variable
   * @return the variable's type
   */
  public String getVariableType(String variable) {
    return current().getType(variable);
  }

  private Environment current() {
    return environment.peekFirst();
  }

  /**
   * adds a new Environment with the given depth
   */
  public void enterEnvironment() {
    logger.trace("Enter level {}", environment.size());
    // by copying the existing environment, we avoid searching through all
    // lower environments at the cost of bigger space consumption
    environment.push(
        environment.isEmpty() ? new Environment() : current().deepCopy());
  }

  public void leaveEnvironment() {
    // restore the values in actual that we changed
    environment.pop();
    logger.trace("Leave level {}", environment.size());
  }

  /**
   * add the rule to the memory
   *
   * @param rule
   * @param toplevel
   * @return the rule's number, to be used in bitwise if markers
   *
  public void addRule(String rule) {
    if (ontop) {
      ontop = false;
      curClass().addRuleOrImport(rule);
    }
    //curRule = rule;
    // neededClasses.put(rule, new ArrayList<String>());
  }*/

  public void addImport(String importName, String conargs) {
    String importClassName = capitalize(importName);
    curClass().addRuleOrImport(importClassName + " "
            + importName + " = new " + importClassName + "(");
  }

  /**
   * not only toplevel rules, but also import calls
   *
   * @param classname
   * @return
   */
  public List<String> getToplevelCalls(String classname) {
    return curClass().getRulesAndImports();
  }

  /**
   * return all toplevel rules contained in the given class
   * @param classname
   * @return
   *
  public Set<String> getTopLevelRules(String classname) {
    HashSet<String> rs = new HashSet<>();
    for (String k : ruleNums.get(classname).keySet()) {
      if (ruleNums.get(classname).get(k) == 1) {
        rs.add(k);
      }
    }
    return rs;
  }*/


  /**
   * tell the memory to remember that the given rule/class needs an instance of
   * the given class
   *
   * @param rule a rule or class
   * @param ruleclass the class needed
   */
  public void needsClass(String rule, String ruleclass) {

    curClass().needsClass(ruleclass);
  }

  /** Return the set of needed classes to generate the proper import statements
   *  TODO: IS THE CLASS NAME PARAMETER REALLY NEEDED?
   * @param ruleOrClass
   * @return
   */
  public Set<String> getNeededClasses() {
    return curClass().getNeededClasses();
  }

  public boolean isExistingRule(String rule) {
    return curClass().isActiveRule(rule);
  }

  public ExpUSingleValue degradeToString(ExpUVariable variable){
    return new ExpUSingleValue(variable.toString(), "String");
  }
}
