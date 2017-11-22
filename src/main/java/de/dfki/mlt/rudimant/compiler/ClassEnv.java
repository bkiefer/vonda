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
