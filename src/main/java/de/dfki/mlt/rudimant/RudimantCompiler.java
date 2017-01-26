package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.*;
import static de.dfki.mlt.rudimant.io.RobotGrammarParser.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbHandler;
import de.dfki.mlt.rudimant.abstractTree.GrammarFile;
import de.dfki.mlt.rudimant.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.abstractTree.VGenerationVisitor;
import de.dfki.mlt.rudimant.abstractTree.VTestTypeVisitor;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;

public class RudimantCompiler {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private boolean throwExceptions = true;
  private boolean typeCheck = true;
  private boolean visualise = false;

  private File inputDirectory;
  private File outputDirectory;
  // there may be users that do not start the .rudi files with capital letters,
  // we don't want to crash in that case by turning it to uppercase and then
  // trying to read it
  private String inputRealName;

//  private StringBuffer out;
  Writer out;

  private Mem mem;

  public List<String> subPackage = new ArrayList<>();
  int rootLevel = 0;

  public String className;

  private String packageName;

  private RudimantCompiler parent;

  // the class that should be extended by the rudi files to fill them into a project
  private final String wrapperClass;

  // ... and its constructor arguments, if any
  private final String constructorArgs;

  public void setPackageName(String name) {
    this.packageName = name;
  }

  public String getPackageName() {
    return this.packageName;
  }

  public String getWrapperClass() {
    return wrapperClass;
  }

  public String getConstructorArgs() {
    return constructorArgs;
  }

  private RudimantCompiler(RudimantCompiler parentCompiler) {
    wrapperClass = parentCompiler.wrapperClass;
    constructorArgs = parentCompiler.getConstructorArgs();
    mem = parentCompiler.mem;
    parent = parentCompiler;
    this.throwExceptions = parentCompiler.throwExceptions;
    this.typeCheck = parentCompiler.typeCheck;
    this.packageName = parent.getPackageName();
    this.rootLevel = parent.rootLevel;
    this.visualise = parent.visualise;
  }
  
  public RudimantCompiler(File rootDir, String wrapperClass,
          String constructorArgs, RdfProxy proxy) {
    mem = new Mem(proxy, rootDir.getAbsolutePath() + "/" + wrapperClass, this);
    this.wrapperClass = wrapperClass;
    parent = null;
    this.constructorArgs = constructorArgs;
  }

  public RudimantCompiler(String wrapperClass, String constructorArgs,
          RdfProxy proxy) {
    mem = new Mem(proxy);
    this.wrapperClass = wrapperClass;
    parent = null;
    this.constructorArgs = constructorArgs;
  }

  public static RdfProxy startClient(File configDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    HfcDbHandler handler = new HfcDbHandler();
    String ontoFileName = (String) configs.get(CFG_ONTOLOGY_FILE);
    if (ontoFileName == null) {
      throw new IOException("Ontology file is missing.");
    }
    handler.readConfig(new File(configDir, ontoFileName));
    return new RdfProxy(handler);
  }

  public static RudimantCompiler init(File configDir, Map<String, Object> configs,
          File rootDir)
      throws IOException, WrongFormatException {
    RdfProxy proxy = startClient(configDir, configs);
    if (configs.containsKey(CFG_NAME_TO_URI)) {
      proxy.setBaseToUri((Map<String, String>)configs.get(CFG_NAME_TO_URI));
    }
    RudimantCompiler rc = null;
      if(rootDir != null){
        rc = new RudimantCompiler(
              rootDir,
              (String)configs.get(CFG_WRAPPER_CLASS),
              (String)configs.get(CFG_TARGET_CONSTRUCTOR),
              proxy);
      } else {
        rc = new RudimantCompiler(
              (String)configs.get(CFG_WRAPPER_CLASS),
              (String)configs.get(CFG_TARGET_CONSTRUCTOR),
              proxy);
    }
    rc.setThrowExceptions((boolean)configs.get(CFG_TYPE_ERROR_FATAL));
    rc.setTypeCheck((boolean)configs.get(CFG_TYPE_CHECK));
    if (configs.containsKey(CFG_PACKAGE)) {
      rc.setPackageName((String) configs.get(CFG_PACKAGE));
    }
    if (configs.containsKey(CFG_VISUALISE)) {
      rc.setVisualisation((boolean) configs.get(CFG_VISUALISE));
      if ((boolean) configs.get(CFG_VISUALISE)) Visualize.init();
    }
    return rc;
  }

