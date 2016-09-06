/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class ReturnManagement {

  private HashMap<String, String> findPredecessor;

  // remember, where this rule (or a neighbour or sub rule) wants to go
  private HashMap<String, HashSet<String>> hasReturnTo;
  private String inRule;
  private String neighbour;
  // on the toplevel methods, we don't need to set a predecessor and we can start
  // counting rule numbers new, so remember where we are
  private int depth = 0;

  // every rule that we want to return to sometime gets a return marker
  // TODO: is it true that we never return to a rule that is not a superrule??
  private HashMap<String, Integer> markers;

  public ReturnManagement(String input) {
    findPredecessor = new HashMap<>();
    hasReturnTo = new HashMap<>();
    markers = new HashMap<>();
    inRule = input;
  }

  /**
   * tell the returnMem that we enter a new rule
   *
   * @param newRule the rule (LABEL) we go to
   */
  public void enterRule(String newRule) {
    if (depth > 0) {
      findPredecessor.put(newRule, inRule);
    }
    depth++;
    hasReturnTo.put(newRule, new HashSet<String>());
    String neighboursPred = findPredecessor.get(neighbour);
    if (neighboursPred != null && neighboursPred.equals(inRule)) {
      // then we are on the same level as that previous rule, and we might want
      // to jump over this with a return statement from there
      for (String r : hasReturnTo.get(neighbour)) {
        hasReturnTo.get(newRule).add(r);
      }
    }
    inRule = newRule;
  }

  /**
   * tell the returnMem that we leave the rule we are currently in
   */
  public void leaveRule() {
    depth--;
    neighbour = inRule;
    inRule = findPredecessor.get(inRule);
  }

  /**
   * is this an existing rule?
   *
   * @param r the label of the rule
   * @return yes or no
   */
  public boolean isExistingRule(String r) {
    return hasReturnTo.containsKey(r);
  }

  public boolean isToplevel(String r) {
    if (isExistingRule(r)) {
      return this.findPredecessor.get(r) == null;
    }
    return false;
  }

  /**
   * tell the returnMem that we found a return (not needed if it is a simple
   * return, i.e., if the return has no argument
   *
   * @param to the LABEL we want to return to
   */
  public void foundReturnTo(String to) {
    // we give this rule a marker of the current depth, if it is not already marked
    if(!markers.containsKey(to)){
      markers.put(to, depth^2);
    }
    hasReturnTo.get(inRule).add(to);
    String r = inRule;
    while (true) {
      r = findPredecessor.get(r);
      if (r == null || r.equals(to)) {
        break;
      }
      hasReturnTo.get(r).add(to);
    }
  }

  public Set<String> getMarkers(){
    return this.markers.keySet();
  }

  public int getMarker(String rule){
    return markers.get(rule);
  }

  /**
   * tells to which rule the lower rules might want to return
   *
   * @param rule the rule (LABEL) we are in
   * @return yes or no
   */
  public HashSet<String> shouldAddReturnto(String rule) {
    if (isExistingRule(rule)) {
      return hasReturnTo.get(rule);
    }
    return null;
  }
}
