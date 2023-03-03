/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.dfki.mlt.rudimant.agent;

import static de.dfki.mlt.rudimant.common.Constants.CFG_DEBUG_PORT;
import static de.dfki.mlt.rudimant.common.Constants.STATE_ALWAYS;
import static de.dfki.mlt.rudimant.common.Constants.STATE_NEVER;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.StreamingClient;
import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlp.LanguageServices;
import de.dfki.mlt.rudimant.agent.nlp.DiaHierarchy;
import de.dfki.mlt.rudimant.agent.nlp.DialogueAct;
import de.dfki.mlt.rudimant.agent.nlp.Pair;
import de.dfki.mlt.rudimant.common.RuleLogger;
import de.dfki.mlt.rudimant.common.SimpleServer;

/**
 *
 * @author chbu02, Bernd Kiefer
 */
public abstract class Agent implements StreamingClient {

  public static final Logger logger = LoggerFactory.getLogger(Agent.class);

  public int startDebuggerGui = -1;

  private static DialogueAct IMPOSSIBLE_DIALOGUEACT;

  /** To generate unique IDs for behaviours, etc. */
  protected static int _generatorCounter = -1;
  public String idPrefix = "";

  protected String executedLast = null;

  protected String _language;

  /** The default namespace prefix used for .rudi Rdf "new" statements
   *  can be changes by top-level generated or wrapper class
   */
  public String DEFNS = "default:";

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
  protected LanguageServices langServices;

  /** Is new data in the repository */
  private boolean newData = false;

  /** The DAs I emitted, newest first, DON'T CHANGE FROM DERIVED CLASSES */
  protected LinkedList<DialogueAct> myLastDAs;
  // protected int myUnprocessedDAs;

  /** The DAs I received, newest first */
  private LinkedList<DialogueAct> lastDAs;
  protected long lastDAprocessed = -1;
  //protected int unprocessedDAs;

  private final Timeouts timeouts = new Timeouts();

