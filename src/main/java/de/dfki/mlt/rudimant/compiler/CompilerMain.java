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

import static de.dfki.mlt.rudimant.compiler.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import de.dfki.lt.hfc.WrongFormatException;
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
  public static Map<String, Object> configs;
  public static File confDir;

  public static boolean process(RudimantCompiler rc, File file)
      throws IOException {
    try {
      rc.processToplevel(file);
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
    { CFG_TYPE_ERROR_FATAL, false, "e" },
    { CFG_VISUALISE, false , "v" },
    { CFG_WRAPPER_CLASS, "de.dfki.mlt.rudimant.agent.Agent", "w" },
  };

  public static Map<String, Object> defaultConfig() {
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
  @SuppressWarnings("rawtypes")
  public static void main(String[] args) throws WrongFormatException {
    // BasicConfigurator.resetConfiguration();
    // BasicConfigurator.configure();

    OptionParser parser = new OptionParser("vedr:w:c:o:");
    parser.accepts("help");
    OptionSet options = null;

    CompilerMain main = new CompilerMain();
    File outputDirectory = null;
    List files = null;
    confDir = new File(".");
    RudimantCompiler rc = null;

    try {
      options = parser.parse(args);
      files = options.nonOptionArguments();

      if (files.isEmpty()) {
        usage("Input file is missing");
        System.exit(1);
      }

      outputDirectory = new File((String)files.get(0)).getParentFile();

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
      if (options.has("v")) {
        configs.put(CFG_VISUALISE, true);
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
      }
      main.setConfig(configs);
      rc = new RudimantCompiler(confDir, configs);
      if (process(rc, new File((String)files.get(0)))) {
        System.out.println("Parsing failed");
        System.exit(1);
      }
    } catch (OptionException ex) {
      usage("Error parsing options: " + ex.getMessage());
    } catch (IOException ex) {
      usage("A file error occured: " + ex.getMessage());
    } finally {
      if (rc != null) rc.shutdown();
    }
  }

  private static void usage(String message) {
    System.out.println(message);
    System.out.println("java -jar vonda.jar\n"
        + "   -v<isualize parses> -e<rror stops>\n"
        + "   -r ontos.ini -w WrapperClass -o outputDir\n"
        + "   -c config.yaml\n"
        + "   TopLevel.rudi\n\n"
        + "Values for -e, -r, -w and -o can also be set in the config file.");
  }

}
