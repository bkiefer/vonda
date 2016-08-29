/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author anna
 */
public class Mem {

  private static List<Environment> environment = new ArrayList<Environment>();
  private static HashMap<String, String> actualValues = new HashMap<String, String>();
  private static int positionAtm = -1;
  private static int depthAtm = -1;

  // bei Eintritt in neues Environment: nichts passiert
  // bei Hinzufügen einer Variablen: Variablen zur HashMap vom Environment hinzufügen, 
  // in actual auf aktuellen Wert ändern
  // bei Austritt aus einem Environment: lokale Variablen des Environments löschen,
  // die, die von obendrüber shadowed wurden in actual wieder einfügen
  // Problem: dann muss man beim Austritt alle Environments nochmal durchgehen, anstatt
  // es wie aktuell dann zu machen wenn die Variable benutzt wird
  // -> Verbesserung: ja, aber immer noch suboptimal
  // Lösung: wir sagen dem Environment, wenn eine Variable schon in actual war, 
  // es soll sich die und ihren Wert gefälligst merken
  public static void newMem() {
    environment = new ArrayList<Environment>();
    positionAtm = -1;
    depthAtm = -1;
  }

  public static int getCurrentDepth() {
    return depthAtm;
  }

  public static void addElement(String variable, String type) {
    //environment.get(positionAtm).put(variable, type);
    if (actualValues.containsKey(variable)) {
      // we overwrite it
      environment.get(positionAtm).override(variable, actualValues.get(variable), type);
    } else {
      // we add it
      environment.get(positionAtm).put(variable, type);
    }
    actualValues.put(variable, type);
  }

  public static void decreaseDepth() {
    depthAtm--;
  }

  public static boolean existsVariable(String variable) {
    return actualValues.containsKey(variable);
  }

  /**
   * get the type of the given variable
   *
   * @param variable a variable
   * @return the variable's type
   */
  public static String getVariableType(String variable) {
    return actualValues.get(variable);
    /*Environment actual = environment.get(positionAtm);
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
    return actual.get(variable);*/
  }

  public static void addNextEnvironment(Environment env) {
    depthAtm = env.getDepth();
    positionAtm++;
    environment.add(env);
  }

  /**
   * adds a new Environment with the given depth
   *
   * @param depth the depth the environment is supposed to lie on
   * @return the position in memory where the environment is stored
   */
  public static int addAndEnterNewEnvironment(int depth) {
    environment.add(new Environment(depth));
    depthAtm = depth;
    return ++positionAtm;
  }

  public static void goToEnvironmentNumber(int number) {
    positionAtm = number;
    depthAtm = environment.get(number).getDepth();
  }

  /*
  public Environment getFollowingEnvironment() {
    this.depthAtm = this.environment.get(++this.positionAtm).getDepth();
    return this.environment.get(this.positionAtm);
  }
   */
  public static void leaveEnvironment() {
    // restore the values in actual that we changed
    environment.get(positionAtm).restoreOld();
    decreaseDepth();
    // this is no longer needed because it's handled in add&Enter
//    this.positionAtm++;
//    this.depthAtm = this.environment.get(this.positionAtm).getDepth();
  }

  public static void eraseLocalV(String variable) {
    actualValues.remove(variable);
  }

  public static void restoreLocalV(String variable, String type) {
    actualValues.put(variable, type);
  }
}
