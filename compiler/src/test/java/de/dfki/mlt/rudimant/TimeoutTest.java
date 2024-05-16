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

package de.dfki.mlt.rudimant;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.agent.Agent;
import de.dfki.mlt.rudimant.agent.Timeouts;

public class TimeoutTest {
  private static Timeouts t;
  private static Agent a;

  private List<String> results;

  @BeforeClass
  public static void setup() {
    t = new Timeouts();
    a = new ShutdownTest.TestAgent();
  }

  @Before
  public void init() {
    results = new ArrayList<>();
    t.clear();
  }

  @Test
  public void test() throws InterruptedException {
    t.newTimeout("t1", 300, a.new Proposal() {

      @Override
      public void run() {
        results.add("t1");
      }});
    t.newTimeout("t2", 400, a.new Proposal() {

      @Override
      public void run() {
        results.add("t2");
      }});
    Thread.sleep(100);
    assertFalse(t.timeoutOccured());
    assertTrue(t.hasActiveTimeout("t1"));
    assertFalse(t.isTimedout("t1"));
    assertTrue(t.hasActiveTimeout("t2"));
    assertFalse(t.isTimedout("t2"));
    Thread.sleep(280);
    assertTrue(t.timeoutOccured());
    assertFalse(t.hasActiveTimeout("t1"));
    assertTrue(t.isTimedout("t1"));
    assertFalse(t.timeoutOccured());
    Thread.sleep(100);
    assertTrue(t.timeoutOccured());
    assertFalse(t.hasActiveTimeout("t2"));
    assertTrue(t.isTimedout("t2"));
    assertFalse(t.timeoutOccured());
  }

  @Test
  public void testClear() throws InterruptedException {
    t.newTimeout("t2", 400, a.new Proposal() {
      @Override
      public void run() {
        results.add("t2");
      }});
    t.newTimeout("t1", 300, a.new Proposal() {
      @Override
      public void run() {
        results.add("t1");
      }});
    Thread.sleep(100);
    assertFalse(t.timeoutOccured());
    assertTrue(t.hasActiveTimeout("t1"));
    assertFalse(t.isTimedout("t1"));
    assertTrue(t.hasActiveTimeout("t2"));
    assertFalse(t.isTimedout("t2"));
    Thread.sleep(280);
    assertTrue(t.timeoutOccured());
    assertFalse(t.hasActiveTimeout("t1"));
    assertTrue(t.isTimedout("t1"));
    t.clear();
    Thread.sleep(100);
    assertFalse(t.timeoutOccured());
    assertFalse(t.hasActiveTimeout("t1"));
    assertFalse(t.isTimedout("t1"));
  }


  @Test
  public void testCancel() throws InterruptedException {
    t.newTimeout("t1", 300, a.new Proposal() {
      @Override
      public void run() {
        results.add("t1");
      }});
    t.newTimeout("t2", 400, a.new Proposal() {
      @Override
      public void run() {
        results.add("t2");
      }});
    Thread.sleep(100);
    assertFalse(t.timeoutOccured());
    assertTrue(t.hasActiveTimeout("t1"));
    assertFalse(t.isTimedout("t1"));
    assertTrue(t.hasActiveTimeout("t2"));
    assertFalse(t.isTimedout("t2"));
    Thread.sleep(270);
    assertTrue(t.timeoutOccured());
    assertFalse(t.hasActiveTimeout("t1"));
    assertTrue(t.isTimedout("t1"));
    t.cancelTimeout("t2");
    Thread.sleep(100);
    assertFalse(t.timeoutOccured());
    assertFalse(t.hasActiveTimeout("t2"));
    assertFalse(t.isTimedout("t2"));
    assertTrue(t.isTimedout("t1"));
    t.remove("t1");
    assertFalse(t.isTimedout("t1"));
  }

  @Test
  public void testReschedule() throws InterruptedException {
    t.newTimeout("t1", 200, a.new Proposal() {
      @Override
      public void run() {
        results.add("t1");
      }});
    Thread.sleep(100);
    t.newTimeout("t1", 100, a.new Proposal() {
      @Override
      public void run() {
        results.add("t1_res");
      }});
    Thread.sleep(150);
    assertFalse(t.timeoutOccured());
    assertTrue(t.hasActiveTimeout("t1"));
    assertFalse(t.isTimedout("t1"));
    Thread.sleep(100);
    assertTrue(t.timeoutOccured());
    assertFalse(t.hasActiveTimeout("t1"));
    assertTrue(t.isTimedout("t1"));
  }
}
