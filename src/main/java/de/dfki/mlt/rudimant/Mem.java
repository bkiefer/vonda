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


  // the rudi file that represents the Agent
  private String upperRudi;

  public Mem(RdfProxy proxy) {
    environment = new ArrayDeque<>();
    classes = new ArrayDeque<>();
    _proxy = proxy;
    Type.setProxy(proxy);
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

  /** Get the current ClassEnv (the one treated now) */
  private ClassEnv curClass() {
    return classes.peekFirst();
  }

  /** Enter a new (imported) class. Automatically enters a new variable scope
   *  (Environment), too.
   *
   * @param classname of the new class
   */
  public void enterClass(String classname) {
    enterEnvironment();
    classes.push(new ClassEnv(classname, environment.size()));
  }

  /** Leave processing of a class. To be called at the very end of processing
   *  the top-level class or import.
   */
  public void leaveClass() {
    classes.pop();
    leaveEnvironment();
  }

  public boolean isActiveRule(String name) {
    return curClass().isActiveRule(name);
  }

  /** get the name of the current class
   *
   * @return The name of the current class to be generated.
   */
  public String getClassName() { return curClass().getName(); }


  public boolean enterRule(String name) {
    // TODO: The condition must be: We're in the topmost
    // environment of a file. This is not the nicest solution
    return curClass().enterRule(name)
        && environment.size() == curClass().getClassEnvironment();
  }

  public void leaveRule() { curClass().leaveRule(); }

  /** Return the name of the innermost rule we're in */
  public String getCurrentRule() { return curClass().getCurrentRule(); }

  /** Return true if we are in a rule (somehow) with this name */
  public boolean isExistingRule(String rule) {
    return curClass().isActiveRule(rule);
  }

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

  /** Return the class where this function is defined */
  public String getFunctionOrigin(String funcname, List<String> partypes){
    String origin = current().getFunctionOrigin(funcname, partypes, this);
    if (getClassName().equals(origin))
      return null;

    return origin;
  }

  /** Return true if a function with this name is defined */
  public boolean existsFunction(String funcname, List<String> partypes) {
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
    String origin = getClassName();
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
    if (origin.equals(getClassName()))
      return null;

    return origin;
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

  public void addImport(String importName, String conargs) {
    String importClassName = capitalize(importName);
    curClass().addRuleOrImport(importClassName + " "
            + importName + " = new " + importClassName + "(");
  }

  /** Return the set of needed classes to generate the proper import statements
   * @return A set of all needed external classes (because of variable or
   * function definitions from this class).
   */
  public Set<String> getNeededClasses() {
    // return all classes above me (import chain), and this class
    Set<String> result = new HashSet<>();
    for(ClassEnv env : classes) {
      result.add(env.getName());
    }
    return result;
  }

  public ExpUSingleValue degradeToString(ExpUVariable variable){
    return new ExpUSingleValue(variable.toString(), "String");
  }
}
