package de.dfki.mlt.agent;

import static de.dfki.mlt.agent.rdf.Child.*;
import static de.dfki.mlt.agent.rdf.Closing.CLOSING_PROPOSED;
import static de.dfki.mlt.agent.rdf.Introduction.*;
import static de.dfki.mlt.agent.rdf.Quiz.*;
import static de.dfki.mlt.agent.rdf.Session.*;
import static de.dfki.mlt.agent.rdf.Turn.*;

import java.util.Arrays;
import java.util.Date;

import de.dfki.mlt.agent.rdf.Activity;
import de.dfki.mlt.agent.rdf.Child;
import de.dfki.mlt.agent.rdf.Closing;
import de.dfki.mlt.agent.rdf.DialogueAct;
import de.dfki.mlt.agent.rdf.Idle;
import de.dfki.mlt.agent.rdf.Introduction;
import de.dfki.mlt.agent.rdf.Quiz;
import de.dfki.mlt.agent.rdf.RdfList;
import de.dfki.mlt.agent.rdf.Session;
import de.dfki.mlt.agent.rdf.Turn;
import de.dfki.lt.tr.dialogue.cplan.DagNode;


public class NaoAgent extends Agent {

  NaoAgent(String ip, int port) {
    super(ip, "InteractionManager", port);
    subscribe("Intention", new IntentionHandler(this));
    subscribe("UserTextFeedback", new InterpretationHandler(this));
    subscribe("QuizResponse", new QuizResponseHandler(this));
    subscribe("QuizCommand", new QuizCommandHandler(this));
    subscribe("QuizQuestion", new QuizQuestionHandler(this));
    subscribe("SystemInfo", new SystemInfoHandler(this));
    subscribe("LowLevelNaoCommand", new LowLevelNaoCommandHandler(this));
  }

  // for the time-sensitive predicates, make a version that takes a boolean[]
  // and sets its first value to true if the information is new.
  // alternatively, we could do this actively: if a piece of information has
  // been used, mark it as used, or clear the container!
  // all methods that change values in this and other RDF proxy classes
  // must signal that some data has changed!
  static final String I_ROBOT = "<pal:nao_0>";

  /**
   * The current activity, if any
   */
  Activity activity = null;

  /**
   * The current user, if any
   */
  Child user = null;

  // see discussion of these two fields in Child
  // RdfList<Session> previousSessions = null;
  // TODO: REPLACE WITH FUNCTION THAT TAKES SESSION END OF PREVSESSIONS
  // INTO ACCOUNT
  Session currentSession = null;

  /**
   * Generate text and motion from a raw speech act representation and send it
   * to the Behaviourmanager
   */
  @Override
  protected DialogueAct createEmitDA(String diaActType, String proposition,
          String ... args) {
    // args is a list of key/value pairs, add default parameters
    String[] allArgs = Arrays.copyOf(args, args.length + 4);
    allArgs[args.length] = "addressee";
    allArgs[args.length + 1] = (String) user.get("id");
    allArgs[args.length + 2] = "sender";
    allArgs[args.length + 3] = I_ROBOT;
    return super.createEmitDA(diaActType, proposition, allArgs);
  }


  protected void reset() {
    super.reset();
    currentSession = null;
    //previousSessions = null;
    user = null;
    activity = null;
  }

  /*
  protected RdfList<Session> retrievePreviousSessions(Child user) {
    // TODO: GET THE LIST OF PREVIOUS SESSIONS
    return new RdfList<Session>(this);
  }


  protected RdfList<Quiz> retrievePreviousQuizzes(Child user) {
    // TODO: GET THE LIST OF PREVIOUS Quiz games
    return new RdfList<Quiz>(this);
  }
   */
  void setUser(String childUri, String location) {
    user = new Child(this, childUri);
    //user.put(CHILD_NAME, "Jan");
    //user.put(CHILD_GENDER, "masc");
    //user.put(CHILD_AGE, "11");
    //user.put(CHILD_HOBBY, "painting");
    //user.put(CHILD_COLOR, "red");
    user.put(CHILD_LOCATION, location);
    newData();
  }

  /* **********************************************************************/
 /* The next are for the InfoStateAccess functionality */
 /* **********************************************************************/
  /**
   * The next two are for the InfoStateAccess functionality
   */
  public String getActivityName() {
    if (activity == null) {
      return "";
    }
    String actName = activity.getClass().getName().toLowerCase();
    int lastdot = actName.lastIndexOf('.');
    if (lastdot >= 0) {
      return actName.substring(lastdot + 1);
    }
    return actName;
  }

  public Child getUser() {
    return user;
  }

  public String getUserName() {
    String n = (String) user.get(CHILD_NAME);
    return (n == null) ? "" : n;
  }

  public String getUserGender() {
    String n = (String) user.get(CHILD_GENDER);
    if (n == null) {
      return "";
    }
    return (n.toLowerCase().contains("male")) ? "masc" : "fem";
  }

