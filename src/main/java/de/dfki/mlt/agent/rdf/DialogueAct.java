package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;
import de.dfki.lt.tr.dialogue.cplan.DagNode;

public class DialogueAct {
  //static { String[][] valid = {}; addValidKeys(DialogueAct.class, valid); }

  public long timeStamp;

  public DagNode dag;

  public DialogueAct(Agent agent) {
    // super(agent);
    timeStamp = System.currentTimeMillis();
  }

  public String toString() { return dag.toString(); }
}
