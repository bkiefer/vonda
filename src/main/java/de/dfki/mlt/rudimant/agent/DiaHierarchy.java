package de.dfki.mlt.rudimant.agent;

import de.dfki.lt.hfc.db.rdfProxy.RdfHierarchy;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.lt.tr.dialogue.cplan.Hierarchy;
import de.dfki.lt.tr.dialogue.cplan.util.ShortIDMap;

/** A class that is used in the content planner to check the hierarchy of
 *  dialogue acts.
 *
 * @author kiefer
 */
public class DiaHierarchy implements Hierarchy {

  protected ShortIDMap<String> nameToFeature = new ShortIDMap<String>();

  RdfHierarchy _hier;

  /** Initialize the hierarchy based on the RDF class hierarchy */
  public DiaHierarchy(RdfProxy proxy) {
    _hier = proxy.getHierarchy();
    assert(_hier.getVertex(DagNode.TOP_TYPE) == DagNode.TOP_ID);
  }

  /** Return a code for the given feature name */
  @Override
  public short getFeatureId(String name) {
    if (nameToFeature.contains(name)) {
      return nameToFeature.getId(name);
    } else {
      return nameToFeature.register(name);
    }
  }

  /** Return the name of the feature with the given code */
  @Override
  public String getFeatureName(short feature) {
    if (feature >= 0) {
      return nameToFeature.fromId(feature);
    } else {
      return "ILL";
    }
  }

  /** Return the code for the given type name */
  @Override
  public int getTypeId(String name) {
    if (DagNode.TOP_TYPE.equals(name))
      return DagNode.TOP_ID;
    int id = -1;
    if (! name.startsWith("<")) {
      String daname = "<dial:"+name+">";
      id = _hier.getVertex(daname);
    }
    if (id < 0) {
      id = _hier.getVertex(name);
      if (id < 0) {
        id = _hier.addNewSingleton(name);
      }
    }
    return id;
  }

  /** Return the type name for the given type code */
  @Override
  public String getTypeName(int type) {
    if (type == -2) return "top";
    return _hier.getVertexName(type);
  }

  /** return true if type1 subsumes (is more general than) type 2 */
  @Override
  public boolean subsumes(int type1, int type2) {
    if (type1 == -2) return true;
    if (type2 == -2) return false;
    return _hier.subsumes(type1, type2);
  }

}
