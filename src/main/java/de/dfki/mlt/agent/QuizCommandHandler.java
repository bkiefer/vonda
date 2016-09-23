package de.dfki.mlt.agent;

import de.dfki.mlt.agent.rdf.Idle;
import de.dfki.mlt.agent.rdf.Quiz;
import pal.TECS.EventHandler;
import pal.TECS.QuizCommand;

public class QuizCommandHandler implements EventHandler<QuizCommand> {

  private NaoAgent agent;

  public QuizCommandHandler(NaoAgent a) { agent = a; }

  @Override
  public void handleEvent(QuizCommand o) {
    // I'm sending quiz commands, do i also have to handle them?
    // yes, for the home version, where the child decides when to play quiz
    agent.logger.info("->QuizCommand: {} {}",o.getAsker(), o.getCommand());

    String[] fields = Agent.tearApart(';', o.getCommand());

    if ("start_home".equals(fields[0])) {
      agent.addLastDA("AcceptRequest", "Playing", "theme", "Quiz");
    }

    if ("stop_home".equals(fields[0])){
      if (agent.activity instanceof Quiz) {
        Quiz quiz = (Quiz)agent.activity;
        quiz.put(Quiz.QUIZ_STATUS, "ended_by_user");
      }
    }
  }

}
