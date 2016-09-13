/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import de.dfki.mlt.rudi.abstractTree.RudiTree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class RuleConditionLog {

  // for each rule, remember its condition-subtree to log it lateron
  private Map<String, RudiTree> rule2condition;
  // as we implement the logging in seperate methods to keep the output
  // readable, all variables must be passed to the log method
  // the arrays in the list are tuples of variable (1) & type (2)
  private Map<String, List<String[]>> rule2conditionArgs;

  public RuleConditionLog(){
    rule2condition = new HashMap<String, RudiTree>();
    rule2conditionArgs = new HashMap<String, List<String[]>>();
  }

  /**
   * add a rule and the condition of its if statement to our logging-memory
   * @param rule
   * @param condition
   */
  public void addRule2condition(String rule, RudiTree condition){
    rule2condition.put(rule, condition);
    rule2conditionArgs.put(rule, new ArrayList<String[]>());
  }

  /**
   * remember that we will need this variable to be passed to the logging method
   * @param rule
   * @param varName
   * @param varType
   */
  public void addRule2conditionArgs(String rule, String varName, String varType){
    String[] vt = {varName, varType};
    rule2conditionArgs.get(rule).add(vt);
  }

  /**
   * get the condition of this rule to log it
   * @param rule
   * @return
   */
  public RudiTree getCond2log(String rule){
    return rule2condition.get(rule);
  }

  /**
   * get the variables + their types that the logging of this rule will require
   * @param rule
   * @return
   */
  public List<String[]> getVarAndType2log(String rule){
    return rule2conditionArgs.get(rule);
  }
}
