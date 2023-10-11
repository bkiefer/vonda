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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.LoaderOptions;
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

  /** For unit tests */
  public void setConfig(Map<String, Object> conf) {
    configs = conf;
  }

  final static Object [][] defaults = {
    { CFG_TYPE_ERROR_FATAL, false, "e" },
    { CFG_PRINT_ERRORS, true, "q" },
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
        configs.put(key, options.valueOf(option));
      }
    } else if (! configs.containsKey(key)) {
      configs.put(key, def);
    }
  }

  @SuppressWarnings("unchecked")
  public static void readConfig(String confname)
      throws FileNotFoundException {
    LoaderOptions lop = new LoaderOptions();
    lop.setProcessComments(true);
    Yaml yaml = new Yaml();

    File confFile = new File(confname);
    confDir = confFile.getParentFile();
    configs = (Map<String, Object>) yaml.load(new FileReader(confFile));
    try {
      convertConfig(confDir, confFile, configs);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public static void convertTypeDefFile(File dir, String name) throws IOException {
    File f = new File(dir, name + ".rudi");
    File to = new File(dir, name + ".v3.rudi");
    List<String> lines = Files.readAllLines(f.toPath(), Charset.forName("UTF-8"));
    try (FileWriter out = new FileWriter(to)) {
      for (String l : lines) {
        out
        .append(l.replaceFirst("\\[([^]]*)\\]\\.", "#$1"))
        .append(System.lineSeparator());
      }
    } finally {}
  }

  public static void convertConfig(File confDir, File confFile, Map<String, Object> configs)
      throws IOException {
    // turn wrapperClass key into agentBase and typeDef
    String agentBase = (String)configs.get(CFG_WRAPPER_CLASS);
    if (agentBase != null) {
      configs.remove(CFG_WRAPPER_CLASS);
      configs.put("agentBase", agentBase);
      int ind = agentBase.lastIndexOf('.');
      String name = ind >= 0 ? agentBase.substring(ind + 1) : agentBase;
      configs.put("typeDef", name + ".rudi");
      String inputFileName = (String)configs.get(CFG_INPUT_FILE);
      File targDir = confDir;
      if (inputFileName != null
          && new File(inputFileName).getParent() != null) {
        targDir = new File(targDir, new File(inputFileName).getParent());
      }
      // convert the type definitions file according to 3.0 syntax
      convertTypeDefFile(targDir, name);
    }
    // for NLG: check that all language subsections get the right "class" key
    Map<String, Object> nlg = (Map<String, Object>) configs.get(CFG_NLG_KEY);
    if (nlg != null) {
      for (String lang : nlg.keySet()) {
        Map<String, Object> langspec = (Map<String, Object>) nlg.get(lang);
        if (! langspec.containsKey(CFG_CLASS)) {
          langspec.put(CFG_CLASS,
              "de.dfki.mlt.rudimant.agent.nlp.LanguageGenerator");
        }
      }
    }

    // write the result to file with new name: change
    String name = confFile.getName();
    int dot = name.lastIndexOf(".");
    String newName = name.substring(0, dot) + "_v3" + name.substring(dot);
    File outDir = confDir;
    if (outDir == null) {
      outDir = new File(".");
    }
    File outFile = new File(outDir, newName);

    DumperOptions dop = new DumperOptions();
    dop.setDefaultFlowStyle(FlowStyle.BLOCK);
    dop.setProcessComments(true);
    dop.setPrettyFlow(true);

    Yaml yaml = new Yaml(dop);
    yaml.dump(configs, new FileWriter(outFile));
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
    System.out.println("VOnDA compiler, version " + COMPILER_VERSION);

    OptionParser parser = new OptionParser("vepdr:w:c:o:");
    parser.accepts("help");
    OptionSet options = null;

    CompilerMain main = new CompilerMain();
    File outputDirectory = null;
    confDir = new File(".");
    RudimantCompiler rc = null;

    try {
      options = parser.parse(args);
      List argfiles = options.nonOptionArguments();

      if (options.has("c")) {
        String confName = (String)options.valueOf("c");
        readConfig(confName);
      } else {
        configs = CompilerMain.defaultConfig();
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
      if (options.has("q")) {
        configs.put(CFG_PRINT_ERRORS, false);
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

      if (!configs.containsKey(CFG_INPUT_FILE)) {
        if (argfiles.isEmpty()) {
          usage("Input file is missing");
          System.exit(1);
        }
        configs.put(CFG_INPUT_FILE, argfiles.get(0));
      }

      main.setConfig(configs);
      if (RudimantCompiler.process(confDir, configs)) {
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
        + "   -v<isualize parses> -e<rror stops> -q<uiet (no errors printed)>\n"
        + "   -r ontos.ini -w WrapperClass -o outputDir\n"
        + "   -c config.yaml\n"
        + "   [TopLevel.rudi]\n\n"
        + "Values for all flags and the TopLevel file can also be set in the config file.");
  }

}
