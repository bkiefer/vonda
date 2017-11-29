package de.dfki.mlt.rudimant.common;

public class Constants {
  public static final String RULE_FILE_EXTENSION = ".rudi";
  public static final String RULE_LOCATION_FILE = "RuleLoc.yml";

  /* used to mark the rule logging state of a rule */
  public static final int STATE_NEVER = 0;
  public static final int STATE_IF_TRUE = 0b1;
  public static final int STATE_IF_FALSE = 0b10;
  public static final int STATE_ALWAYS = STATE_IF_TRUE | STATE_IF_FALSE;
  public static final int STATE_PARTLY = 9;
}
