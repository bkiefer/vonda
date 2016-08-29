/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.util.HashMap;


/**
 *
 * @author anna
 */
public class Environment {
  
  private HashMap<String, String> memory;
  private HashMap<String, String> overwritten;
  private int depth;
  
  public Environment(int depth){
    this.depth = depth;
    this.memory = new HashMap<String, String>();
    this.overwritten = new HashMap<String, String>();
  }
  
  /**
   * restore the environment that was actual before this one
   */
  public void restoreOld(){
    for (String v : memory.keySet()){
      Mem.eraseLocalV(v);
    }
    for (String v : overwritten.keySet()){
      Mem.restoreLocalV(v, overwritten.get(v));
    }
  }
  
  /**
   * there is a variable that is shadowed; remember its old value
   * @param variable the variable in question
   * @param oldType the old type of the variable
   * @param newType the new type of the variable
   */
  public void override(String variable, String oldType, String newType){
    overwritten.put(variable, oldType);
    memory.put(variable, newType);
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
