/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class returnManagement {

  private static HashMap<String, String> findPredecessor;
  private static HashMap<String, Boolean> hasReturn;
  private static String inRule;

  /**
   * tell the returnMem that we enter a new rule
   *
   * @param newRule the rule we go to
   */
  public static void enterRule(String newRule) {
    findPredecessor.put(newRule, inRule);
    hasReturn.put(newRule, Boolean.FALSE);
    inRule = newRule;
  }

  /**
   * tell the returnMem that we leave the rule we are currently in
   */
  public static void leaveRule() {
    inRule = findPredecessor.get(inRule);
  }

  public static boolean isExistingRule(String r){
    return hasReturn.containsKey(r);
  }

  /**
   * tell the returnMem that we found a return (not needed if it is a simple
   * return, i.e., if the return has no argument
   *
   * @param to the label we want to return to
   */
  public static void foundReturnTo(String to) {
    hasReturn.put(inRule, Boolean.TRUE);
    String r = inRule;
    while (true) {
      r = findPredecessor.get(r);
      if (r == null || r.equals(to)) {
        break;
      }
      hasReturn.put(r, Boolean.TRUE);
    }
  }

  /**
   * tells whether it is necessary to look up whether we should jump higher in
   * the hierarchy
   *
   * @param rule the rule we are in
   * @return yes or no
   */
  public static boolean shouldAddReturnto(String rule) {
    return hasReturn.get(rule);
  }
}
