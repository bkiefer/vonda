package de.dfki.mlt.rudimant.compiler;

public interface Constants {
  public static final String RULE_FILE_EXT = ".rudi";
  // Definitions for methods and variables in Agent.java
  public static final String AGENT_DEFS = "Agent" + RULE_FILE_EXT;

  public static final String CFG_OUTPUT_DIRECTORY = "outputDirectory";
  public static final String CFG_WRAPPER_CLASS = "wrapperClass";
  public static final String CFG_ONTOLOGY_FILE = "ontologyFile";
  public static final String CFG_TYPE_ERROR_FATAL = "failOnError";
  public static final String CFG_PACKAGE = "rootPackage";
  public static final String CFG_NAME_TO_URI = "nameToURI";
  public static final String CFG_VISUALISE = "visualise";
  public static final String CFG_LOGGING = "logging";

  public static final String DIALOGUE_ACT_TYPE = "<dial:DialogueAct>";

  public static final String CANCEL_LOCAL = "1";
  public static final String CANCEL_GLOBAL = "-1";

  public static final String CFG_RULE_LOCATION_FILE = "ruleLocationFile";
  public static final String CFG_RULE_LOCATION_SUFFIX = "RuleLocation.yml";
}
