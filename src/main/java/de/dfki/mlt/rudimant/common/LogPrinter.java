package de.dfki.mlt.rudimant.common;

public interface LogPrinter {
  /** print a log for the given rule id and concrete results.
   * @param ruleId the numeric id of the rule
   * @param result boolean values for all *base terms* of the rule expression,
   *        plus the overall result, which is in position zero.
   */
  public void printLog(RuleInfo ruleId, boolean[] result);
}
