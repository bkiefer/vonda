package de.dfki.mlt.rudimant.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Configs {

  // TODO: MAYBE ADD CONFIG FOR CHARSET USED IN SOURCE AND GENERATED FILES
  // Only used internally, the directory where the config file is located,
  // resp. to which all relative file references will be resolved
  public static final String CFG_CONFIG_DIRECTORY = "configDirectory";

  /** The location of the ontology configuration file, if any */
  public static final String CFG_ONTOLOGY_FILE = "ontologyFile";

  public static final String CFG_INPUT_FILE = "inputFile";
  public static final String CFG_OUTPUT_DIRECTORY = "outputDirectory";
  public static final String CFG_AGENT_BASE_CLASS = "agentBase";
  public static final String CFG_TYPE_ERROR_FATAL = "failOnError";
  public static final String CFG_TYPE_DEFS_FILE = "typeDef";
  public static final String CFG_PRINT_ERRORS = "printErrors";
  public static final String CFG_PACKAGE = "rootPackage";
  public static final String CFG_NAME_TO_URI = "nameToURI";
  public static final String CFG_NAME_TO_CLASS = "nameToClass";
  public static final String CFG_VISUALISE = "visualise";
  public static final String CFG_PERSISTENT_VARS = "persistentVars";

  /** the port for the debugger to connect to the running system */
  public static final String CFG_DEBUG_PORT = "debugPort";

  /** The class of NLG or NLU plugin classes */
  public static final String CFG_CLASS = "class";
  /** NLG option section */
  public static final String CFG_NLG_KEY = "NLG";
  /** NLG mapper config */
  public static final String CFG_NLG_MAPPER_PROJECT = "mapperProject";
  /** NLG main config */
  public static final String CFG_NLG_GENERATION_PROJECT = "generationProject";
  /** NLG Flag if numbers should be translated to text by ICU or not */
  public static final String CFG_NLG_TRANSLATE_NUMBERS = "translateNumbers";

  /** NLU option section */
  public static final String CFG_NLU_KEY = "NLU";
  /** NLU config key for grammar name, semantics depends on plugin */
  public static final String CFG_NLU_GRAMMAR = "grammar";
  /** NLU specify location of project file for cplan based converter of JSON
   *  input, see Interpreter abstract base class
   */
  public static final String CFG_NLU_CONVERTER = "converter";
  /** NLU config key for eventually loading a tokenizer */
  public static final String CFG_NLU_TOKENIZER= "tokenizer";

  /** TrivialTokenizer: convert input to lowercase in preprocess */
  public static final String CFG_TOK_TOLOWER = "toLower";
  /** TrivialTokenizer: Remove punctuation symbols from input string, currently
   *  fixed set */
  public static final String CFG_TOK_REMOVE_PUNCTUATION = "removePunctuation";

  public static Map<String,Object> readConfig(File confFile)
      throws FileNotFoundException {
    Yaml yaml = new Yaml();
    File confDir = confFile.getParentFile();
    if (confDir == null) {
      confDir = new File(".");
    }
    Map<String,Object> configs = yaml.load(new FileReader(confFile));
    configs.put(CFG_CONFIG_DIRECTORY, confDir);
    return configs;
  }

  public static Map<String,Object> readConfig(String confName)
      throws FileNotFoundException {
    return readConfig(new File(confName));
  }
}
