package de.dfki.mlt.rudimant.agent;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StubClient implements CommunicationHub {

  private enum EventType { INTENTION, BEHAVIOUR };

  private class Event {
    public Event(EventType t, Object c) {
      type = t; content = c;
    }
    EventType type;
    Object content;
  }

  private final static Logger logger = LoggerFactory.getLogger(StubClient.class);

  /** How much time in milliseconds must pass between two behaviours, if
   *  no message came back that the previous behaviour was finished.
   */
  public static long MIN_TIME_BETWEEN_BEHAVIOURS = 10000;

  private Random r = new Random();

  private boolean isRunning = false;

  private Agent _agent;

  /** Don't send the next behaviour before this point in time is reached */
  private long behaviourNotBefore = 0;

  private Deque<Event> inQueue = new ArrayDeque<>();
  private Deque<Event> itemsToSend = new ArrayDeque<>();

  private Deque<Event> pendingEvents = new ArrayDeque<>();

  public StubClient(Agent agent) {
    _agent = agent;
  }

  public void stringToIntention(String in) {
    inQueue.add(new Event(EventType.INTENTION, new DialogueAct(in)));
  }

  @Override
  public void sendBehaviour(Behaviour b) {
    System.out.println(b.getText());
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
    inQueue.push(new Event(EventType.INTENTION, intention));
  }

  private void onEvent(Event evt) {
  }

  private void sendThis(Event e) {
    switch (e.type) {
    case BEHAVIOUR: sendBehaviour((Behaviour)e.content); break;
    default: logger.warn("Unknown Event: {}", e);
    }
  }

  private void runReceiveSendCyle() {
    while (isRunning) {
      boolean emptyRun = true;
      while (! inQueue.isEmpty()) {
        Event event = inQueue.pollFirst();
        if (_agent.waitForIntention()
            && event.type != EventType.INTENTION) {
          pendingEvents.addFirst(event);
        } else {
          // TODO: DO STH WITH THE EVENT
          onEvent(event);
        }
        emptyRun = false;
      }
      // if a proposal was executed, handle pending events now
      if (!_agent.waitForIntention()) {
        // handle any pending events
        while (!pendingEvents.isEmpty()) {
          onEvent(pendingEvents.removeLast());
        }
      }
      synchronized (this) {
        _agent.actOnNewData();
      }
      synchronized (itemsToSend) {
        Event c = itemsToSend.peekFirst();
        if (c != null && (c.type == EventType.BEHAVIOUR)) {
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
          sendThis(c);
          emptyRun = false;
        }
      }
      if (emptyRun) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {

        }
      }
    }
  }

  public void startListening() {
    Thread listenToClient = new Thread() {
      @Override
      public void run() {
        runReceiveSendCyle();
      }
    };
    listenToClient.setDaemon(true);
    listenToClient.start();
  }

  public static void main(String[] args) {
    StubClient client = new StubClient(null);
    client.startListening();
  }
}
