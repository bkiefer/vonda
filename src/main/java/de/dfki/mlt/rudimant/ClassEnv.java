package de.dfki.mlt.rudimant;

import java.util.*;

/** A class environment, storing things like top-level rules and imports, which
 *  are later on important for generation.
 *
 * @author kiefer
 */
public class ClassEnv {
  private String name;

  // remember the toplevel rules and imports in the correct order
  private List<String> rules;

  /** A stack of the currently processed rules */
  private List<String> activeRules;

  public ClassEnv(String className) {
    name = className;
    rules = new ArrayList<>();
    activeRules = new ArrayList<>();
  }

  /** add the rule to this environment
   * @param rule the name of the rule
   */
  public void addRule(String rule) { rules.add(rule); }

  public String getName() { return name; }

  public void addRuleOrImport(String name) { rules.add(name); }

  public List<String> getRulesAndImports() { return rules; }

  /** Are we on the top-level (not in a rule already) */
  private boolean ontop() { return activeRules.isEmpty(); }

  public boolean enterRule(String name) {
    boolean topLevelRule = ontop();
    if (topLevelRule) addRuleOrImport(name);
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
