package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

public class Closing extends Activity {
  public static final String CLOSING_PROPOSED = "proposed";

  static {
    String[][] valid = {
        { CLOSING_PROPOSED, "DagNode" },
    };
    addValidKeys(Closing.class, "<dom:Closing>", valid);
  }

  public Closing(Agent agent) { super(agent); }
}