package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

public class Session extends RdfProxy {
  public Session(Agent agent) { super(agent); }

  public static final String SESSION_CHILD = "hasChild";
  public static final String SESSION_ACTIVITIES = "hasActivities";


  static {
    // valid keys:
    String[][] valid = {
      { SESSION_CHILD, "Child" },
      { SESSION_ACTIVITIES, "RdfList<Activity>" }
    };
    addValidKeys(Session.class, "<dom:Session>", valid);
  }
}