  public static RudimantCompiler getEmbedded(RudimantCompiler parent) {
    RudimantCompiler result = new RudimantCompiler(parent);
    return result;
  }

  public void process(File topLevel, File outputdir) throws IOException {
    inputDirectory = topLevel.getParentFile();
    outputDirectory = outputdir;
    className = getClassName(topLevel);

    if (packageName != null && !packageName.isEmpty()) {
      subPackage.addAll(Arrays.asList(packageName.split("\\.")));
      rootLevel = subPackage.size() - 1;
    }
    processForReal(getOutputDirectory());
  }

  public void process(File topLevel) throws IOException {
    process(topLevel, topLevel.getParentFile());
  }

  public void process(String importSpec) throws IOException {
    inputDirectory = parent.inputDirectory;
    outputDirectory = parent.outputDirectory;

    String[] elements = importSpec.split("\\.");
    inputRealName = elements[elements.length - 1];
    subPackage = parent.subPackage;
    subPackage.addAll(Arrays.asList(elements).subList(0, elements.length - 1));

    className = inputRealName.substring(0, 1).toUpperCase()
            + inputRealName.substring(1);

    processForReal(getOutputDirectory());
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
    if (inputRealName != null) {
      return new File(result, inputRealName + RULES_FILE_EXTENSION);
    } else {
      return new File(result, className + RULES_FILE_EXTENSION);
    }
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

  public void setTypeCheck(boolean typeCheck) {
    this.typeCheck = typeCheck;
  }

  public void setThrowExceptions(boolean b) {
    this.throwExceptions = b;
  }

  public void setVisualisation(boolean vis) {
    this.visualise = vis;
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

  public String getClassName(File inputFile) {
    // remember the real name, without upper case transformation, so getInputFile()
    // won't crash
    this.inputRealName = inputFile.getName().replace(RULES_FILE_EXTENSION, "");
    String classname = null;
    try {
      classname = inputRealName.substring(0, 1).toUpperCase()
              + inputRealName.substring(1);
    } catch (StringIndexOutOfBoundsException e) {
      logger.error("Could not find class name " + inputFile.getName());
    }
    return classname;
  }

  private void processForReal(File outputdir) throws IOException {
    if (!outputdir.isDirectory()) {
      Files.createDirectories(outputdir.toPath());
    }
    File outputFile = new File(outputdir, className + ".java");
    Writer output = Files.newBufferedWriter(outputFile.toPath());

    File inputFile = getInputFile();
    logger.info("parsing " + inputFile.getName() + " to " + outputFile);
    processForReal(new FileInputStream(inputFile), output);
    this.flush();
    try {
      // TODO: NEEDS BETTER SPEC FOR UNCR.CFG
      String[] cmdArray = {
          "uncrustify",  "--no-backup",
          "-c", "src/main/resources/uncrustify.cfg",
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

  public static Pair<GrammarFile, LinkedList<Token>> parseInput(String realName, InputStream in)
      throws IOException {
    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(in));

    List<Integer> toCollect = Arrays.asList(
        new Integer[] { JAVA_CODE, ONE_L_COMMENT, MULTI_L_COMMENT, NLWS });
    CollectorTokenSource collector = new CollectorTokenSource(lexer, toCollect);

    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(
        new CommonTokenStream(collector));

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(realName,
        collector.getCollectedTokens());

    // create the abstract syntax tree
    RudiTree myTree = visitor.visit(tree);
    if (! (myTree instanceof GrammarFile)) return null;
    return new Pair<>((GrammarFile)myTree, collector.getCollectedTokens());
  }


  void processForReal(InputStream in, Writer output) throws IOException {
    out = output;
    Pair<GrammarFile, LinkedList<Token>> pair = parseInput(inputRealName, in);
    if (pair == null)
      throw new UnsupportedOperationException("Parsing failed.");

    GrammarFile gf = pair.first;
    // do the type checking
    VTestTypeVisitor ttv = new VTestTypeVisitor(this);
    ttv.visitNode(gf);
    if (output == null) {
      // then there is nothing to write to; we are in a memory initialization
      return;
    }
    // generate the output
    VGenerationVisitor gv = new VGenerationVisitor(this, pair.second);
    // tell the file its name (for class definition)
    gf.setClassName(className);
    try {
      gv.visitNode(gf);
    } catch (InOutException e) {
      throw new IOException(e);
    }
    logger.info("Done parsing");
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
    if (this.typeCheck) {
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
