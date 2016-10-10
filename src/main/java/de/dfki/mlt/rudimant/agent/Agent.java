package de.dfki.mlt.rudimant.agent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.lt.tr.dialogue.cplan.DagEdge;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import de.dfki.tecs.rpc.RPCFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.thrift.TBase;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author chbu02, Bernd Kiefer
 */
public abstract class Agent {

  public static final String EVENT_PACKAGE_NAME = "de.dfki.mlt.agent.events";

  public static Logger logger;

  public static int PERCEPTION_LATENCY = 2000; // in milliseconds

  /**
   * How much time in milliseconds must pass between two behaviours (al least)
   */
  public static long MIN_TIME_BETWEEN_BEHAVIOURS = 10000;

  public static long MIN_PAUSE_FOR_FINISHED_BEHAVIOURS = 500;

  //public static final Voice AILA_VOICE = new Voice("Aila", 20227);
  //public static final Voice ARTEMIS_VOICE = new Voice("Artemis", 20226);
  // private static final String TBS_HOST = TBS_SERVER_HOST;
  static final String RESOURCE_DIR = "src/main/resources/";

  protected String executedLast = null;

  protected long lastDAprocessed = -1;

  protected String _language;

  // TODO: that's not nice. The mood is transported in the Intention, and
  // i set this field in the intention manager.
  public double proposedRobotMood = 0;

  protected Random random = new Random(System.currentTimeMillis());

  protected String _clientName;

  protected CommunicationSystem comSys;

  protected abstract class Proposal implements Runnable {

    public String name;

    public void go() {
      executedLast = name;
      pendingProposals.clear();
      run();
    }
  }

  /**
   * The RDF storage and reasoner
   */
  public HfcDbService.Client _client;

  /**
   * A class that cares about ASR events and interpretation
   */
  AsrTts asr;

  /**
   * The DAs I emitted, newest first
   */
  protected LinkedList<DialogueAct> myLastDAs;

  /**
   * The DAs I received, newest first
   */
  private LinkedList<DialogueAct> lastDAs;

  protected LinkedList<Event> itemsToSend = new LinkedList<Event>();

  protected Timeouts timeouts = new Timeouts();

  /**
   * Is new data in the repository
   */
  private boolean newData = false;

  public Map<String, Proposal> pendingProposals = new HashMap<>();

  protected static int _generatorCounter = 0;

  /**
   * Generate a unique id for the speech acts
   */
  public static int generateId() {
    return (int) (System.currentTimeMillis() + _generatorCounter++);
  }

  /**
   *
   */
  private long behaviourNotBefore = 0;

  /**
   * the current time, to be attached to chunks of information, and to separate
   * old from new information
   */
  protected long _time;

  /**
   * In this queue, incoming events are stored for later processing when
   * proposals have been sent out but no answer was received up to now.
   */
  protected Deque<Event> pendingEvents = new ArrayDeque<>();

  /**
   * Are we waiting for a proposal to be selected? In this case, put all
   * incoming events into the event queues
   */
  protected boolean proposalsSent;

  /**
   * All Event types that can be processed
   */
  protected Map<String, List<EventHandler<? extends Event>>> subscribedEvents;

