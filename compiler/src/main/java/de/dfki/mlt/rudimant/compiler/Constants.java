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

package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.common.Constants.RULE_FILE_EXTENSION;

/** A set of constants only relevant during the compilation phase */
public interface Constants {

  // Definitions for methods and variables in Agent.java
  public static final String AGENT_DEFS = "Agent" + RULE_FILE_EXTENSION;

  public static final String DIALOGUE_ACT_TYPE = "<dial:DialogueAct>";

  public static final String CANCEL_LOCAL = "1";
  public static final String CANCEL_GLOBAL = "-1";

  public static final String COMPILER_VERSION = "3.0";
}

