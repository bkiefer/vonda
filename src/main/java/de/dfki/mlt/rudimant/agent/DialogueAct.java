package de.dfki.mlt.rudimant.agent;

import de.dfki.lt.tr.dialogue.cplan.DagEdge;
import de.dfki.lt.tr.dialogue.cplan.DagNode;

public class DialogueAct {

  public long timeStamp;

  private DagNode _dag;

  public DialogueAct() {
    timeStamp = System.currentTimeMillis();
  }

  private DialogueAct(DagNode da) {
    this();
    _dag = da;
  }

  public DialogueAct(String s) {
    this();
    _dag = DagNode.parseLfString(s);
  }

  public DagNode getDag() {
    return _dag;
  }

  public String toString() {
    return _dag.toString(true);
  }

  public boolean isSubsumedBy(DialogueAct moreGeneral) {
    return this._dag.isSubsumedBy(moreGeneral._dag);
  }

  public boolean subsumes(DialogueAct moreGeneral) {
    return this._dag.subsumes(moreGeneral._dag);
  }

  /** Return the argument for key slot */
  public String getSlot(String slot) {
    DagEdge e = _dag.getEdge(DagNode.getFeatureId(slot));
    if (e == null) {
      return null;
    }
    DagNode node = e.getValue();
    if (node.getType() == DagNode.TOP_ID) {
      e = node.getEdge(DagNode.PROP_FEAT_ID);
      if (e == null) {
        return DagNode.TOP_TYPE;
      }
      return e.getValue().getTypeName();
    }
    return node.getTypeName();
  }

  /** Set the argument for key slot */
  public void setSlot(String slot, String value) {
    _dag.addEdge(DagNode.getFeatureId(slot),
        new DagNode(DagNode.PROP_FEAT_ID, new DagNode(value)));
  }

  /** Return the dialogue act */
  public String getDialogueActType() {
    DagNode d = _dag.getValue(DagNode.TYPE_FEAT_ID);
    return (d == null) ? null : d.getTypeName();
  }

  /** Return the dialogue act */
  public void setDialogueActType(String daType) {
    DagEdge e = _dag.getEdge(DagNode.TYPE_FEAT_ID);
    if (e == null) {
      _dag.addEdge(DagNode.TYPE_FEAT_ID, new DagNode(daType));
    } else {
      e.getValue().setType(DagNode.getTypeId(daType));
    }
  }

  /** Return the proposition */
  public String getProposition() {
    DagNode prop = _dag.getValue(DagNode.PROP_FEAT_ID);
    return (prop == null) ? null : prop.getTypeName();
  }

  /** Set the proposition of a dialogue act*/
  public void setProposition(String prop) {
    DagEdge p = _dag.getEdge(DagNode.PROP_FEAT_ID);
    if (p == null) {
      _dag.addEdge(DagNode.PROP_FEAT_ID, new DagNode(prop));
    } else {
      p.getValue().setType(DagNode.getTypeId(prop));
    }
  }

  public DialogueAct copy() {
    return new DialogueAct(this._dag.copySafely());
  }

  // TODO
  public static DialogueAct fromRdf(String uri) {
    return null;
  }

  // TODO
  public void toRdf() {

  }


}

