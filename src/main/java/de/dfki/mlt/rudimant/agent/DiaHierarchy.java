package de.dfki.mlt.rudimant.agent;

import de.dfki.lt.hfc.db.rdfProxy.RdfHierarchy;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.tr.dialogue.cplan.Hierarchy;
import de.dfki.lt.tr.dialogue.cplan.util.ShortIDMap;

/**
 *
 * @author kiefer
 */
public class DiaHierarchy implements Hierarchy {

  protected ShortIDMap<String> nameToFeature = new ShortIDMap<String>();

  RdfHierarchy _hier;

  public DiaHierarchy(RdfProxy proxy) {
    _hier = proxy.getHierarchy();
  }

  @Override
  public short getFeatureId(String name) {
    if (nameToFeature.contains(name)) {
      return nameToFeature.getId(name);
    } else {
      return nameToFeature.register(name);
    }
  }

  @Override
  public String getFeatureName(short feature) {
    if (feature >= 0) {
      return nameToFeature.fromId(feature);
    } else {
      return "ILL";
    }
  }

  @Override
  public int getTypeId(String name) {
    int id = _hier.getVertex(name);
    if (id < 0) {
      id = _hier.addNewSingleton(name);
    }
    return id;
  }

  @Override
  public String getTypeName(int type) {
    if (type == -2) return "top";
    return _hier.getVertexName(type);
  }

  @Override
  public boolean subsumes(int type1, int type2) {
    if (type1 == -2) return true;
    if (type2 == -2) return false;
    return _hier.subsumes(type1, type2);
  }

}
