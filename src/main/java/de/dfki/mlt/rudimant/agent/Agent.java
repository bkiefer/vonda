package de.dfki.mlt.rudimant.agent;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.tr.dialogue.cplan.DagEdge;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import java.util.logging.Level;
import org.apache.thrift.TException;

/**
 *
 * @author chbu02, Bernd Kiefer
 */
public abstract class Agent {

  public static final Logger logger = LoggerFactory.getLogger(Agent.class);

  static final String RESOURCE_DIR = "src/main/resources/";

  protected String executedLast = null;

  protected long lastDAprocessed = -1;

  protected String _language;

  // TODO: that's not nice. The mood is transported in the Intention, and
  // i set this field in the intention manager.
  public double proposedRobotMood = 0;

  protected Random random = new Random(System.currentTimeMillis());

  public class Proposal {

    public void run() throws Exception {}

    public String name;

    public void go() throws Exception {
      executedLast = name;
      pendingProposals.clear();
      run();
    }
  }

  /** The RDF access object */
  public RdfProxy _proxy;

  /** The object that is responsible for outgoing communication */
  protected CommunicationHub _hub;

  /** A class that cares about ASR events and interpretation */
  protected AsrTts asr;

  /** The DAs I emitted, newest first */
  protected Deque<DialogueAct> myLastDAs;

  /** The DAs I received, newest first */
  private Deque<DialogueAct> lastDAs;

  private Timeouts timeouts = new Timeouts();

  /** Is new data in the repository */
  private boolean newData = false;

  /** The set of all proposals generated in one (fixpoint) run of the rules */
  public Map<String, Proposal> pendingProposals = new HashMap<>();

  /** Are we waiting for a proposal to be selected? In this case, put all
   *  incoming events into the event queue
   */
  protected boolean proposalsSent;

  /** Send something out to the world */
  protected void sendBehaviour(Object obj) {
    // TODO implement it, possibly with a Listener.
  }

