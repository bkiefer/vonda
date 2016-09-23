package de.dfki.mlt.agent;

import static de.dfki.mlt.agent.rdf.Quiz.*;
import static de.dfki.mlt.agent.rdf.Turn.*;

import de.dfki.mlt.agent.rdf.Quiz;
import de.dfki.mlt.agent.rdf.Turn;
import pal.TECS.EventHandler;
import pal.TECS.QuizResponse;

public class QuizResponseHandler implements EventHandler<QuizResponse> {

  private NaoAgent agent;

  public QuizResponseHandler(NaoAgent a) { agent = a; }

  @Override
  public void handleEvent(QuizResponse quizResponse) {
    if (! (agent.activity instanceof Quiz)) return;
    Quiz quiz = (Quiz)agent.activity;
    Turn t = (Turn)quiz.getCurrentTurn();
    if (t == null || ! t.has(TURN_QUESTIONID)) {
      agent.logger.warn("Incoming QuizResponse without active Question");
      return;
    }
    t.put(TURN_ANSWER, quizResponse.getResponse());
    //QuizQuestion q = (QuizQuestion) t.get("QuestionId");
    boolean correct = quizResponse.isCorrect();
    t.put(TURN_ANSWERCORRECT, correct);
    t.put(TURN_CATEGORY, quizResponse.getCategory()); // what's this?
    //quizResponse.getQuestionText(); // why included here?
    quiz.put(QUIZ_STATUS, "answer_given");
  }
}
