/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * represents a namespace (to be used by the memory=
 *
 * @author Anna Welker
 */
public class Environment {

  private Map<String, String> variableToType;
  private Map<String, String> variableOrigin;
  private HashSet<String> rdfs;

  public Environment() {
    this.variableToType = new HashMap<>();
    this.variableOrigin = new HashMap<>();
    this.rdfs = new HashSet<>();
  }

  public Environment deepCopy() {
    Environment newEnv = new Environment();
    newEnv.variableOrigin.putAll(this.variableOrigin);
    newEnv.variableToType.putAll(this.variableToType);
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
}
