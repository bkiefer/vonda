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

  /** Add a new timeout with the given id and proposal. A timeout will only be
   *  added if there is no pending (active) timeout with the same id, otherwise,
   *  the timeout waiting time will be set to the new waiting time, taking into
   *  account the time that already passed since first adding it.
   */
  public synchronized void newTimeout(final String id, int millis, Proposal p) {
    MyTimer t = pendingTimeouts.get(id);
    int timeToFire = millis;
    if (t == null) {
      t = new MyTimer();
      pendingTimeouts.put(id, t);
      logger.debug("timeout added: " + id + " " + millis);
    } else {
      timeToFire = (int) (t.timer.getInitialDelay() + t.started
              - System.currentTimeMillis());
      t.timer.stop();
      logger.debug("timeout updated: " + id + " " + timeToFire);
    }
    t.timer = new Timer(timeToFire, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if ("fired".equals(e.getActionCommand())
                || e.getActionCommand() == null) {
          pendingTimeouts.get(id).timer.stop();
          pendingTimeouts.remove(id);
          occuredTimeouts.add(id);
          logger.debug("timeout fired: " + id);
          p.run();
          timeoutOccured = true;
        }
      }
    });
    t.started = System.currentTimeMillis();
    t.timer.start();
  }

  /** Did a timeout with this name fire? If so, this will return true until the
   *  occurrence is explicitely removed with the remove method. Thus, timeouts
   *  that are supposed to be used exactly once are easy to implement.
   */
  public boolean isTimedout(String id) {
    return occuredTimeouts.contains(id);
  }

  /** Is there a pending timeout with the given id? */
  public boolean hasActiveTimeout(String id) {
    return pendingTimeouts.containsKey(id);
  }

  /** If there is a pending (active) timeout with the given id, cancel it
   *  without executing its proposal.
   */
  public synchronized boolean cancelTimeout(String id) {
    MyTimer t = pendingTimeouts.get(id);
    if (t == null) return false;
    t.timer.stop();
    pendingTimeouts.remove(id);
    return true;
  }

  /** Remove the given id from the set of timeouts that have fired */
  public synchronized void remove(String id) {
    occuredTimeouts.remove(id);
  }

  /** Did some timeout fire in the meantime? */
  public boolean timeoutOccured() {
    boolean result = timeoutOccured;
    timeoutOccured = false;
    return result;
  }

  /** Cancel all running timers and clear all data structures */
  public synchronized void clear() {
    occuredTimeouts.clear();
    timeoutOccured = false;
    for(MyTimer t : pendingTimeouts.values()) {
      t.timer.stop();
    }
    pendingTimeouts.clear();
  }

}
