package de.dfki.mlt.rudimant.agent;

import static de.dfki.mlt.rudimant.common.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbApiHandler;
import de.dfki.lt.hfc.db.server.StreamingClient;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import de.dfki.mlt.rudimant.common.RuleLogger;

/**
 *
 * @author chbu02, Bernd Kiefer
 */
public abstract class Agent implements StreamingClient {

  public static final Logger logger = LoggerFactory.getLogger(Agent.class);

  private static DialogueAct IMPOSSIBLE_DIALOGUEACT;

  /** To generate unique IDs for behaviours, etc. */
  protected static int _generatorCounter = -1;
  public String idPrefix = "";

  protected String executedLast = null;

  protected String _language;

  // TODO: that's not nice. The mood is transported in the Intention, and
  // i set this field in the intention manager.
  public double proposedRobotMood = 0;

  protected Random random = new Random(System.currentTimeMillis());

  public abstract class Proposal implements Runnable {
    public String name;
  }

  /** takes care of the logging for the execution of rules */
  public RuleLogger ruleLogger;

  /** The object that is responsible for outgoing communication */
  protected CommunicationHub _hub;

  /** A class that cares about ASR events and interpretation */
  protected AsrTts asr;

  /** Is new data in the repository */
  private boolean newData = false;

  /** The DAs I emitted, newest first, DON'T CHANGE FROM DERIVED CLASSES */
  protected LinkedList<DialogueAct> myLastDAs;
  // protected int myUnprocessedDAs;

  /** The DAs I received, newest first */
  private LinkedList<DialogueAct> lastDAs;
  protected long lastDAprocessed = -1;
  //protected int unprocessedDAs;

  private Timeouts timeouts = new Timeouts();

  /** Proposals to be executed in the next round, coming from fired timeouts
   *  or other triggers, like finished behaviours
   */
  protected Deque<Proposal> proposalsToExecute;

  /** The set of all proposals generated in one (fixpoint) run of the rules */
  public Map<String, Proposal> pendingProposals = new HashMap<>();

  /** Are we waiting for a proposal to be selected? In this case, put all
   *  incoming events into the event queue
   */
  protected boolean proposalsSent;

  public RdfProxy _proxy;

  /** Send something out to the world */
  protected void sendBehaviour(Object obj) {
    // TODO implement it, possibly with a Listener.
  }

  /** Generate a unique id, e.g., for the behaviours */
  public String generateId() {
    return idPrefix +
        (int) ((System.currentTimeMillis() & 0xFF) +
            (_generatorCounter++ << 8));
  }

  // TODO: we need sth like this to use the dialogue history; is this the right way?
  protected Deque<DialogueAct> getLastDAs(){
    return lastDAs;
  }

  protected void reset() {
    timeouts.clear();
    myLastDAs = new LinkedList<>();
    //myUnprocessedDAs = 0;
    lastDAs = new LinkedList<>();
    //unprocessedDAs = 0;
    proposalsToExecute = new LinkedList<>();
    proposalsSent = false;
  }

