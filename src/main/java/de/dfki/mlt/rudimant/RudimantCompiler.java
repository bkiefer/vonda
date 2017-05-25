package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.*;
import static de.dfki.mlt.rudimant.Utils.*;
import static de.dfki.mlt.rudimant.io.RobotGrammarParser.*;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbHandler;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.tree.GrammarFile;
import de.dfki.mlt.rudimant.tree.RudiTree;
import de.dfki.mlt.rudimant.tree.VGenerationVisitor;

public class RudimantCompiler {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private static HfcDbHandler handler;

  private boolean typeCheck = true;
  private boolean visualise = false;

  private File inputDirectory;
  private File outputDirectory;
  // there may be users that do not start the .rudi files with capital letters,
  // we don't want to crash in that case by turning it to uppercase and then
  // trying to read it
  private String inputRealName;

//  private StringBuffer out;
  public Writer out;

  private Mem mem;

  private List<String> subPackage = new ArrayList<>();
  private int rootLevel = 0;

  // private String className;

  private String packageName;

  private RudimantCompiler parent;

  // the class that should be extended by the rudi files to fill them into a project
  private final String wrapperClass;

  // ... and its constructor arguments, if any
  private final String constructorArgs;

  // Definitions for methods and variables in Agent.java
  private static final String agentInit = "/Agent.rudi";

  // what should be logged in the rules (true = rudi code vs false = java code)
  private boolean versionToLog = true;


  /** Constructor for imports */
  private RudimantCompiler(RudimantCompiler parentCompiler) {
    wrapperClass = parentCompiler.wrapperClass;
    constructorArgs = parentCompiler.getConstructorArgs();
    mem = parentCompiler.mem;
    parent = parentCompiler;
    typeCheck = parentCompiler.typeCheck;
    packageName = parent.getPackageName();
    rootLevel = parent.rootLevel;
    visualise = parent.visualise;
    versionToLog = parent.versionToLog;
  }

  /** Constructor for top-level file */
  private RudimantCompiler(RdfProxy proxy, String wrapper,
      String targetConstructor){
    wrapperClass = wrapper;
    constructorArgs = targetConstructor;
    mem = new Mem(proxy);
  }

  /**
   * ATTENTION!! This method is only to be used to set a classname for testing
   * purposes!!!!!!!!!!!!!!
   * @param name
   *
  public void setClassName(String name){
    // className = name;
    mem.setClassName(name);
  }*/

  private void checkOutputDirectory(File configDir, Map<String, Object> configs)
      throws IOException {
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
    if (outputDirectory == null) return;
  }

