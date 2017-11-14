package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.compiler.Constants.*;
import static de.dfki.mlt.rudimant.compiler.Utils.capitalize;
import static de.dfki.mlt.rudimant.compiler.tree.GrammarFile.*;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbHandler;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import de.dfki.mlt.rudimant.common.BasicInfo;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;
import de.dfki.mlt.rudimant.compiler.tree.Import;
import de.dfki.mlt.rudimant.compiler.tree.ToplevelBlock;

public class RudimantCompiler {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private static HfcDbHandler handler;

  private boolean typeCheck = true;
  private boolean visualise = false;

  private final File inputDirectory;
  private final File outputDirectory;
  // there may be users that do not start the .rudi files with capital letters,
  // we don't want to crash in that case by turning it to uppercase and then
  // trying to read it
  //private String inputRealName;

  private Mem mem;

  private final String[] subPackage;

  private RudimantCompiler parent;

  // Definitions for methods and variables in Agent.java
  private static final String agentInit = "/Agent.rudi";

  // what should be logged in the rules (true = rudi code vs false = java code)
  private boolean versionToLog = true;

  // Save location of rules to file
  private String rulesLocFile;

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

  /** Constructor for imports */
  private RudimantCompiler(RudimantCompiler parentCompiler, String[] dirSpec) {
    parent = parentCompiler;
    // TODO: THE FOLLOWING NEVER CHANGE. PUT THEM INTO A CONTAINER?
    mem = parent.mem;
    typeCheck = parent.typeCheck;
    visualise = parent.visualise;
    versionToLog = parent.versionToLog;

    subPackage = Arrays.copyOf(parent.subPackage,
        parent.subPackage.length + dirSpec.length);
    for (int i = 0; i < dirSpec.length; ++i) {
      subPackage[parent.subPackage.length + i] = dirSpec[i];
    }
    inputDirectory = getSubDirectory(parentCompiler.inputDirectory, dirSpec);
    outputDirectory = getSubDirectory(parentCompiler.outputDirectory, dirSpec);
  }

  /** Constructor for top-level file */
  private RudimantCompiler(RdfProxy proxy, String wrapper, File outDir,
      File topLevel, String packageName){
    inputDirectory = topLevel == null ? new File(".") : topLevel.getParentFile();
    if (packageName == null) packageName = "";
    subPackage = packageName.split("\\.");
    outputDirectory = outDir == null
        ? inputDirectory : getSubDirectory(outDir, subPackage);
    parent = null;
    mem = new Mem(proxy, wrapper);
  }

  private static File checkOutputDirectory(File configDir, Map<String, Object> configs)
      throws IOException {
    File outputDirectory = null;
    if (configs.containsKey(CFG_OUTPUT_DIRECTORY)) {
      Object o = configs.get(CFG_OUTPUT_DIRECTORY);
      if (o instanceof String) {
        outputDirectory = new File((String)o);
        if (! outputDirectory.isAbsolute()) {
          outputDirectory = new File(configDir, (String)o);
        }
      } else {
        outputDirectory = (File)o;
      }
    }
    return outputDirectory;
  }

  /** Process the Agent.rudi, treating all definitions as if they came from
   *  the toplevel rudi file.
   * @param topLevel
   */
  public void initMem(String inputRealName, String[] pkg) {
    String className = capitalize(inputRealName);
    mem.enterClass(className, pkg);

    try {
      parseAndTypecheck(this, RudimantCompiler.class.getResourceAsStream(agentInit), inputRealName);
    } catch (IOException ex) {
      logger.error("Agent initializer file import fails: {}", ex);
    }
  }

  private static RdfProxy startClient(File configDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    handler = new HfcDbHandler();
    String ontoFileName = (String) configs.get(CFG_ONTOLOGY_FILE);
    if (ontoFileName == null) {
      throw new IOException("Ontology file is missing.");
    }
    handler.readConfig(new File(configDir, ontoFileName));
    return new RdfProxy(handler);
  }

  @SuppressWarnings("unchecked")
  public static RudimantCompiler init(File configDir,
      Map<String, Object> configs, File topLevel)
      throws IOException, WrongFormatException {
    if(configs.get(CFG_WRAPPER_CLASS) == null) {
      logger.error("No implementation class specified, exiting.");
      return null;
    }
    RdfProxy proxy = startClient(configDir, configs);
    if (configs.containsKey(CFG_NAME_TO_URI)) {
      proxy.setBaseToUri((Map<String, String>)configs.get(CFG_NAME_TO_URI));
    }
    RudimantCompiler rc = new RudimantCompiler(proxy,
              (String)configs.get(CFG_WRAPPER_CLASS),
              checkOutputDirectory(configDir, configs),
              topLevel,
              configs.containsKey(CFG_PACKAGE)
                  ? (String) configs.get(CFG_PACKAGE)
                      : null
              );
    rc.typeCheck = (boolean)configs.get(CFG_TYPE_ERROR_FATAL);
    if (configs.containsKey(CFG_VISUALISE)) {
      if (rc.visualise = (boolean) configs.get(CFG_VISUALISE))
        Visualize.init();
    }
    if (configs.containsKey(CFG_LOGGING)) {
      rc.versionToLog = false;
    }
    return rc;
  }

