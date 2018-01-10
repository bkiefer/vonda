package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.common.Constants.*;
import static de.dfki.mlt.rudimant.compiler.Constants.*;
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

import de.dfki.mlt.rudimant.common.Location;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;

public class RudimantCompiler {

  public static final Logger logger =
      LoggerFactory.getLogger(RudimantCompiler.class);

  static String INFO_DIR = "src/main/resources/generated";

  private static HfcDbHandler handler;

  private boolean typeCheck = false;
  private boolean visualise = false;

  // what should be logged in the rules (true = rudi code vs false = java code)
  private boolean versionToLog = true;

  private File inputRootDir;
  private File outputRootDir;

  private Mem mem;

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

  /** Constructor for top-level file */
  public RudimantCompiler(HfcDbHandler handler, RdfProxy proxy,
      String wrapper, File outDir, String packageName) {
    RudimantCompiler.handler = handler;
    String[] rootpkg =  (packageName == null)? new String[0] : packageName.split("\\.");
    outputRootDir = outDir;
    mem = new Mem(proxy, wrapper, rootpkg);
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
        while ((b = in.read()) >= 0) { out.write(b); }
      } catch (IOException ex){
        logger.error("Failed to write uncrustify config");
      }
      finally {
        try {
          if (out != null) { in.close(); out.flush(); out.close(); }
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
    readAgentSpecs(className);
    String wrapperClass = mem.getWrapperClass();
    File wrapperInit = new File(inputRootDir,
        wrapperClass.substring(wrapperClass.lastIndexOf(".") + 1) + RULE_FILE_EXTENSION);
    try {
      if (wrapperInit.exists()) {
        parseAndTypecheck(this, new FileInputStream(wrapperInit), className);
      } else {
        logger.info("No method declaration file for {}", wrapperInit);
      }
    } catch (IOException ex) {
      logger.error("Initializer file import: {}", ex);
    }
    try {
      processForReal(className);
    } finally {
      mem.leaveClass();
    }

    // save ruleLocMap to .yml file
    DumperOptions options = new DumperOptions();
    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    Yaml yaml = new Yaml(options);
    File infoDir = new File(INFO_DIR);
    if (!infoDir.isDirectory()) Files.createDirectories(infoDir.toPath());
    yaml.dump(mem.getInfo(), new FileWriter(new File(infoDir, RULE_LOCATION_FILE)));
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

}
