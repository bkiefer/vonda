package mini;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.dfki.lt.hfc.db.HfcDbHandler;
import de.dfki.lt.hfc.db.rdfProxy.DbClient;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.Agent;
import de.dfki.mlt.rudimant.agent.Behaviour;
import de.dfki.mlt.rudimant.agent.CommunicationHub;
import de.dfki.mlt.rudimant.agent.Intention;
import de.dfki.mlt.rudimant.agent.nlp.DialogueAct;
import de.dfki.mlt.rudimant.agent.nlp.LanguageServices;
import de.dfki.mlt.rudimant.agent.nlp.Pair;

public abstract class WrapperClass extends Agent {

  public int getSomething() { return 0; }

  final static Object [][] defaults = {
    { "ontologyFile", "../ontologies/inits/pal.inference.yml", "o" },
  };

  public static Map<String, Object> defaultConfig() {
    Map<String, Object> configs = new LinkedHashMap<>();
    for (Object[] pair : defaults) {
      configs.put((String)pair[0], pair[1]);
    }
    return configs;
  }

  static final String[] nms = { "Frank", "Otto", "Jan", "John", "Bill" };

  static List<String> names = new LinkedList<>();

  static {
    names.addAll(Arrays.asList(nms));
  }

  static DbClient handler;

  static AllYouCanDo _agent;

  static boolean isRunning = true;

  Deque<Object> inQueue = new ArrayDeque<>();
  Deque<Object> itemsToSend = new ArrayDeque<>();

  Deque<Object> pendingEvents = new ArrayDeque<>();

  void onEvent(Object evt) {
    logger.debug("onEvent: {}", evt);
    if (evt instanceof Intention) {
      _agent.executeProposal((Intention)evt);
    } else if (evt instanceof DialogueAct) {
      _agent.addLastDA((DialogueAct)evt);
      _agent.newData();
    } else if (evt instanceof String) {
      DialogueAct da = _agent.analyse((String)evt);
      inQueue.add(da);
    } else {
      logger.warn("Unknown incoming object: {}", evt);
    }
  }

  private void runReceiveSendCycle() {
    while (isRunning) {
      boolean emptyRun = true;
      while (! inQueue.isEmpty()) {
        Object event = inQueue.pollFirst();
        onEvent(event);
      }
      // if a proposal was executed, handle pending events now
      if (!_agent.waitForIntention()) {
        // handle any pending events
        while (!pendingEvents.isEmpty()) {
          onEvent(pendingEvents.removeLast());
        }
        _agent.processRules();
      }
      synchronized (itemsToSend) {
        Object c = itemsToSend.peekFirst();
        if (c != null && (c instanceof Behaviour)
            && _agent.waitForBehaviours((Behaviour)c)) {
          c = null;
        }
        if (c != null) {
          itemsToSend.removeFirst();
          logger.debug("<-- {}", c);
          emptyRun = false;
        }
      }
      if (emptyRun) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          // shut down?
        }
      }
    }
    _agent.shutdown();
  }

  public void init() {
    File confDir = new File(".");
    //_agent.init(confDir, "eng", defaultConfig());

    //RdfProxy proxy = startClient(confDir, defaultConfig());
    Map<String, Object> configs = defaultConfig();
    String ontoFileName = (String) configs.get("ontologyFile");
    handler = new HfcDbHandler(ontoFileName);
    RdfProxy proxy = new RdfProxy(handler);
    handler.registerStreamingClient(proxy);

    super.init(confDir, "eng", proxy, configs, "all");
    //robot = proxy.getRdf("<chatcat:robot1>");
    this.langServices = new LanguageServices() {
      @Override
      public Pair<String, String> generate(DagNode saEvent) {
        return new Pair<>(saEvent.toString(), saEvent.toString());
      }
    };
    // start first round of rule evaluations
    newData();
    // log all rules to stdout
    this.logAllRules();

    _agent.setCommunicationHub(new CommunicationHub() {

      @Override
      public void sendIntentions(Set<String> intentions) {
        logger.debug("Intentions: {}", intentions);
      }

      @Override
      public void sendBehaviour(Behaviour b) {
        logger.debug("Behaviour: {}", b);
      }
    });

    Thread listenToClient = new Thread() {
      @Override
      public void run() { runReceiveSendCycle(); }
    };
    listenToClient.setName("ListenToEvents");
    listenToClient.setDaemon(true);
    listenToClient.start();
    newData();
  }

  public static void main(String[] args) {
    _agent = new AllYouCanDo();
    _agent.init();
  }
}
