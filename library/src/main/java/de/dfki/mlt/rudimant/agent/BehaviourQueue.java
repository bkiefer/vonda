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

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.agent.Agent.Proposal;
import de.dfki.mlt.rudimant.agent.nlp.Pair;

/**
 * This class implements a queue for behaviours that were sent out, but
 * have received no signal yet that they have been finished. Additionally,
 * a timer is started to ensure that behaviours that get lost to not block
 * the system.
 */
public class BehaviourQueue {

  private static final Logger logger = LoggerFactory.getLogger(Agent.class);

  /** How much time in milliseconds must pass between two behaviours, if
   *  no message came back that the previous behaviour was finished.
   */
  public static long MIN_TIME_BETWEEN_BEHAVIOURS = 10000;

  /** The minimum pause after we got a signal that the previous behaviour
   *  was finished.
   */
  public static long MIN_PAUSE_FOR_FINISHED_BEHAVIOURS = 500;

  /** Don't send the next behaviour before this point in time is reached */
  private long behaviourNotBefore = 0;

  // to estimate the duration per word/motion behaviour, can change during session
  protected long sumOfDurations = 400;
  protected long numberOfItems = 1;

  private class BHContainer {
    public long delayBefore, delayAfter, issued;
    public int items; // no of words and motion behaviours in this container
    String id;
    Behaviour b;
  }

  /** The queue of unfinished behaviours already sent out */
  private Deque<BHContainer> _pendingBehaviours = new ArrayDeque<>();

  private IdentityHashMap<Behaviour, Integer> _customDelay = new IdentityHashMap<>();

  private Map<String, Pair<Proposal, Integer>> _behaviourTriggers = new HashMap<>();

  /** Excecute the proposal either after maxWait msecs have passed or the
   *  last behaviour added has ended. This is the only function that attaches
   *  a proposal to a behaviour, so _behaviourTriggers is only there to handle
   *  Agent.behaviourTimeout calls.
   *
   */
  void addBehaviourTimeout(String id, int maxWait, Proposal p) {
    synchronized(_behaviourTriggers) {
      _behaviourTriggers.put(id, new Pair<Proposal, Integer>(p, maxWait));
    }
  }

  Pair<Proposal, Integer> getTrigger(String behaviourId) {
    return _behaviourTriggers.get(behaviourId);
  }

  void clear() {
    _pendingBehaviours.clear();
    _behaviourTriggers.clear();
  }

  Proposal checkBehaviourTimeout(String behaviourId) {
    Proposal result = null;
    synchronized(_behaviourTriggers) {
      if (_behaviourTriggers.containsKey(behaviourId)) {
        result = _behaviourTriggers.get(behaviourId).first;
        _behaviourTriggers.remove(behaviourId);
      }
    }
    return result;
  }

  private double estimatedTime(int items) {
    return items * ((double)sumOfDurations / numberOfItems);
  }

  /** Return a list of overdue behaviours, such that possible attached proposals
   *  can be executed. Overdue behaviours are deleted from the _pendingBehaviours
   *
   *  Uses BHContainer
   * @return
   */
  List<String> removeOverdueBehaviours() {
    List<String> result = new ArrayList<>();
    long currentTime = System.currentTimeMillis();
    while (!_pendingBehaviours.isEmpty()) {
      BHContainer bc = _pendingBehaviours.peek();
      long timeUsed = currentTime - bc.issued;
      double timeEstimated = estimatedTime(bc.items);
      if (timeUsed > 3 * timeEstimated) {
        _pendingBehaviours.pop();
        result.add(bc.id);
        logger.info("removing overdue behaviour {} (ETA {}/{}), was {}", bc.id,
            timeEstimated, bc.items, timeUsed);
      } else {
        // logger.info("Waiting for behaviour {} to finish", bc.id);
        break;
      }
    }
    return result;
  }

  boolean waitForBehaviours() {
    return (! _pendingBehaviours.isEmpty());
  }

  /** Put the behaviour into a waiting queue to see when it's finished.
   *  There will be a SystemInfo "finished" message that contains the id of the
   *  behaviour
   * @param c the message containing the behaviour
   */
  void enqueueBehaviour(Behaviour b) {
    BHContainer bc = new BHContainer();
    bc.items = b.getText().split("  *").length + b.getMotion().split("\\|").length;
    bc.id = b.getId();
    bc.b = b;
    bc.issued = System.currentTimeMillis();
    /*
    if (minTime < 0) {
      bc.delayBefore = - minTime;
    } else if (minTime > 0) {
      bc.delayAfter = minTime;
    }
    */
    logger.info("enqueueing behaviour {} (ETA {}/{})", bc.id,
        (long)estimatedTime(bc.items), bc.items);
    _pendingBehaviours.push(bc);
  }


  /**
   * A low level NAO command (or a timeout) signalled that the NAO finished
   * speaking / moving
   *
   * If event sending was blocked while waiting for this signal, unblock it now.
   */
  List<String> setBehaviourFinished(String behaviourId) {
    List<String> result = new ArrayList<>();
    if (_pendingBehaviours.isEmpty()) return result;
    int i = 0;
    for (BHContainer b : _pendingBehaviours) {
      if (b.id.equals(behaviourId)) {
        long timeNeeded = System.currentTimeMillis() - b.issued;
        logger.info("behaviour {} (ETA {}/{}), was {}", b.id,
            (long) estimatedTime(b.items), b.items, timeNeeded);
        sumOfDurations += timeNeeded;
        numberOfItems += b.items;
        break;
      }
      ++i;
    }
    if (i == _pendingBehaviours.size()) {
      logger.warn("no matching behaviour found for: {}", behaviourId);
      return result;
    } else if (i > 0) {
      logger.warn("Receiving behaviour id in wrong order: in: {} pending: {}"
          , behaviourId, _pendingBehaviours.peek().id);
    }
    while (i >= 0) {
      BHContainer bc = _pendingBehaviours.pop();
      result.add(bc.id);
      logger.info("removing behaviour {}", bc.id);
      --i;
    }
    return result;
  }

}
