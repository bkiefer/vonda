/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * represents a namespace (to be used by the memory=
 * @author Anna Welker
 */
public class Environment {

  private HashMap<String, String> memory;
  private HashMap<String, String> overwritten;
  private HashMap<String, ArrayList<String>> variableOrigin;
  private int depth;

  public Environment(int depth){
    this.depth = depth;
    this.memory = new HashMap<>();
    this.overwritten = new HashMap<>();
    this.variableOrigin = new HashMap<>();
  }

  /**
   * restore the environment that was actual before this one
   */
  public void restoreOld(Mem mem){
    for (String v : memory.keySet()){
     mem.eraseLocalV(v);
    }
    for (String v : overwritten.keySet()){
      mem.restoreLocalV(v, overwritten.get(v), variableOrigin.get(v));
    }
  }

  /**
   * there is a variable that is shadowed; remember its old value
   * @param variable the variable in question
   * @param oldType the old type of the variable
   * @param newType the new type of the variable
   * @param oldClass the old rule where the variable came from
   * @param newClass the new (current) rule
   */
  public void override(String variable, String oldType, String newType, ArrayList<String> oldOrigin){
    overwritten.put(variable, oldType);
    variableOrigin.put(variable, oldOrigin);
    memory.put(variable, newType);
  }

  public boolean overrides(String variable){
    return overwritten.containsKey(variable);
  }

  public boolean isVisibleFrom(int somewhere){
    return this.depth < somewhere;
  }

  public int getDepth(){
    return this.depth;
  }

  public void put(String v, String t){
    this.memory.put(v, t);
  }

  public boolean containsKey(String k){
    return this.memory.containsKey(k);
  }

  public String get(String k){
    if(!this.containsKey(k)){
      return null;
    }
    return this.memory.get(k);
  }

}
