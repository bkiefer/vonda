/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2;

import Versuch2.abstractTree.AbstractType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anna
 */
public class Mem {

  private static List<Environment> environment = new ArrayList<Environment>();
  private static int positionAtm = -1;
  private static int depthAtm = -1;
  
  public static int getCurrentDepth(){
    return depthAtm;
  }

  public static void addElement(String variable, AbstractType type) {
    environment.get(positionAtm).put(variable, type);
  }

  public static boolean existsVariable(String variable) {
    return getVariableType(variable) != null;
  }

  public static AbstractType getVariableType(String variable) {
    Environment actual = environment.get(positionAtm);
    if (!actual.containsKey(variable)) {
      int depth = actual.getDepth();
      int position = positionAtm;
      while (position >= 0) {
        if (!actual.containsKey(variable)) {
          actual = environment.get(position--);
          while (!actual.isVisibleFrom(depth) && position > 0) {
            actual = environment.get(position--);
          }
        }
        else{
          break;
        }
      }
    }
    return actual.get(variable);
  }

  public static void addNextEnvironment(Environment env) {
    depthAtm = env.getDepth();
    positionAtm++;
    environment.add(env);
  }
  
  /**
   * adds a new Environment with the given depth
   * @param depth the depth the environment is supposed to lie on
   * @return the position in memory where the environment is stored
   */
  public static int addAndEnterNewEnvironment(int depth){
    environment.add(new Environment(depth));
    depthAtm = depth;
    return ++positionAtm;
  }
  
  public static void goToEnvironmentNumber(int number){
    positionAtm = number;
    depthAtm = environment.get(number).getDepth();
  }
/*
  public Environment getFollowingEnvironment() {
    this.depthAtm = this.environment.get(++this.positionAtm).getDepth();
    return this.environment.get(this.positionAtm);
  }

  public void leaveEnvironment() {
    this.positionAtm++;
    this.depthAtm = this.environment.get(this.positionAtm).getDepth();
  }
*/
}
