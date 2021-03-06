// RULES FROM THE "IDLE" NODE

/* what should rule labels look like?
   if[check-start] (test) ....

   check-start: // or sth else than ':' ?
   if (test)
*/

// A new game is started
initiated:
if (game.status == initiated) {
  propose("start-game") {
    // gameTypePlayed is a predefined custom query (ASK)
    if (currentUser.gameTypePlayed(game)) {
      emitDA(@Inform(Instructions, what = game.name, sender = I_ROBOT, addressee = currentUser.id));
    }
    gameLogic.startSession();
  }
}

ask_for_role:
if (game.status == startSession && ! myLastSA <= @ChoiceQuestion(Role)) {
  if (gameLogic.isTurnBased()) {
    emitDA(@ChoiceQuestion(Role));
  } else {
    gameLogic.newRound();
  }
}

// A role has been chosen by the game partner
role_chosen:
if (currentSA <= @Inform(Role, hasActor = _) && (game.status == startSession)) {
  propose("roleChosen-newRound") {
    var actor = currentSA.hasActor;

    // this is a transaction of three actions!
    emitDA(@Confirm(Role, hasActor = actor));
    emitDA(@Inform(Instructions, what = game.name, active = game.activeParticipant));
    gameLogic.newRound(actor);
    /* GAME LOGIC START: This is injected by the game logic!
    game.activeParticipant = actor;
    game.status = inSession; // previously: SE.gameTurn
    // GAME LOGIC END
    */
  }
}

ask_for_turntablet:
/*
 oneOf(game.tablet, tablet => (tablet.isAvailable &&
    tablet.supposedOrientation != tablet.currentOrientation))
 allOf(...)
 */
if (game.tablet.isAvailable
    && game.tablet.supposedOrientation != game.tablet.currentOrientation) {
  var last = lastSubsumedDA(@Request(Turn, what = tablet, sender = I_MYSELF));
  if (last != null && last.time - currentTime > MAX_WAIT_FOR_TABLET) {
    propose("request_turntablet") {
      emitDA(@Request(Turn, what = tablet, sender = I_MYSELF));
    }
  }
}

give_next_question:
// if inSession, which means a new turn is in progress, do something
if (game.status == inSession && game.activeParticipant == I_MYSELF
    // GAME LOGIC START
    && (! game.tablet.isAvailable
        || game.tablet.orientation == POINTS_TO_ME)) {
  propose("ask-question") {
    var qa = gameLogic.getNewQuestionAndAnswers();
    // game.lastMove.Question = qa; // done by game logic
    emitDA(@ChoiceQuestion(Quiz, what = qa.question, answers = qa.answers));
    if (game.tablet.isAvailable)
      emitDA(@Inform(Turn, hasTheme = tablet)); // GAME LOGIC??
    // game.status = questionAsked; // done by game logic
  }
}

repeat_sth_requested:
//                                   vvv should be implied by prev clause
if (game.status == questionAsked && game.lastMove.Question != null
    && game.activeParticipant == I_MYSELF
    && currentSA <= @Request(Repeat, what = _)) {
  var what = currentSA.what;
  if (what == Question)
    propose("repeat-question") {
      emitDA(@Inform(what, what = lastQuestion.question));
    }
  else if (what == Answers)
    propose("repeat-answers") {
      emitDA(@Inform(what, what = lastQuestion.answers));
    }
  else if (what == Solution && game.status == turnFinished) {
    propose("repeat-solution") {
      emitDA(@Inform(Answer, what = game.lastMove.Question.solution));
    }
  }
}

// An answer was given
answer_given1:
if (currentSA <= @Answer(Quiz, what = _)) {
  /* Done by the game logic!
     game.lastMove.tries++;
     game.lastMove.answer = currentSA.what;
     game.lastMove.answerCorrect =
  */
  game.assessAnswer(currentSA.what);
  // This also turns status from questionAsked to answerGiven
}

answer_given:
if (game.status == answerGiven) {
  if (game.lastMove.answerCorrect) {
    // I have to do this. Or could I cheat??
    propose("inform-correct") {
      emitDA(@Inform(Answer, correct = true));
      /*
        game.status = turnFinished;
        game.activeParticipant = game.nextOpponent();
      */
      gameLogic.finishTurn();
    }
  } else {
    // maybe the next if should be done in the decision process
    if (game.lastMove.tries < game.maxTries) {
      propose("answer-retry") {
        emitDA(@YNQuestion(Retry, what = Answer));
        game.status = questionAsked;
      }
    }
    propose("give-solution") {
      emitDA(@Inform(Answer, what = game.lastMove.Question.solution));
      gameLogic.finishTurn();
    }
  }
}

// Retry offer rejected, go to next turn
retry_rejected:
if (currentSA <= @Reject(Retry, addressee = I_MYSELF)
    && lastSA <= @YNQuestion(Retry, what = Answer, sender = I_MYSELF)) {
  propose("give-solution") {
    emitDA(@Inform(Answer, what = game.lastMove.Question.solution));
    // game.status = turnFinished; + activeParticipant done by game logic
    gameLogic.finishTurn();
  }
}

turn_finished:
// somebody finished a turn
if (game.status == turnFinished) {
  // explanationGiven will be yes in case there is no explanation
  if (game.lastMove.Question.explanationGiven == no) {
    propose("ask-provide-explanation-" + game.lastMove.answerCorrect) {
      emitDA(@YNQuestion(Explanation));
      game.lastMove.explanationGiven = proposed;
      // do you know why? (when correct)
      // do you want to know why? (when incorrect)
    }
    propose("provide-explanation-" + game.lastMove.answerCorrect) {
      emitDA(@Inform(Explanation, what = game.lastMove.Question.explanation));
      game.lastMove.explanationGiven = yes;
    }
  }
}

provide_explanation_if_available:
if (currentSA <= @Request(Explanation)
    && game.lastMove.Question != null) {
  // explanation may be null, which results in "I don't know"
  propose("provide-explanation") {
    emitDA(@Inform(Explanation, what = game.lastMove.Question.explanation));
  }
}