  public static void shutdown() {
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

  public RudimantCompiler getParent() {
    return parent;
  }

  private static final File tmpCfg = new File("/tmp/uncrustify.cfg");
  private static boolean cfgWritten = false;

  private static void uncrustify(File outputFile) {
    if (!cfgWritten && ! tmpCfg.exists()) {
      InputStream in = null;
      OutputStream out = null;
      try {
        cfgWritten = true;
        in = RudimantCompiler.class.getResourceAsStream("/uncrustify.cfg");
        out = new FileOutputStream(tmpCfg);
        int b;
        while ((b = in.read()) >= 0) {
          out.write(b);
        }
      } catch (IOException ex){
        logger.error("Failed to write uncrustify config");
      }
      finally {
        try {
          if (out != null) {
            in.close();
            out.flush();
            out.close();
          }
        }
        catch (IOException ex){
          logger.error("Failed to write uncrustify config");
        }
      }
    }
    try {
      String[] cmdArray = {
          "uncrustify",  "--no-backup", "-c", tmpCfg.getAbsolutePath(),
          outputFile.getAbsolutePath()
      };
      Process proc = Runtime.getRuntime().exec(cmdArray);
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
    // get the real name, without upper case transformation
    String inputRealName = topLevel.getName().replace(RULES_FILE_EXTENSION, "");
    // TODO: not nice. should always come in "brackets", but makes it more messy
    // contains: mem.enterClass
    initMem(inputRealName, subPackage);
    String wrapperClass = mem.getWrapperClass();
    File wrapperInit = new File(inputDirectory,
        wrapperClass.substring(wrapperClass.lastIndexOf(".") + 1) + RULES_FILE_EXTENSION);
    try {
      if (wrapperInit.exists()) {
        parseAndTypecheck(this, new FileInputStream(wrapperInit), inputRealName);
      } else {
        logger.info("No method declaration file for {}", wrapperInit);
      }
    } catch (IOException ex) {
      logger.error("Initializer file import: {}", ex);
    }
    processForReal(inputRealName);
    mem.leaveClass();

    // save ruleLocMap to .yml file
    DumperOptions options = new DumperOptions();
    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    Yaml yaml = new Yaml(options);
    BasicInfo rootInfo = mem.getInfo();
    String rulesLocFilePath = outputDirectory + "/RuleLoc.yml";
    FileWriter writer = new FileWriter(rulesLocFilePath);
    yaml.dump(rootInfo, writer);
  }


  /** Create output directories, open a writer to the output file and process
   *  the current input
   */
  private void processForReal(String inputRealName)
      throws IOException {
    File outputdir = outputDirectory;
    if (!outputdir.isDirectory()) {
      Files.createDirectories(outputdir.toPath());
    }
    File outputFile = new File(outputdir, mem.getClassName() + ".java");

    /* Compute the inputfile, which is relative to inputDirectory, the subdirectory
     * is specified by the subPackage, and the last entry of subPackage, which is
     * the class name of the file to be processed, is removed.
     */
    File inputFile = new File(inputDirectory,
        (inputRealName != null ? inputRealName : mem.getClassName())
        + RULES_FILE_EXTENSION);

    logger.info("parsing " + inputFile.getName() + " to " + outputFile);
    GrammarFile gf = parseAndTypecheck(this,
        new FileInputStream(inputFile), inputRealName);
    if (gf == null)
      throw new UnsupportedOperationException("Parsing failed.");
    if (visualise)
      Visualize.show(gf, inputRealName);
    Writer output = Files.newBufferedWriter(outputFile.toPath());
    mem.enterGeneration();
    gf.generate(this, output);
    mem.leaveGeneration();
    output.close();

    uncrustify(outputFile);
  }

  /** Process an imported rudi file */
  private void processImportInternal(String name)
      throws IOException {
    mem.enterClass(capitalize(name), subPackage);
    processForReal(name);
    mem.leaveClass();
  }

  public void processImport(String name, String[] dirSpec, Location loc) {
    logger.info("Processing import {}/{}", Arrays.toString(dirSpec), name);
    try {
      mem.addImport(name, dirSpec, loc);
      RudimantCompiler result = new RudimantCompiler(this, dirSpec);
      result.processImportInternal(name);
      mem.leaveImport();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
