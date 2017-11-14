/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.compiler.Utils.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.common.BasicInfo;
import de.dfki.mlt.rudimant.common.ErrorInfo;
import de.dfki.mlt.rudimant.common.ImportInfo;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.compiler.tree.RTBlockNode;
import de.dfki.mlt.rudimant.compiler.tree.ToplevelBlock;

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

  /** Info about all imports and rules (tree) */
  private BasicInfo root;
  private int ruleId;
  private boolean doingTypeCheck;

  // the class that should be extended by the rudi files to fill them into a project
  private final String wrapperClass;

  // the rudi file that represents the Agent
  private ClassEnv topLevelClass;

  public Mem(RdfProxy proxy, String wrapper) {
    current = null;
    classBlock = null;
    _proxy = proxy;
    Type.setProxy(proxy);
    ruleId = 0;
    doingTypeCheck = true;
    wrapperClass = wrapper;
  }

  /** Get the current ClassEnv (the one treated now) */
  private ClassEnv curClass() {
    return classBlock.getClassEnv();
  }

  private void setToplevelFile(ClassEnv env) {
    topLevelClass = env;
    String[] path = {};
    root = new ImportInfo(env.getName(), path, -1, null);
  }

  public String getTopLevelClass() {
    return topLevelClass.getName();
  }

  public String getTopLevelPackage() {
    return getPackageName(topLevelClass.packageSpec());
  }

  public String getWrapperClass() {
    return wrapperClass;
  }

  /** Return the package of the class currently processed */
  public String getPackage() {
    return getPackageName(curClass().packageSpec());
  }

  // TODO the next two are candidates for refactoring, check call graph!
  public String getToplevelInstance() {
    return lowerCaseFirst(getTopLevelClass());
  }

  public boolean isNotToplevelClass() {
    return classBlock.getParentClass() != null;
  }

  public RdfProxy getProxy() {
    return _proxy;
  }

  /** Enter a new (imported) class. Automatically enters a new variable scope
   *  (Environment), too.
   *
   * @param classname of the new class
   */
  public void enterClass(String classname, String[] pkg) {
    ClassEnv newEnv = new ClassEnv(classname, pkg);
    if (classBlock == null)
      setToplevelFile(newEnv);
    ToplevelBlock node = new ToplevelBlock();
    enterEnvironment(node);

    node.setClass(classBlock, newEnv);
    classBlock = node;
  }

  /** Leave processing of a class. To be called at the very end of processing
   *  the top-level class or import.
   */
  public void leaveClass() {
    ToplevelBlock node = classBlock;
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


  public void enterGeneration() {
    doingTypeCheck = false;
  }

  public void leaveGeneration() {
    doingTypeCheck = true;
  }


  public boolean enterRule(String name, Location loc) {
    if (doingTypeCheck)
      root = new RuleInfo(++ruleId, name, loc.getLineNumber(), root);
    // The condition is: We're in the topmost environment of a file.
    return curClass().enterRule(name) && classBlock.getBindings() == current;
  }

  public void leaveRule() {
    if (doingTypeCheck)
      root = root.getParent();
    curClass().leaveRule();
  }

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

  // TODO: REFACTOR INTO ENTERCLASS/LEAVECLASS
  public void addImport(String name, String[] pathSpec, Location loc) {
    root = new ImportInfo(name, pathSpec, loc.getLineNumber(), root);
  }

  public void leaveImport() {
    root = root.getParent();
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

  public BasicInfo getInfo() {
    return root;
  }

  public void registerError(String errorMessage, Location location) {
    BasicInfo current = root;
    while (! (current instanceof ImportInfo))
      current = current.getParent();
    ImportInfo info = (ImportInfo)current;
    info.getErrors().add(new ErrorInfo(errorMessage, location));
  }
}