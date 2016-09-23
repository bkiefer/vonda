package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

public class Introduction extends Activity {
  public static final String INTRODUCTION_GREETING = "greeting";
  public static final String INTRODUCTION_GREETINGRECEIVED = "greetingReceived";
  public static final String INTRODUCTION_GAMEPROPOSED = "gameProposed";

  static {
    String[][] valid = {
      { INTRODUCTION_GREETING, "DagNode" },
      { INTRODUCTION_GREETINGRECEIVED, "DagNode" },
      { INTRODUCTION_GAMEPROPOSED, "DagNode" },
    };
    addValidKeys(Introduction.class, "<dom:Introduction>", valid);
  }

  public Introduction(Agent agent) { super(agent); }
}