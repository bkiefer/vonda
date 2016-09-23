package de.dfki.mlt.agent.rdf;

import de.dfki.mlt.agent.Agent;

// TODO: CHECK WITH QUIZ LOGIC
public class Turn extends RdfProxy {
  public static final String TURN_ASKER = "Asker";
  public static final String TURN_RESPONDER = "Responder";
  public static final String TURN_QUESTIONID = "QuestionId";
  public static final String TURN_ANSWER = "Answer";
  public static final String TURN_ANSWERCORRECT = "AnswerCorrect";
  public static final String TURN_CATEGORY = "Category";

  public Turn(Agent agent) { super(agent); }

  public void setAsker(String askerUri, String responderUri) {
    put(TURN_ASKER, askerUri);
    put(TURN_RESPONDER, responderUri);
  }

  static {
    // valid keys:
    String[][] valid = {
        // TODO: MATCH NAMES WITH ONTOLOGY
        { TURN_ASKER, "Agent" },
        { TURN_RESPONDER, "Agent" },
        { TURN_QUESTIONID, "URI" },
        { TURN_ANSWER, "Number" }, // TODO: CHECK WITH QUIZ LOGIC
        { TURN_ANSWERCORRECT, "boolean" },
        { TURN_CATEGORY, "String" }  // Not sure what this is
    };
    addValidKeys(Turn.class, "<dom:Turn>", valid);
  }
}
