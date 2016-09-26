package de.dfki.mlt.agent;

import de.dfki.mlt.agent.events.UserTextFeedback;
import de.dfki.mlt.agent.events.QuizQuestion;
import static de.dfki.mlt.agent.rdf.Child.CHILD_BIRTHDATE;
import static de.dfki.mlt.agent.rdf.Child.CHILD_NAME;
import static de.dfki.mlt.agent.rdf.Introduction.INTRODUCTION_GREETINGRECEIVED;
import static de.dfki.mlt.agent.rdf.Quiz.QUIZ_STATUS;
import static de.dfki.mlt.agent.rdf.Turn.*;

import de.dfki.mlt.agent.rdf.*;
import de.dfki.lt.tr.dialogue.cplan.DagNode;


public class InterpretationHandler implements EventHandler<UserTextFeedback> {
  NaoAgent agent;

  public InterpretationHandler(NaoAgent ag) {
    agent = ag;
  }

  private DialogueAct addLastDA(String a, String b, String ... c) {
    DialogueAct result =  agent.addLastDA(a, b, c);
    agent.logger.info("Incoming DialogueAct: {}", result);
    return result;
  }

  private DialogueAct addLastDA(DagNode reply) {
    DialogueAct result = new DialogueAct(agent);
    result.dag = reply;
    agent.addLastDA(result);
    agent.logger.info("Incoming DialogueAct: {}", result);
    return result;
  }

  /** TODO: This should be shielded against processing an incoming speech act
   *  twice by checking if we ran that already on the lastDA using its
   *  timestamp.
   */
  @Override
  public void handleEvent(UserTextFeedback o) {
    String[] chunks = Agent.tearApart(';', o.getContent());
    if (chunks.length < 2) {
      agent.logger.error("-> WRONG UserFeedback, only one part: {}", o.getContent());
      return;
    } else {
      agent.logger.debug("->UserFeedback: {}", o.getContent());
    }

    if (agent.isMyLastDA("Request", "Liking")) {
      if (agent.activity instanceof Introduction) {
        Introduction intro = (Introduction) agent.activity;
        String theme = Agent.getSlot(agent.getMyLastDA(), "theme");
        DialogueAct da =
            addLastDA("Inform", "Liking", "theme", theme, "value", chunks[1]);
        agent.user.setLiking(theme, chunks[1]);

//        logger.info(theme + " was introduced with value: " + chunks[1] );

        if (! intro.has(INTRODUCTION_GREETINGRECEIVED))
          intro.put(INTRODUCTION_GREETINGRECEIVED, da);
      } else {
        agent.logger.warn("liking request outside intro");
      }
      return; // avoid the general handling of request / confirm
    }

    if (agent.isMyLastDA("Request", "Age")) {
      if (agent.activity instanceof Introduction) {
        Introduction intro = (Introduction) agent.activity;
        DialogueAct da = addLastDA("Inform", "Age", "value", chunks[1]);
        agent.user.put(CHILD_BIRTHDATE, chunks[1]);
        if (! intro.has(INTRODUCTION_GREETINGRECEIVED))
          intro.put(INTRODUCTION_GREETINGRECEIVED, da);
        } else {
        agent.logger.warn("age request outside intro");
      }
      return;
    }

    if (agent.isMyLastDA("Request", "Name")) {
      if (agent.activity instanceof Introduction) {
        Introduction intro = (Introduction) agent.activity;
        DialogueAct da = addLastDA("Inform", "Name", "value", chunks[1]);
        agent.user.put(CHILD_NAME, chunks[1]);
        if (! intro.has(INTRODUCTION_GREETINGRECEIVED))
          intro.put(INTRODUCTION_GREETINGRECEIVED, da);
      } else {
        agent.logger.warn("name request outside intro");
      }
      return;
    }

    if (agent.isMyLastDA("InitialGreeting", "Meeting")
        || agent.isMyLastDA("ReturnGreeting", "Meeting")) {
      String dialogueAct = Agent.getDialogueAct(agent.getMyLastDA());
      if (agent.activity instanceof Introduction) {
        //Introduction intro = (Introduction) agent.activity;
        DialogueAct last = addLastDA(dialogueAct, "Meeting");
        //intro.put(INTRODUCTION_GREETINGRECEIVED, last);
      } else {
        agent.logger.warn("name request outside intro");
      }
      return;
    }

    if (agent.isMyLastDA("Request", "top")) {
      String replyType = null;
      switch (chunks[1].toLowerCase().trim()) {
      case "yes":
        replyType = "AcceptRequest";
        break;
      case "no":
        replyType = "RejectRequest";
        break;
      default: break;
      }
      if (null != replyType) {
        DagNode reply = agent.getMyLastDA().dag.copySafely();
        DagNode type = reply.getEdge(DagNode.TYPE_FEAT_ID).getValue();
        type.setType(DagNode.getTypeId(replyType));
        addLastDA(reply);
      }
    }

    if (agent.activity != null && agent.activity instanceof Quiz &&
        agent.activity.isEqual(QUIZ_STATUS, "wait_for_question_read")) {
      agent.userReadsQuestion();
    }

    if (agent.activity instanceof Quiz) {
      Quiz quiz = ((Quiz)agent.activity);
      if (quiz.isEqual(QUIZ_STATUS, "wait_for_answer")
          && quiz.getCurrentAsker().equals(NaoAgent.I_ROBOT)) {
        // this was probably a quiz answer
        Turn t = quiz.getCurrentTurn();
        if (t != null) {
          int answerNo = 0;
          try {
            answerNo = Integer.parseInt(chunks[1]) - 1 ;
          } catch(NumberFormatException ex) {
            // TODO: DO IT CLEVERLY: if this was real ASR, we should have
            // multiple ways of identifying the answer.
          }
          t.put(TURN_ANSWER, answerNo);
          QuizQuestion q = (QuizQuestion) t.get(TURN_QUESTIONID);
          boolean correct = q.getWhichCorrect() == answerNo;
          t.put(TURN_ANSWERCORRECT, correct);
          t.put(TURN_CATEGORY, "unknown"); // what's this?
          quiz.put(QUIZ_STATUS, "answer_given");
        }
      }
    }

  }

}
