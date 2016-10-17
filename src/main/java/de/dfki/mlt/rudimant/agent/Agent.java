package de.dfki.mlt.rudimant.agent;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.tr.dialogue.cplan.DagEdge;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlg.Pair;

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
  public DialogueAct daFromVals(String diaActType, String proposition,
          String... args) {
    // args is a list of key/value pair
    assert ((args.length & 1) == 0);

    String dialogueAct = AsrTts.toRawSpeechAct(diaActType, proposition, args);
    DialogueAct da = new DialogueAct();
    da.dag = asr.toDag(dialogueAct);
    return da;
  }

  public boolean subsumes(DialogueAct moreSpecific,
      String type, String prop, String... keyVal) {
    if (moreSpecific == null) {
      return false;
    }
    DialogueAct general = new DialogueAct();
    general.dag = asr.toDag(AsrTts.toRawSpeechAct(type, prop, keyVal));
    return general.dag.subsumes(moreSpecific.dag);
  }

  /**
   * Generate DialogueAct from a raw speech act representation
   */
  protected DialogueAct createEmitDA(String diaActType, String proposition,
          String ... args) {
    String dialogueAct = AsrTts.toRawSpeechAct(diaActType, proposition, args);
    DialogueAct da = new DialogueAct();
    da.dag = asr.toDag(dialogueAct);
    myLastDAs.addFirst(da);

    newData();
    return da;
  }

  /**
   * Generate text and motion from a raw speech act representation and send it
   * to the Behaviourmanager
   */
  protected DialogueAct emitDA(String diaActType, String proposition,
          String ... args) {
    DialogueAct da = createEmitDA(diaActType, proposition, args);
    Pair<String, String> toSay = asr.generate(da.dag);
    _hub.sendBehaviour(new Behaviour(toSay.first, toSay.second));
    return da;
  }

  public DialogueAct getMyLastDA() {
    return myLastDAs.peekFirst();
  }

  public boolean isMyLastDA(String type, String prop, String... keyVal) {
    return subsumes(getMyLastDA(), type, prop, keyVal);
  }

  /**
   * Return the index of the last speech act equal or more specific than the
   * given one
   */
  protected int lastOccurenceOfMyDA(String type, String prop, String... keyVal) {
    DialogueAct general = daFromVals(type, prop, keyVal);
    int i = 0;
    for (DialogueAct evt : myLastDAs) {
      if (general.dag.subsumes(evt.dag)) {
        return i;
      }
      ++i;
    }
    return -1;
  }

  /**
   * Return the index of the last speech act with a type equal or more specific
   * than the given one
   */
  protected int lastOccurenceOfMyDA(String type) {
    return lastOccurenceOfMyDA(type, "top");
  }

  public DialogueAct getLastDA() {
    DialogueAct last = lastDAs.peekFirst();
    // TODO: THIS IS NOT QUITE RIGHT. I SHOULD MARK SINGLE INCOMING DA'S AS
    // PROCESSED
    if (last == null || last.timeStamp < lastDAprocessed) {
      return null;
    }
    return last;
  }

  public DialogueAct addLastDA(String type, String prop, String... keyVal) {
    DialogueAct newDA = daFromVals(type, prop, keyVal);
    return addLastDA(newDA);
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

  public boolean isLastDA(String type, String prop, String... keyVal) {
    if (getLastDA() == null) {
      return false;
    }
    return subsumes(getLastDA(), type, prop, keyVal);
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
}
