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
import java.util.Set;
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
  private HashMap<String, Set<Function>> functions;

  public Environment() {
    this.variableToType = new HashMap<>();
    this.variableOrigin = new HashMap<>();
    this.rdfs = new HashSet<>();
    this.functions = new HashMap<>();
  }

  public Environment deepCopy() {
    Environment newEnv = new Environment();
    newEnv.variableOrigin.putAll(this.variableOrigin);
    newEnv.variableToType.putAll(this.variableToType);
    newEnv.functions.putAll(this.functions);
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

  /**
   * Add a function/method declaration, optionally with return and parameter
   * types. If the types are not known, it's assumed they are null.
   *
   * @param funcname The name of the function
   * @param functype the return type of the function, or null
   * @param partypes the parameter types of the function's parameters
   * @param origin first element class, second rule origin
   */
  public void addFunction(String funcname, String functype,
          ArrayList<String> partypes, String origin, Mem mem) {
    functype = mem.checkRdf(functype);
    for (int i = 0; i < partypes.size(); ++i) {
      partypes.set(i, mem.checkRdf(partypes.get(i)));
    }
    // test whether we already have an entry for this method
    if (functions.keySet().contains(funcname)) {
      for (Function f : functions.get(funcname)) {
        if (f.areParametertypes(partypes, mem)) {
          // in this case we have an obvious error
          if (!f.isReturnType(functype, mem)) {
            // TODO: add a description about where we are in the input file
            logger.warn("redeclaring function " + funcname
                    + " with new return type - was: " + f.getReturnType()
                    + " is: " + functype);
          }
          return;
        }
      }
      // else: just add the method
    } else {
      // if we did not know of this method until now, create a new entry for it
      functions.put(funcname, new HashSet<Function>());
    }
    functions.get(funcname).add(new Function(funcname, origin, functype,
                partypes));
  }

  public String getFunctionOrigin(String funcname, ArrayList<String> partypes,
          Mem mem) {
    if (functions.keySet().contains(funcname)) {
      for (Function f : functions.get(funcname)) {
        if (f.areParametertypes(partypes, mem)) {
          return f.getOrigin();
        }
      }
    }
    return null;
  }

  public boolean existsFunction(String funcname,
          ArrayList<String> partypes, Mem mem) {
    if (!functions.containsKey(funcname)) {
      return false;
    }
    for (Function f : functions.get(funcname)) {
      if (f.areParametertypes(partypes, mem)) {
        return true;
      }
    }
    return false;
  }

  /**
   * returns null if there is no such function
   *
   * @param funcname the name of the function
   * @return its return type or null
   */
  public String getFunctionRetType(String funcname, ArrayList<String> partypes,
          Mem mem) {
    if(!functions.containsKey(funcname)){
      return null;
    }
    for (Function f : functions.get(funcname)) {
      if (f.areParametertypes(partypes, mem)) {
        return f.getReturnType();
      }
    }
    return null;
  }

  private class Function {

    private String name;
    private String origin;
    private String returnType;
    private ArrayList<String> parameterTypes;

    public Function(String name, String origin, String returnType, ArrayList<String> parameterTypes) {
      this.name = name;
      this.origin = origin;
      this.returnType = returnType;
      this.parameterTypes = parameterTypes;
    }

    public String getName() {
      return this.name;
    }

    public String getReturnType() {
      return this.returnType;
    }

    public String getOrigin(){
      return this.origin;
    }

    public boolean isName(String name) {
      return this.name.equals(name);
    }

    public boolean isReturnType(String type, Mem mem) {
      return this.returnType.equals(type)
            || mem.unifyTypes(this.returnType, type) != null;
    }

    public boolean areParametertypes(ArrayList<String> partypes, Mem mem) {
      if (this.parameterTypes.size() != partypes.size()) {
        return false;
      }
      for (int i = 0; i < this.parameterTypes.size(); i++) {
        if (!(this.parameterTypes.get(i).equals(partypes.get(i))
                || mem.unifyTypes(this.parameterTypes.get(i), partypes.get(i)) != null)) {
          return false;
        }
      }
      return true;
    }

    @Override
    public int hashCode() {
      return parameterTypes.hashCode();
    }

    @Override
    public boolean equals(Object fun) {
      if (!(fun instanceof Function)) {
        return false;
      }
      return this.name.equals(((Function) fun).name)
              && this.returnType.equals(((Function) fun).returnType)
              && this.parameterTypes.equals(((Function) fun).parameterTypes);
    }
  }
}
