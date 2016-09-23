package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

/** The activity that waits until something happens */
public class Idle extends Activity {
  
  static {
    String[][] valid = {
    };
    addValidKeys(Idle.class, "<dom:Idle>", valid);
  }
  
  public Idle(Agent agent) { super(agent); }
}