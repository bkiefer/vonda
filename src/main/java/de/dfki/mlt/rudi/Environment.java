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
  private int depth;
  
  public Environment(int depth){
    this.depth = depth;
    this.memory = new HashMap<String, String>();
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
