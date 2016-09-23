package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

public class Activity extends RdfProxy {
  static {
    String[][] valid = {};
    addValidKeys(Activity.class, "<dom:Activity>", valid);
  }

  public Activity(Agent agent) { super(agent); }
  
}