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

/**
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class Behaviour {

  /** default delay between behaviours in milliseconds */
  public static int DEFAULT_DELAY = 0;

  private final String _id;
  private final String _text;
  private final String _motion;
  private final int _delay;

  /** Create a new behaviour
   *
   * @param text   The text to speak
   * @param motion The motion to execute
   * @param delay the minimum delay from or to the next speech act. A negative
   *              delay means that the delay should be before this behaviour,
   *              a positive after this.
   */
  public Behaviour(String id, String text, String motion, int delay) {
    _id = id;
    _text = text;
    _motion = motion;
    _delay = delay;
  }

  /** Create a new behaviour
   *
   * @param text   The text to speak
   * @param motion The motion to execute
   */
  public Behaviour(String text, String motion) {
    this(Long.toHexString(System.currentTimeMillis()),
        text, motion, DEFAULT_DELAY);
  }

  public Behaviour(String text, String motion, int delay) {
    this(Long.toHexString(System.currentTimeMillis()), text, motion, delay);
  }

  public String getId() {
    return _id;
  }

  public String getText() {
    return _text;
  }

  public String getMotion() {
    return _motion;
  }

  public Integer getDelay() {
    return _delay;
  }
}
