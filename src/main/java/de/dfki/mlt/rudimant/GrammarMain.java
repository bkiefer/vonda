/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import de.dfki.lt.hfc.WrongFormatException;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * the main class of rudimant
 *
 * @author Anna Welker
 */
public class GrammarMain {

  private static String help = "Hello, this is rudimant.\n"
          + "Currently, the following flags are available:\n"
          + "-l\tTranscribe file in logmode. Text will be added so that "
          + "the outcome of\n"
          + "\tall boolean expressions is being logged as "
          + "soon as they are evaluated.\n"
          + "-e\tDo not crash if there are .rudi files that cannot be translated\n"
          + "-d\tDo not try to do type checking while translating\n"
          + "\n\nPlease use this tool as follows:\n"
          + "java rudimant <directory_to_be_searched/> <paht_of_config_file/> OPTIONS\n"
          + "-o=DIRECTORY\tSpecify DIRECTORY as the output directory";

  public static Map<String, Object> configs;
  public static File confDir;

  // rdf functionality

  public static boolean process(RudimantCompiler rc, List<String> files)
      throws IOException {
    try {
      for (String file : files) {
        rc.processToplevel(new File(file));
      }
    } catch (UnsupportedOperationException ex) {
      if (ex.getMessage().startsWith("Parsing")) return true;
      throw(ex);
    }
    return false;
  }

  /** For unit tests */
  public void setConfig(Map<String, Object> conf) {
    configs = conf;
  }

  final static Object [][] defaults = {
    { CFG_TYPE_ERROR_FATAL, true, "e" },
    { CFG_VISUALISE, false , "v" },
    { CFG_TARGET_CONSTRUCTOR, "", "CA" },
    { CFG_WRAPPER_CLASS, "w", "DummyAgent" },
  };

  public Map<String, Object> defaultConfig() {
    configs = new LinkedHashMap<String, Object>();
    for (Object[] pair : defaults) {
      configs.put((String)pair[0], pair[1]);
    }
    return configs;
  }

  static Object getDefault(String key) {
    Object result = null;
    for (Object[] pair : defaults) {
      if (key.equals(pair[0])) {
        result = pair[1];
        break;
      }
    }
    return result;
  }

  static void setDefault(String key, Map<String, Object> configs) {
    if (! configs.containsKey(key)) {
      configs.put(key, getDefault(key));
    }
  }

  static void setValue(String key, String option, Object def,
      Map<String, Object> configs, OptionSet options) {
    if (options.has(option)) {
      if (options.valueOf(option) == null) {
        configs.put(key, ! (boolean) def);
      } else {
        configs.put(key, (String) options.valueOf(option));
      }
    } else if (! configs.containsKey(key)) {
      configs.put(key, def);
    }
  }

  @SuppressWarnings("unchecked")
  public static void readConfig(String confname)
      throws FileNotFoundException {
    Yaml yaml = new Yaml();
    File confFile = new File(confname);
    confDir = confFile.getParentFile();
    configs = (Map<String, Object>) yaml.load(new FileReader(confFile));
  }

  /**
   *
   * @param args: the file that should be parsed without ending (in args[0])
   *             + the complete path of the configfile
   * @throws WrongFormatException
   * @throws Exception
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static void main(String[] args) throws WrongFormatException {
    // BasicConfigurator.resetConfiguration();
    // BasicConfigurator.configure();

    OptionParser parser = new OptionParser("hlver:w:c:o:");
    parser.accepts("help");
    OptionSet options = null;

    GrammarMain main = new GrammarMain();
    File outputDirectory = null;
    List files = null;
    confDir = new File(".");

    try {
      options = parser.parse(args);
      files = options.nonOptionArguments();

      if (files.isEmpty()) {
        usage("Input file is missing");
        System.exit(1);
      }

      outputDirectory = new File((String)files.get(0)).getParentFile();

      if (options.has("help") || options.has("h")) {
        System.out.println(help);
        System.exit(0);
      }
      if (options.has("c")) {
        String confName = (String)options.valueOf("c");
        readConfig(confName);
      } else {
        configs = main.defaultConfig();
      }

      for (Object[] val : defaults) {
        if (val[2] != null) {
          setValue((String)val[0], (String)val[2], val[1], configs, options);
        }
      }

      if (options.has("w")) {
        configs.put(CFG_WRAPPER_CLASS, options.valueOf("w"));
      }
      if (options.has("l")) {
        configs.put(CFG_WRAPPER_CLASS, false);
      }
      if (options.has("v")) {
        configs.put(CFG_VISUALISE, true);
      }
      if (options.has("r")) {
        configs.put(CFG_ONTOLOGY_FILE, options.valueOf("r"));
      }
      if (options.has("o")) {
        outputDirectory = new File((String)options.valueOf("o"));
        configs.put(CFG_OUTPUT_DIRECTORY, outputDirectory);
      }
      main.setConfig(configs);
      if (process(RudimantCompiler.init(confDir, configs), files)) {
        System.out.println("Parsing failed");
        System.exit(1);
      }
    } catch (OptionException ex) {
      usage("Error parsing options: " + ex.getMessage());
    } catch (IOException ex) {
      usage("A file error occured: " + ex.getMessage());
    } finally {
      RudimantCompiler.shutdown();
    }
  }

  private static void usage(String message) {
    System.out.println(message);
    System.out.println(
          "java rudimant -h<elp> -v<isualize> -e<rrorstops>\n"
        + "              -r ontos.ini -w WrapperClass -c config.yaml\n"
        + "              -o outputDir toplevelfile.rudi (file.rudi)*\n"
        + "              -l log with java, not rudi look of code\n"
        + "For help see rumdimant -help\n");
  }

}
