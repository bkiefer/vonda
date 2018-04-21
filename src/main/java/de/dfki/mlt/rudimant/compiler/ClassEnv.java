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

import java.util.*;

import de.dfki.mlt.rudimant.common.RuleInfo;

/** A class environment, storing things like top-level rules and imports, which
 *  are later on important for generation.
 *
 * @author kiefer
 */
public class ClassEnv {
  private String name;

  /** A stack of the currently processed rules */
  private Map<Integer, RuleInfo> activeRules;

  /** The package this class lives in */
  private String[] packageSpec;

  public ClassEnv(String className, String[] pkg) {
    name = className;
    packageSpec = pkg;
    activeRules = new HashMap<>();
  }

  public String getName() { return name; }

  public String[] packageSpec() { return packageSpec; }

  /** Are we on the top-level (not in a rule already) */
  private boolean ontop() { return activeRules.isEmpty(); }

  public boolean enterRule(RuleInfo info) {
    boolean topLevelRule = ontop();
    activeRules.put(info.getId(), info);
    return topLevelRule;
  }

  public RuleInfo getRuleInfo(int id) {
    return activeRules.get(id);
  }
}
