package de.dfki.chatcat;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.chatcat.ui.Listener;
import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.types.XsdDouble;
import de.dfki.lt.hfc.types.XsdFloat;
import de.dfki.mlt.rudimant.agent.*;
import javax.swing.JLabel;

public class StubClient implements CommunicationHub {

  private final static Logger logger = LoggerFactory.getLogger(StubClient.class);

  /** How much time in milliseconds must pass between two behaviours, if
   *  no message came back that the previous behaviour was finished.
   */
  public static long MIN_TIME_BETWEEN_BEHAVIOURS = 10000;


  public static float getDefault(Float d) {
    if (d == null) return 0.0f;
    return d;
  }

  public static double getDefault(Double d) {
    if (d == null) return 0.0;
    return d;
  }

  public static int getDefault(Integer d) {
    if (d == null) return 0;
    return d;
  }

  public static boolean getDefault(Boolean b) {
    if (b == null) return false;
    return b;
  }

  public static String float2xsd(Float f) {
    return new XsdFloat(getDefault(f)).toString(true);
  }

  public static String float2xsd(Double f) {
    return new XsdDouble(getDefault(f)).toString(true);
  }

  private Random r = new Random();

  private boolean isRunning = true;

  private ChatCat _agent;

  private Deque<Object> inQueue = new ArrayDeque<>();
  private Deque<Object> itemsToSend = new ArrayDeque<>();

  private Deque<Object> pendingEvents = new ArrayDeque<>();

  private List<Listener<Behaviour>> _listeners = new ArrayList<>();

  public void init(File configDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    //RdfProxy proxy = startClient(configDir, configs);
    _agent = new ChatCat();
    _agent.init(configDir, "eng", configs);
    _agent.setCommunicationHub(this);
  }

  public void registerBehaviourListener(Listener<Behaviour> listener) {
    _listeners.add(listener);
  }

  public void sendEvent(Object in) {
    inQueue.push(in);
  }

  public Agent getAgent() { return _agent; }

  @Override
  public void sendBehaviour(Behaviour b) {
    for (Listener<Behaviour> l : _listeners) {
      l.listen(b);
    }
  }

  // select one of a set of intentions
  @Override
  public void sendIntentions(Set<String> intentions) {
    if (intentions.isEmpty()) return;
    // The following is a stub "statistical" component which randomly selects
    // one intention
    int rand = r.nextInt(intentions.size());
    String intention = null;
    Iterator<String> it = intentions.iterator();
    for(int i = 0; i <= rand; ++i) {
      intention = it.next();
    }
    inQueue.push(new Intention(intention, 0.0));
  }

  public DialogueAct analyse(String in) {
    return _agent.analyse(in);
  }

  // depends on the concrete Event class
  private void onEvent(Object evt) {
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

  // Depends on the concrete Event class
  private void sendThis(Object e) {
    if (e instanceof Behaviour)
      sendBehaviour((Behaviour)e);
    else
      logger.warn("Unknown Object to send: {}", e);
  }

  private boolean isRunning() {
    return isRunning;
  }

  private void runReceiveSendCycle() {
    while (isRunning()) {
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
          sendThis(c);
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

  public void startListening() {
    // eventually connect to communication infrastructure
    // _communicationChannel.connect();
    Thread listenToClient = new Thread() {
      @Override
      public void run() { runReceiveSendCycle(); }
    };
    listenToClient.setName("ListenToEvents");
    listenToClient.setDaemon(true);
    listenToClient.start();
  }

  public void shutdown() {
    // eventually disconnect from communication infrastructure
    // _communicationChannel.disconnect();
    isRunning = false;
  }
}
