package de.dfki.mlt.rudimant.compiler;

import java.util.*;

/** A class environment, storing things like top-level rules and imports, which
 *  are later on important for generation.
 *
 * @author kiefer
 */
public class ClassEnv {
  private String name;

  /** A stack of the currently processed rules */
  private List<String> activeRules;

  /** The package this class lives in */
  private String[] packageSpec;

  public ClassEnv(String className, String[] pkg) {
    name = className;
    packageSpec = pkg;
    activeRules = new ArrayList<>();
  }

  public String getName() { return name; }

  public String[] packageSpec() { return packageSpec; }

  /** Are we on the top-level (not in a rule already) */
  private boolean ontop() { return activeRules.isEmpty(); }

  public boolean enterRule(String name) {
    boolean topLevelRule = ontop();
    activeRules.add(name);
    return topLevelRule;
  }

  public void leaveRule() {
    activeRules.remove(activeRules.size() - 1);
  }

  public String getCurrentRule() {
    if (activeRules.isEmpty()) return null;
    return activeRules.get(activeRules.size() - 1);
  }

  public boolean isActiveRule(String name) {
    return activeRules.indexOf(name) >= 0;
  }
}
