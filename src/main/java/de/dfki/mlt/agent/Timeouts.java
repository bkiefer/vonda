package de.dfki.mlt.agent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.agent.Agent.Proposal;

public class Timeouts {
  private static final Logger logger = LoggerFactory.getLogger(Timeouts.class);

  private Map<String, MyTimer> pendingTimeouts = new HashMap<>();

  private Set<String> occuredTimeouts = new HashSet<>();

  private boolean timeoutOccured = false;

  private class MyTimer {
    Timer timer;
    long started;
  }

  public void newTimeout(final String id, int millis, final Proposal p) {
    MyTimer t = pendingTimeouts.get(id);
    int timeToFire = millis;
    if (t == null) {
      t = new MyTimer();
      pendingTimeouts.put(id, t);
      logger.info("timeout added: " + id + " " + millis);
    } else {
      timeToFire = (int) (t.timer.getInitialDelay() + t.started
              - System.currentTimeMillis());
      t.timer.stop();
      logger.info("timeout updated: " + id + " " + timeToFire);
    }
    t.timer = new Timer(timeToFire, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if ("fired".equals(e.getActionCommand())
                || e.getActionCommand() == null) {
          pendingTimeouts.get(id).timer.stop();
          pendingTimeouts.remove(id);
          occuredTimeouts.add(id);
          logger.info("timeout fired: " + id);
          // TODO clean this up. This is certainly not doing the exact same
          // thing as go, but run may be not enough.
          p.run();
          timeoutOccured = true;
        }
      }
    });
    t.started = System.currentTimeMillis();
    t.timer.start();
  }

  public boolean isTimedout(String id) {
    return occuredTimeouts.contains(id);
  }

  public void remove(String id) {
    occuredTimeouts.remove(id);
  }

  public boolean timeoutOccured() {
    boolean result = timeoutOccured;
    timeoutOccured = false;
    return result;
  }

}
