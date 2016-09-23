package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

public class Hobby extends RdfProxy {
  public static final String HOBBY_NAME = "name";
  
  static {
    // valid keys:
    String[][] valid = {
        // TODO: MATCH NAMES WITH ONTOLOGY
        { HOBBY_NAME, "name" },
    };
    addValidKeys(Hobby.class, "<dom:Hobby>", valid);
  }
  
  public Hobby(Agent agent) {
    super(agent);
  }
    
}
