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

package de.dfki.mlt.rudimant.common;

/** A set of constants only relevant at run-time and compilation */
public interface Constants {

  public static final String RULE_FILE_EXTENSION = ".rudi";
  public static final String RULE_LOCATION_FILE = "RuleLoc.yml";

  /* used to mark the rule logging state of a rule */
  public static final int STATE_NEVER = 0;
  public static final int STATE_IF_TRUE = 0b1;
  public static final int STATE_IF_FALSE = 0b10;
  public static final int STATE_ALWAYS = STATE_IF_TRUE | STATE_IF_FALSE;
  public static final int STATE_PARTLY = 9;
  public static final int STATE_RULELESS = 10;
}
