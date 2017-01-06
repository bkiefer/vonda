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

  public abstract class Proposal {

    public abstract void run() throws Exception;

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

  protected boolean isTimedout(String name) {
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
 
//  public static boolean isEqual(){}
//  public static boolean isSmaller(){}
//  public static boolean isGreater(){}
//  public static boolean isSmallerEqual(){}
//  public static boolean isGreaterEqual(){}
  
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
    try {
      return isEqual(_proxy.getRdfClass(left), right.getClazz());
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  } 
  public boolean isSmaller(String left, Rdf right){
    try {
      return isSmaller(_proxy.getRdfClass(left), right.getClazz());
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isGreater(String left, Rdf right){
    try {
      return isGreater(_proxy.getRdfClass(left), right.getClazz());
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isSmallerEqual(String left, Rdf right){
    try {
      return isSmallerEqual(_proxy.getRdfClass(left), right.getClazz());
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isGreaterEqual(String left, Rdf right){
    try {
      return isGreaterEqual(_proxy.getRdfClass(left), right.getClazz());
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  
  public boolean isEqual(Rdf left, String right){
    try {
      return isEqual(left.getClazz(), _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  } 
  public boolean isSmaller(Rdf left, String right){
    try {
      return isSmaller(left.getClazz(), _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isGreater(Rdf left, String right){
    try {
      return isGreater(left.getClazz(), _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }  
  public boolean isSmallerEqual(Rdf left, String right){
    try {
      return isSmallerEqual(left.getClazz(), _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isGreaterEqual(Rdf left, String right){
    try {
      return isGreaterEqual(left.getClazz(), _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
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
    try {
      return isEqual(_proxy.getRdfClass(left), right);
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);      
    }
    return false;
  } 
  public boolean isSmaller(String left, RdfClass right){
    try {
      return isSmaller(_proxy.getRdfClass(left), right);
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isGreater(String left, RdfClass right){
    try {
      return isGreater(_proxy.getRdfClass(left), right);
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isSmallerEqual(String left, RdfClass right){
    try {
      return isSmallerEqual(_proxy.getRdfClass(left), right);
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isGreaterEqual(String left, RdfClass right){
    try {
      return isGreaterEqual(_proxy.getRdfClass(left), right);
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  
  public boolean isEqual(RdfClass left, String right){
    try {
      return isEqual(left, _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  } 
  public boolean isSmaller(RdfClass left, String right){
    try {
      return isSmaller(left, _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isGreater(RdfClass left, String right){
    try {
      return isGreater(left, _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }  
  public boolean isSmallerEqual(RdfClass left, String right){
    try {
      return isSmallerEqual(left, _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  public boolean isGreaterEqual(RdfClass left, String right){
    try {
      return isGreaterEqual(left, _proxy.getRdfClass(right));
    } catch (TException ex) {
      java.util.logging.Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  
  public boolean isEqual(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    try{
      RdfClass leftRdf = _proxy.getRdfClass(left);
      RdfClass rightRdf = _proxy.getRdfClass(right);
      if(leftRdf == null || rightRdf == null){
        return isEqual(new DialogueAct(left), new DialogueAct(right));
      }
      return isEqual(leftRdf, rightRdf);
    }
    catch (TException e){
      return isEqual(new DialogueAct(left), new DialogueAct(right));
    }
  }
  
  public boolean isSmaller(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    try{
      RdfClass leftRdf = _proxy.getRdfClass(left);
      RdfClass rightRdf = _proxy.getRdfClass(right);
      if(leftRdf == null || rightRdf == null){
        return isSmaller(new DialogueAct(left), new DialogueAct(right));
      }
      return isSmaller(leftRdf, rightRdf);
    }
    catch (TException e){
      return isSmaller(new DialogueAct(left), new DialogueAct(right));
    }
  }  
  public boolean isGreater(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    try{
      RdfClass leftRdf = _proxy.getRdfClass(left);
      RdfClass rightRdf = _proxy.getRdfClass(right);
      if(leftRdf == null || rightRdf == null){
        return isGreater(new DialogueAct(left), new DialogueAct(right));
      }
      return isGreater(leftRdf, rightRdf);
    }
    catch (TException e){
      return isGreater(new DialogueAct(left), new DialogueAct(right));
    }
  }  
  public boolean isSmallerEqual(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    try{
      RdfClass leftRdf = _proxy.getRdfClass(left);
      RdfClass rightRdf = _proxy.getRdfClass(right);
      if(leftRdf == null || rightRdf == null){
        return isSmallerEqual(new DialogueAct(left), new DialogueAct(right));
      }
      return isSmallerEqual(leftRdf, rightRdf);
    }
    catch (TException e){
      return isSmallerEqual(new DialogueAct(left), new DialogueAct(right));
    }
  }
  public boolean isGreaterEqual(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    try{
      RdfClass leftRdf = _proxy.getRdfClass(left);
      RdfClass rightRdf = _proxy.getRdfClass(right);
      if(leftRdf == null || rightRdf == null){
        return isGreaterEqual(new DialogueAct(left), new DialogueAct(right));
      }
      return isGreaterEqual(leftRdf, rightRdf);
    }
    catch (TException e){
      return isGreaterEqual(new DialogueAct(left), new DialogueAct(right));
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
