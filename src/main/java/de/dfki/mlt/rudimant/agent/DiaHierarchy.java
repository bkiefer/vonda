package de.dfki.mlt.rudimant.agent;

import de.dfki.lt.hfc.db.rdfProxy.DbClient;
import de.dfki.lt.hfc.db.rdfProxy.RdfHierarchy;
import de.dfki.lt.tr.dialogue.cplan.Hierarchy;
import de.dfki.lt.tr.dialogue.cplan.util.ShortIDMap;

/**
 *
 * @author kiefer
 */
public class DiaHierarchy extends RdfHierarchy implements Hierarchy {

  protected ShortIDMap<String> nameToFeature = new ShortIDMap<String>();

  public DiaHierarchy(DbClient client) {
    super(client);
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
    int id = getVertex(name);
    if (id < 0) {
      id = addNewSingleton(name);
    }
    return id;
  }

  @Override
  public String getTypeName(int type) {
    return getVertexName(type);
  }

}
