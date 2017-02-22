/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;
import de.dfki.mlt.rudimant.abstractTree.USingleValue;
import de.dfki.mlt.rudimant.abstractTree.UVariable;

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
  private HashMap<String, HashSet<String>> neededClasses = new HashMap<>();

  // remember the toplevel rules and imports in the correct order
  private Map<String, List<String>> rulesAndImports;

  private RdfProxy _proxy;

  /**
   * to be able to not go down into environments for our different
   * initialization files
   **/
  boolean initializing = false;

  public Mem(RdfProxy proxy) {
    environment = new ArrayDeque<>();
    rulesAndImports = new HashMap<>();
    _proxy = proxy;
    current = new Environment();
    // enter our very first environment,
    this.enterEnvironment();
  }

  static Map<String, Long> typeCodes = new HashMap<>();

  static final long JAVA_TYPE = 0x10;
  static {
    typeCodes.put("Object",         0x111l);
    typeCodes.put("String",           0x1l);
    typeCodes.put("Rdf",             0x10l);
    typeCodes.put("double",     0x1000000l);
    typeCodes.put("Double",     0x1000100l);
    typeCodes.put("float",      0x1100000l);
    typeCodes.put("Float",      0x1100100l);
    typeCodes.put("int",        0x1110000l);
    typeCodes.put("Integer",    0x1110100l);
    typeCodes.put("boolean",   0x10000000l);
    typeCodes.put("Boolean",   0x10000100l);
    typeCodes.put("null",     0x100000000l);
  }

  /** Indicates if this type should be compared with == or equals */
  public static boolean isPODType(String name) {
    Long code = typeCodes.get(name);
    return code != null && (code & 0x111100000l) != 0;
  }

  public static boolean isRdfType(String type) {
    return (type != null && type.charAt(0) == '<');
  }

  /**
   * returns a correct java type for xsd types
   * @param something
   * @return
   */
  public String convertXsdType(String something){
    String ret = RdfClass.xsdToJavaPod(something);
    if (ret != null) return ret;
    return something;
  }

  /**
   * returns a correct java type for whatever input
   * @param something
   * @return
   */
  public String convertRdfType(String something){
    something = convertXsdType(something);
    if(!isRdfType(something)) return something;
    return (DIALOGUE_ACT_TYPE.equals(something)) ? "DialogueAct" : "Rdf";
  }

  /** Return the more specific of the two types, if it exists, null otherwise */
  public String unifyTypes(String left, String right) {
    if (left == null || "Object".equals(left)) return right;
    if (right == null || "Object".equals(right)) return left;
    // check if these are RDF types and are in a type relation.
    if (isRdfType(left) || isRdfType(right)) {
      if (isRdfType(left) && isRdfType(right))
        return _proxy.fetchMostSpecific(left, right);
      if ("Rdf".equals(left)) return right;
      if ("Rdf".equals(right)) return left;
      return null;
    }

    // this should return the more specific of the two, or null if they are
    // incompatible
    Long leftCode = typeCodes.get(left);
    if (leftCode == null) leftCode = JAVA_TYPE;
    Long rightCode = typeCodes.get(right);
    if (rightCode == null) rightCode = JAVA_TYPE;

    long common = leftCode & rightCode;
    if (common == leftCode) {
      return left;
    }
    if (common == rightCode) {
      return right;
    }
    return null;
  }

  public RdfProxy getProxy() {
    return _proxy;
  }

  public String checkRdf(String type) {
    // if is necessary, because otherwise, Object as the static type in a
    // declaration gets changed to RdfType by
    // VGenerationVisitor.visitNode(ExpAssignment node)
    if ("Object".equals(type)
        || "String".equals(type)){ // what about Integer, int, etc.??
      return type;
    }
    RdfClass clazz = _proxy.fetchClass(type);
    if (clazz != null) {
      type = clazz.toString(); // the URI of the class
    }
    return type;
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
      this.neededClasses.put(classname, new HashSet<String>());
      this.rulesAndImports.put(classname, new ArrayList<String>());
    }
  }

  /**
   * get the name of the current class
   *
   * @return
   */
  public String getClassName() {
    return this.curClass;
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
  public void addFunction(String funcname, String functype,
          ArrayList<String> partypes, String origin) {
    if(initializing){
      origin = null;
    }
    this.current.addFunction(funcname, functype, partypes, origin, this);
  }

  public String getFunctionOrigin(String funcname, ArrayList<String> partypes){
    return current.getFunctionOrigin(funcname, partypes, this);
  }

  public boolean existsFunction(String funcname,
          ArrayList<String> partypes) {
    return current.existsFunction(funcname, partypes, this);
  }

  /**
   * returns null if there is no such function
   *
   * @param funcname the name of the function
   * @return its return type or null
   */
  public String getFunctionRetType(String funcname, ArrayList<String> partypes) {
    return current.getFunctionRetType(funcname, partypes, this);
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
      origin = null;
    }
    type = checkRdf(type);
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
  public void addRule(String rule, boolean toplevel) {
    if (toplevel) {
      this.rulesAndImports.get(this.curClass).add(rule);
      this.ruleNumber = 1;
      curTopRule = rule;
    } else {
      this.ruleNumber *= 2;
    }
    this.ruleNums.get(curClass).put(rule, ruleNumber);
    curRule = rule;
    this.neededClasses.put(rule, new HashSet<String>());
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
    if(this.curClass.toLowerCase().equals(ruleclass.toLowerCase())){
      return;
    }
    this.neededClasses.get(rule).add(ruleclass);
  }

  public Set<String> getNeededClasses(String ruleOrClass) {
    return this.neededClasses.get(ruleOrClass);
  }

  public boolean isExistingRule(String rule) {
    return ruleNums.get(this.curClass).containsKey(rule);
  }

  public USingleValue degradeToString(UVariable variable){
    return new USingleValue(variable.toString(), "String");
  }
}