  // TODO: Why does this already resetted to LinkedList before Deque was removed?
  protected void reset() {
    myLastDAs = new LinkedList<DialogueAct>();
    lastDAs = new LinkedList<DialogueAct>();
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

  // Constructors ************************************************************
  // public Agent() {}


  // **********************************************************************
  // DialogueAct functions
  // **********************************************************************

  /** Generate DialogueAct from a raw speech act representation */
  protected DialogueAct createEmitDA(DialogueAct da) {
    myLastDAs.addFirst(da);
    newData();
    return da;
  }

  /** Generate text and motion from a raw speech act representation and send it
   * to the Behaviourmanager
   */
  protected DialogueAct emitDA(int delay, DialogueAct da) {
    Pair<String, String> toSay = asr.generate(da.dag);
    _hub.sendBehaviour(new Behaviour(toSay.first, toSay.second, delay));
    return da;
  }

  /** Generate text and motion from a raw speech act representation and send it
   * to the Behaviourmanager
   */
  protected DialogueAct emitDA(DialogueAct da) {
    return emitDA(Behaviour.DEFAULT_DELAY, da);
  }

  public DialogueAct getMyLastDA() {
    return myLastDAs.peekFirst();
  }

  public DialogueAct myLastDA() {
    return getMyLastDA();
  }

  public boolean isMyLastDA(DialogueAct da) {
    return da.subsumes(getMyLastDA());
  }

  public boolean isMyLastDA(String raw) {
    return isMyLastDA(new DialogueAct(raw));
  }

  /** Return the index of the last speech act equal or more specific than the
   *  given one
   */
  protected int lastOccurence(DialogueAct da, Iterable<DialogueAct> daList) {
    int i = 0;
    for (DialogueAct evt : daList) {
      if (da.dag.subsumes(evt.dag)) {
        return i;
      }
      ++i;
    }
    return -1;
  }

  /** Return the index of the last speech act with a type equal or more specific
   *  than the given one
   */
  public int lastOccurenceOfMyDA(String raw) {
    return saidInSession(new DialogueAct(raw));
  }

  /** When did i say this in this session? */
  int saidInSession(DialogueAct da) {
    return lastOccurence(da, myLastDAs);
  }

  /** Am I currently waiting for a response?
   *  The condition is: I was the last to say something, and my dialogue act
   *  was a question or a request.
   * @return true if i'm waiting for a response.
   */
  protected boolean waitingForResponse() {
    // if my last DA was a request or a question, and there is no newer incoming
    // da, i'm waiting for an answer.
    DialogueAct myLast = getMyLastDA();
    if (myLast == null) {
      return false;
    }
    DialogueAct lastDA = lastDA();
    final DialogueAct[] requests = {
        new DialogueAct("Question(Frame)"),
        new DialogueAct("Request(Frame)")
    };
    if (myLast.timeStamp < lastDA.timeStamp)
      return false;
    for (DialogueAct req : requests) {
      if (myLast.isSubsumedBy(req)) return true;
    }
    return false;
  }

  /** Return the index of the last speech act equal or more specific than the
   *  given one
   */
  protected int receivedInSession(DialogueAct da) {
    return lastOccurence(da, lastDAs);
  }

  public DialogueAct lastDA() {
    DialogueAct last = lastDAs.peekFirst();
    // TODO: THIS IS NOT QUITE RIGHT. I SHOULD MARK SINGLE INCOMING DA'S AS
    // PROCESSED
    if (last == null || last.timeStamp < lastDAprocessed) {
      return null;
    }
    return last;
  }

  public DialogueAct addLastDA(DialogueAct newDA) {
    try {
      if (newDA == null) {
        throw new IllegalArgumentException("NULL dialogueact");
      }
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
      // throw ex;
    }
    lastDAs.addFirst(newDA);
    newData();
    return newDA;
  }

  // todo: RAUS!
  public boolean isLastDA(String raw) {
    if (lastDA() == null) {
      return false;
    }
    return new DialogueAct(raw).subsumes(lastDA());
  }

  public void lastDAprocessed() {
    lastDAprocessed = System.currentTimeMillis();
  }

  public static String getSlot(DialogueAct diaAct, String feature) {
    DagNode da = diaAct.dag;
    DagEdge e = da.getEdge(DagNode.getFeatureId(feature));
    if (e == null) {
      return null;
    }
    DagNode node = e.getValue();
    if (node.getType() == DagNode.TOP_ID) {
      e = node.getEdge(DagNode.PROP_FEAT_ID);
      if (e == null) {
        return DagNode.TOP_TYPE;
      }
      return e.getValue().getTypeName();
    }
    return node.getTypeName();
  }

  public static String getDialogueAct(DialogueAct diaAct) {
    DagNode da = diaAct.dag;
    DagEdge e = da.getEdge(DagNode.TYPE_FEAT_ID);
    String result = e.getValue().getTypeName();
    return result;
  }

  // **********************************************************************
  // Timeouts
  // **********************************************************************
  Proposal emptyProposal = new Proposal() {
    public void run() {
    }
  };

  protected void newTimeout(String name, int millis) {
    timeouts.newTimeout(name, millis);
  }

  protected boolean isTimedOut(String name) {
    return timeouts.isTimedout(name);
  }

  protected void removeTimeout(String name) {
    timeouts.remove(name);
  }

  public void newData() {
    newData = true;
  }

  public String getLanguage() {
    return _language;
  }

  public boolean waitForIntention() {
    return proposalsSent;
  }

  /* *************************************************************************
   Rdf shortcuts
   ************************************************************************* */

  //public boolean isSubclassOf(Rdf sub, String clz) throws TException {
  //  return sub.getClazz().isSubclassOf(_proxy.fetchRdfClass(clz));
  //}


  /**
   * If new data arrived, start the rules processing until no new proposals are
   * added and send the final set to the decision process. After that, the flag
   * signalling that new data arrived is reset
   */
  public void actOnNewData() {
    if (newData || timeouts.timeoutOccured()) {
      int oldSize = 0;
      do {
        oldSize = pendingProposals.size();
        processRules();
      } while (pendingProposals.size() != oldSize);
      if (oldSize > 0) {
        sendIntentions(pendingProposals.keySet());
      }
      newData = false;
    }
  }

  /** Send the list of possible intentions to the communication hub */
  void sendIntentions(Set<String> strings) {
    // TODO: implement it, maybe using a listener

    proposalsSent = true;
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
  protected abstract void processRules();

  public void init(String language) {
    _language = language;
    asr = new AsrTts();
    try {
      asr.loadGrammar(language, this);
    } catch (IOException ex) {
      logger.error("Error loading grammar: {}", ex);
      System.exit(1);
    }
    reset();
  }

  protected void propose(String name, Proposal p) {
    // add the proposal to the pending proposals, but not twice
    if (!pendingProposals.containsKey(name) //&& name != executedLast
            ) {
      p.name = name;
      pendingProposals.put(name, p);
    }
  }

  public void executeProposal(Intention intention) throws Exception {
    String continuationName = intention.getContent();
    Proposal p = pendingProposals.get(continuationName);
    try {
      if (p != null) {
        logger.info("Execute intention: {}", continuationName);
        p.go();
      } else {
        logger.error("Inactive intention: {}", continuationName);
      }
    }
    finally {
      proposalsSent = false;
    }
  }

  /********************************************************
   * overloaded boolean operator methods
   ********************************************************/

//  public boolean isEqual(){}
//  public boolean isSmaller(){}
//  public boolean isGreater(){}
//  public boolean isSmallerEqual(){}
//  public boolean isGreaterEqual(){}

  public static boolean isEqual(DialogueAct left, DialogueAct right){
    return (left.isSubsumedBy(right) && right.isSubsumedBy(left));
  }
  public static boolean isSmaller(DialogueAct left, DialogueAct right){
    return (isSmallerEqual(left, right) && !isSmallerEqual(right, left));
  }
  public static boolean isGreater(DialogueAct left, DialogueAct right){
    return (isGreaterEqual(left, right) && !isGreaterEqual(right, left));
  }
  public static boolean isSmallerEqual(DialogueAct left, DialogueAct right){
    return left.isSubsumedBy(right);
  }
  public static boolean isGreaterEqual(DialogueAct left, DialogueAct right){
    return isSmallerEqual(right, left);
  }

  public static boolean isEqual(String left, DialogueAct right){
    return isEqual(new DialogueAct(left), right);
  }

  public static boolean isSmaller(String left, DialogueAct right){
    return isSmaller(new DialogueAct(left), right);
  }

  public static boolean isGreater(String left, DialogueAct right){
    return isGreater(new DialogueAct(left), right);
  }
  public static boolean isSmallerEqual(String left, DialogueAct right){
    return isSmallerEqual(new DialogueAct(left), right);
  }
  public static boolean isGreaterEqual(String left, DialogueAct right){
    return isGreaterEqual(new DialogueAct(left), right);
  }

  public static boolean isEqual(DialogueAct left, String right){
    return isEqual(left, new DialogueAct(right));
  }
  public static boolean isSmaller(DialogueAct left, String right){
    return isSmaller(left, new DialogueAct(right));
  }
  public static boolean isGreater(DialogueAct left, String right){
    return isGreater(left, new DialogueAct(right));
  }
  public static boolean isSmallerEqual(DialogueAct left, String right){
    return isSmallerEqual(left, new DialogueAct(right));
  }
  public static boolean isGreaterEqual(DialogueAct left, String right){
    return isGreaterEqual(left, new DialogueAct(right));
  }

    public static boolean isEqual(Rdf left, Rdf right){
    return isEqual(left.getClazz(), right.getClazz());
  }
  public static boolean isSmaller(Rdf left, Rdf right){
    return (isSmallerEqual(left, right) && !isSmallerEqual(right, left));
  }
  public static boolean isGreater(Rdf left, Rdf right){
    return (isGreaterEqual(left, right) && !isGreaterEqual(right, left));
  }
  public static boolean isSmallerEqual(Rdf left, Rdf right){
    return isSmallerEqual(left.getClazz(), right.getClazz());
  }

  public static boolean isGreaterEqual(Rdf left, Rdf right){
    return isSmallerEqual(right, left);
  }

  public boolean isEqual(String left, Rdf right){
    return isEqual(_proxy.getRdfClass(left), right.getClazz());
  }

  public boolean isSmaller(String left, Rdf right){
    return isSmaller(_proxy.getRdfClass(left), right.getClazz());
  }
  public boolean isGreater(String left, Rdf right){
    return isGreater(_proxy.getRdfClass(left), right.getClazz());
  }

  public boolean isSmallerEqual(String left, Rdf right){
    return isSmallerEqual(_proxy.getRdfClass(left), right.getClazz());
  }
  public boolean isGreaterEqual(String left, Rdf right){
    return isGreaterEqual(_proxy.getRdfClass(left), right.getClazz());
  }

  public boolean isEqual(Rdf left, String right){
    return isEqual(left.getClazz(), _proxy.getRdfClass(right));
  }
  public boolean isSmaller(Rdf left, String right){
    return isSmaller(left.getClazz(), _proxy.getRdfClass(right));
  }
  public boolean isGreater(Rdf left, String right){
    return isGreater(left.getClazz(), _proxy.getRdfClass(right));
  }
  public boolean isSmallerEqual(Rdf left, String right){
    return isSmallerEqual(left.getClazz(), _proxy.getRdfClass(right));
  }
  public boolean isGreaterEqual(Rdf left, String right){
    return isGreaterEqual(left.getClazz(), _proxy.getRdfClass(right));
  }

  public static boolean isEqual(RdfClass left, RdfClass right){
    return (left.isSubclassOf(right) && right.isSubclassOf(left));
  }
  public static boolean isSmaller(RdfClass left, RdfClass right){
    return (isSmallerEqual(left, right) && !isSmallerEqual(right, left));
  }
  public static boolean isGreater(RdfClass left, RdfClass right){
    return (isGreaterEqual(left, right) && !isGreaterEqual(right, left));
  }
  public static boolean isSmallerEqual(RdfClass left, RdfClass right){
    return (left.isSubclassOf(right));
  }
  public static boolean isGreaterEqual(RdfClass left, RdfClass right){
    return isSmallerEqual(right, left);
  }

  public boolean isEqual(String left, RdfClass right){
    return isEqual(_proxy.getRdfClass(left), right);
  }
  public boolean isSmaller(String left, RdfClass right){
    return isSmaller(_proxy.getRdfClass(left), right);
  }
  public boolean isGreater(String left, RdfClass right){
    return isGreater(_proxy.getRdfClass(left), right);
  }
  public boolean isSmallerEqual(String left, RdfClass right){
    return isSmallerEqual(_proxy.getRdfClass(left), right);
  }
  public boolean isGreaterEqual(String left, RdfClass right){
    return isGreaterEqual(_proxy.getRdfClass(left), right);
  }

  public boolean isEqual(RdfClass left, String right){
    return isEqual(left, _proxy.getRdfClass(right));
  }
  public boolean isSmaller(RdfClass left, String right){
    return isSmaller(left, _proxy.getRdfClass(right));
  }
  public boolean isGreater(RdfClass left, String right){
    return isGreater(left, _proxy.getRdfClass(right));
  }
  public boolean isSmallerEqual(RdfClass left, String right){
    return isSmallerEqual(left, _proxy.getRdfClass(right));
  }
  public boolean isGreaterEqual(RdfClass left, String right){
    return isGreaterEqual(left, _proxy.getRdfClass(right));
  }

  public boolean isEqual(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isEqual(new DialogueAct(left), new DialogueAct(right));
    }
    return isEqual(leftRdf, rightRdf);
  }

  public boolean isSmaller(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isSmaller(new DialogueAct(left), new DialogueAct(right));
    }
    return isSmaller(leftRdf, rightRdf);
  }
  public boolean isGreater(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isGreater(new DialogueAct(left), new DialogueAct(right));
    }
    return isGreater(leftRdf, rightRdf);
  }
  public boolean isSmallerEqual(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isSmallerEqual(new DialogueAct(left), new DialogueAct(right));
    }
    return isSmallerEqual(leftRdf, rightRdf);
  }

  public boolean isGreaterEqual(String left, String right){
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isGreaterEqual(new DialogueAct(left), new DialogueAct(right));
    }
    return isGreaterEqual(leftRdf, rightRdf);
  }
  
  public boolean isEqual(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isEqual((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isEqual((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isEqual((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isEqual((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isEqual((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isEqual((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isEqual((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isEqual((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isEqual((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isEqual((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isEqual((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isEqual((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isEqual((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isEqual((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isEqual((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isEqual((DialogueAct) left, (DialogueAct) right);
      }
    }
  }
  
  public boolean isSmaller(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isSmaller((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmaller((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmaller((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmaller((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isSmaller((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmaller((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmaller((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmaller((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isSmaller((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmaller((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmaller((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmaller((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isSmaller((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmaller((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmaller((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmaller((DialogueAct) left, (DialogueAct) right);
      }
    }
  }
  
  public boolean isGreater(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isGreater((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isGreater((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isGreater((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isGreater((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isGreater((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isGreater((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isGreater((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isGreater((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isGreater((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isGreater((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isGreater((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isGreater((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isGreater((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isGreater((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isGreater((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isGreater((DialogueAct) left, (DialogueAct) right);
      }
    }
  }
  
  public boolean isSmallerEqual(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isSmallerEqual((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isSmallerEqual((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isSmallerEqual((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isSmallerEqual((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((DialogueAct) left, (DialogueAct) right);
      }
    }
  }
  
  public boolean isGreaterEqual(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isSmallerEqual((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isSmallerEqual((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isSmallerEqual((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isSmallerEqual((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((DialogueAct) left, (DialogueAct) right);
      }
    }
  }

  /**
   * function that logs (rule) conditions
   * @param values the parts of the condition, mapped to true or false
   * @param rule the name of the rule whose condition this is
   * @param file the file the rule is in
   */
  public void LoggerFunction(HashMap<String,Boolean> values, String rule, String file){
    // TODO: do sth useful with information
  }
}
