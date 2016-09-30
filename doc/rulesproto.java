/** The next rules are from treatAsrSpeechact:
    General rules that provide information for underspecified slots, such
    as:
    - If the last speech act is a "reply" to the current, the current will
      most likely refer to it
    - who should be addressed when there's only a pronoun
*/
// or as a macro/function??
infer_speechact_info:
if (((lastSA.type == Request || lastSA.type == YNQuestion)
     && (currentSA.type == Confirm || currentSA.type == Disconfirm))
    || (lastSA.type == Offer
        && (currentSA.type == AcceptOffer || currentSA.type == DeclineOffer))
    || (lastSA.type == WHQuestion && currentSA.type == Inform)) {
  /* Most likely this speech act refers to the last one */
  if (! currentSA.refersTo) {
    currentSA.refersTo = lastSA.id;
  }

  /* If the frame's underspecified, fill it with the frame from the referring
     speech act */
  if (currentSA.Frame == Frame) {
    currentSA.Frame = lastSA.Frame;
  }

  if (currentSA.addressee == "UNKNOWN") {
    currentSA.addressee = lastSA.sender;
  }
} else {
  if (currentSA.addressee == "UNKNOWN") {
    // not referring, so most likely the addressee stays the same
    currentSA.addressee = lastSA.addressee;
  }
}

// again: maybe function / macro? but we really want to add it!
resolve_pronouns:
if (currentSA.hasTheme <= pronoun) {
  // look for the last Theme in a certain time or turn frame that matches
  // the pronoun specification

  // assuming that they come sorted by time, latest first
  var turn = 0;
  //for (sa <- collectionOfSAs.ofType(SpeechAct) if sa.type >= SpeechAct)
  for (sa : sa.type >= SpeechAct) { // OR: query(sa.type >= speechAct)
    ++turn;
    if (sa.time - currentSA.time > MAX_TIME
        || turn > MAX_TURN)
      break;
    if (! sa.hasTheme <= pronoun &
        agreementMatch(sa.hasTheme, currentSA.hasTheme)) {
      currentSA.hasTheme = sa.hasTheme;
    }
  }
}

/** If an offer is Declined, i'll confirm that. I'm the addressee
 */
ack_declined_offer:
if (currentSA.type == DeclineOffer) {
  sa = @Agreement(Frame, sender = currentSA.addressee,
        addressee = currentSA.sender, refersTo = currentSA.id);
  // sa.frame = ( Bringing, Holding ); // multiple assingment
  // sa.frame += Bringing; // add one, could also add multiple
  emitSA(sa);
}

/** If my offer is accepted, get my offer and generate an appropriate action
    out of it
 */
execute_accepted_offer:
if (currentSA.type == AcceptOffer) {
  if (offer == lastMatchingSA(@Offer(_, sender = currentSA.addressee))) {
    createAction(offer.Frame, offer.getArguments(), _something);
  } else {
    propose("clarify") {
      // maybe a misunderstanding?
      // TODO: this is maybe not covered by the current literal syntax:
      // splicing in the last argument
      emitSA(@Clarification(currentSA.Frame, currentSA.getArguments()));
    }
  }
}

/** If something i proposed is disconfirmed, i'll ask for an alternative */
ask_alternative:
if (currentSA.type == Disconfirm) {
  propose("ask-alternative") {
    emitSA(@WHQuestion(currentSA.Frame, refersTo = currentSA.id,
        currentSA.getArguments()));
  }
}

/** If i have a pending "remove" task without the information what to remove,
    and the information is given to me, issue an action if that was the last
    information needed
 */
ask_missing_info:
if ((currentSA.type == Inform || currnetSA.type == Confirm)
    && _pendingTask.frame >= currentSA.frame) {
  // Deconstruction of arguments, also for query results
  for ((arg, val) : _pendingTask.getArguments()) {
    // needs resolution that arg is a variable, not a symbol
    if (!_pendingTask.arg) {
      _pendingTask.arg = val;
    }
  }
  if (isFullySpecified(_pendingTask)) {
    createTask(_pendingTask);
    // possibly inform that the task will now be executed
    emitSA(@Inform(_pendingTask.Frame));
    _pendingTask = null;
  }
}


/** If Gloria gets adressed by the worker that he takes a task, she assigns it
    for him (send a BBChange with processor=<sender>

 TODO: CURRENTLY NOT IN SYNTAX: SUBSUMPTION + BINDING VARIABLES
 */
assign_task:
if (currentSA <= @Inform(Activity, hasActivity =  ? activity, sender =  ? sender)) {
  tasknum = getTaskNum(taskName); // get task no from: task_\([0-9]+\)
  emitBBTask(BBTask.TakeTask, tasknum, sender);
}


/** A person issues a request to bring sth. to Gloria */
process_bring_request:
if (currentSA <= @Request(Bringing, hasTheme = _, sender = _)) {
  var sender = currentSA.sender;
  var theme = currentSA.hasTheme;
  // getLocationOf is a SPARQL query
  var toLoc = getLocationOf(sender);
  var fromLoc = getLocationOf(theme);

  // additional complication: find an empty slot at toLoc
  var toSlot = getEmptySlotAt(toLoc); // might return null or sth. like this

  if (toSlot == NULL) {
    // getPrefBoxToTake() could be a SPARQL query
    prefBox = getPrefBoxToTake();
    if (prefBox == NULL || _lastIncomingSA <= @Decline(Removing)) { // removed three dots behind "Removing" (cannot be parsed)
      propose("ask-box") {
        // which box should i take away?
        emitSA(@WHQuestion(Removing, hasTheme = "box", refersTo = currentSA.id));
      }
    } else { // should i take away the glue?
      propose("take-away") {
        // getContentInBox() could be a SPARQL query
        var content = getContentInBox(prefBox);
        emitSA(@YNQuestion(Removing, hasTheme = content,
        hasAgent = currentSA.addressee,
        refersTo = currentSA.id));
      }
    }
    // the third arg is where to bring the thing from toSlot
    newTask(Bringing, fromLoc, toSlot, fromLoc, theme);
  } else {
    newTask(Bringing, fromLoc, toSlot, fromLoc, theme);
  }
}
/*
// ?? commented out for it cannot be parsed (no label)
if (! _pendingTask.isEmpty()) {
  createCompleteTasks();
}*/