  protected final BehaviourQueue bhq = new BehaviourQueue();

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
            (++_generatorCounter << 8));
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

  private static DialogueAct getImpossibleDialogueAct() {
    if (IMPOSSIBLE_DIALOGUEACT == null) {
      IMPOSSIBLE_DIALOGUEACT = new DialogueAct("Bottom(Bottom)");
    }
    return IMPOSSIBLE_DIALOGUEACT;
  }

  // Constructors ************************************************************
  // public Agent() {}

  // **********************************************************************
  // StreamingClient compute() function to register changes in DB
  // **********************************************************************

  @Override
  public void compute(Set<String> users) {
    newData();
  }


  // **********************************************************************
  // DialogueAct functions
  // **********************************************************************

  /** Create a behaviour from a dialogue act. Must be overridden by a subclass
   *  if 1) the DA should be enriched e.g. by sender and addressee by default
   *  or 2) if the Behaviour should contain additional application-specific
   *        information
   */
  protected Behaviour createBehaviour(int delay, DialogueAct da) {
    Pair<String, String> toSay = langServices.generate(da.getDag());
    return new Behaviour(generateId(), toSay.second, toSay.first, delay);
  }

  /** Generate DialogueAct from a raw speech act representation */
  public DialogueAct addToMyDA(DialogueAct da) {
    myLastDAs.addFirst(da);
    // enter into database
    DialogueAct.toRdf(da, _proxy);
    // ++myUnprocessedDAs;
    newData();
    return da;
  }

  /** Send a Behaviour to the Behaviourmanager (communication hub) */
  public final void emitBehaviour(Behaviour b) {
    _hub.sendBehaviour(b);
  }

  /** Generate text and motion from a raw speech act representation and send it
   *  to the Behaviourmanager
   */
  public final DialogueAct emitDA(int delay, DialogueAct da) {
    emitBehaviour(createBehaviour(delay, da));
    return addToMyDA(da);
  }

  /** Generate text and motion from a raw speech act representation and send it
   * to the Behaviourmanager
   */
  public final DialogueAct emitDA(DialogueAct da) {
    return emitDA(Behaviour.DEFAULT_DELAY, da);
  }

  /** The last dialogue act spoken by the agent */
  public DialogueAct myLastDA() {
    DialogueAct result = myLastDAs.peekFirst();
    return (result == null ? getImpossibleDialogueAct() : result);
    //if (myUnprocessedDAs == 0) return null;
    //return myLastDAs.get(myUnprocessedDAs - 1);
  }

  /** The last dialogue act spoken by the agent */
  public DialogueAct myLastDA(int back) {
    DialogueAct result = myLastDAs.size() > back ? myLastDAs.get(back) : null;
    return (result == null ? getImpossibleDialogueAct() : result);
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
      return getImpossibleDialogueAct();
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
    DialogueAct.toRdf(newDA, _proxy);
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

  // **********************************************************************
  // Timeouts
  // **********************************************************************
  Proposal emptyProposal = new Proposal() {
    @Override
    public void run() {
    }
  };

  /** Create a new timeout with an empty proposal */
  public void newTimeout(String name, int millis) {
    timeouts.newTimeout(name, millis, emptyProposal);
  }

  /** Add a new timeout if there ain't an active one with the given name,
   *  otherwise change the time to fire to the given milliseconds.
   *
   *  A fired timeout with that name will not prevent adding a new one.
   */
  public void newTimeout(String name, int millis, final Proposal p) {
    timeouts.newTimeout(name, millis,
        new Proposal(){ @Override
        public void run(){
          proposalsToExecute.offerLast(p);
        }});
  }

  /** Tell me if a timer if the given name ran out, and that fact was not
   *  actively cleared using removeTimeout.
   */
  public boolean isTimedOut(String name) {
    boolean result = timeouts.isTimedout(name);
    // Do that explicitely, if wanted!
    // Otherwise it's harder to code "once only" timeouts
    //if (result) removeTimeout(name);
    return result;
  }

  /** Remove the fact that a timer with the given name has fired. */
  public void removeTimeout(String name) {
    timeouts.remove(name);
  }

  /** Is there any active timeout with the given name? */
  public boolean hasActiveTimeout(String name) {
    return timeouts.hasActiveTimeout(name);
  }

  /** Cancel this active timeout, if there is one */
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
  public int toInt(String s) {
    try { return Integer.parseInt(s); }
    catch (NumberFormatException ex) { return -1; }
  }
  public float toFloat(String s) {
    try { return Float.parseFloat(s); }
    catch (NumberFormatException ex) { return -1.0f; }
  }
  public double toDouble(String s) {
    try { return Double.parseDouble(s); }
    catch (NumberFormatException ex) { return -1.0d; }
  }

  public boolean toBool(String s) {
    return "true".equals(s.toLowerCase());
  }

  /** Return the string representing the POD */
  public String toStr(Number n) {
    return n.toString();
  }

  public String toStr(Boolean b) {
    return b.toString();
  }

  public DialogueAct analyse(String input) {
    return langServices.interpret(input);
  }

  /** Return the epoch time for now */
  public long now() { return System.currentTimeMillis(); }

  /** Removes namespace and trailing angle bracked from an URI, works only
   *  for short forms.
   * @param uri
   * @return the "name" without namespace and angle brackets
   * TODO: should be a RdfProxy util method
   */
  public static String getUriName(String uri) {
    return uri.substring(uri.lastIndexOf(':') + 1, uri.length() - 1);
  }

  /* *******************************************************
   * overloaded boolean operator methods
   ********************************************************/

  public static boolean exists(Object s) { return (s != null); }

  public static boolean exists(Number s) {
    return s != null && s.doubleValue() != 0;
  }

  public static boolean exists(String s) { return s != null && ! s.isEmpty(); }

  public static boolean exists(Boolean b) { return b != null && b; }

  public static boolean exists(Collection<?> c) {
    return c != null && ! c.isEmpty();
  }

  public static boolean exists(Map<?, ?> m) {
    return m != null && ! m.isEmpty();
  }

  public static boolean exists(DialogueAct d) {
    return d != null && d != getImpossibleDialogueAct();
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
   * @param configDir   the directory of the global configuration file
   * @param language    the language of the agent
   * @param proxy       the RdfProxy object to access Rdf objects and classes
   * @param configs     the global configuration settings
   * @param agentPrefix the string prefix used as default namespace for creating
   *                    new RDF objects (must be a valid short namespace!) 
   *                    and Behaviours
   */
  @SuppressWarnings("rawtypes")
  public void init(File configDir, String language, RdfProxy proxy, Map configs
      , String agentPrefix) {
    _proxy = proxy;
    _language = language;
    DEFNS = agentPrefix + ":";
    idPrefix = agentPrefix;
    DagNode.init(new DiaHierarchy(_proxy));
    langServices = new LanguageServices();
    try {
      langServices.loadGrammar(configDir, language, configs);
    } catch (IOException ex) {
      logger.error("Error loading grammar: {}", ex);
      System.exit(1);
    }
    ruleLogger = new RuleLogger(new ColorLogger());
    if (configs.containsKey(CFG_DEBUG_PORT) && (startDebuggerGui <= 0)) {
      startDebuggerGui = (int) configs.get(CFG_DEBUG_PORT);
    }
    if (startDebuggerGui > 0) {
      try {
        connectToDebugger("localhost", startDebuggerGui);
      } catch (IOException ex) {
        logger.error("Can not connect to debugger: {}", ex);
      }
    }
    reset();
  }

  /** For system-specific functions and communication with the outside, we have
   *  the communication hub
   */
  public void setCommunicationHub(CommunicationHub hub) {
    _hub = hub;
  }

  // ######################################################################
  // behaviour handling
  // ######################################################################

  /** Cancel any behaviourTimeouts for this behaviour, if any,
   *  and "execute" the proposal connected to it, again, if applicable.
   *
   * @param behaviourId
   */
  private void executeTrigger(String behaviourId) {
    timeouts.cancelTimeout(behaviourId);
    Proposal p = bhq.checkBehaviourTimeout(behaviourId);
    if (p != null) {
      proposalsToExecute.offerLast(p);
    }
  }

  /** This starts the behaviourTimeout, if there is any, because now the
   *  behaviour is really sent out.
   *
   * @param behaviourId
   */
  private void startBehaviourTimeout(String behaviourId) {
    Pair<Proposal, Integer> p = bhq.getTrigger(behaviourId);
    if (p != null && ! timeouts.hasActiveTimeout(behaviourId)) {
      timeouts.newTimeout(behaviourId, p.second, new Proposal() {
        @Override
        public void run() { executeTrigger(behaviourId); }
      });
    }
  }

  public boolean waitForBehaviours(Behaviour b) {
    // this kicks out behaviours that take too much time, or are never
    // acknowledged by a finished message, without using timeouts.
    for(String id : bhq.removeOverdueBehaviours()) {
      executeTrigger(id);
    }
    boolean result =  bhq.waitForBehaviours();
    // This condition makes sure that by no means a second element will be added
    // to the queue, which makes the queue a bit of an overkill
    if (! result) {
      // start a timeout for a proposal that is eventually attached to the
      // behaviour by a behaviourTimeout
      startBehaviourTimeout(b.getId());
      // Put the behaviour into a waiting queue to see when it's finished.
      // There will be a setBehaviourFinished that signals the end of the
      // behaviour
      bhq.enqueueBehaviour(b);
    }
    // if we return true, the sending of behaviours (and other system events)
    // has to be blocked by the calling function !!!
    return result;
  }

  /** A low level NAO command (or a timeout) signalled that the NAO finished
   *  speaking / moving
   *
   * If event sending was blocked while waiting for this signal, unblock it now.
   */
  public void setBehaviourFinished(String behaviourId) {
    for (String s : bhq.setBehaviourFinished(behaviourId)) {
      executeTrigger(s);
    }
  }

  /** Execute this proposal either after maxWait msecs or when the
   *  behaviour that was enqueued has finished.
   */
  public void behaviourTimeout(DialogueAct da, int maxWait, Proposal p) {
    Behaviour b = createBehaviour(maxWait + 500, da);
    emitBehaviour(b);
    addToMyDA(da);
    bhq.addBehaviourTimeout(b.getId(), maxWait, p);
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
    ruleLogger.clearRecentResults();
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
    //_pendingBehaviours.clear();
    bhq.clear();
  }

  /** Empty all queues that might make the thing going */
  public void shutdown() {
    // remove all behaviours, proposals and timeouts
    clearBehavioursAndProposals();
    timeouts.clear();
  }

  // ######################################################################
  // Collection + lambda methods
  // ######################################################################

  /** Return true if the predicate returns true for some element of the
   *  collection
   */
  public <T> boolean some(Collection<T> coll, Predicate<? super T> p) {
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

  /** Return the first element that matches pred */
  public static <T> T first(Collection<T> coll, Predicate<? super T> pred) {
    for(T elt : coll)
      if (pred.test(elt)) return elt;
    return null;
  }

  /** Return a random element from the collection */
  public <T> T random(Collection<T> coll) {
    int r = random(coll.size());
    for (T elt: coll) {
      if (r == 0) return elt;
      --r;
    }
    return null; // should never happen
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

  public void connectToDebugger(String debugHost, int debugPort)
      throws IOException {
    DebuggingService ds = new DebuggingService(this, debugPort);
    SimpleServer s = ds.startServer();
    RemoteLogger logprinter = new RemoteLogger(s);
    this.ruleLogger.registerPrinter(logprinter);
    logger.debug("DebuggingService has been started on port [" + debugPort + "].");
  }
}