  public static String putTogether(char sep, String... strings) {
    if (strings.length == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(strings[0]);
    for (int i = 1; i < strings.length; ++i) {
      sb.append(sep).append(strings[i]);
    }
    return sb.toString();
  }

  public static String putTogether(char sep, Iterable<String> strings) {
    Iterator<String> it = strings.iterator();
    if (!it.hasNext()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(it.next());
    while (it.hasNext()) {
      sb.append(sep).append(it.next());
    }
    return sb.toString();
  }

  public static String[] tearApart(char sep, String s) {
    String pat = ((".*".indexOf(sep) >= 0) ? "\\" : "") + sep;
    String result[] = s.split(pat, -1);
    return result;
  }

  private static DialogueAct getImpossibleDialogueact() {
    if (IMPOSSIBLE_DIALOGUEACT == null) {
      IMPOSSIBLE_DIALOGUEACT = new DialogueAct("Bottom(Bottom)");
    }
    return IMPOSSIBLE_DIALOGUEACT;
  }

  // Constructors ************************************************************
  // public Agent() {}

  // **********************************************************************
  // StreamingClient init() & compute() function to register changes in DB
  // **********************************************************************
  @Override
  public void init(HfcDbApiHandler handler) {
    // nothing to do
  }

  @Override
  public void compute() {
    newData();
  }


  // **********************************************************************
  // DialogueAct functions
  // **********************************************************************

  /** Generate DialogueAct from a raw speech act representation */
  public DialogueAct addToMyDA(DialogueAct da) {
    myLastDAs.addFirst(da);
    // ++myUnprocessedDAs;
    newData();
    return da;
  }

  /** Send a Behaviour to the Behaviourmanager (communication hub) */
  public final void emitBehaviour(Behaviour b) {
    lastBehaviourId = b.getId();
    _hub.sendBehaviour(b);
  }

  /** Generate text and motion from a raw speech act representation and send it
   *  to the Behaviourmanager
   */
  public DialogueAct emitDA(int delay, DialogueAct da) {
    Pair<String, String> toSay = asr.generate(da.getDag());
    emitBehaviour(new Behaviour(generateId(), toSay.second, toSay.first, delay));
    // enter into database
    daToDB(da);
    return addToMyDA(da);
  }

  /** Generate text and motion from a raw speech act representation and send it
   * to the Behaviourmanager
   */
  public DialogueAct emitDA(DialogueAct da) {
    return emitDA(Behaviour.DEFAULT_DELAY, da);
  }

  /** The last dialogue act spoken by the agent */
  public DialogueAct myLastDA() {
    DialogueAct result = myLastDAs.peekFirst();
    return (result == null ? getImpossibleDialogueact() : result);
    //if (myUnprocessedDAs == 0) return null;
    //return myLastDAs.get(myUnprocessedDAs - 1);
  }

  //public void myLastDAprocessed() { --myUnprocessedDAs; }

  /** Return the index of the last speech act equal or more specific than the
   *  given one. Zero means there is no such speechact, valid indices start at
   *  one, which means that if it should be retrieved from the respective list,
   *  one position must be subtracted from the result.
   */
  protected int lastOccurence(DialogueAct da, Iterable<DialogueAct> daList) {
    int i = 1;
    for (DialogueAct evt : daList) {
      if (da.subsumes(evt)) {
        return i;
      }
      ++i;
    }
    return 0;
  }

  /** When did i say this in this session?
   *  Zero means never, that was my last utterance.
   */
  public int saidInSession(DialogueAct da) {
    return lastOccurence(da, myLastDAs);
  }

  /** Am I currently waiting for a response?
   *  The condition is: I was the last to say something, and my dialogue act
   *  was a question or a request.
   * @return true if i'm waiting for a response.
   */
  public boolean waitingForResponse() {
    // if my last DA was a request or a question, and there is no newer incoming
    // da, i'm waiting for an answer.
    DialogueAct myLast = myLastDA();
    //logger.error("waitResp: {} ", myLast);
    if (myLast == null) {
      return false;
    }
    // Do not use lastDA() here! it may return null because of lastDAprocessed(),
    // not because the queue is empty
    DialogueAct lastDA = lastDAs.peekFirst();
    final DialogueAct[] requests = {
        new DialogueAct("Question(top)"),
        new DialogueAct("Request(top)"),
        new DialogueAct("IndirectRequest(top)")
    };
    if (lastDA != null && myLast.timeStamp < lastDA.timeStamp)
      return false;
    for (DialogueAct req : requests) {
      //RdfClass my = _proxy.getClass(myLast.getDialogueActType());
      //RdfClass r = _proxy.getClass(req.getDialogueActType());
      //logger.error("waitResp: {} <= {}", my, r);
      //if (my.isSubclassOf(r)) return true;
      if (myLast.isSubsumedBy(req)) return true;
    }
    return false;
  }

  /** Return the index of the last speech act equal or more specific than the
   *  given one
   *  Zero means: was never said, one that was the last incoming utterance, etc.
   */
  public int receivedInSession(DialogueAct da) {
    return lastOccurence(da, lastDAs);
  }

  /** The last dialogue act spoken by the user */
  public DialogueAct lastDA() {
    DialogueAct last = lastDAs.peekFirst();
    // TODO: THIS IS NOT QUITE RIGHT. I SHOULD MARK SINGLE INCOMING DA'S AS
    // PROCESSED
    if (last == null || last.timeStamp < lastDAprocessed) {
      return getImpossibleDialogueact();
    }
    //if (unprocessedDAs == 0) return null;
    //DialogueAct last = lastDAs.get(unprocessedDAs - 1);
    return last;
  }

  /** Add the given dialogue act to the list of the user's dialogue acts */
  public DialogueAct addLastDA(DialogueAct newDA) {
    if (newDA == null) {
      logger.error("input DA is null");
      return null;
    }
    lastDAs.addFirst(newDA);
    // enter into database
    daToDB(newDA);
    //++unprocessedDAs;
    newData();
    return newDA;
  }

  /** We reacted appropriately to the last dialogue act from the user and let
   *  the system know that we did.
   */
  public void lastDAprocessed() {
    lastDAprocessed = System.currentTimeMillis();
    // --unprocessedDAs;
  }

  /** Get the value of the given slot from the dialogue act */
  public static String getSlot(DialogueAct diaAct, String feature) {
    return diaAct.getValue(feature);
  }

  /** Return true if the given slot (feature) is available in this DialogueAct */
  public static boolean hasSlot(DialogueAct diaAct, String feature) {
    return diaAct.hasSlot(feature);
  }

  /** Get the dialogue act type from the dialogue act */
  public static String getDialogueAct(DialogueAct diaAct) {
    return diaAct.getDialogueActType();
  }
  
  private String propertyToSlot(String uri) {
    return uri.substring(uri.indexOf(":"), uri.lastIndexOf(">"));
  }
  
  /** Serialize DialogueAct da to database */
  public void daToDB(DialogueAct da) {
    // TODO: verify namespaces (what is the DEFNS here?)
    Rdf entered = _proxy.getClass(da.getDialogueActType())
        .getNewInstance("dial");
    
    for (String prop : entered.getClazz().getProperties()) {
      String slot = propertyToSlot(prop);
      if (da.hasSlot(slot)) 
        entered.add(prop, da.getValue(slot));
    }
    
    Rdf frame = _proxy.getRdfClass(da.getProposition()).getNewInstance("dial");
    if (frame == null)
      throw new RuntimeException("unknown frame in dialogue act: ".concat(da.getProposition()));

    for (String prop : frame.getClazz().getProperties()) {
      String slot = propertyToSlot(prop);
      if (da.hasSlot(slot)) 
        frame.add(prop, da.getValue(slot));
    }
    
    entered.setValue("<dial:frame>", frame);
  }
  
  /** Serialize DialogueAct da from database */
  public DialogueAct rdfToDA(Rdf da) {
    // TODO: verify that that Rdf is some subclass of DialogueAct
    
    String rawDA = da.getClass().getSimpleName();
    rawDA += "(" + da.getValue("<dial:frame>").getClass().getSimpleName();

    for (String prop : da.getClazz().getProperties()) {
      if (da.has(prop))
        rawDA += ", " + propertyToSlot(prop) + "=" + da.getValue(prop);
    }

    Rdf frame = (Rdf) da.getValue("<dial:frame>");
    for (String prop : frame.getClazz().getProperties()) {
      if (frame.has(prop))
        rawDA += ", " + propertyToSlot(prop) + "=" + frame.getValue(prop);
    }

    return new DialogueAct(rawDA + ")");
  }

  // **********************************************************************
  // Timeouts
  // **********************************************************************
  Proposal emptyProposal = new Proposal() {
    public void run() {
    }
  };

  /** Create a new timeout */
  public void newTimeout(String name, int millis) {
    timeouts.newTimeout(name, millis, emptyProposal);
  }

  public void newTimeout(String name, int millis, final Proposal p) {
    timeouts.newTimeout(name, millis,
        new Proposal(){ public void run(){
          proposalsToExecute.offerLast(p);
        }});
  }

  /** Tell me if the timer if the given name ran out */
  public boolean isTimedOut(String name) {
    boolean result = timeouts.isTimedout(name);
    if (result) removeTimeout(name);
    return result;
  }

  /** Remove the timer with the given name */
  public void removeTimeout(String name) {
    timeouts.remove(name);
  }

  /** Is there and active timeout with the given name */
  public boolean hasActiveTimeout(String name) {
    return timeouts.hasActiveTimeout(name);
  }

  public void cancelTimeout(String name) {
    timeouts.cancelTimeout(name);
  }

  /* *************************************************************************

  ************************************************************************* */

  /** Signal that new data has arrived and the rules have to be executed */
  public void newData() {
    newData = true;
  }

  /** Get the language of the current agent */
  public String getLanguage() {
    return _language;
  }

  /** Return true if the agent's waiting for the statistical component to
   *  return the best action alternative.
   */
  public boolean waitForIntention() {
    return proposalsSent;
  }

  /* *************************************************************************
   Rdf shortcuts
   ************************************************************************* */

  /** Turn a URI into an RDF object */
  public Rdf toRdf(String uri) {
    if (uri.startsWith("\"")) uri = uri.substring(1, uri.length() -1);
    return _proxy.getRdf(uri);
  }

  /** Return the RDF class object for the given name */
  public RdfClass getRdfClass(String name) {
    return _proxy.fetchClass(name);
  }

  public List<List<Object>> queryTable(String query, String ... args) {
    return _proxy.queryTable(query, args);
  }

  public List<Object> query(String query, String ... args) {
    return _proxy.query(query, args);
  }

  /* *************************************************************************
   Java shortcuts
   ************************************************************************* */

  /** Return a new random number between zero and one */
  public float random() {
    return random.nextFloat();
  }

  /** Return a random integer between zero (inclusive) and bound (exclusive) */
  public int random(int bound) {
    return random.nextInt(bound);
  }

  /** Return the POD represented by the string */
  public int toInt(String s) { return Integer.parseInt(s); }
  public float toFloat(String s) { return Float.parseFloat(s); }
  public double toDouble(String s) { return Double.parseDouble(s); }
  public boolean toBool(String s) { return Boolean.getBoolean(s); }

  public DialogueAct analyse(String input) {
    return asr.interpret(input);
  }

  /* *******************************************************
   * overloaded boolean operator methods
   ********************************************************/

  @SuppressWarnings("rawtypes")
  public static boolean exists(Object s) {
    if (s == null) return false;
    if (s instanceof Number) return ((Number) s).doubleValue() != 0;
    if (s instanceof String) return ! ((String)s).isEmpty();
    if (s instanceof Boolean) return (Boolean)s;
    if (s instanceof Collection) return !((Collection)s).isEmpty();
    if (s instanceof Map) return !((Map)s).isEmpty();
    return true;
  }

//  /**
//   * Interpret what he's saying and create the proper reaction
//   * @param fromAsr a SpeechActEvent coming from the ASR
//   */
//  void treatAsrSpeechact(SpeechActEvent fromAsr) { // I CAN NOT DO ALL THE
//    INTERPRETATION IN THE ASR
//    . A SIMPLE EXAMPLE // IS "yes" -> confirm() is a
//        agreement if the
//      SA
//    it refers to is a // ynquestion, and acceptoffer() if
//    it is an offer, and acceptrequest // if it is a request. // also, missing
//        slots may have to be filled // turn confirm/disconfirm with previous offer into accept/decline offer
//        SpeechActEvent lastSpeechact = getLastSpeechAct();
//    if (lastSpeechact
//        != null) {
//      if (lastSpeechact.getSpeechact().equals("Offer")) {
//        switch (fromAsr.getSpeechact()) {
//          case "Disconfirm":
//            fromAsr.setSpeechact("DeclineOffer");
//            break;
//          case "Confirm":
//            fromAsr.setSpeechact("AcceptOffer");
//            break;
//        }
//      }
//      boolean referring = App.referringSpeechactType(fromAsr.getSpeechact(),
//          lastSpeechact.getSpeechact());
//      if (fromAsr.getProposition().equals("Frame")) {
//        if (referring) {
//          fromAsr.setProposition(lastSpeechact.getProposition());
//        }
//      }
//      if (fromAsr.getAddressee().equals("UNKNOWN")) {
//        if (referring) {
//          fromAsr.setAddressee(lastSpeechact.getSender());
//        } else {
//          fromAsr.setAddressee(lastSpeechact.getAddressee());
//        }
//      }
//      if (referring && !fromAsr.getArguments().containsKey("refersTo")) {
//        fromAsr.getArguments().put("refersTo", lastSpeechact.getId());
//      }
//      if (fromAsr.getSpeechact().equals("Request")
//          && fromAsr.getProposition().equals("Greeting")
//          && fromAsr.getArguments().containsKey("hasTheme")
//          && fromAsr.getArguments().get("hasTheme").startsWith("pron")
//          && lastSpeechact.getArguments().containsKey("hasTheme")) {
//        fromAsr.getArguments().put("hasTheme",
//            lastSpeechact.getArguments().get("hasTheme"));
//      }
//    }
//    logger.info(fromAsr);
//    comSys.send(".*", "SpeechActEvent", fromAsr);
//  }

  /** The implementation of this method in the wrapper class starts rule
   *  processing
   */
  public abstract int process();

  /**Initialize the agent.
   * @param configDir the directory of the global configuration file
   * @param language  the language of the agent
   * @param proxy     the RdfProxy object to access Rdf objects and classes
   * @param configs   the global configuration settings
   */
  @SuppressWarnings("rawtypes")
  public void init(File configDir, String language, RdfProxy proxy, Map configs) {
    _proxy = proxy;
    _proxy.registerStreamingClient(this);
    _language = language;
    DagNode.init(new DiaHierarchy(_proxy));
    asr = new AsrTts();
    try {
      asr.loadGrammar(configDir, language, this, configs);
    } catch (IOException ex) {
      logger.error("Error loading grammar: {}", ex);
      System.exit(1);
    }
    ruleLogger = new RuleLogger(new ColorLogger());
    reset();
  }

  /** For system-specific functions and communication with the outside, we have
   *  the communication hub
   */
  public void setCommunicationHub(CommunicationHub hub) {
    _hub = hub;
  }

  private static final int AGENT_SERVER_PORT = 2000, DEBUG_PORT = 3000;
  private static String DEBUG_HOST = "localhost";

  public void startDebuggerGui() {
    try {
      connectToDebugger(AGENT_SERVER_PORT, DEBUG_HOST, DEBUG_PORT);
    } catch (IOException ex) {
      logger.error("Can not connect to debugger: {}", ex);
    }
  }

  // ######################################################################
  // behaviour handling --> move to another class!
  // ######################################################################
  /** How much time in milliseconds must pass between two behaviours, if
   *  no message came back that the previous behaviour was finished.
   */
  public static long MIN_TIME_BETWEEN_BEHAVIOURS = 10000;

  /** The minimum pause after we got a signal that the previous behaviour
   *  was finished.
   */
  public static long MIN_PAUSE_FOR_FINISHED_BEHAVIOURS = 500;

  protected String lastBehaviourId = null;

  /** Don't send the next behaviour before this point in time is reached */
  private long behaviourNotBefore = 0;

  // to estimate the duration per word/motion behaviour, can change during session
  protected long sumOfDurations = 400;
  protected long numberOfItems = 1;

  protected class BHContainer {
    public long delayBefore, delayAfter, issued;
    public int items; // no of words and motion behaviours in this container
    String id;
    Behaviour b;
  }

  /** The queue of unfinished behaviours already sent out */
  protected Deque<BHContainer> _pendingBehaviours = new ArrayDeque<>();

  private IdentityHashMap<Behaviour, Integer> _customDelay = new IdentityHashMap<>();

  private Map<String, Pair<Proposal, Integer>> behaviourTriggers = new HashMap<>();

  public void lastBehaviourTrigger(int maxWait, Proposal p) {
    synchronized(behaviourTriggers) {
      behaviourTriggers.put(lastBehaviourId,
          new Pair<Proposal, Integer>(p, maxWait));
    }
  }

  private void startLastBehaviourTriggerTimeout(String behaviourId) {
    Pair<Proposal, Integer> p = behaviourTriggers.get(behaviourId);
    if (p != null && ! timeouts.hasActiveTimeout(behaviourId)) {
      timeouts.newTimeout(lastBehaviourId, p.second, new Proposal() {
        public void run() {
          synchronized(behaviourTriggers) { executeTrigger(lastBehaviourId); }
        }
      });
    }
  }



  private void executeTrigger(String behaviourId) {
    synchronized(behaviourTriggers) {
      timeouts.cancelTimeout(behaviourId);
      if (behaviourTriggers.containsKey(behaviourId)) {
        proposalsToExecute.offerLast(behaviourTriggers.get(behaviourId).first);
        behaviourTriggers.remove(behaviourId);
      }
    }
  }

  // TODO: Replaced by lastBehaviourTrigger?
  /** Seems agnostic to implementation --> Agent ??
  boolean waitForBehaviours(Object message) {
    long currentTime = System.currentTimeMillis();
    if (currentTime < behaviourNotBefore) {
      return true;
    } else {
      //TODO: check if that does the trick
      long delay = MIN_TIME_BETWEEN_BEHAVIOURS;
      if (_customDelay.containsKey(message)) {
        delay = _customDelay.get(message);
        _customDelay.remove(message);
      }
      behaviourNotBefore = currentTime + delay;
    }
    return false;
  }*/

  double estimatedTime(int items) {
    return items * ((double)sumOfDurations / numberOfItems);
  }

  // Uses BHContainer
  public boolean waitForBehaviours() {
    long currentTime = System.currentTimeMillis();
    while (!_pendingBehaviours.isEmpty()) {
      BHContainer bc = _pendingBehaviours.peek();
      long timeUsed = currentTime - bc.issued;
      double timeEstimated = estimatedTime(bc.items);
      if (timeUsed > 3 * timeEstimated) {
        _pendingBehaviours.pop();
        executeTrigger(bc.id);
        logger.info("removing overdue behaviour {} (ETA {}/{}), was {}", bc.id,
            timeEstimated, bc.items, timeUsed);
      } else {
        // logger.info("Waiting for behaviour {} to finish", bc.id);
        break;
      }
    }
    return (! _pendingBehaviours.isEmpty());
  }

  /** Put the behaviour into a waiting queue to see when it's finished.
   *  There will be a SystemInfo "finished" message that contains the id of the
   *  behaviour
   * @param c the message containing the behaviour
   */
  public void enqueueBehaviour(Behaviour b) {
    startLastBehaviourTriggerTimeout(b.getId());

    BHContainer bc = new BHContainer();
    bc.items = b.getText().split("  *").length + b.getMotion().split("\\|").length;
    bc.id = b.getId();
    bc.b = b;
    bc.issued = System.currentTimeMillis();
    /*
    if (minTime < 0) {
      bc.delayBefore = - minTime;
    } else if (minTime > 0) {
      bc.delayAfter = minTime;
    }
    */
    logger.info("enqueueing behaviour {} (ETA {}/{})", bc.id,
        (long)estimatedTime(bc.items), bc.items);
    _pendingBehaviours.push(bc);
  }

  /**
   * A low level NAO command (or a timeout) signalled that the NAO finished
   * speaking / moving
   *
   * If event sending was blocked while waiting for this signal, unblock it now.
   */
  public void setBehaviourFinished(String behaviourId) {
    if (_pendingBehaviours.isEmpty()) return;
    int i = 0;
    for (BHContainer b : _pendingBehaviours) {
      if (b.id.equals(behaviourId)) {
        long timeNeeded = System.currentTimeMillis() - b.issued;
        logger.info("behaviour {} (ETA {}/{}), was {}", b.id,
            (long) estimatedTime(b.items), b.items, timeNeeded);
        sumOfDurations += timeNeeded;
        numberOfItems += b.items;
        break;
      }
      ++i;
    }
    if (i == _pendingBehaviours.size()) {
      logger.warn("no matching behaviour found for: {}", behaviourId);
      return;
    } else if (i > 0) {
      logger.warn("Receiving behaviour id in wrong order: in: {} pending: {}"
          , behaviourId, _pendingBehaviours.peek().id);
    }
    while (i >= 0) {
      BHContainer bc = _pendingBehaviours.pop();
      executeTrigger(bc.id);
      logger.info("removing behaviour {}", bc.id);
      --i;
    }
  }

  // ######################################################################
  // proposal handling and main loop functions
  // ######################################################################


  /** Add a proposal to the list of pending proposals, if not already in */
  public void propose(String name, Proposal p) {
    // add the proposal to the pending proposals, but not twice
    if (!pendingProposals.containsKey(name)) {
      p.name = name;
      pendingProposals.put(name, p);
    }
  }

  /** Execute a selected proposal */
  public void executeProposal(Intention intention) {
    String continuationName = intention.getContent();
    Proposal p = pendingProposals.get(continuationName);
    if (p != null) {
      logger.info("Execute intention: {}", continuationName);
      executedLast = p.name;
      pendingProposals.clear();
      // this is the first thing to execute in the next round
      proposalsToExecute.offerFirst(p);
      proposalsSent = false;
    } else {
      logger.error("Inactive intention: {}", continuationName);
    }
  }

  /** Send the list of possible intentions to the communication hub */
  void sendIntentions(Set<String> strings) {
    _hub.sendIntentions(strings);
    proposalsSent = true;
  }

  /**
   * If new data arrived, start the rules processing until no new proposals are
   * added and send the final set to the decision process. After that, the flag
   * signalling that new data arrived is reset
   */
  private void actOnNewData() {
    if (newData || timeouts.timeoutOccured()) {
      int oldSize = 0;
      do {
        newData = false;
        oldSize = pendingProposals.size();
        process();
      } while (pendingProposals.size() != oldSize || newData);
      if (oldSize > 0) {
        sendIntentions(pendingProposals.keySet());
      }
    }
  }

  public void processRules() {
    synchronized (this) {
      // execute code injected from timeouts or external triggers
      while (!proposalsToExecute.isEmpty()) {
        proposalsToExecute.pollFirst().run();
        newData();
      }
      if (newData) {
        actOnNewData();
      }
    }
  }

  /** Empty all queues that might make the thing going */
  public void clearBehavioursAndProposals() {
    // remove all behaviours, proposals and timeouts
    pendingProposals.clear();
    proposalsToExecute.clear();
    _pendingBehaviours.clear();
  }

  /** Empty all queues that might make the thing going */
  public void shutdown() {
    // remove all behaviours, proposals and timeouts
    clearBehavioursAndProposals();
    behaviourTriggers.clear();
    timeouts.clear();
  }

  // ######################################################################
  // Collection + lambda methods
  // ######################################################################

  /** Return true if the predicate returns true for some element of the
   *  collection
   */
  public <T> boolean contains(Collection<T> coll, Predicate<? super T> p) {
    for(T elt : coll)
      if (p.test(elt)) return true;
    return false;
  }

  /** Return true if the predicate returns true for all elements of the
   *  collection
   */
  public <T> boolean all(Collection<T> coll, Predicate<? super T> p) {
    for(T elt : coll)
      if (! p.test(elt)) return false;
    return true;
  }

  /** Return a list of all elements of collection where the predicate returns
   *  true for the element.
   */
  public <T> List<T> filter(Collection<T> coll, Predicate<? super T> p) {
    List<T> result = new ArrayList<>();
    for(T elt : coll)
      if (p.test(elt)) result.add(elt);
    return result;
  }

  /** Return a new list with the elements of the collections sorted according
   *  to the comparator.
   */
  public <T> List<T> sort(Collection<T> coll,  Comparator<? super T> c) {
    List<T> l = new LinkedList<>(coll);
    Collections.sort(l, c);
    return l;
  }

  /** Return the number of all elements of collection where the predicate returns
   *  true for the element.
   */
  public <T> int count(Collection<T> coll, Predicate<? super T> p) {
    int result = 0;
    for(T elt : coll)
      if (p.test(elt)) ++result;
    return result;
  }

  /** Map the elements of the input collection to an output collection
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public <T, S>
  Collection<S> map(Collection<T> coll, Function<? super T, ? extends S> f) {
    try {
      Collection c = coll.getClass().newInstance();
      for(T elt : coll)
      c.add(f.apply(elt));
      return c;
    } catch (InstantiationException | IllegalAccessException e) {
      // Won't happen.
    }
    return null;
  }

  // ######################################################################
  // Rule logging for debugging
  // ######################################################################

  /** log all rules */
  public void logAllRules() { ruleLogger.logAllRules(); }

  /** Reset logging to false for all rules */
  public void resetLogging() { ruleLogger.resetLogging(); }

  /** Start logging a specific rule */
  public void logRule(int id, int what) { ruleLogger.logRule(id, what); }

  /** Set logging status of a specific rule */
  public void logRule(int id) { logRule(id, STATE_ALWAYS); }

  /** Stop logging a specific rule */
  public void unLogRule(int id) { logRule(id, STATE_NEVER); }

  /**
   * function that prints logs of (rule) conditions
   * @param id the id of the rule whose evaluation this is
   * @param values the parts of the condition, mapped to true or false
   */
  public void logRule(int ruleId, boolean[] result) {
    ruleLogger.logRule(ruleId, result);
  }

  // ######################################################################
  // Connection to rudibugger
  // ######################################################################

  public void connectToDebugger(int ownPort, String debugHost, int debugPort) throws IOException {
    DebuggingService ds = new DebuggingService(this);
    ds.startServer(ownPort);
    logger.debug("DebuggingService has been started on port [" + ownPort + "].");
    RemoteLogger logprinter = new RemoteLogger(debugHost, debugPort);
    this.ruleLogger.registerPrinter(logprinter);
    logger.debug("RemoteLogger started looking for server on port ["
        + debugPort + "].");
  }
}
