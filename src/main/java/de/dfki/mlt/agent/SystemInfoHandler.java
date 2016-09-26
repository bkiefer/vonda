package de.dfki.mlt.agent;

import static de.dfki.mlt.agent.rdf.Quiz.QUIZ_STATUS;
import static de.dfki.mlt.agent.rdf.Quiz.QUIZ_TABLETORIENTATION;

import de.dfki.mlt.agent.rdf.Activity;
import de.dfki.mlt.agent.rdf.Quiz;
import de.dfki.mlt.agent.events.SystemInfo;

public class SystemInfoHandler implements EventHandler<SystemInfo> {

  private NaoAgent _agent;

  public SystemInfoHandler(NaoAgent agent) {
    _agent = agent;
  }

  @Override
  public void handleEvent(SystemInfo o) {
    _agent.logger.debug("->SystemInfo {}", o.getContent());
    String[] fields = Agent.tearApart(';', o.getContent());
    Activity a = _agent.activity;
    switch (fields[0]) {
      case "tablet_orientation_child":
        if (a instanceof Quiz) {
          ((Quiz) a).put(QUIZ_TABLETORIENTATION, "child");
        }
        break;
      case "tablet_orientation_robot":
        if (a instanceof Quiz) {
          Quiz q = ((Quiz) a);
          q.put(QUIZ_TABLETORIENTATION, "robot");
          if (q.isEqual(QUIZ_STATUS, "wait_for_question_read")) {
            _agent.userReadsQuestion();
          }
        }
        break;
      case "start_hospital":
        if (fields.length < 2) {
          _agent.logger.error("No URI after start_hospital");
          return;
        }
        // fields[1] = URI of child
        _agent.setUser(fields[1], "Hospital");
        break;
      case "start_home":
        if (fields.length < 2) {
          _agent.logger.error("No URI after start_home");
          return;
        }
        _agent.setUser(fields[1], "Home");
        break;
      case "end_session":
        // TODO: CURRENTLY I DO THE FULL RESET AFTER CLOSING, MAYBE I SHOULD PUT
        // THE STATUS TO IDLE AND DO THE RESET HERE
        _agent.reset();
        break;
      case "stop_quiz": // THIS IS JUST FOR TESTING! NOT LEGAL IN REAL VERSION
        if (a instanceof Quiz) {
          _agent.endQuiz(((Quiz) a), "time");
        }
        break;
      default:
        // TODO: maybe log unknown sysmsg for debugging
        // log sth.
        _agent.logger.error("Unknown system message: {}", o.getContent());
        break;
    }
  }
}
