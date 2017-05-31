package de.dfki.mlt.rudimant.agent.nlg;

import de.dfki.lt.tr.dialogue.cplan.DagEdge;
import de.dfki.lt.tr.dialogue.cplan.DagNode;

/** A data structure to represent incomplete (proto) logical forms which will
 *  have to be transformed into fully specified logical forms that can be
 *  passed on to, e.g., natural language generation.
 */
public class ProtoLf {

  private String _stringRep;
  private DagNode _dag;

  /** Initialize a PLF using a string representation
   */
  public ProtoLf(String stringRep) {
    _dag = null;
    _stringRep = stringRep;
  }

  /** Initialize a PLF using a string representation
   */
  public ProtoLf(DagNode dag) {
    _dag = dag;
    _stringRep = null;
  }

  /** Return the string representation of this PLF
   */
  @Override
  public String toString() {
    return _stringRep == null ? _dag.toString() : _stringRep;
  }

  public DagNode getDag() {
    return _dag;
  }

  private static int getTypeId(DagNode dag) {
    DagEdge edge = dag.getEdge(DagNode.TYPE_FEAT_ID);
    if (edge == null) return DagNode.BOTTOM_ID;
    return edge.getValue().getType();
  }

  private static String getType(DagNode dag) {
    return DagNode.getTypeName(getTypeId(dag));
  }

  public int getTypeId() { return getTypeId(_dag); }

  public String getType() { return getType(_dag); }

  private static int getPropId(DagNode dag) {
    DagEdge edge = dag.getEdge(DagNode.PROP_FEAT_ID);
    if (edge == null) return DagNode.BOTTOM_ID;
    return edge.getValue().getType();
  }

  private static String getProp(DagNode dag) {
    return DagNode.getTypeName(getPropId(dag));
  }

  public int getPropId() { return getPropId(_dag); }

  public String getProp() { return getProp(_dag); }

  /** return the atomic string value under the given edge, if it exists,
   *  null otherwise
   * @param edgeName
   * @return the atomic string value under the given edge, if it exists,
   *  null otherwise
   */
  public String getEdgeValue(String edgeName) {
    DagEdge edge = _dag.getEdge(DagNode.getFeatureId(edgeName));
    if (edge == null) return null;
    return getProp(edge.getValue());
  }

}
