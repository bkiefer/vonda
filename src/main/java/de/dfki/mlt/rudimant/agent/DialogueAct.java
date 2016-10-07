package de.dfki.mlt.rudimant.agent;

import de.dfki.lt.tr.dialogue.cplan.DagNode;

public class DialogueAct {

  public long timeStamp;

  public DagNode dag;

  public DialogueAct() {
    timeStamp = System.currentTimeMillis();
  }

  public String toString() {
    return dag.toString();
  }
}
