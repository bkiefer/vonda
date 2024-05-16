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

import static com.google.common.io.Files.asCharSink;
import static de.dfki.mlt.rudimant.common.Constants.*;
import static de.dfki.mlt.rudimant.compiler.Constants.AGENT_DEFS;
import static de.dfki.mlt.rudimant.compiler.tree.GrammarFile.parseAndTypecheck;
import static java.nio.file.Files.createDirectories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.HfcDbHandler;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.common.BasicInfo;
import de.dfki.mlt.rudimant.common.ErrorInfo;
import de.dfki.mlt.rudimant.common.ErrorInfo.ErrorType;
import de.dfki.mlt.rudimant.common.IncludeInfo;
import de.dfki.mlt.rudimant.common.Location;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;

public class RudimantCompiler {

  public static final Logger logger =
      LoggerFactory.getLogger(RudimantCompiler.class);

  private HfcDbHandler handler;

  private boolean typeCheck = false;
  private boolean printErrors = false;
  private boolean visualise = false;
  private boolean persistentVarMode = false;

  private String typeDefFileName = null;

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
    String ontoFileName = (String) configs.get(CFG_ONTOLOGY_FILE);
    if (ontoFileName == null) {
      throw new IOException("Ontology file is missing.");
    }
    handler = new HfcDbHandler(new File(configDir, ontoFileName).getPath());
  }

  /** Constructor for top-level file */
  @SuppressWarnings("unchecked")
  public RudimantCompiler(File configDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
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
    mem = new Mem(proxy, (String)configs.get(CFG_AGENT_BASE_CLASS), rootpkg);

    if ((configs.containsKey(CFG_TYPE_ERROR_FATAL) &&
        (boolean)configs.get(CFG_TYPE_ERROR_FATAL))) {
      throwTypeErrors();
    }
    if (configs.containsKey(CFG_VISUALISE) &&
        (boolean) configs.get(CFG_VISUALISE)) {
      Visualize.init();
      showTree();
    }
    if (configs.containsKey(CFG_PRINT_ERRORS) &&
        (boolean) configs.get(CFG_PRINT_ERRORS)) {
      printErrors = true;
    }
    if (configs.containsKey(CFG_PERSISTENT_VARS)) {
      persistentVarMode = (boolean)configs.get(CFG_PERSISTENT_VARS);
    }
    if (configs.containsKey(CFG_TYPE_DEFS_FILE)) {
      typeDefFileName = (String) configs.get(CFG_TYPE_DEFS_FILE);
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
      logger.error("Agent definitions file include fails: {}", ex);
    }
  }

  /** Process the type definitions file, treating all definitions of fields and
   *  methods
   *
   *  TODO: THIS SHOULD BE REPLACED BY ANALYSING THE SOURCE FILES IN THE PROJECT
   *  AND THE CLASSPATH
   */
  public void readTypeDefFile() {
    if (typeDefFileName != null) {
      File typeDefFile = new File(inputRootDir, typeDefFileName);
      try {
        if (typeDefFile.exists()) {
          parseAndTypecheck(this, new FileInputStream(typeDefFile), typeDefFile.getName());
        } else {
          logger.warn("Type declaration file {} not found", typeDefFile.getAbsolutePath());
        }
      } catch (IOException ex) {
        logger.error("Type declaration file include: {}", ex);
      }
    }
  }

  public void shutdown() {
    if (handler != null) {
      handler.shutdownNoExit();
    }
  }

  public void throwTypeErrors() { typeCheck = true; }

  public void showTree() { visualise = true; }

  public boolean typeErrorsFatal() { return typeCheck; }

  public boolean visualise() { return visualise; }

  public void persistAllVars() { persistentVarMode = true; }

  public boolean persistentVars() { return persistentVarMode; }

  public Mem getMem() {
    return mem;
  }


  /** Create output directories, open a writer to the output file and process
   *  the current input
   */
  private void processForReal(String name)
      throws IOException {
    File outputdir = getSubDirectory(outputRootDir, mem.getTopLevelPackageSpec());
    outputdir = getSubDirectory(outputdir, mem.getPackageSpec());
    if (!outputdir.isDirectory()) {
      createDirectories(outputdir.toPath());
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
    GrammarFile gf = parseAndTypecheck(this, new FileInputStream(inputFile), name);
    if (gf == null)
      throw new UnsupportedOperationException("Parsing failed.");

    if (visualise)
      Visualize.show(gf, name);

    StringWriter sw = new StringWriter();
    gf.generate(this, sw);
    CharSource source = CharSource.wrap(sw.getBuffer());
    CharSink sink = asCharSink(outputFile, Charset.forName("UTF-8"));
    try {
      new Formatter().formatSource(source, sink);
    } catch (FormatterException ex) {
      mem.registerError("Code Formatter: " + ex.getMessage(),
          new Location(name), ErrorType.ERROR);
      // Error occured, we have to write unformatted;
      sink.writeFrom(source.openStream());
    }
  }

  /** Process an included rudi file
   * @throws IOException
   */
  public void processInclude(String name, String[] dirSpec, Location loc)
      throws IOException {
    logger.info("Processing include {}/{}", Arrays.toString(dirSpec), name);
    mem.enterClass(name, dirSpec, loc);
    try {
      processForReal(name);
    } finally {
      mem.leaveClass();
    }
  }

  /**
   * print errors and warnings in emacs compilation mode error format
   * for plain emacs use.
   */
  private static void printErrors(IncludeInfo b, File dir) {
    Path fileLoc = dir.toPath().resolve(b.getFilePath());
    for (BasicInfo c : b.getChildren()) {
      if (c instanceof IncludeInfo) {
        printErrors((IncludeInfo)c, fileLoc.toFile().getParentFile());
      }
    }
    for (ErrorInfo info : b.getErrors()) {
      System.out.println(info.getLocation().toString(fileLoc) + " "
          + info.getType().toString().toLowerCase() + ": " + info.getMessage());
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
    readTypeDefFile();
    try {
      processForReal(className);
    } finally {
      mem.leaveClass();
      if (printErrors) {
        printErrors(mem.getInfo(), inputRootDir);
      }
    }
  }

  public static IncludeInfo process(File confDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    RudimantCompiler rc = null;
    try {
      rc = new RudimantCompiler(confDir, configs);
      File rootFile = new File(confDir, (String) configs.get(CFG_INPUT_FILE));
      rc.processToplevel(rootFile);
    } catch (UnsupportedOperationException ex) {
      if (ex.getMessage().startsWith("Parsing")) {
        rc.getMem().getInfo().setFailed();
      }
    } finally {
      if (rc != null) rc.shutdown();
    }
    return rc == null ? null : rc.getMem().getInfo();
  }
}