  public String getUserColor() {
    String n = (String) user.getLiking(CHILD_COLOR);
    return (n == null) ? "" : n;
  }

  public String getUserHobby() {
    String n = (String) user.getLiking(CHILD_HOBBY);
    return (n == null) ? "" : n;
  }

  public boolean firstEncounter() {
    return !user.has(CHILD_SESSIONS);
  }

  public boolean isFamiliar() {
    return user.has(CHILD_NAME);
  }

  @SuppressWarnings("unchecked")
  public boolean hasPlayedGame() {
    // TODO: This needs to take into account what game has been proposed
    // currently quiz is hardwired
    if (!user.has(CHILD_SESSIONS)) {
      return false;
    }
    for (Session s : (RdfList<Session>) user.get(CHILD_SESSIONS)) {
      if (s.has(SESSION_ACTIVITIES)) {
        Object o = s.get(SESSION_ACTIVITIES);
        if (o instanceof RdfList) {
          RdfList<?> activities = (RdfList<?>) o;
          for (Object act : activities) {
            if (act instanceof Quiz) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public int getNumberOfQuestionsCurrentAsker() {
    Quiz quiz = (activity != null && activity instanceof Quiz) ? (Quiz) activity : null;
    if (quiz == null) {
      return 0;
    }
    Object asker = quiz.getCurrentAsker();
    RdfList<Turn> turns = (RdfList<Turn>) quiz.get(QUIZ_TURNS);
    int result = 0;
    if (turns != null) {
      for (Turn t : turns) {
        if (t.get(TURN_ASKER).equals(asker)) {
          ++result;
        }
      }
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public String getCurrentAsker() {
    Quiz quiz = (activity != null && activity instanceof Quiz) ? (Quiz) activity : null;
    if (quiz == null) {
      return "unknown";
    }
    RdfList<Turn> turns = (RdfList<Turn>) quiz.get(QUIZ_TURNS);
    if (turns == null || turns.isEmpty()) {
      return "unknown";
    }
    return mapToAgent((String) turns.peekFirst().get(TURN_ASKER));
  }

  /* **********************************************************************/
  /* Up to here InfoStateAccess functionality */
  /* **********************************************************************/

  private boolean childAtHome() {
    return user.isEqual(CHILD_LOCATION, "Home");
  }

  /**
   * Tell the QuizLogic that the quiz starts now, and who starts asking
   */
  protected static void startQuiz(Quiz q, NaoAgent agent) {
    QuizCommand start = new QuizCommand();
    start.setId(generateId());
    start.setCommand("start;" + agent.getUser().get("id"));
    start.setAsker((String) q.getCurrentAsker());
    agent.send(start);
    agent.logger.info("<- Quiz Start {}", System.currentTimeMillis());
  }

  protected static void stopQuiz(Quiz q, NaoAgent agent) {
    QuizCommand stop = new QuizCommand();
    stop.setId(generateId());
    stop.setCommand("stop");
    stop.setAsker((String) q.getCurrentAsker());
    agent.send(stop);
    agent.logger.info("<- Quiz Stop {}", System.currentTimeMillis());
  }

  protected void endQuiz(Quiz quiz, String reason) {
    if (!isMyLastDA("Inform", "AssessingPerformance")
        && !isMyLastDA("ReturnGoodbye", "Meeting")
        && !isMyLastDA("InitialGoodbye", "Meeting")) {
      emitDA("Breaking", "Playing", "Reason", reason);

      quiz.put(QUIZ_STATUS, "wait_for_assessment");
    }
  }

  protected void assessQuizSession(Quiz quiz, String whose) {
    if (whose != null) {
      // Get the amount of questions answered, and the number of correct answers
      int[] results = quiz.getAssessment(I_ROBOT);
      int index = whose.equals("robot") ? 0 : 2;

      emitDA("Inform", "AssessingPerformance", "theme", "Answer",
          "agent", whose, "total", Integer.toString(results[index]),
          "correct", Integer.toString(results[index + 1]));
    }
    quiz.put(QUIZ_STATUS, "wait_for_ending");
  }

  protected void closeSession(Closing closing, DialogueAct close) {
    activity.put(CLOSING_PROPOSED, close);
    user.addToList(CHILD_SESSIONS, currentSession);

    if (childAtHome()) {
      newActivity(new Idle(this));
    } else {
      newTimeout("EndSession", 35000, new Proposal() {
        public void run() {
          SystemInfo endsession = new SystemInfo();
          endsession.setId(generateId());
          endsession.setContent("end_session");
          send(endsession);
          logger.info("<- End Session {}", System.currentTimeMillis());
        }
      });
    }
  }

  /**
   * Tell the QuizLogic to provide a new question
   */
  protected static void requestNextQuestion(NaoAgent agent) {
    // TODO: REMOVE WHEN HAMMER SENDS THE CORRECT COMMAND
    QuizCommand qc = new QuizCommand(generateId(), "question", "");
    //QuizSelectQuestion qc = new QuizSelectQuestion();
    qc.setAsker(
            ((String) ((Quiz) agent.activity).getCurrentAsker())
            .equals(NaoAgent.I_ROBOT)
            ? "robot" : "child");
    // qc.setGoal("");
    agent.send(qc);
    agent.logger.info("<- Quiz Question {}", System.currentTimeMillis());
  }

  private String[] getQuestionArgs(QuizQuestion q, Turn currentTurn) {
    String[] result = {
      "string", q.getQuestionText(),
      "asker", mapToAgent((String) currentTurn.get("Asker")),
      "id", Integer.toString(getNumberOfQuestionsCurrentAsker())
    };
    return result;
  }

  private String[] getAnswerArgs(QuizQuestion q) {
    String[] answerArgs = new String[2 * q.getAnswers().size()];
    int i = 0;
    for (String answer : q.getAnswers()) {
      answerArgs[i] = "string" + (1 + i / 2);
      answerArgs[i + 1] = answer;
      i += 2;
    }
    return answerArgs;
  }

  /**
   * Mimic speech act coming from the user: He reads the question and answers
   */
  public void userReadsQuestion() {
    Quiz quiz = (Quiz) activity;
    Turn currentTurn = quiz.getCurrentTurn();
    if (currentTurn != null) {
      QuizQuestion q = (QuizQuestion) currentTurn.get("QuestionId");
      if (q != null) {
        // Generate next question / answers
        addLastDA("Inform", "Asking", getQuestionArgs(q, currentTurn));
        addLastDA("Inform", "Answering", getAnswerArgs(q));
      }
    }
  }

  public void robotAnswers(String answerString, int number, boolean correct) {
    if (Math.random() > 0.5) {
      // Robot thinks about the anser
      emitDA("Stalling", "Answering");
    }
    Quiz quiz = (Quiz) activity;
    emitDA("Inform", "Answer", "id", Integer.toString(number + 1),
            "string" + Integer.toString(number + 1), answerString);
    quiz.put(QUIZ_STATUS, "answer_given");
    Turn current = quiz.getCurrentTurn();
    current.put("AnswerCorrect", new Boolean(correct));
    lastDAprocessed();
  }

  protected <T extends Activity> T newActivity(T a) {
    currentSession.addToList(SESSION_ACTIVITIES, a);
    activity = a;
    logger.debug("New activity: " + activity.getClass().getSimpleName());
    return a;
  }

  protected void processRules() {
    if (user == null) {
      return;
    }
    if (currentSession == null) {
      currentSession = new Session(this);
      currentSession.put(SESSION_CHILD, user);
      // currentSession.put(SESSION_ACTIVITIES, new RdfList<Activity>(this));
      if (activity == null) {
        newActivity(new Introduction(this));
        /*
        if (! childAtHome()) {
          newActivity(new Introduction(this));
        } else {
          // This has to be done as reaction to a QuizCommand coming from the
          // MyPalHomeApp
          initQuiz();
        }
        */
      }
    }
    processTimeouts();
    processIncomingSpeechActs();
    processRulesIntroduction();
    processRulesQuiz();
    processRulesClosing();
  }

  protected void processTimeouts() {
    if (isTimedout("start")) {
      // if (! user.has("id")) setUser("<dom:child_0>");
      removeTimeout("start");
    }
  }

  public String mapToAgent(String userUri) {
    return (userUri.equals(I_ROBOT)) ? "robot" : "user";
  }

  public String mapAgentToUri(String agentString) {
    return (agentString.equals("robot")) ? I_ROBOT : (String) user.get("id");
  }

  private void initQuiz() {
    Quiz quiz = newActivity(new Quiz(this));
    if (quiz.isEqual(QUIZ_STATUS, "none") || !quiz.has(QUIZ_STATUS)) {
      quiz.put(QUIZ_PLAYER, (String) user.get("id"));
      quiz.put(QUIZ_STATUS, "started");
      quiz.put(QUIZ_BEGINS, new Date());

      quiz.put(QUIZ_TABLETORIENTATION, "child");
    }
  }

  /* Incoming Messages

   XXXGreeting(Meeting) -->
      currentSession.put("greetingReceived", myLastDAs.getFirst());

   Confirm(Playing, theme=Quiz)
      --> Set the activity to a new Game, and possibly extract all
          relevant data? or lazy?
   */
  protected void processIncomingSpeechActs() {
    // TODO: ALL THESE RULES DEFINITELY LOOK AT AN !!UNPROCESSED!!
    // INCOMING DIALOGUE ACT

    if (isLastDA("InitialGreeting", "Meeting")
            || isLastDA("ReturnGreeting", "Meeting")) {
      if (activity instanceof Introduction) {
        Introduction intro = (Introduction) activity;
        intro.put(INTRODUCTION_GREETINGRECEIVED, getLastDA());
      } else {
        logger.warn("greeting while not doing intro.");
      }
      lastDAprocessed();
    }

    if ((isLastDA("AcceptRequest", "Playing") || isLastDA("Confirm", "Playing"))
            && !(activity instanceof Quiz)) {
      initQuiz();
      lastDAprocessed();
    }

    if ((isLastDA("RejectRequest", "Playing") || isLastDA("Disconfirm", "Playing"))
            && (activity != null)
            && !(activity instanceof Quiz) && !(activity instanceof Closing)) {
      // OK, maybe next time
      emitDA("Disconfirm", "Playing");
      newActivity(new Closing(this));
      lastDAprocessed();
    }

    /*
    - if role requested (see processIncomingSpeechActs)
       - if asked for role: accept,
         otherwise accept or reject (current roles stay)
     */
    if (isLastDA("Request", "AssigningRole")
            || isLastDA("AcceptRequest", "AssigningRole")
            || isLastDA("RejectRequest", "AssigningRole")) {
      if (activity == null || !(activity instanceof Quiz)) {
        // TODO: clarification dialogue??
        return;
      }
      String theme = getSlot(getLastDA(), "theme");
      String agent = getSlot(getLastDA(), "agent");
      if (null == agent) {
        agent = "user";
      } else // turn agent around
       if (isLastDA("RejectRequest", "AssigningRole")) {
          agent = "user".equals(agent) ? "robot" : "user";
        }
      String currentAgent = mapAgentToUri(agent);

      // TODO check if theme matches current activity (e.g. quiz game)
      Quiz quiz = (Quiz) activity;
      Turn t = quiz.newTurn();
      t.setAsker(currentAgent,
              (currentAgent.equals(I_ROBOT) ? (String) user.get("id") : I_ROBOT));
      // Todo: see above, not yet implemented, always accept
      if (true) { // accept role
        emitDA("Accept", "AssigningRole", "theme", theme,
                "agent", agent);
        /* This is more or less a duplication of the above
        emitDA("Inform", "Instructing", "theme", theme,
                "agent", agent, "purpose", "Asking");
        */
        if (! childAtHome()) {
          // THIS IS ONLY THE COMMUNICATION WITH THE QUIZ LOGIC
          // this is sending a QuizCommand(start_hospital, whereas in the home
          // situation, this command comes from the MyPalApp
          startQuiz(quiz, this);
        }
        if (quiz.isEqual(QUIZ_STATUS, "started")) {
          quiz.put(QUIZ_STATUS, "running");
        }
      }
      lastDAprocessed();
    }

    // The child has read question and answers, now the robot has to give an
    // answer
    if (isLastDA("Inform", "Answering")) {

      Quiz quiz = (Quiz) activity;
      Turn currentTurn = quiz.getCurrentTurn();
      QuizQuestion q = (QuizQuestion) currentTurn.get("QuestionId");
      int correct = q.getWhichCorrect();
      // Answers were read by child, give an answer

      propose("give_correct_answer", new Proposal() {
        public void run() {
          robotAnswers(q.getAnswers().get(correct), correct, true);
          // maybe invalidate the lastDAs, also below
        }
      });

      propose("give_wrong_answer", new Proposal() {
        public void run() {
          int number = 0;
          while ((number = random.nextInt(q.getAnswersSize())) == correct) {
          }
          robotAnswers(q.getAnswers().get(number), number, false);
        }
      });
      lastDAprocessed();
    }

  }

  boolean waitingForResponse() {
    // if my last DA was a request or a question, and there is no newer incoming
    // da, i'm waiting for an answer.
    DialogueAct myLast = getMyLastDA();
    if (myLast == null) {
      return false;
    }
    // TODO we need the hierarchy here, otherwise it's really awkward
    String[] towaitfor = {
      "Question", "Request", "WhQuestion", "YNQuestion"
    };
    String myLastType = myLast.dag.getValue(DagNode.TYPE_FEAT_ID).getTypeName();
    boolean questionPosed = Arrays.binarySearch(towaitfor, myLastType) >= 0;
    if (!questionPosed) {
      return false;
    }
    DialogueAct lastDA = getLastDA();
    return (lastDA == null || lastDA.timeStamp <= myLast.timeStamp);
  }


  /* **********************************************************************
   * The rule section : Introduction
   *   - no data for color, name, hobby, age : ask for data
   *   - if hobby given that NAO shares: inform about it
   *   - if only hobby given : ask about other hobbies
   * ********************************************************************* */
  protected void processRulesIntroduction() {
    if (activity == null || !(activity instanceof Introduction)) {
      return;
    }

    Introduction intro = (Introduction) activity;
    // new session: greet user
    // TODO: ADD INFO FUNCTION TO GENERATION SO IT CAN CAPTURE USER NAME ETC.
    if (user.get("id") != null) {
      if (getMyLastDA() == null) {
        propose("greeting", new Proposal() {
          public void run() {
            String dialogueAct
                    = firstEncounter() ? "InitialGreeting" : "ReturnGreeting";
            DialogueAct da = emitDA(dialogueAct, "Meeting");
            // the default for the timeout name should be the proposal name
            // timeouts.newTimeout("greeting", 10000);
            // TODO: add the timeout if you want to do something in case the
            // child does not react.
            intro.put(INTRODUCTION_GREETING, da);
            try {
              Thread.sleep(900);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            // TODO: CONDITION THIS ON THE FIRST SESSION
            // TODO: HAVE A REASONABLE PAUSE BETWEEN TO TWO SENTENCES
            String slot = CHILD_NAME;
            if (user.get(slot) == null
                    && lastOccurenceOfMyDA("Request", slot) < 0) {
              // TODO: USE SAME LABELS IN GRAMMAR AND DATABASE
              //Max: ERROR? TODO: there is no Request(Name) DA!
              emitDA("Request", "Name");
            }
          }
        });
      }

      // All these rules require that a greeting has taken place
      if (intro.has(INTRODUCTION_GREETING)
              && intro.has(INTRODUCTION_GREETINGRECEIVED)
              && !waitingForResponse()) {
        if (childAtHome()) {
          newActivity(new Idle(this));
          return;
        }

        if (!intro.has(INTRODUCTION_GAMEPROPOSED)) {
          // TODO: GET LIST FROM THE PROPERTIES OF A USER
          String[] slots = {CHILD_HOBBY, CHILD_COLOR};
          for (String slot : slots) {
            // SLOT unknown: ask for slot
            // Ask for favorite hobby and color
            if (user.getLiking(slot) == null
                    && lastOccurenceOfMyDA("Request", slot) < 0) {
              propose("ask_for_preference_" + slot.toLowerCase(), new Proposal() {
                public void run() {
                  emitDA("Request", "Liking", "theme", slot);
                }
              });

            }

            // Nao says he also likes the hobby/color
            // TODO: Check whether Nao shares the hobby / likes the color.
            if (user.getLiking(slot) != null
                    && isLastDA("Inform", "Liking", "theme", slot)
                    && !isMyLastDA("Confirm", "Liking", "theme", slot)) {
              logger.info("We are talking about " + slot);
              propose("like_preference_" + slot.toLowerCase(), new Proposal() {
                public void run() {
                  emitDA("Confirm", "Liking", "theme", slot);
                }
              });
            }
          }

          // TODO: GET LIST FROM THE PROPERTIES OF A USER
          String[] infoslots = {CHILD_BIRTHDATE};
          // TODO: USE SAME LABELS IN GRAMMAR AND IN DATABASE
          String[] mapped = {"Age"};
          int i = 0;
          for (String slot : infoslots) {
            // SLOT unknown: ask for slot
            final String daLabel = mapped[i];
            if (user.get(slot) == null
                    && lastOccurenceOfMyDA("Request", daLabel) < 0) {
              propose("ask_for_" + daLabel, // slot.toLowerCase()
                      new Proposal() {
                public void run() {
                  emitDA("Request", daLabel);
                }
              });
            }
            ++i;
          }
        }

        propose("play_game", new Proposal() {
          public void run() {
            // TODO: isn't this rather a propose
            //DialogueAct da = emitDA("YNQuestion", "Playing", "theme", "Quiz");
            DialogueAct da = emitDA("Request", "Playing", "theme", "Quiz");
            intro.put(INTRODUCTION_GAMEPROPOSED, da);
          }
        });

      }
    }
  }  // end processRulesIntroduction

  /* **********************************************************************
   * The rule section : Quiz
   * ********************************************************************* */
  protected void processRulesQuiz() {
    if (activity == null || !(activity instanceof Quiz)) {
      return;
    }

    Quiz quiz = (Quiz) activity;
    logger.info("Quiz status: {}, Asker: {}", quiz.get(QUIZ_STATUS),
            quiz.getCurrentAsker());

    /* - just_started
         - if first time: give explanation
         - ask for role
         - if role assigned: --> in_game

       - if asked for role, wait for reaction for some time, then propose role

       - if role requested (see processIncomingSpeechActs)
         - if asked for role: accept,
           otherwise accept or reject (current roles stay)
     */
    if (quiz.isEqual(QUIZ_STATUS, "started")) {
      logger.info("Quiz started {}", System.currentTimeMillis());
      int lastInfo = lastOccurenceOfMyDA("Inform", "Instructing", "theme", "Quiz");
      if (!user.hasPlayedQuiz() && lastInfo < 0) {
        propose("inform_gamerules", new Proposal() {
          public void run() {
            emitDA("Inform", "Instructing", "theme", "Quiz");
          }
        });
      }

      if ((user.hasPlayedQuiz()
              || lastOccurenceOfMyDA("Inform", "Instructing", "theme", "Quiz") >= 0)
              && quiz.getCurrentAsker() == null
              && !waitingForResponse()) {
        propose("ask_for_user_starts", new Proposal() {
          public void run() {
            emitDA("Request", "AssigningRole", "theme", "Quiz", "agent", "user");
            // TODO: ADD: RE-ASK WHEN NO REACTION: MAYBE DO IT MORE GENERAL
            // timeouts.newTimeout("ask_for_role", 10000);
          }
        });
        propose("ask_for_robot_starts", new Proposal() {
          public void run() {
            emitDA("Request", "AssigningRole", "theme", "Quiz", "agent", "robot");
            // TODO: ADD: RE-ASK WHEN NO REACTION: MAYBE DO IT MORE GENERAL
            // timeouts.newTimeout("ask_for_role", 10000);
          }
        });
      }
    }

    if (quiz.isEqual(QUIZ_STATUS, "wait_for_ending")
        || quiz.isEqual(QUIZ_STATUS, "ended_by_user")) {
      logger.info("we are in endQuiz");
      propose("endQuiz", new Proposal() {
        @Override
        public void run() {
          String dialogueAct = firstEncounter() ? "InitialGoodbye" : "ReturnGoodbye";
          if (childAtHome()) {
            // TODO this is not really necessary/right, since if it is the first
            // time, the robot should use the initial goodbye, which will never
            // occur in our setup.
            dialogueAct = "ReturnGoodbye";
          }
          DialogueAct close = emitDA(dialogueAct, "Meeting", "theme", "Quiz");
          if (quiz.isEqual(QUIZ_STATUS, "wait_for_ending")) {
            // otherwise this comes from the user / app
            stopQuiz(quiz, NaoAgent.this);
          }
          newActivity(new Closing(NaoAgent.this));
          closeSession((Closing) activity, close);
        }
      });
    }

    // TAKE CARE THAT YOU PROPERLY UPDATE THIS METHOD WHEN ADDING A NEW QUIZ
    // STATUS
    // This should comprise all rules that need a valid asker / turn
    if (quiz.isRunning()) {
      processRulesQuizRunning(quiz);
    }
  }  // end processRulesQuiz

  /*
   - quiz.status == running
    - no question asked [beginning of turn]
      - with tablet seesaw
        - tablet points wrongly to the Robot --> /turn_tablet/
                                   ... User --> /request_turn_tablet/
          [wait_for_tablet]
      - retrieve question/answer from quizdb
        - real robot: /asks_question/, /presents_answers/, /turns_tablet/
          virtual robot: asks_question (answers on tablet)
          --> [wait_for_answer]
        - user: question & answers are presented to the child on the tablet,
          with real robot: read to the robot
          --> [wait_for_answer]
    - [wait_for_answer]
      - asker == robot, wait, maybe re-ask after longer silence, or do small
          gestures (get increasingly nervous? /get_nervous/)
        - user gives answer:
          interpret answer given, check if correct
          /feedback_on_answer/
          if wrong answer /inform_correctAnswer/ --> [answer_given]
             or  /propose_retry/
      - asker == user: "think" gestures and small noise,
        select answer and give_answer --> [answer_given]

    - [answer_given]
      - asker robot & explanation available & wrong answer
        --> /ask_explanation_wanted/ | /present_explanation/
      - asker child & answer wrong
        --> /ask_explanation/ | /retry_requested/ | /feedback_self_wrong/
        & answer right
        --> /ask_explanation_wanted/ | /feedback_self_correct/

    - [retry_rejected]
      - robot feedback (confirm_reject) --> [beginning of turn]

    - [explanation_offered] & accepted --> /present_explanation/
   */
  protected void processRulesQuizRunning(Quiz quiz) {

    // TODO: THIS IS ONLY A TEMPORARY SOLUTION TO FIX ISSUE dialogmanager:#10
    if (quiz.isEqual(QUIZ_STATUS, "running")) {
      boolean askerRobot = quiz.getCurrentAsker().equals(I_ROBOT);
      if (askerRobot && !quiz.isEqual(QUIZ_TABLETORIENTATION, "robot")) {
        if (childAtHome()) {
          quiz.put(QUIZ_TABLETORIENTATION, "robot");
          logger.info("Turn tablet to robot in home version here.");
          userReadsQuestion();
        } else {
          newTimeout("TurnTabletToRobot", 10000, new Proposal() {
            @Override
            public void run() {
              if (!(activity instanceof Quiz)) {
                return;
              }
              Quiz q = (Quiz) activity;
              boolean askerRobotNow = q.getCurrentAsker().equals(I_ROBOT);
              if (q.isEqual(QUIZ_STATUS, "wait_for_question_read")
                  && !askerRobotNow
                  && q.isEqual(QUIZ_TABLETORIENTATION, "child")) {
                // Change the tablet orientation automatically in the home version.
                propose("request_turn_tablet", new Proposal() {
                  public void run() {
                    emitDA("Request", "Turning", "theme", "Tablet");
                    // maybe introduce a timeout to ask again
                  }
                });
              }
            }
          });
        }
      }
    }

    // Question asked already??
    if (!quiz.getCurrentTurn().has(TURN_QUESTIONID)
            && !quiz.isEqual(QUIZ_STATUS, "wait_for_question")
            && !quiz.isEqual(QUIZ_STATUS, "wait_for_continue")) {

      logger.info("REQUEST NEW QUESTION, THE CURRENT ASKER IS {}, THE CURRENT RESPONDER IS {}",
              quiz.getCurrentAsker(),
              quiz.getCurrentAsker().equals(I_ROBOT) ? user.get("id") : I_ROBOT);

      // retrieve question/answer from quizdb
      requestNextQuestion(this);
      quiz.put(QUIZ_STATUS, "wait_for_question");
      // TODO: ADD A CONTINOUS TIMER THAT RETRIES SEVERAL TIMES IF NO QUESTION
      // COMES, AND AFTER TWO RETRIES COMES BACK TO THE USER AND TELLS HER/HIM
      // THERE ARE TECHNICAL PROBLEMS

    } else if (quiz.isEqual(QUIZ_STATUS, "wait_for_tablet")) {
      // CurrentAsker has the correct value now, a question arrived
      boolean askerRobot = quiz.getCurrentAsker().equals(I_ROBOT);
      if (!quiz.isEqual(QUIZ_TABLETORIENTATION,
              askerRobot ? "robot" : "child")) {
        /*
        if (! isMyLastDA("Request", "Turning")
            && ((askerRobot && isMyLastDA("Inform", "Turning"))
                || (!askerRobot && isLastDA("Inform", "Answering")))) {
          propose("request_turn_tablet", new Proposal() {
            public void run() {
              emitDA("Request", "Turning", "theme", "Tablet");
              // maybe introduce a timeout to ask again
            }
          });
        }
         */
      } //      }
      else // tablet orientation is OK, question will be posed by robot or child
      {
        if (quiz.getCurrentAsker().equals(I_ROBOT)) {
          // The robot reads the question and answers
          propose("give_next_question", new Proposal() {
            public void run() {
              Turn current = quiz.getCurrentTurn();
              QuizQuestion q = (QuizQuestion) current.get(Turn.TURN_QUESTIONID);
              // Generate next question / answers
              // This needs to know the number of questions that was asked
              // by this agent, done by infoState
              emitDA("Inform", "Asking", getQuestionArgs(q, current));
              emitDA("Inform", "Answering", getAnswerArgs(q));

              if (!childAtHome()) {
                emitDA("Inform", "Turning", "theme", "Tablet", "manner", "silent");
              } else {
                DialogueAct da = createEmitDA(
                    "Inform", "Turning", "theme", "Tablet", "manner", "silent");
              }
              // change status: maybe rather to wait_for_tablet!!!!
              quiz.put(QUIZ_STATUS, "wait_for_answer");

              // not needed in home version
              if (!childAtHome()) {
                newTimeout("TurnTabletToChild", 20000, new Proposal() {
                  @Override
                  public void run() {
                    if (!(activity instanceof Quiz)) {
                      return;
                    }
                    Quiz q = (Quiz) activity;
                    boolean askerRobotNow = q.getCurrentAsker().equals(I_ROBOT);
                    if (q.isEqual(QUIZ_STATUS, "wait_for_answer")
                        && askerRobotNow
                        && q.isEqual(QUIZ_TABLETORIENTATION, "robot")) {
                      propose("request_turn_tablet", new Proposal() {
                        public void run() {
                          emitDA("Request", "Turning", "theme", "Tablet");
                          // maybe introduce a timeout to ask again
                        }
                      });
                    }
                  }
                });
              }
            }
          });
        } else {
          // The user is the asker, wait for the user to finish reading
          // the question and answers, if we have a real robot
          quiz.put(QUIZ_STATUS, "wait_for_question_read");

          newTimeout("TurnTabletToRobot", 10000, new Proposal() {
            @Override
            public void run() {
              if (!(activity instanceof Quiz)) {
                return;
              }
              Quiz q = (Quiz) activity;
              boolean askerRobotNow = q.getCurrentAsker().equals(I_ROBOT);
              if (q.isEqual(QUIZ_STATUS, "wait_for_question_read")
                      && !askerRobotNow
                      && q.isEqual(QUIZ_TABLETORIENTATION, "child")) {
                // Change the tablet orientation automatically in the home version.
                if (childAtHome()) {
                  quiz.put(QUIZ_TABLETORIENTATION, "robot");
                  logger.info("Turn tablet to robot in home version here.");
                  userReadsQuestion();
                } else {
                  propose("request_turn_tablet", new Proposal() {
                    public void run() {
                      emitDA("Request", "Turning", "theme", "Tablet");
                      // maybe introduce a timeout to ask again
                    }
                  });
                }
              }
            }
          });
        }
      }
    } else if (quiz.isEqual(QUIZ_STATUS, "answer_given")) {

      Turn current = quiz.getCurrentTurn();
      Boolean correct = (Boolean) current.get(TURN_ANSWERCORRECT);
      String responder = ((String) current.get(TURN_RESPONDER)).equals(I_ROBOT)
              ? "robot" : "user";
      String proposal = "feedback_" + (correct ? "correct_" : "wrong_")
              + responder;

      // give feedback to my or the user's success, <agent> translates to
      // the asker, so be careful!
      propose(proposal, new Proposal() {
        public void run() {
          if (responder.equals("robot")) {
            emitDA("Allo" + (correct ? "Positive" : "Negative"),
                    "BeingSuccessful", "agent", "user", "theme", "Quiz");
            quiz.put(QUIZ_STATUS, "wait_for_continue");
          } else {
            emitDA((correct ? "Confirm" : "Disconfirm"), "BeingCorrect",
                    "theme", "Quiz", "agent", "robot");
            quiz.put(QUIZ_STATUS, "wait_for_continue");
            if (!correct) {
              QuizQuestion q = (QuizQuestion) current.get(Turn.TURN_QUESTIONID);
              String solutionNo = Integer.toString(q.getWhichCorrect() + 1);
              emitDA("Inform", "GivingSolution",
                      "string" + solutionNo, q.answers.get(q.getWhichCorrect()),
                      "id", solutionNo);
            }
            quiz.put(QUIZ_STATUS, "wait_for_continue");
          }
        }
      });

      // since currently one proposal is always taken, we just propose also
      // a continue_quiz
      // TODO: adapt once that changes
      quiz.put(QUIZ_STATUS, "wait_for_feedback");
    }

    if (quiz.isEqual(QUIZ_STATUS, "wait_for_continue")) {
      /*
      propose("role_change", new Proposal() {
        public void run() {
          emitDA("provide", "role", "theme", "quiz");
        }
      });
       */
      // TODO FIX THIS, ONLY FOR TESTING !!!!!
      if (true || quiz.getNumberOfTurns() < 5) {
        propose("continue_quiz", new Proposal() {
          public void run() {
            Turn last = quiz.getCurrentTurn();
            Turn t = quiz.newTurn();
            // Switch roles after each turn
            t.setAsker((String) last.get(TURN_RESPONDER),
                    (String) last.get(TURN_ASKER));
            quiz.put(QUIZ_STATUS, "running");
          }
        });
      }

      propose("quiz_game_ends_max_time", new Proposal() {
        public void run() {
          endQuiz(quiz, "technical");
        }
      });

      propose("quiz_game_ends_max_rounds", new Proposal() {
        public void run() {
          endQuiz(quiz, "time");
        }
      });
    }

    if (quiz.isEqual(QUIZ_STATUS, "wait_for_assessment")) {

      logger.info("AssessingPerformance");

      propose("AssessUserPerformance", new Proposal() {
        // seems strange, but is correct, this must be "user" and vice versa
        @Override
        public void run() {
          assessQuizSession(quiz, "robot");
        }
      });
      propose("AssessRobotPerformance", new Proposal() {
        @Override
        public void run() {
          assessQuizSession(quiz, "user");
        }
      });
      propose("NoAssessment", new Proposal() {
        @Override
        public void run() {
          assessQuizSession(quiz, null);
        }
      });
    }

    if (childAtHome()
        && isMyLastDA("Inform", "Turning", "theme", "Tablet", "sender", I_ROBOT)
        && ! quiz.isEqual(QUIZ_TABLETORIENTATION, "child")) {
      quiz.put(QUIZ_TABLETORIENTATION, "child");
      logger.info("Turn tablet to child here in Home version.");
    }
  }

  protected void processRulesClosing() {
    if (!(activity instanceof Closing)) {
      return;
    }

    Closing closing = (Closing) activity;

    if (!closing.has(CLOSING_PROPOSED)) {
      closing.put(CLOSING_PROPOSED, "null");

      propose("closing", new Proposal() {
        public void run() {

          logger.info("When does this happen?");

          String dialogueAct = firstEncounter() ? "InitialGoodbye" : "ReturnGoodbye";
          logger.info("emitDA ReturnGoodby in method processRulesClosing()");

          if (! childAtHome()) {
            NaoAgent.this.closeSession(closing, emitDA(dialogueAct, "Meeting"));
          } else {
            NaoAgent.this.closeSession(closing, emitDA("ReturnGoodbye", "Meeting"));
          }
        }
      });
    }
  }

}
