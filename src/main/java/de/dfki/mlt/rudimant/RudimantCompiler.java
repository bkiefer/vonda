package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.*;

import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.abstractTree.VGenerationVisitor;
import de.dfki.mlt.rudimant.abstractTree.GrammarFile;
import de.dfki.mlt.rudimant.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.abstractTree.VTestTypeVisitor;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public class RudimantCompiler {

  public static Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private boolean log;
  private boolean throwExceptions = true;
  private boolean typeCheck = true;

  private File inputDirectory;
  private File outputDirectory;
  // there may be users that do not start the .rudi files with capital letters,
  // we don't want to crash in that case by turning it to uppercase and then trying to read it
  private String inputRealName;

  private StringBuffer out;
  private Writer toFile;

  private HfcDbService.Client _client;
  private RdfProxy _proxy;

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

  public void setPackageName(String name){
    this.packageName = name;
  }

  public String getPackageName(){
    return this.packageName;
  }

  public RdfProxy getProxy() {
    return _proxy;
  }

  public String getWrapperClass() {
    return wrapperClass;
  }

  public String getConstructorArgs() {
    return constructorArgs;
  }

  private RudimantCompiler(RudimantCompiler parentCompiler) {
    wrapperClass = parentCompiler.getWrapperClass();
    constructorArgs = parentCompiler.getConstructorArgs();
    mem = parentCompiler.mem;
    _proxy = parentCompiler._proxy;
    parent = parentCompiler;
    this.log = parentCompiler.log;
    this.throwExceptions = parentCompiler.throwExceptions;
    this.typeCheck = parentCompiler.typeCheck;
    this.packageName = parent.getPackageName();
    this.rootLevel = parent.rootLevel;
  }

  public RudimantCompiler(String wrapperClass, String constructorArgs, RdfProxy proxy) {
    mem = new Mem();
    this.wrapperClass = wrapperClass;
    this._proxy = proxy;
    parent = null;
    this.constructorArgs = constructorArgs;
  }

  public static RudimantCompiler getEmbedded(RudimantCompiler parent) throws TException {
    RudimantCompiler result = new RudimantCompiler(parent);
    return result;
  }

  public void process(File topLevel, File outputdir) throws IOException, FormatterException {
    inputDirectory = topLevel.getParentFile();
    outputDirectory = outputdir;
    className = getClassName(topLevel);
    // subPackage.add(className);

    if (packageName != null && ! packageName.isEmpty()) {
      subPackage.addAll(Arrays.asList(packageName.split("\\.")));
      rootLevel = subPackage.size() - 1;
    }
    processForReal(getOutputDirectory());
  }

  public void process(File topLevel) throws IOException, FormatterException {
    process(topLevel, topLevel.getParentFile());
  }

  public void process(String importSpec) throws IOException, FormatterException {
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

  public RudimantCompiler getParent() {
    return parent;
  }

  public RudimantCompiler append(char c) {
    out.append(c);
    return this;
  }

  public RudimantCompiler append(CharSequence c) {
    out.append(c);
    return this;
  }

  public void flush() throws FormatterException {
    try {
      String formattedSource = new Formatter().formatSource(out.toString());
      toFile.write(formattedSource);
      toFile.flush();
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

  private void processForReal(File outputdir) throws IOException, FormatterException {
    if (!outputdir.isDirectory()) {
      Files.createDirectories(outputdir.toPath());
      // throw new IOException(outputdir + " is not a directory");
    }
    File outputFile = new File(outputdir, className + ".java");
    toFile = Files.newBufferedWriter(outputFile.toPath());
    out = new StringBuffer();

    File inputFile = getInputFile();
    logger.info("parsing " + inputFile.getName() + " to " + outputFile);

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
    ParseTreeVisitor visitor = new ParseTreeVisitor(inputRealName, _client);

    // walk the parse tree
    RudiTree myTree = visitor.visit(tree);

//    // walk the rudi tree
//    // look for returns
//    rm = new ReturnManagement(className);
//    VReturnVisitor vret = new VReturnVisitor(rm);
//    vret.visitNode(myTree);
    // do the type checking
    VTestTypeVisitor ttv = new VTestTypeVisitor(this);
    ttv.visitNode(myTree);
    logger.info("Done testing types of " + inputFile.getName());

    // generate the output
    VGenerationVisitor gv = new VGenerationVisitor(this);

    if (myTree instanceof GrammarFile) {
      // tell the file its name (for class definition)
      ((GrammarFile) myTree).setClassName(className);
      try {
        gv.visitNode(myTree);
      } catch (InOutException e) {
        throw new IOException(e);
      }
    } else {
      throw new UnsupportedOperationException("There is sth going very, very wrong...");
    }

    logger.info("Done parsing " + inputFile.getName());
    try {
      this.flush();
    } catch (FormatterException ex) {
      logger.warn("Could not autoformat the output; syntax may be broken");
      toFile.write(out.toString());
      toFile.flush();
    }
  }

  /**
   * use this to report a type checking error; it will be handled according to
   * the set typeCheck parameter
   *
   * @param errorMessage
   */
  public void handleTypeError(String errorMessage) {
    if (this.typeCheck) {
      // throw a real Exception
      throw new TypeException(errorMessage);
    } else {
      // just set a warning into the logger
      //System.out.println("warning");
      logger.warn(errorMessage);
    }
  }
}
