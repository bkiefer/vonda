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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.agent.Agent.Proposal;

public class Timeouts {
  private static final Logger logger = LoggerFactory.getLogger(Timeouts.class);

  private Map<String, MyTimer> pendingTimeouts = new HashMap<>();

  private Set<String> occuredTimeouts = new HashSet<>();

  private boolean timeoutOccured = false;

  private class MyTimer {
    Timer timer;
    long started;
  }

  public synchronized void newTimeout(final String id, int millis, Proposal p) {
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

  public boolean hasActiveTimeout(String id) {
    return pendingTimeouts.containsKey(id);
  }

  public synchronized boolean cancelTimeout(String id) {
    MyTimer t = pendingTimeouts.get(id);
    if (t == null) return false;
    t.timer.stop();
    pendingTimeouts.remove(id);
    return true;
  }

  public synchronized void remove(String id) {
    occuredTimeouts.remove(id);
  }

  public boolean timeoutOccured() {
    boolean result = timeoutOccured;
    timeoutOccured = false;
    return result;
  }

  // Cancel all running timers and clear all data structures
  public synchronized void clear() {
    occuredTimeouts.clear();
    timeoutOccured = false;
    for(MyTimer t : pendingTimeouts.values()) {
      t.timer.stop();
    }
    pendingTimeouts.clear();
  }

}
