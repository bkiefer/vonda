/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <bernd.kiefer@dfki.de>
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.agent.Agent.Proposal;

public class Timeouts {
  private static final Logger logger = LoggerFactory.getLogger(Timeouts.class);

  private final ScheduledThreadPoolExecutor pool;

  private final Map<String, ScheduledFuture<?>> pendingTimeouts = new HashMap<>();

  private final Set<String> occuredTimeouts = new HashSet<>();

  private boolean timeoutOccured = false;

  public Timeouts() {
    pool = new ScheduledThreadPoolExecutor(50);
    pool.setRemoveOnCancelPolicy(true);
  }

  /** Add a new timeout with the given id and proposal. A timeout will only be
   *  added if there is no pending (active) timeout with the same id, otherwise,
   *  the timeout waiting time will be set to the new waiting time, taking into
   *  account the time that already passed since first adding it.
   */
  public synchronized void newTimeout(final String id, int millis, Proposal p) {
    ScheduledFuture<?> t = pendingTimeouts.get(id);
    long timeToFire = millis;
    if (t == null) {
      logger.debug("timeout added: " + id + " " + timeToFire);
    } else {
      timeToFire += t.getDelay(TimeUnit.MILLISECONDS);
      t.cancel(false);
      logger.debug("timeout updated: " + id + " " + timeToFire);
    }
    t = pool.schedule(new Runnable() {
      @Override
      public void run() {
        if (! pendingTimeouts.containsKey(id)) {
          logger.warn("Removed timer fired: {}", id);
          return;
        }
        // don't think i have to do this here
        //pendingTimeouts.get(id).timer.cancel(false);
        pendingTimeouts.remove(id);
        occuredTimeouts.add(id);
        logger.debug("timeout fired: " + id);
        p.run();
        timeoutOccured = true;
      }
    }, timeToFire, TimeUnit.MILLISECONDS);
    pendingTimeouts.put(id, t);
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
    ScheduledFuture<?> t = pendingTimeouts.get(id);
    if (t == null) return false;
    t.cancel(false);
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
    for(ScheduledFuture<?> t : pendingTimeouts.values()) {
      t.cancel(false);
    }
    pendingTimeouts.clear();
  }

}
