/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2;

import Versuch2.abstractTree.AbstractType;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author anna
 */
public class Mem {

  private Stack<Environment> environment;
  private int depthAtm;

  public Mem() {
    this.environment = new Stack<Environment>();
    this.depthAtm = 0;
  }
  
  public int getActualDepth(){
    return this.depthAtm;
  }

  public void addElement(String variable, AbstractType type) {
    this.environment.peek().put(variable, type);
  }

  public boolean existsVariable(String variable) {
    return this.getVariableType(variable) != null;
  }

  public AbstractType getVariableType(String variable) {
    Environment actual = this.environment.peek();
    if (!actual.containsKey(variable)) {
      int position = actual.getDepth();
      Iterator it = this.environment.listIterator();
      while (it.hasNext()) {
        if (!actual.containsKey(variable)) {
          actual = (Environment) it.next();
          while (!actual.isVisibleFrom(position) && it.hasNext()) {
            actual = (Environment) it.next();
          }
        }
      }
      return actual.get(variable);
    }
    return null;
  }

  public void addNextEnvironment(Environment env) {
    this.depthAtm = env.getDepth();
    this.environment.add(env);
  }

  public Environment getNextEnvironment() {
    this.depthAtm = this.environment.peek().getDepth();
    return this.environment.peek();
  }

  public void leaveEnvironment() {
    this.environment.pop();
    this.depthAtm = this.environment.peek().getDepth();
  }
}