  /**
   * Subscribe to an event time, and add the handler
   */
  @SuppressWarnings("rawtypes")
  public void subscribe(String eventType,
          EventHandler<? extends Event> handler) {
    List<EventHandler<? extends Event>> handlers
            = subscribedEvents.get(eventType);
    if (handlers == null) {
      handlers = new ArrayList<EventHandler<? extends Event>>();
      subscribedEvents.put(eventType, handlers);
    }
    handlers.add(handler);
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
  public Agent(String hostIP, String id, int port) {
    logger = LoggerFactory.getLogger(id);
    _clientName = id;
    /* TODO: DO RECOGNITION
    subscribe("SpeechActEvent", new EventHandler<SpeechActEvent>() {
      @Override
      public void handleEvent(SpeechActEvent saEvent) {
        //String lastPercept = fc.addRdfSpeechact(saEvent);
        lastSpeechActs.add(saEvent);
      }});
    subscribe("SpeechActEvent", (id.equals("Malte"))
        ? new EventHandler<SpeechActEvent>() {
            @Override
            public void handleEvent(SpeechActEvent saEvent) {
              logger.info(saEvent.getSender() + " sez: " +
                  AsrTts.toRawSpeechAct(saEvent));
              // Agent.this.fc.addRdfSpeechact(saEvent);
            }
          }
        : new SpeechActToTtsHandler(this));
     */
  }

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

  private boolean subsumes(DialogueAct moreSpecific,
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
          String... args) {
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
          String... args) {
    Integer delay = null;  // TODO: Where does delay come from?
    DialogueAct da = createEmitDA(diaActType, proposition, args);
    Pair<String, String> toSay = asr.generate(da.dag);
    Behaviour b = new Behaviour(toSay.first, toSay.second, delay);
    // String type = da.dag.toString();
    /**/
    // TODO: fix this once we have the final generation in place
    // TODO: maybe exclude (Dis)Confirm(BeingCorrect & AlloPos/Neg(BeingSuccessful
    String type = "";
    if (subsumes(da, "Request", "Turning")
            || subsumes(da, "Disconfirm", "top")
            || subsumes(da, "AlloPositive", "top")
            || subsumes(da, "AlloNegative", "top")
            || subsumes(da, "Confirm", "top")
            || subsumes(da, "Inform", "Instructing")
            || subsumes(da, "InitialGoodbye", "Meeting")
            || subsumes(da, "ReturnGoodbye", "Meeting")
            || subsumes(da, "Accept", "AssigningRole")
            //            || subsumes(da, "Stalling", "Answering")
            || subsumes(da, "Inform", "AssessingPerformance")
            || subsumes(da, "Breaking", "Playing")
            || subsumes(da, "Inform", "GivingSolution")) {
      type = "inform";
    } else if (subsumes(da, "Request", "AssigningRole")
            || subsumes(da, "Request", "Playing")) {
      type = "ynquestion";
    } else if (subsumes(da, "InitialGreeting", "Meeting")
            || subsumes(da, "ReturnGreeting", "Meeting")
            || subsumes(da, "Request", "Liking")
            || subsumes(da, "Request", "Age")) {
      type = "whquestion";
    } else {
      logger.debug("No fitting subsume relation for App? " + da.toString());
    }

    // send a behaviour (motion, textToSay) to the robot
    comSys.sendBehaviour(b);
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
    timeouts.newTimeout(name, millis, emptyProposal);
  }

  protected void newTimeout(String name, int millis, Proposal p) {
    timeouts.newTimeout(name, millis, p);
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

  /**
   * If new data arrived, start the rules processing until no new proposals are
   * added and send the final set to the decision process. After that, the flag
   * signalling that new data arrived is reset
   */
  protected void actOnNewData() {
    //if (pendingProposals.size() > 0) return;
    int oldSize = 0;
    do {
      oldSize = pendingProposals.size();
      try {
        processRules();
      } catch (StopProcessingException ex) { }
    } while (pendingProposals.size() != oldSize);
    if (oldSize > 0) {
      sendIntentions(pendingProposals.keySet());
    }
    newData = false;
  }

  /**
   * Send the list of possible intentions to Hammer
   */
  void sendIntentions(Set<String> strings) {
    List<String> il = new ArrayList<>();
    il.addAll(strings);
    String message = putTogether(';', strings);
    logger.info("Intentions: {}", message);
    comSys.sendIntentions(il);
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

  public void init(String dbHost, int dbPort, String language) {
    _client = RPCFactory.createSyncClient(HfcDbService.Client.class,
            dbHost, dbPort);

    _language = language;
    asr = new AsrTts();
    /*
    try {
      asr.loadGrammar(language, (Agent) this);
    } catch (IOException ex) {
      logger.error("Error loading grammar: {}", ex);
      System.exit(1);
    }
     */
    reset();
  }

  /**
   * Common event handling for all agents
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void onEvent(Event event) {

    String eventType = event.getType();
    if (subscribedEvents.containsKey(eventType)) {
      Object o;
      try {
        String className = EVENT_PACKAGE_NAME + "." + eventType;
        o = Class.forName(className).getConstructor().newInstance();
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
        e.printStackTrace();
        return;
      }

      // do not handle message send by yourself
      if (!event.getSource().equals(_clientName)) {
        // System.out.println(
        // "source " + event.getHeader().source + ", I am " + _clientName);
        for (EventHandler h : subscribedEvents.get(eventType)) {
          h.handleEvent((Event) o);
        }
      }
    }
  }

  private void runReceiveSendCyle() {
    while (comSys.isOnline()) {
      boolean emptyRun = true;
      while (comSys.canReceive()) {
        Event event = comSys.receive();
        if (proposalsSent && !event.getType().equals("Intention")) {
          pendingEvents.addFirst(event);
        } else {
          onEvent(event);
        }
        emptyRun = false;
      }
      // if a proposal was executed, handle pending events now
      if (!proposalsSent) {
        // handle any pending events
        while (!pendingEvents.isEmpty()) {
          onEvent(pendingEvents.removeLast());
        }
      }
      synchronized (this) {
        if (newData || timeouts.timeoutOccured()) {
          actOnNewData();
        }
      }
      synchronized (itemsToSend) {
        Event c = itemsToSend.peekFirst();
        if (c != null
                && (c instanceof Behaviour)) {
          long currentTime = System.currentTimeMillis();
          if (currentTime < behaviourNotBefore) {
            c = null;
          } else {
            //TODO: Use handleEvent from LowLevelNaoCommandHandler?
            behaviourNotBefore = currentTime + MIN_TIME_BETWEEN_BEHAVIOURS;
          }
        }
        if (c != null) {
          itemsToSend.removeFirst();
          logger.debug("Send message via TECS {}", c);
          comSys.sendEvent(c);
          emptyRun = false;
        }
      }
      if (emptyRun) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          comSys.disconnect();
        }
      }
    }
  }

//  @Override
  public void startListening() {
    for (String event : subscribedEvents.keySet()) {
      comSys.subscribe(event);
    }
    comSys.connect();

    newTimeout("start", 2000);

    Thread listenToClient = new Thread() {
      @Override
      public void run() {
        Agent.this.runReceiveSendCyle();
      }
    };
    listenToClient.start();
  }

  protected void propose(String name, Proposal p) {
    // add the proposal to the pending proposals, but not twice
    if (!pendingProposals.containsKey(name) //&& name != executedLast
            ) {
      p.name = name;
      pendingProposals.put(name, p);
    }
  }

  protected void executeProposal(String continuationName) {
    Proposal p = pendingProposals.get(continuationName);
    if (p != null) {
      logger.info("Execute intention: {}", continuationName);
      p.go();
    } else {
      logger.error("Inactive intention: {}", continuationName);
    }
  }

  /**
   * A low level NAO command (or a timeout) signalled that the NAO finished
   * speaking / moving
   *
   * If event sending was blocked while waiting for this signal, unblock it now.
   */
  public void setBehaviourFinished() {
    long currentTime = System.currentTimeMillis();
    logger.info("curr: {}, bnb:{}", currentTime, behaviourNotBefore);
    if (currentTime < behaviourNotBefore) {
      behaviourNotBefore = currentTime + MIN_PAUSE_FOR_FINISHED_BEHAVIOURS;
    }
  }

  private static LinkedHashMap<String, String> configs;

  public static Yaml yaml;

  /**
   * A method to initialize the configuration
   */
  public void init() throws FileNotFoundException {
    configs = (LinkedHashMap<String, String>) yaml.load(new FileInputStream("/../../agent.config.yml"));
  }

  /*
   * SEND part. Message to be send can originate from different threads. But the
   * sending is not "thread-safe" So each message to be send is put into a queue
   * And there a thread is started to do the actual sending
   */
  @SuppressWarnings("rawtypes")
  public void send(TBase event) {
    send(".*", event);
  }

  // queue to hold messages to be send
  protected LinkedList<MessageContainer> messagesToSend = new LinkedList<MessageContainer>();
  // to prevent "multi-use" of the queue
  private Object messagesToSendLock = new Object();

  @SuppressWarnings("rawtypes")
  public void send(final String toWhom, final TBase toSend) {
    synchronized (messagesToSendLock) {
      MessageContainer mc = new MessageContainer(toWhom, toSend);
      messagesToSend.add(mc);
    }
  }
  
  public void stopProcessing(){
    throw new StopProcessingException();
  }
}
