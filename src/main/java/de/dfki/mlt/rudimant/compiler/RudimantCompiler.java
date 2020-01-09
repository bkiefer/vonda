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
import static de.dfki.mlt.rudimant.compiler.Constants.*;
import static de.dfki.mlt.rudimant.compiler.tree.GrammarFile.parseAndTypecheck;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HandlerFactory;
import de.dfki.lt.hfc.db.server.HfcDbHandler;
import de.dfki.mlt.rudimant.common.Location;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;

public class RudimantCompiler {

  public static final Logger logger =
      LoggerFactory.getLogger(RudimantCompiler.class);

  static String INFO_DIR = "src/main/resources/generated";

  private HfcDbHandler handler;

  private boolean typeCheck = false;
  private boolean visualise = false;

  // what should be logged in the rules (true = rudi code vs false = java code)
  private boolean versionToLog = true;

  private File inputRootDir;
  private File outputRootDir;

  private Mem mem;

  private void checkOutputDirectory(File configDir, Map<String, Object> configs) {
    outputRootDir = null;
    if (configs.containsKey(CFG_OUTPUT_DIRECTORY)) {
      Object o = configs.get(CFG_OUTPUT_DIRECTORY);
      if (o instanceof String) {
        outputRootDir = new File((String)o);
        if (! outputRootDir.isAbsolute()) {
          outputRootDir = new File(configDir, (String)o);
        }
      } else {
        outputRootDir = (File)o;
      }
    }
  }

  private void startClient(File configDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    handler = HandlerFactory.getHandler();
    String ontoFileName = (String) configs.get(CFG_ONTOLOGY_FILE);
    if (ontoFileName == null) {
      throw new IOException("Ontology file is missing.");
    }
    handler.readConfig(new File(configDir, ontoFileName));
  }

  /** Constructor for top-level file */
  @SuppressWarnings("unchecked")
  public RudimantCompiler(File configDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    if(configs.get(CFG_WRAPPER_CLASS) == null) {
      System.out.println("No implementation class specified, exiting.");
      System.exit(1);
    }
    startClient(configDir, configs);
    RdfProxy proxy = new RdfProxy(handler);
    if (configs.containsKey(CFG_NAME_TO_URI)) {
      proxy.setBaseToUri((Map<String, String>)configs.get(CFG_NAME_TO_URI));
    }
    if (configs.containsKey(CFG_NAME_TO_CLASS)) {
      Type.setJavaClasses((Map<String, String>)configs.get(CFG_NAME_TO_CLASS));
    }
    String[] rootpkg = configs.get(CFG_PACKAGE) != null
        ? ((String) configs.get(CFG_PACKAGE)).split("\\.")
        : new String[0];
    checkOutputDirectory(configDir, configs);
    mem = new Mem(proxy, (String)configs.get(CFG_WRAPPER_CLASS), rootpkg);

    if ((configs.containsKey(CFG_TYPE_ERROR_FATAL) &&
        (boolean)configs.get(CFG_TYPE_ERROR_FATAL))) {
      throwTypeErrors();
    }
    if (configs.containsKey(CFG_VISUALISE) &&
        (boolean) configs.get(CFG_VISUALISE)) {
      Visualize.init();
      showTree();
    }
  }

  /**
   * Return the inputfile, which is relative to inputDirectory, the subdirectory
   * is specified by the subPackage, and the last entry of subPackage, which is
   * the class name of the file to be processed, is removed.
   */
  private File getSubDirectory(File dir, String[] pathSpec) {
    File result = dir;
    for (String s : pathSpec) {
      result = new File(result, s);
    }
    return result;
  }

  /** Process the Agent.rudi, treating all definitions as if they came from
   *  the toplevel rudi file.
   * @param topLevel
   */
  public void readAgentSpecs() {
    try {
      parseAndTypecheck(this,
          RudimantCompiler.class.getResourceAsStream("/" + AGENT_DEFS),
          AGENT_DEFS);
    } catch (IOException ex) {
      logger.error("Agent definitions file import fails: {}", ex);
    }
  }

  public void shutdown() {
    if (handler != null) {
      handler.shutdown();
    }
  }

  public void throwTypeErrors() { typeCheck = true; }

  public void showTree() { visualise = true; }

  public boolean typeErrorsFatal() { return typeCheck; }

  public boolean visualise() { return visualise; }

  public boolean logRudi(){
    return versionToLog;
  }

  public Mem getMem() {
    return mem;
  }

  private static final File tmpCfg = new File("/tmp/uncrustify.cfg");
  private static boolean cfgWritten = false;

  private static final Pattern vers_no =
      Pattern.compile("[^0-9]*([0-9]+)\\.([0-9]+).*");
  private static String cfgName = UNCRUST_CFG_OLD;

