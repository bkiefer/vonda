package de.dfki.mlt.rudi;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.mlt.rudi.abstractTree.GenerationVisitor;
import de.dfki.mlt.rudi.abstractTree.GrammarFile;
import de.dfki.mlt.rudi.abstractTree.ReturnVisitor;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;

public class RudimantCompiler {

  public static Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private Writer logwriter;
  private boolean log;
  private boolean throwExceptions = true;
  private boolean typeCheck = true;

  private File inputDirectory;
  private File outputDirectory;

  private Writer out;

  //public RobotContext context;
  protected HfcDbService.Client _client;

  private Mem mem;

  public ReturnManagement rm;

  public List<String> subPackage = new ArrayList<>();

  public String className;

  private RudimantCompiler parent;

  private RudimantCompiler(Mem m) {
    mem = m;
    //context = new TestContext();
  }

  public RudimantCompiler() {
    this(new Mem());
  }

  public static RudimantCompiler getEmbedded(RudimantCompiler parent) {
    RudimantCompiler result = new RudimantCompiler(parent.mem);
    result.parent = parent;
    return result;
  }

  public void process(File topLevel, File outputdir) throws IOException {
    inputDirectory = topLevel.getParentFile();
    outputDirectory = outputdir;

    className = getClassName(topLevel);
    subPackage.add(className);

    processForReal(outputdir);
  }

  public void process(File topLevel) throws IOException {
    process(topLevel, topLevel.getParentFile());
  }

  public void process(String importSpec) throws IOException {
    inputDirectory = parent.inputDirectory;
    outputDirectory = parent.outputDirectory;

    String[] elements = importSpec.split("\\.");
    subPackage = parent.subPackage;
    subPackage.add(parent.className);
    subPackage.addAll(Arrays.asList(elements));

    className = subPackage.get(subPackage.size() - 1);

    processForReal(getOutputDirectory());
  }

  /**
   * Return the inputfile, which is relative to inputDirectory, the subdirectory
   * is specified by the subPackage, and the last entry of subPackage, which is
   * the class name of the file to be processed, is removed.
   */
  private File getInputFile() {
    File result = inputDirectory;
    for (String s : subPackage.subList(0, subPackage.size() - 1)) {
      result = new File(result, s);
    }
    return new File(result, className + ".rudi");
  }

  /**
   * Return the inputfile, which is relative to inputDirectory, the subdirectory
   * is specified by the subPackage, and the last entry of subPackage, which is
   * the class name of the file to be processed, is removed.
   */
  private File getOutputDirectory() {
    File result = outputDirectory;
    for (String s : subPackage.subList(0, subPackage.size() - 1)) {
      result = new File(result, s);
    }
    return result;
  }

  public void setLogwriter(Writer logwriter) {
    this.logwriter = logwriter;
  }

  public void setLog(boolean log) {
    this.log = log;
  }

  public void setTypeCheck(boolean typeCheck) {
    this.typeCheck = typeCheck;
  }

  public void setThrowExceptions(boolean b) {
    this.throwExceptions = b;
  }

  public Mem getMem() {
    return mem;
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
    String classname = null;
    try {
      classname = inputFile.getName().substring(0, 1).toUpperCase()
              + inputFile.getName().substring(1, inputFile.getName().indexOf("."));
    } catch (StringIndexOutOfBoundsException e) {
      logger.error("Could not find class name " + inputFile.getName());
    }
    return classname;
  }

  private void processForReal(File outputdir) throws IOException {
    if (!outputdir.isDirectory()) {
      Files.createDirectories(outputdir.toPath());
      // throw new IOException(outputdir + " is not a directory");
    }
    File outputFile = new File(outputdir, className + ".java");
    out = Files.newBufferedWriter(outputFile.toPath());

    File inputFile = getInputFile();
    logger.info("parsing: " + inputFile.getName() + " to " + outputFile);

    // initialise the context magic
    // context = new TestContext(log);
    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(
            new ANTLRInputStream(new FileInputStream(inputFile)));

    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(new CommonTokenStream(lexer));

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(className, _client);

    // walk the parse tree
    RudiTree myTree = visitor.visit(tree);

    // walk the rudi tree
    // look for returns
    rm = new ReturnManagement(className);
    ReturnVisitor vret = new ReturnVisitor(rm);
    vret.visitNode(myTree);
    // generate the output
    GenerationVisitor gv = new GenerationVisitor(this);

    if (myTree instanceof GrammarFile) {
      try {
        gv.visitNode(myTree);
      } catch (RuntimeException e) {
        throw new IOException(e);
      }
    } else {
      throw new UnsupportedOperationException("There is sth going very,very wrong...");
    }

    logger.info("Done parsing" + inputFile.getName());
  }

  /**
   * takes a look into the memory to tell whether the given variable has the
   * given type
   *
   * @param variable the variable
   * @param type the type
   * @return true if it is correct; false otherwise
   */
  public boolean testType(String variable, String type) {
    return this.mem.getVariableType(variable).equals(type);
  }

  /**
   * asks the memory about the type of the given variable
   *
   * @param variable
   * @return the variable's type
   */
  public String getType(String variable) {
    return this.mem.getVariableType(variable);
  }

  /**
   * to test whether the parameters given to the function are of the correct
   * types
   *
   * @param funcname
   * @param partypes
   * @return true or false
   */
  public boolean testFunctionType(String funcname, ArrayList<String> partypes) {
    return this.mem.existsFunction(funcname, partypes);
  }

  /**
   * asks the memory about the type of the given function
   *
   * @param funcname
   * @return the function's type
   */
  public String getFunctionReturnType(String funcname) {
    return this.mem.getFunctionRetType(funcname);
  }

}
