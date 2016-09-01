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
public class ReturnManagement {

  private HashMap<String, String> findPredecessor;
  private HashMap<String, Boolean> hasReturn;
  private String inRule;


  public ReturnManagement(String input) {
    findPredecessor = new HashMap<>();
    hasReturn = new HashMap<>();
    inRule = input;
  }

  /**
   * tell the returnMem that we enter a new rule
   *
   * @param newRule the rule (LABEL) we go to
   */
  public void enterRule(String newRule) {
    findPredecessor.put(newRule, inRule);
    hasReturn.put(newRule, Boolean.FALSE);
    inRule = newRule;
  }

  /**
   * tell the returnMem that we leave the rule we are currently in
   */
  public void leaveRule() {
    inRule = findPredecessor.get(inRule);
  }

  /**
   * is this an existing rule?
   * @param r the label of the rule
   * @return yes or no
   */
  public boolean isExistingRule(String r){
    return hasReturn.containsKey(r);
  }

  /**
   * tell the returnMem that we found a return (not needed if it is a simple
   * return, i.e., if the return has no argument
   *
   * @param to the LABEL we want to return to
   */
  public void foundReturnTo(String to) {
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
   * tells whether this rule has a return statement (= whether it is necessary to look up whether we should jump higher in
   * the hierarchy)
   *
   * @param rule the rule (LABEL) we are in
   * @return yes or no
   */
  public boolean shouldAddReturnto(String rule) {
    return hasReturn.get(rule);
  }
}