  /** Process the Agent.rudi, treating all definitions as if they came from
   *  the toplevel rudi file.
   * @param topLevel
   */
  public void initMem(String className) {
    mem.setToplevelFile(className);
    //mem.initializing = true;
    parent = null;
    try {
      processForReal(RudimantCompiler.class.getResourceAsStream(agentInit), null);
    } catch (IOException ex) {
      logger.error("Agent initializer file import fails: {}", ex);
    }
    //mem.initializing = false;
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
      Map<String, Object> configs)
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
              (String)configs.get(CFG_TARGET_CONSTRUCTOR));
    rc.checkOutputDirectory(configDir, configs);
    rc.typeCheck = (boolean)configs.get(CFG_TYPE_ERROR_FATAL);
    if (configs.containsKey(CFG_PACKAGE)) {
      rc.packageName = (String) configs.get(CFG_PACKAGE);
    }
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

  public void throwTypeErrors() {
    typeCheck = true;
  }

  /** Process the top-level rudi file */
  public void process(File topLevel) throws IOException {
    String className = computeClassName(topLevel);
    mem.enterClass(className);
    initMem(className);
    inputDirectory = topLevel.getParentFile();
    File wrapperInit = new File(inputDirectory,
        wrapperClass.substring(wrapperClass.lastIndexOf(".") + 1) + RULES_FILE_EXTENSION);
    try {
      if (wrapperInit.exists()) {
        //mem.initializing = true;
        processForReal(new FileInputStream(wrapperInit), null);
      } else {
        logger.info("No method declaration file for {}", wrapperInit);
      }
    } catch (IOException ex) {
      logger.error("Initializer file import: {}", ex);
    } finally {
      //mem.initializing = false;
    }
    if (outputDirectory == null)
      outputDirectory = inputDirectory;

    if (packageName != null && !packageName.isEmpty()) {
      subPackage.addAll(Arrays.asList(packageName.split("\\.")));
      rootLevel = subPackage.size() - 1;
    }
    processForReal();
    mem.leaveClass();
  }

  /** Process an imported rudi file */
  private void process(String importSpec) throws IOException {
    inputDirectory = parent.inputDirectory;
    outputDirectory = parent.outputDirectory;

    String[] elements = importSpec.split("\\.");
    inputRealName = elements[elements.length - 1];
    subPackage = parent.subPackage;
    subPackage.addAll(Arrays.asList(elements).subList(0, elements.length - 1));

    mem.enterClass(capitalize(inputRealName));
    processForReal();
    mem.leaveClass();
  }

  public void processImport(String importSpec) {
    logger.info("Processing import {}", importSpec);
    try {
      RudimantCompiler result = new RudimantCompiler(this);
      result.process(importSpec);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Return the inputfile, which is relative to inputDirectory, the subdirectory
   * is specified by the subPackage, and the last entry of subPackage, which is
   * the class name of the file to be processed, is removed.
   */
  private File getInputFile() {
    File result = inputDirectory;
    if (!subPackage.isEmpty()) {
      for (String s : subPackage.subList(rootLevel, subPackage.size() - 1)) {
        result = new File(result, s);
      }
    }
    return new File(result,
        (inputRealName != null ? inputRealName : mem.getClassName())
        + RULES_FILE_EXTENSION);
  }

  /**
   * Return the inputfile, which is relative to inputDirectory, the subdirectory
   * is specified by the subPackage, and the last entry of subPackage, which is
   * the class name of the file to be processed, is removed.
   */
  private File getOutputDirectory() {
    File result = outputDirectory;
    for (String s : subPackage) {
      result = new File(result, s);
    }
    return result;
  }

  public boolean logRudi(){
    return versionToLog;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getWrapperClass() {
    return wrapperClass;
  }

  public String getConstructorArgs() {
    return constructorArgs;
  }

  public Mem getMem() {
    return mem;
  }

  public RudimantCompiler getParent() {
    return parent;
  }

  public RudimantCompiler append(char c) {
    try {
      out.append(c);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    return this;
  }

  public RudimantCompiler append(CharSequence c) {
    try {
      out.append(c);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    return this;
  }

  public void flush() {
    try {
      out.flush();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private String computeClassName(File inputFile) {
    // remember the real name, without upper case transformation, so getInputFile()
    // won't crash
    inputRealName = inputFile.getName().replace(RULES_FILE_EXTENSION, "");
    String classname = null;
    try {
      classname = capitalize(inputRealName);
    } catch (StringIndexOutOfBoundsException e) {
      logger.error("Could not find class name " + inputFile.getName());
    }
    return classname;
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

  private void processForReal() throws IOException {
    File outputdir = getOutputDirectory();
    if (!outputdir.isDirectory()) {
      Files.createDirectories(outputdir.toPath());
    }
    File outputFile = new File(outputdir, mem.getClassName() + ".java");
    Writer output = Files.newBufferedWriter(outputFile.toPath());

    File inputFile = getInputFile();
    logger.info("parsing " + inputFile.getName() + " to " + outputFile);
    if (processForReal(new FileInputStream(inputFile), output) == null)
      throw new UnsupportedOperationException("Parsing failed.");
    flush();
    uncrustify(outputFile);
  }

  private static Pair<GrammarFile, LinkedList<Token>> parseInput(
      final String realName, InputStream in) throws IOException {
    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(in));

    List<Integer> toCollect = Arrays.asList(
        new Integer[] { JAVA_CODE, ONE_L_COMMENT, MULTI_L_COMMENT, NLWS });
    CollectorTokenSource collector = new CollectorTokenSource(lexer, toCollect);

    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(
        new CommonTokenStream(collector));
    final boolean[] errorOccured = { false };
    parser.addErrorListener(new BaseErrorListener() {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer,
          Object offendingSymbol, int line, int charPositionInLine, String msg,
          RecognitionException e) {
        errorOccured[0] = true;
        logger.error("{}.rudi:{}:{}: {}", realName, line, charPositionInLine, msg);
      }
    });

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();
    if (errorOccured[0])
      return null;

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(realName,
        collector.getCollectedTokens());

    // create the abstract syntax tree
    RudiTree myTree = visitor.visit(tree);
    if (! (myTree instanceof GrammarFile))
      return null;
    return new Pair<>((GrammarFile)myTree, collector.getCollectedTokens());
  }

  /** Prerequisite: mem.enterClass(<className>) has been called, and
   *  mem.leaveClass(<className>) is called afterwards.
   * @param in
   * @param output
   * @return
   * @throws IOException
   */
  public GrammarFile processForReal(InputStream in, Writer output)
      throws IOException {
    out = output;
    Pair<GrammarFile, LinkedList<Token>> pair = parseInput(inputRealName, in);
    if (pair == null)
      return null;

    GrammarFile gf = pair.first;
    // do the type checking, which also adds function and variable definitions
    // to Mem
    gf.startTypeInference(this);

    if (visualise) {
      Visualize.show(gf, inputRealName);
    }
    if (output != null) {
      // generate the output
      gf.startGeneration(this, new VGenerationVisitor(this, pair.second));
    }
    logger.info("Done parsing");
    return gf;
  }

  /**
   * use this to report a type checking error; it will be handled according to
   * the set typeCheck parameter
   *
   * @param errorMessage
   * @param node the tree node where the error occured
   */
  public void typeError(String errorMessage, RudiTree node) {
    String newErrorMessage = node.getLocation() + " " + errorMessage;
    if (typeCheck) {
      // throw a real Exception
      throw new TypeException(newErrorMessage);
    } else {
      // just set a warning into the logger
      logger.error(newErrorMessage);
    }
  }

  /**
   * use this to report a type checking warning
   *
   * @param errorMessage
   * @param node the tree node where the error occured
   */
  public void typeWarning(String errorMessage, RudiTree node) {
    // just set a warning into the logger
    logger.warn(node.getLocation() + " " + errorMessage);
  }
}
