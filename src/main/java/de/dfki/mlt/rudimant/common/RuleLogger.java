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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class RuleLogger {

  private static final Logger logger = LoggerFactory.getLogger(RuleLogger.class);

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

  private final Map<Integer, boolean[]> _lastLogged;
  private boolean _lastLoggedChanged = false;

  public boolean logAllRules = false;

  /** The value of this variable determines the "verbosity" of rule logging.
   *  The logger keeps an internal memory of the logged rules, and if the
   *  current situation for a rule did not change from last time, the rule will
   *  not be logged. After a while, however, we maybe want to be reminded of
   *  the current state of affairs.
   *
   *  Setting this variable to zero will result in the original behaviour, while
   *  setting it to a high value will silence logging heavily
   */
  public int clearLogMemoryThreshold = 10;

  /** This counts the calls to processRules since the last clear of the logger
   *  memory. Setting it to a value equal or above clearLogMemoryThreshold
   *  will clear the logger memory on the next call to processRules
   */
  private int callsToProcessRules = 0;

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
    LoaderOptions opt = new LoaderOptions();
    opt.setMaxAliasesForCollections(1000);
    rootInfo = (BasicInfo) new Yaml(opt).load(
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
    clearLogMemory();
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

  /** On the next call to processRules all rules will be logged */
  public void clearLogMemory() {
    _lastLogged.clear();
    _lastLoggedChanged = false;
    callsToProcessRules = 0;
  }

  /** This method will be called on every call to processRules, which means
   *  that it is called once for every rule fix point computation.
   */
  public void clearRecentResults() {
    // completely clear log memory after a certain number of rule process runs
    if (_lastLoggedChanged) {
      _lastLoggedChanged = false;
      ++callsToProcessRules;
      if (callsToProcessRules >= clearLogMemoryThreshold) {
        logger.info("Clearing log cache after {} turns.", callsToProcessRules);
        clearLogMemory();
      } else {
        logger.info("Log cache size: {}", _lastLogged.size());
      }
    }
  }

  private boolean lastLogged(int ruleId, boolean[] result) {
    boolean[] lastResult = _lastLogged.get(ruleId);
    boolean res = lastResult != null && Arrays.equals(lastResult, result);
    if (! res) {
      _lastLogged.put(ruleId, result);
      _lastLoggedChanged = true;
    }
    return res;
  }

  /**
   * function that prints logs of (rule) conditions
   * @param id the id of the rule whose evaluation this is
   * @param values the parts of the condition, mapped to true or false
   */
  public void logRule(int ruleId, boolean[] result) {
    if (ruleInfos != null && shouldLog(ruleId, result[0])
        && ! lastLogged(ruleId, result)) {
      for (LogPrinter printer : printers)
        printer.printLog(ruleInfos.get(ruleId), result);
    }
  }
}
