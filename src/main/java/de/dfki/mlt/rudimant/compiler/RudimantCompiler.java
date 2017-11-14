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

import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbHandler;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import de.dfki.mlt.rudimant.common.BasicInfo;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;

public class RudimantCompiler {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private static HfcDbHandler handler;

  private boolean typeCheck = false;
  private boolean visualise = false;

  // what should be logged in the rules (true = rudi code vs false = java code)
  private boolean versionToLog = true;


  private final File inputDirectory;
  private final File outputDirectory;
  // there may be users that do not start the .rudi files with capital letters,
  // we don't want to crash in that case by turning it to uppercase and then
  // trying to read it
  //private String inputRealName;

  private Mem mem;

  private final String[] subPackage;

  private RudimantCompiler parent;

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
  public RudimantCompiler(HfcDbHandler handler, RdfProxy proxy,
      String wrapper, File inpDir, File outDir, String packageName) {
    RudimantCompiler.handler = handler;
    inputDirectory = inpDir == null ? new File(".") : inpDir;
    if (packageName == null) packageName = "";
    subPackage = packageName.split("\\.");
    outputDirectory = outDir == null
        ? inputDirectory : getSubDirectory(outDir, subPackage);
    parent = null;
    mem = new Mem(proxy, wrapper);
  }

  /** Process the Agent.rudi, treating all definitions as if they came from
   *  the toplevel rudi file.
   * @param topLevel
   */
  public void readAgentSpecs(String inputRealName) {
    try {
      parseAndTypecheck(this,
          RudimantCompiler.class.getResourceAsStream("/" + AGENT_DEFS),
          inputRealName);
    } catch (IOException ex) {
      logger.error("Agent definitions file import fails: {}", ex);
    }
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
    String inputRealName = topLevel.getName().replace(RULE_FILE_EXT, "");
    // TODO: not nice. should always come in "brackets", but makes it more messy
    // contains: mem.enterClass
    String className = capitalize(inputRealName);
    mem.enterClass(className, subPackage);
    readAgentSpecs(inputRealName);
    String wrapperClass = mem.getWrapperClass();
    File wrapperInit = new File(inputDirectory,
        wrapperClass.substring(wrapperClass.lastIndexOf(".") + 1) + RULE_FILE_EXT);
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
        + RULE_FILE_EXT);

    logger.info("parsing " + inputFile.getName() + " to " + outputFile);
    GrammarFile gf = parseAndTypecheck(this,
        new FileInputStream(inputFile), inputRealName);
    if (gf == null)
      throw new UnsupportedOperationException("Parsing failed.");
    if (visualise)
      Visualize.show(gf, inputRealName);
    Writer output = Files.newBufferedWriter(outputFile.toPath());
    mem.leaveTypecheck();
    gf.generate(this, output);
    mem.enterTypecheck();
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
