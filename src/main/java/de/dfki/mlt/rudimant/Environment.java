/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * represents a namespace (to be used by the memory=
 *
 * @author Anna Welker
 */
public class Environment {

  private static Logger logger = LoggerFactory.getLogger(Environment.class);

  private Map<String, String> variableToType;
  private Map<String, String> variableOrigin;
  private HashSet<String> rdfs;  
  private HashMap<String, String> functionReturnTypes;
  private HashMap<String, String> functionOrigins;
  private HashMap<String, ArrayList<String>> functionParamaterTypes;

  public Environment() {
    this.variableToType = new HashMap<>();
    this.variableOrigin = new HashMap<>();
    this.rdfs = new HashSet<>();
    this.functionOrigins = new HashMap<>();
    this.functionParamaterTypes = new HashMap<>();
    this.functionReturnTypes = new HashMap<>();
  }

  public Environment deepCopy() {
    Environment newEnv = new Environment();
    newEnv.variableOrigin.putAll(this.variableOrigin);
    newEnv.variableToType.putAll(this.variableToType);
    newEnv.functionOrigins.putAll(this.functionOrigins);
    newEnv.functionParamaterTypes.putAll(this.functionParamaterTypes);
    newEnv.functionReturnTypes.putAll(this.functionReturnTypes);
    return newEnv;
  }

  public void put(String v, String t) {
    this.variableToType.put(v, t);
  }

  public void put(String v, String t, String o) {
    put(v, t);
    this.variableOrigin.put(v, o);
  }

  public boolean containsKey(String k) {
    return this.variableToType.containsKey(k);
  }

  public String get(String k) {
    return this.variableToType.get(k);
  }

  /**
   * May return null, either because variable does not exist or is not from the
   * top level, in which case the origin is not of our concern
   */
  public String getOrigin(String k) {
    return this.variableOrigin.get(k);
  }

  public boolean isRdf(String variable) {
    return this.rdfs.contains(variable);
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
          ArrayList<String> partypes, String origin, Mem mem) {
    functype = mem.checkRdf(functype);
    functionReturnTypes.put(funcname, functype);
    for (int i = 0; i < partypes.size(); ++i) {
      partypes.set(i, mem.checkRdf(partypes.get(i)));
    }
    functionParamaterTypes.put(funcname, partypes);
    // we may need this later, it doesn't harm us now
    // TODO: still sensible?
    functionOrigins.put(funcname, origin);
  }
    public String getFunctionOrigin(String funcname){
    return this.functionOrigins.get(funcname);
  }

  public boolean existsFunction(String funcname,
          ArrayList<String> partypes, Mem mem) {
    if (!functionReturnTypes.containsKey(funcname)) {
      return false;
    }
    int i = 0;
    if(partypes.size() != functionParamaterTypes.get(funcname).size()){
      logger.error("You are using overridden methods (call to " + funcname + ") which rudimant currently doesn't allow!!");
      return false;
    }
    for(String parameter: partypes){
      if(mem.mergeTypes(parameter, functionParamaterTypes.get(funcname).get(i)) == null){
        return false;
      }
      i++;
    }
    return true;
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
    return functionReturnTypes.get(funcname);
  }
}
