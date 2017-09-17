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
import de.dfki.mlt.rudimant.tree.RTBlockNode;
import de.dfki.mlt.rudimant.tree.ToplevelBlock;

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
  private ToplevelBlock classBlock;

  /** A stack of the active variable / function scopes */
  private Environment current;

  private int blockNesting = 0;

  private RdfProxy _proxy;

  private LinkedHashMap<String, Object> rulesLocMap;
  public LinkedHashMap<String, Object> currentMap;
  public List<LinkedHashMap<String,Object>> previousMaps;
  public boolean rulesLoc;

  // the rudi file that represents the Agent
  private String upperRudi;

  public Mem(RdfProxy proxy) {
    current = null;
    classBlock = null;
    _proxy = proxy;
    Type.setProxy(proxy);
  }

  /** Get the current ClassEnv (the one treated now) */
  private ClassEnv curClass() {
    return classBlock.getClassEnv();
  }

  public void setToplevelFile(String name) {
    upperRudi = name;
  }

  // TODO the next two are candidates for refactoring, check call graph!
  public String getToplevelInstance() {
    return lowerCaseFirst(upperRudi);
  }

  public boolean isNotToplevelClass() {
    return getClassName().compareToIgnoreCase(upperRudi) != 0;
  }

  public RdfProxy getProxy() {
    return _proxy;
  }

  /** Enter a new (imported) class. Automatically enters a new variable scope
   *  (Environment), too.
   *
   * @param classname of the new class
   */
  public void enterClass(String classname, ToplevelBlock node) {
    enterEnvironment(node);

    System.out.println("entering " + classname);

    if (rulesLoc)
      previousMaps.add(currentMap);

    node.setClass(classBlock, new ClassEnv(classname));
    classBlock = node;

    if (rulesLoc) {
      if (currentMap.containsKey(curClass().getName())) {
        currentMap = (LinkedHashMap) currentMap.get(curClass().getName());
      } else {
        currentMap = new LinkedHashMap<String, Object>();
      }
    }
  }

  /** Leave processing of a class. To be called at the very end of processing
   *  the top-level class or import.
   */
  public void leaveClass(ToplevelBlock node) {
    if (rulesLoc) {
      if (previousMaps.isEmpty()) {
        rulesLocMap = currentMap;
      } else {
        previousMaps.get(previousMaps.size() - 1).put(curClass().getName(), currentMap);
        currentMap = previousMaps.remove(previousMaps.size() - 1);
      }
    }

    classBlock = classBlock.getParentClass();

    leaveEnvironment(node);
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
    // The condition is: We're in the topmost environment of a file.
    return curClass().enterRule(name) && classBlock.getBindings() == current;
  }

  public void leaveRule() { curClass().leaveRule(); }

  /** Return the name of the innermost rule we're in */
  public String getCurrentRule() { return curClass().getCurrentRule(); }

  /** Return true if we are in a rule (somehow) with this name */
  public boolean isExistingRule(String rule) {
    return curClass().isActiveRule(rule);
  }

  /** Add a function/method declaration, optionally with return and parameter
   *  types. If the types are not known, it's assumed they are null.
   *
   * @param funcname The name of the function
   * @param functype the return type of the function, or null
   * @param partypes the parameter types of the function's parameters
   * @param origin first element class, second rule origin
   */
  public void addFunction(String funcname, Type functype, Type calledUpon,
          List<Type> partypes) {
    current.addFunction(funcname, functype, calledUpon, partypes,
        getClassName());
  }

  /** Return the class where this function is defined */
  public String getFunctionOrigin(String funcname, List<Type> partypes){
    String origin = current.getFunctionOrigin(funcname, partypes);
    return (getClassName().equals(origin)) ? null : origin;
  }

  /** return the return type of the given function or method, or null if there
   *  is no such function
   *
   * @param funcname   the name of the function
   * @param calledUpon the class to which the method belongs, or null if it
   *        is a pure function
   * @param partypes   the parameter type list
   * @return its return type or null
   */
  public Type getFunctionRetType(String funcname, Type calledUpon, List<Type> partypes) {
    return current.getFunctionRetType(funcname, calledUpon, partypes);
  }

  /** Add a new variable declaration, providing the variable name and type
   *
   * @param variable
   * @param type
   * @return true if the variable is not already defined, false otherwise
   */
  public boolean addVariableDeclaration(String variable, Type type) {
    if (current.isVarDefined(variable)) {
      return false;
    }
    String origin = getClassName();
    current.put(variable, type, origin);
    logger.trace("Add var {}:{} [{}]", blockNesting, variable, type);
    return true;
  }

  public boolean variableExists(String variable) {
    return current.isVarDefined(variable);
  }

  /** get the class where the given variable was defined
   *
   * @param variable a variable name
   * @return the class it came from, null if not declared
   */
  public String getVariableOriginClass(String variable) {
    String origin = current.getOrigin(variable);
    if (getClassName().equals(origin))
      return null;

    return origin;
  }

  /** get the type of the given variable
   *
   * @param variable a variable name
   * @return the variable's type
   */
  public Type getVariableType(String variable) {
    return current.getType(variable);
  }

  /** enter a new Environment (variable binding level) in TypeVisitor */
  public void enterEnvironment(RTBlockNode node) {
    logger.trace("Enter level {}", blockNesting);
    // by copying the existing environment, we avoid searching through all
    // lower environments at the cost of bigger space consumption
    Environment newEnv = node.getBindings();
    if (newEnv == null) {
      newEnv = current == null ? new Environment() : current.deepCopy();
      node.setBindings(current, newEnv);
    }
    current = newEnv;
    ++blockNesting;
  }

  /** leave an environment (variable binding level) */
  public void leaveEnvironment(RTBlockNode node) {
    // restore the values in actual that we changed
    current = node.getParentBindings();
    --blockNesting;
    logger.trace("Leave level {}", blockNesting);
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
    ToplevelBlock tb = classBlock;
    while(tb != null) {
      result.add(tb.getClassEnv().getName());
      tb = tb.getParentClass();
    }
    return result;
  }

  public void initRulesLocMap() {
    rulesLoc = true;
    rulesLocMap = new LinkedHashMap<>();
    currentMap = rulesLocMap;
    previousMaps = new ArrayList<>();
  }

  public Map getRulesLocMap() {
    return rulesLocMap;
  }
}
