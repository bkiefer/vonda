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

import static de.dfki.mlt.rudimant.common.Constants.*;

import java.util.*;

import org.yaml.snakeyaml.Yaml;

public class RuleLogger {

  /** The stored information from the compiler that will be used for logging
   *  the rules
   */
  public BasicInfo rootInfo;

  /** A mapping from positions (rule IDs) to RuleInfo structures, contained
   *  in the rootInfo, which is a tree.
   */
  private List<RuleInfo> ruleInfos;

  /** A class that does the printing */
  private List<LogPrinter> printers;

  /** The next two variable determine which rudi rules are logged */
  public BitSet rulesToLogTrue = new BitSet();
  public BitSet rulesToLogFalse = new BitSet();

  private final Map<Integer, boolean[]> _justLogged;
  private final Map<Integer, boolean[]> _lastLogged;

  public boolean logAllRules = false;

  private void collectRuleInfos(BasicInfo info) {
    if (info instanceof RuleInfo) {
      ruleInfos.add((RuleInfo)info);
      assert(((RuleInfo)info).getId() == ruleInfos.size() - 1);
    }
    for (BasicInfo child : info.getChildren()) {
      collectRuleInfos(child);
    }
  }

  public void loadFromResource() {
    // load the rule infos for logging
    rootInfo = (BasicInfo) new Yaml().load(
        RuleLogger.class.getResourceAsStream("/generated/" + RULE_LOCATION_FILE));
    if (rootInfo != null) {
      ruleInfos = new ArrayList<>();
      collectRuleInfos(rootInfo);
    } else {
      ruleInfos = null;
    }
  }

  public RuleLogger() {
    printers = new ArrayList<>();
    ruleInfos = null;
    _justLogged = new HashMap<>();
    _lastLogged = new HashMap<>();
  }

  public RuleLogger(LogPrinter p){
    this();
    registerPrinter(p);
    loadFromResource();
  }

  public void registerPrinter(LogPrinter p) {
    printers.add(p);
  }

  public void setRootInfo(BasicInfo i) {
    rootInfo = i;
    ruleInfos = new ArrayList<>();
    collectRuleInfos(rootInfo);
  }

  public BasicInfo getRootInfo() {
    return rootInfo;
  }

  /** log all rules */
  public void logAllRules() {
    logAllRules = true;
  }

  /** Reset logging to false for all rules */
  public void resetLogging() {
    logAllRules = false;
    rulesToLogFalse.clear();
    rulesToLogTrue.clear();
  }

  /** Start logging a specific rule */
  public void logRule(int id, int what) {
    logAllRules = false;
    rulesToLogTrue.set(id, ((what & STATE_IF_TRUE) != 0));
    rulesToLogFalse.set(id, ((what & STATE_IF_FALSE) != 0));
  }

  /** For the compiled code, to determine if a rule should be logged */
  private boolean shouldLog(int ruleId, boolean result) {
    return logAllRules
            || (!result && rulesToLogFalse.get(ruleId))
            || (result && rulesToLogTrue.get(ruleId));
  }

  public void clearRecentResults() {
    // TODO: completely clear _lastLogged after a certain time
    //       -> maybe add option to change that value
    if (_justLogged.size() > 0) {
      //_lastLogged.clear();
      _lastLogged.putAll(_justLogged);
      System.out.println("Size of last logged: " + _lastLogged.size());
    }
    _justLogged.clear();
  }

  private boolean justLogged(int ruleId, boolean[] result) {
    boolean[] lastResult = _justLogged.get(ruleId);
    return lastResult != null && Arrays.equals(lastResult, result);
  }

  private boolean lastLogged(int ruleId, boolean[] result) {
    boolean[] lastResult = _lastLogged.get(ruleId);
    return lastResult != null && Arrays.equals(lastResult, result);
  }

  /**
   * function that prints logs of (rule) conditions
   * @param id the id of the rule whose evaluation this is
   * @param values the parts of the condition, mapped to true or false
   */
  public void logRule(int ruleId, boolean[] result) {
    if (ruleInfos != null && shouldLog(ruleId, result[0])
        && ! justLogged(ruleId, result)
        && ! lastLogged(ruleId, result)) {
      _justLogged.put(ruleId, result);
      for (LogPrinter printer : printers)
        printer.printLog(ruleInfos.get(ruleId), result);
    }
  }
}