  private static void uncrustify(File outputFile) {
    if (cfgName == null) return;
    if (!cfgWritten && ! tmpCfg.exists()) {
      try {
        // determine the current version of uncrustify to get the right config
        String[] cmdArray = { "uncrustify",  "--version" };
        Process proc = Runtime.getRuntime().exec(cmdArray);
        InputStream stdout = proc.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdout);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null)
          sb.append(line);
        line = sb.toString();
        @SuppressWarnings("unused")
        boolean killed = proc.waitFor(5, TimeUnit.SECONDS);
        int exitCode = proc.exitValue();
        if (exitCode != 0) {
          logger.warn("Uncrustify finished with error code {}", exitCode);
          cfgName = null;
        } else {
          Matcher m = vers_no.matcher(line);
          if (! m.matches()) {
            logger.warn("Uncrustify version problem: {}", line);
          } else {
            if (Integer.parseInt(m.group(1)) > 0 ||
                Integer.parseInt(m.group(2)) > 65) {
              cfgName = UNCRUST_CFG_NEW;
            }
          }
        }
      } catch (IOException ex){
        logger.error("Failed to run uncrustify");
        cfgName = null;
      } catch (InterruptedException e) {
        logger.warn("uncrustify was interrupted");
        cfgName = null;
      }
      if (cfgName == null) return;
      InputStream in = null;
      OutputStream out = null;
      try {
        cfgWritten = true;
        in = RudimantCompiler.class.getResourceAsStream(cfgName);
        out = new FileOutputStream(tmpCfg);
        int b;
        while ((b = in.read()) >= 0) { out.write(b); }
      } catch (IOException ex){
        logger.error("Failed to write uncrustify config");
        cfgName = null;
      }
      finally {
        try {
          if (out != null) { in.close(); out.flush(); out.close(); }
        }
        catch (IOException ex){
          logger.error("Failed to write uncrustify config");
          cfgName = null;
        }
      }
      if (cfgName == null) return;
    }
    try {
      String[] cmdArray = {
          "uncrustify",  "--replace", "--no-backup",
          "-c", tmpCfg.getAbsolutePath(),
          outputFile.getAbsolutePath()
      };
      Process proc = Runtime.getRuntime().exec(cmdArray);
      @SuppressWarnings("unused")
      boolean killed = proc.waitFor(5, TimeUnit.SECONDS);
      int exitCode = proc.exitValue();
      if (exitCode != 0) {
        logger.warn("Uncrustify finished with error code {}", exitCode);
      }
    } catch (IOException ex){
      logger.error("Failed to run uncrustify");
    } catch (InterruptedException e) {
      logger.warn("uncrustify was interrupted");
    }
  }


  /** Process the top-level rudi file */
  public void processToplevel(File topLevel) throws IOException {
    inputRootDir = topLevel.getParentFile();
    if (outputRootDir == null) outputRootDir = inputRootDir;
    // get the name from the input file name
    String className = topLevel.getName().replace(RULE_FILE_EXTENSION, "");

    mem.enterClass(className, new String[0], null);
    readAgentSpecs();
    String wrapperClass = mem.getWrapperClass();
    File wrapperInit = new File(inputRootDir,
        wrapperClass.substring(wrapperClass.lastIndexOf(".") + 1) + RULE_FILE_EXTENSION);
    try {
      if (wrapperInit.exists()) {
        parseAndTypecheck(this, new FileInputStream(wrapperInit), wrapperInit.getName());
      } else {
        logger.warn("No method declaration file for {}", wrapperInit);
      }
    } catch (IOException ex) {
      logger.error("Initializer file import: {}", ex);
    }
    try {
      processForReal(className);
    } finally {
      mem.leaveClass();
      // save ruleLocMap to .yml file
      dumpToYaml();
    }

  }

  /**
   * Saves generated rule structure and errors/warnings to a YAML file
   */
  private void dumpToYaml() throws IOException {
    DumperOptions options = new DumperOptions();
    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    Yaml yaml = new Yaml(options);
    File infoDir = new File(INFO_DIR);
    if (!infoDir.isDirectory()) Files.createDirectories(infoDir.toPath());
    yaml.dump(mem.getInfo(),
              new FileWriter(new File(infoDir, RULE_LOCATION_FILE)));
  }


  /** Create output directories, open a writer to the output file and process
   *  the current input
   */
  private void processForReal(String name)
      throws IOException {
    File outputdir = getSubDirectory(outputRootDir, mem.getTopLevelPackageSpec());
    outputdir = getSubDirectory(outputdir, mem.getPackageSpec());
    if (!outputdir.isDirectory()) {
      Files.createDirectories(outputdir.toPath());
    }
    File outputFile = new File(outputdir, mem.getClassName() + ".java");

    /* Compute the inputfile, which is relative to inputDirectory, the subdirectory
     * is specified by the subPackage, and the last entry of subPackage, which is
     * the class name of the file to be processed, is removed.
     */
    File inputFile = new File(
        getSubDirectory(inputRootDir, mem.getPackageSpec()),
        (name != null ? name : mem.getClassName()) + RULE_FILE_EXTENSION);

    logger.info("parsing " + inputFile.getName() + " to " + outputFile);
    GrammarFile gf = parseAndTypecheck(this,
        new FileInputStream(inputFile), name);
    if (gf == null)
      throw new UnsupportedOperationException("Parsing failed.");

    if (visualise)
      Visualize.show(gf, name);
    Writer output = Files.newBufferedWriter(outputFile.toPath());
    gf.generate(this, output);
    output.close();

    uncrustify(outputFile);
  }

  /** Process an imported rudi file
   * @throws IOException
   */
  public void processImport(String name, String[] dirSpec, Location loc)
      throws IOException {
    logger.info("Processing import {}/{}", Arrays.toString(dirSpec), name);
    mem.enterClass(name, dirSpec, loc);
    try {
      processForReal(name);
    } finally {
      mem.leaveClass();
    }
  }

  public static boolean process(File confDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    RudimantCompiler rc = null;
    try {
      rc = new RudimantCompiler(confDir, configs);
      rc.processToplevel(new File((String) configs.get(CFG_INPUT_FILE)));
    } catch (UnsupportedOperationException ex) {
      if (ex.getMessage().startsWith("Parsing")) return true;
      throw(ex);
    } finally {
      if (rc != null) rc.shutdown();
    }
    return false;
  }


}
