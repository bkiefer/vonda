package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

public class Color extends RdfProxy {
  public static final String COLOR_NAME = "name";
  
  static {
    // valid keys:
    String[][] valid = {
        // TODO: MATCH NAMES WITH ONTOLOGY
        { COLOR_NAME, "name" },
    };
    addValidKeys(Color.class, "<dom:Color>", valid);
  }
  
  public Color(Agent agent) {
    super(agent);
  }
    
}
