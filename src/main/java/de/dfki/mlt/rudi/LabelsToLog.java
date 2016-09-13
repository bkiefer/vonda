/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import de.dfki.mlt.rudi.abstractTree.RudiTree;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class LabelsToLog {

  // for each rule, remember its condition-subtree to log it lateron
  private Map<String, RudiTree> rule2condition;
  // as we implement the logging in seperate methods to keep the output
  // readable, all variables must be
  private Map<String, List<String>> rule2conditionArgs;

  public LabelsToLog(){
    rule2condition = new HashMap<String, RudiTree>();
    rule2conditionArgs = new HashMap<String, List<String>>();
  }
}
