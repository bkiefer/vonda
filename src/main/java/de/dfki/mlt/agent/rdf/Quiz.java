package de.dfki.mlt.agent.rdf;

import static de.dfki.mlt.agent.rdf.Turn.*;

import de.dfki.mlt.agent.Agent;

/** There's no separate QuizSession, a Quiz obj is a session */
public class Quiz extends Activity {
  public static final String QUIZ_PLAYER = "player";
  public static final String QUIZ_STATUS = "status";
  public static final String QUIZ_BEGINS = "Begins";
  public static final String QUIZ_ENDS = "Ends";
  public static final String QUIZ_TURNS = "Turns";
  public static final String QUIZ_TABLETORIENTATION = "TabletOrientation";

  public Quiz(Agent agent) { super(agent); }

  public Turn getCurrentTurn() {
    RdfList<Turn> turns = (RdfList<Turn>)get(QUIZ_TURNS);
    if (turns == null) return null;
    return turns.peekLast();
  }

  public int getNumberOfTurns() {
    RdfList<Turn> turns = (RdfList<Turn>)get(QUIZ_TURNS);
    if (turns == null) return 0;
    return turns.length();
  }

  /** Return the number of answered questions and correctly answered questions
   *  for user and robot.
   * @param robot the URI of the robot, so the method knows the responder
   * @return positions:
   *    0 : user answered questions
   *    1 : user correct
   *    2 : robot answered
   *    3 : robot correct
   */
  public int[] getAssessment(String robot) {
    RdfList<Turn> turns = (RdfList<Turn>)get(QUIZ_TURNS);
    int[] result = {0,0,0,0};
    for (Turn turn : turns) {
      if (null == turn.get(TURN_RESPONDER)
          || null == turn.get(TURN_ANSWERCORRECT))
        continue;
      int offset = turn.get(TURN_RESPONDER).equals(robot)
          ? 2 : 0;
      result[offset]++;
      if ((Boolean)turn.get(TURN_ANSWERCORRECT)) {
        result[offset + 1]++;
      }
    }
    return result;
  }

  public Turn newTurn() {
    Turn t = new Turn(_agent);
    addToList(QUIZ_TURNS, t);
    return t;
  }

  public String getCurrentAsker() {
    Turn t = getCurrentTurn();
    if (t == null) return null;
    return (String) t.get(TURN_ASKER);
  }

  public boolean isRunning() {
    return oneOf(QUIZ_STATUS,
        "running", "wait_for_question", "wait_for_question_read",
        "wait_for_tablet", "wait_for_answer", "answer_given",
        "wait_for_continue", "wait_for_feedback",
        "wait_for_assessment");
  }

  static {
    String[][] valid = {
        // TODO: MATCH NAMES WITH ONTOLOGY
        { QUIZ_PLAYER, "String" },
        { QUIZ_STATUS, "String" }, // "none" "started" "running"
        { QUIZ_BEGINS, "Date" },
        { QUIZ_ENDS, "Date" },
        { QUIZ_TURNS, "RdfList<Turn>" }, // returns an RDF list proxy containing turns
        { QUIZ_TABLETORIENTATION, "String" }, // "user/robot"
    };
    addValidKeys(Quiz.class, "<dom:Quiz>", valid);
  };
}
