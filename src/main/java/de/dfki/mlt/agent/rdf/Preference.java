package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

public class Preference extends RdfProxy {
  public static final String PREFERENCE_HOBBY = "hasLocalHobby";
  public static final String PREFERENCE_COLOR = "hasLocalColor";
  
  static {
    // valid keys:
    String[][] valid = {
        // TODO: MATCH NAMES WITH ONTOLOGY
        { PREFERENCE_HOBBY, "Color" },
        { PREFERENCE_COLOR, "Hobby" },
    };
    addValidKeys(Preference.class, "<dom:Preference>", valid);
  }
  
  public Preference(Agent agent) {
    super(agent);
  }
    
}
