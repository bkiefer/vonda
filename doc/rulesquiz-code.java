
public class RulesQuiz extends RuleUnit {

  // GameData is an RDF class proxy in java code
  // TODO: The type of game must specified, can not be inferred, same for currentUser
  // and speech act
  // TODO: what about relational properties aka multi-valued features
  // TODO: possibly gamePlayed must be a custom method / ASK query
  GameData game;

  // User is an RDF class proxy in java code
  User currentUser;

  // This must be a real class
  GameLogic gameLogic;

  // SpeechActs, RDF proxies
  DialogueAct myLastSA, currentSA;

  private void computeProxies() {
    // TODO: ... missing definition generating this code
  }

  public void mainLoop() {
    computeProxies();

    execute("initiated");
    if (game.get("status").equals("initiated")) {
      propose("start-game", new Proposal() {
        public void run() {
          if (executeQuery(currentUser, gameTypePlayed, game)) {
            emitDA(new DialogueAct("Inform(Instructions, what=" + game.name
                    + ", sender=I_ROBOT, addressee=" + currentUser.id + ")"));
          }
          gameLogic.startSession();
        }
      });
    }

    execute("ask_for_role");
    if (game.status.equals("startSession")
            && !isSubsumed(myLastSA, new DialogueAct("ChoiceQuestion(Role))"))) {
      if (gameLogic.isTurnBased()) {
        emitDA(new DialogueAct("ChoiceQuestion(Role))"));
      } else {
        gameLogic.newRound();
      }
    }

    // A role has been chosen by the game partner
    execute("role_chosen");
    // TODO: isSubsumed could also be translated into a query
    if (isSubsumed(currentSA, new DialogueAct("Inform(Role, hasActor=_)"))
            && (game.status.equals("startSession"))) {
      propose("roleChosen-newRound", new Proposal() {
        public void run() {
          var actor = currentSA.hasActor;

          // this is a transaction of three actions!
          emitDA(new DialogueAct("Confirm(Role, hasActor=" + actor + "))"));
          emitDA(new DialogueAct("Inform(Instructions, what=" + game.name
                  + ", active=" + game.activeParticipant));
          gameLogic.newRound(actor);
          /* GAME LOGIC START: This is injected by the game logic!
               game.activeParticipant = actor;
               game.status = inSession; // previously: SE.gameTurn
               // GAME LOGIC END
           */
        }
      });
    }

    execute("ask_for_turntablet");
    /*
       // for relational tablet feature: functional quantifier, returns first
       // object that fulfills the test
    oneOf(game.tablet, new Apply() { public boolean apply(tablet) {
      return (tablet.isAvailable
              && tablet.supposedOrientation != tablet.currentOrientation);
      }});
     */
    if (game.tablet.isAvailable
            && game.tablet.supposedOrientation != game.tablet.currentOrientation) {
      // lastMatchingSA could be an existing function. Problem: might return
      // null
      var last = lastSubsumedDA(new DialogueAct("Request(Turn, what=tablet, sender=" + I_MYSELF + "))"));
      if (last != null && last.time - currentTime > MAX_WAIT_FOR_TABLET) {
        propose("request_turntablet", new Proposal() {
          public void run() {
            emitDA(new DialogueAct("Request(Turn, what=tablet, sender=" + I_MYSELF + "))"));
          }
        });
      }
    }

    execute("give_next_question");
    // if inSession, which means a new turn is in progress, do something
    if (game.status.equals("inSession") && game.activeParticipant == I_MYSELF
            // GAME LOGIC START
            && (!game.tablet.isAvailable
            || game.tablet.orientation == POINTS_TO_ME)) {
      propose("ask-question", new Proposal() {
        public void run() {
          var qa = gameLogic.getNewQuestionAndAnswers();
          // game.lastMove.Question = qa; // done by game logic
          emitDA(new DialogueAct("ChoiceQuestion(Quiz, what=" + qa.question
                  + ", answers=" + qa.answers));
          if (game.tablet.isAvailable) {
            emitDA(@Inform(Turn, hasTheme=tablet));  // GAME LOGIC??
          }            // game.status = questionAsked; // done by game logic
        }
      });
    }

    execute("repeat_sth_requested");
    //                                  vvv should be implied by prev clause
    if (game.status.equals("questionAsked") && game.lastMove.Question != null
            && game.activeParticipant == I_MYSELF
            && isSubsumed(currentSA, new DialogueAct("Request(Repeat, what=_))"))) {
      var what = currentSA.what;
      // Question, Answer, Solution are RDF classes, how do we handle this???
      if (what == Question) {
        propose("repeat-question", new Proposal() {
          public void run() {
            emitDA(new DialogueAct("Inform(" + what + ", what=" + lastQuestion.question));
          }
        });
      } else if (what == Answers) {
        propose("repeat-answers", new Proposal() {
          public void run() {
            emitDA(new DialogueAct("Inform(" + what + ", what=" + lastQuestion.answers));
          }
        });
      } else if (what == Solution && game.status == turnFinished) {
        propose("repeat-solution", new Proposal() {
          public void run() {
            emitDA(new DialogueAct("Inform(Answer, what=" + game.lastMove.Question.solution));
          }
        });
      }
    }

    // An answer was given
    execute("answer_given1");
    if (isSubsumed(currentSA, new DialogueAct("Answer(Quiz, what=_)"))) {
      /* Done by the game logic!
         game.lastMove.tries++;
         game.lastMove.answer = currentSA.what;
         game.lastMove.answerCorrect =
       */
      game.assessAnswer(currentSA.what);
      // This also turns status from questionAsked to answerGiven
    }

    execute("answer_given");
    if (game.status.equals("answerGiven")) {
      if (game.lastMove.answerCorrect) {
        // I have to do this. Or could I cheat??
        propose("inform-correct", new Proposal() {
          public void run() {
            emitDA(new DialogueAct("Inform(Answer, correct=true))"));
            /*
                game.status = turnFinished;
                game.activeParticipant = game.nextOpponent();
             */
            gameLogic.finishTurn();
          }
        });
      } else // maybe the next if should be done in the decision process
      {
        if (game.lastMove.tries < game.maxTries) {
          propose("answer-retry", new Proposal() {
            public void run() {
              emitDA(new DialogueAct("YNQuestion(Retry, what=Answer))"));
              game.status = questionAsked;
            }
          });
          propose("give-solution", new Proposal() {
            public void run() {
              emitDA(new DialogueAct("Inform(Answer, what=" + game.lastMove.Question.solution + "))"));
              gameLogic.finishTurn();
            }
          });
        }
      }
    }

    // Retry offer rejected, go to next turn
    execute("retry_rejected");
    if (isSubsumed(currentSA, new DialogueAct("Reject(Retry, addressee=" + I_MYSELF + ")"))
            && isSubSumed(lastSA, new DialogueAct("YNQuestion(Retry, what=Answer, sender=" + I_MYSELF + "))"))) {
      propose("give-solution", new Proposal() {
        public void run() {
          emitDA(new DialogueAct("Inform(Answer, what=" + game.lastMove.Question.solution + "))"));
          // game.status = turnFinished; + activeParticipant done by game logic
          gameLogic.finishTurn();
        }
      });
    }

    execute("turn_finished");
    // somebody finished a turn
    if (game.status.equals("turnFinished")) {
      // explanationGiven will be yes in case there is no explanation
      if (game.lastMove.Question.explanationGiven.equals("no")) {
        propose("ask-provide-explanation-" + game.lastMove.answerCorrect, new Proposal() {
          public void run() {
            emitDA(new DialogueAct("YNQuestion(Explanation))"));
            // TODO: changed data: write back to DB!!!
            game.lastMove.explanationGiven = "proposed";
            // do you know why? (when correct)
            // do you want to know why? (when incorrect)
          }
        });
        propose("provide-explanation-" + game.lastMove.answerCorrect, new Proposal() {
          public void run() {
            emitDA(new DialogueAct("Inform(Explanation, what=" + game.lastMove.Question.explanation + "))"));
            game.lastMove.explanationGiven = "yes";
          }
        });
      }
    }

    execute("provide_explanation_if_available");
    if (isSubsumed(currentSA, new DialogueAct("Request(Explanation)"))
            && game.lastMove.Question != null) {
      // explanation may be null, which results in "I don't know"
      propose("provide-explanation", new Proposal() {
        public void run() {
          emitDA(new DialogueAct("Inform(Explanation, what=" + game.lastMove.Question.explanation + "))"));
        }
      });
    }
  }
}
