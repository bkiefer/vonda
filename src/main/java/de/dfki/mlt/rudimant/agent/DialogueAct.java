package de.dfki.mlt.rudimant.agent;

import de.dfki.lt.tr.dialogue.cplan.DagNode;

public class DialogueAct {

  public long timeStamp;

  public DagNode dag;

  public DialogueAct() {
    timeStamp = System.currentTimeMillis();
  }

  public DialogueAct(String s) {
    this();
    dag = DagNode.parseLfString(s);
  }

  public String toString() {
    return dag.toString();
  }

  public boolean isSubsumedBy(DialogueAct moreGeneral) {
    return this.dag.isSubsumedBy(moreGeneral.dag);
  }

  public boolean subsumes(DialogueAct moreGeneral) {
    return this.dag.subsumes(moreGeneral.dag);
  }

  /** Return the argument for key slot */
  public String getSlot(String slot) {
    return null;// TODO
  }

  /** Return the argument for key slot */
  public void setSlot(String slot, String value) {
    // TODO
  }

  /** Return the dialogue act */
  public String getDialogueAct() {
    return null;// TODO
  }

  /** Return the dialogue act */
  public void setDialogueAct(String da) {
    // TODO
  }

  /** Return the proposition */
  public String getProposition() {
    return null;// TODO
  }

  /** Return the proposition */
  public void setProposition() {
    // TODO
  }

  public DialogueAct copy() {
    // TODO
    return null;
  }

  // TODO
  public static DialogueAct fromRdf(String uri) {
    return null;
  }

  // TODO
  public void toRdf() {

  }


}

