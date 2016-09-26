package de.dfki.mlt.agent;

import static de.dfki.mlt.agent.rdf.Quiz.*;
import static de.dfki.mlt.agent.rdf.Turn.*;

import de.dfki.mlt.agent.rdf.Quiz;
import de.dfki.mlt.agent.rdf.Turn;
import de.dfki.mlt.agent.events.QuizQuestion;

public class QuizQuestionHandler implements EventHandler<QuizQuestion> {

  private NaoAgent agent;

  public QuizQuestionHandler(NaoAgent a) { agent = a; }

  @Override
  public void handleEvent(QuizQuestion quizQuestion) {
    agent.logger.debug("->Question: {}", quizQuestion.getQuestionText());
    if (agent.activity == null || !(agent.activity instanceof Quiz)) return;
    Quiz quiz = (Quiz) agent.activity;
    if (! quiz.isEqual(QUIZ_STATUS, "wait_for_question")) {
      agent.logger.warn("Question arrived while in status {}",
          quiz.get(QUIZ_STATUS).toString());
      return;
    }
    // create a new Turn, add it to the turns of the current Quiz and
    // make it the current turn
    Turn turn = quiz.getCurrentTurn();
    // check that this turn is "fresh"
    if (turn.has(TURN_QUESTIONID)) {
      agent.logger.warn("Question arrived when Turn already had a question");
    }
    if (! turn.get(TURN_ASKER).equals(
        agent.mapAgentToUri(quizQuestion.getAsker()))) {
      agent.logger.warn("Question asker does not match Turn asker: {} {}",
          turn.get(TURN_ASKER), quizQuestion.getAsker());
    }
    turn.put(TURN_QUESTIONID, quizQuestion);
    quiz.put(QUIZ_STATUS, "wait_for_tablet");
  }

}
