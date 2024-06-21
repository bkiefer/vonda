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

package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.common.Constants.*;
import static de.dfki.mlt.rudimant.compiler.Constants.COMPILER_VERSION;
import static java.nio.file.Files.createDirectories;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.common.IncludeInfo;
import de.dfki.mlt.rudimant.compiler.io.BisonParser;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * the main class of rudimant
 *
 * @author Anna Welker
 */
public class CompilerMain {

  /** Where to put the info file about rules and their source code locations */
  public static String INFO_DIR = "src/main/resources/generated";

  final static Object [][] defaults = {
    { CFG_TYPE_ERROR_FATAL, false, "e" },
    { CFG_PRINT_ERRORS, true, "q" },
    { CFG_VISUALISE, false , "v" },
    { CFG_PERSISTENT_VARS, false , "p" },
    { CFG_AGENT_BASE_CLASS, "de.dfki.mlt.rudimant.agent.Agent", "w" },
    { CFG_CONFIG_DIRECTORY, new File("."), null },
  };

  public static Map<String, Object> defaultConfig() {
    Map<String, Object> configs = new LinkedHashMap<String, Object>();
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
        configs.put(key, options.valueOf(option));
      }
    } else if (! configs.containsKey(key)) {
      configs.put(key, def);
    }
  }

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

  /**
   * Saves generated rule structure and errors/warnings to a YAML file
   */
  public static void dumpToYaml(File configDir, IncludeInfo info)
      throws IOException {
    File infoDir = new File(configDir, INFO_DIR);
    if (!infoDir.isDirectory()) createDirectories(infoDir.toPath());
    IncludeInfo.saveInfo(info,
        new FileWriter(new File(infoDir, RULE_LOCATION_FILE)));
  }

  public static IncludeInfo compileAll(Map<String, Object> configs)
      throws WrongFormatException, IOException {
    File confDir = (File)configs.get(CFG_CONFIG_DIRECTORY);
    IncludeInfo info = RudimantCompiler.process(confDir, configs);
    // save ruleLocMap to .yml file
    dumpToYaml(confDir, info);
    return info;
  }

  /**
   *
   * @param args: the file that should be parsed without ending (in args[0])
   *             + the complete path of the configfile
   * @throws WrongFormatException
   * @throws Exception
   */
  @SuppressWarnings({ "rawtypes" })
  public static void main(String[] args) throws WrongFormatException {
    System.out.println("VOnDA compiler, version " + COMPILER_VERSION);

    OptionParser parser = new OptionParser("vepdr:w:c:o:");
    parser.accepts("help");
    OptionSet options = null;

    File outputDirectory = null;
    Map<String, Object> configs = CompilerMain.defaultConfig();

    try {
      options = parser.parse(args);
      List argfiles = options.nonOptionArguments();

      if (options.has("c")) {
        String confName = (String)options.valueOf("c");
        configs = readConfig(confName);
      }

      for (Object[] val : defaults) {
        if (val[2] != null) {
          setValue((String)val[0], (String)val[2], val[1], configs, options);
        }
      }

      if (options.has("w")) {
        configs.put(CFG_AGENT_BASE_CLASS, options.valueOf("w"));
      }
      if (options.has("v")) {
        configs.put(CFG_VISUALISE, true);
      }
      if (options.has("q")) {
        configs.put(CFG_PRINT_ERRORS, false);
      }
      if (options.has("p")) {
        configs.put(CFG_PERSISTENT_VARS, true);
      }
      if (options.has("d")) {
        BisonParser.DEBUG_GRAMMAR = true;
      }
      if (options.has("r")) {
        configs.put(CFG_ONTOLOGY_FILE, options.valueOf("r"));
      }
      if (options.has("o")) {
        outputDirectory = new File((String)options.valueOf("o"));
        configs.put(CFG_OUTPUT_DIRECTORY, outputDirectory);
      } else {
        if (! configs.containsKey(CFG_OUTPUT_DIRECTORY)) {
          if (argfiles.isEmpty()) {
            usage("No output file specified");
            System.exit(1);
          } else {
            outputDirectory = new File((String)argfiles.get(0)).getParentFile();
          }
        }
      }

      if (argfiles.isEmpty()) {
        if (!configs.containsKey(CFG_INPUT_FILE)) {
          usage("Input file is missing");
          System.exit(1);
        }
      } else {
        configs.put(CFG_INPUT_FILE, argfiles.get(0));
      }

      if (compileAll(configs).hasFailed()) {
        System.out.println("Parsing failed");
        System.exit(1);
      }
    } catch (OptionException ex) {
      usage("Error parsing options: " + ex.getMessage());
    } catch (IOException ex) {
      usage("A file error occured: " + ex.getMessage());
    }
  }

  private static void usage(String message) {
    System.out.println(message);
    System.out.println("java -jar vonda.jar\n"
        + "   -v<isualize parses> -e<rror stops> -q<uiet (no errors printed)>\n"
        + "   -r ontos.yml -w WrapperClass -o outputDir\n"
        + "   -c config.yaml\n"
        + "   [TopLevel.rudi]\n\n"
        + "Values for all flags and the TopLevel file can also be set in the config file.");
  }

}
