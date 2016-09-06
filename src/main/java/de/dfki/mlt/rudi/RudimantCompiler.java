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
import de.dfki.mlt.rudi.abstractTree.TestTypeVisitor;
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
  // there may be users that do not start the .rudi files with capital letters,
  // we don't want to crash in that case
  private String inputRealName;

  private Writer out;

  protected HfcDbService.Client _client;

  private Mem mem;

  public ReturnManagement rm;

  public List<String> subPackage = new ArrayList<>();

  public String className;

  private RudimantCompiler parent;

  private RudimantCompiler(Mem m) {
    mem = m;
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
    // subPackage.add(className);

    processForReal(outputdir);
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
    // subPackage.add(parent.className);
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
    if (!subPackage.isEmpty()) {
      for (String s : subPackage.subList(0, subPackage.size() - 1)) {
        result = new File(result, s);
      }
    }
    if (inputRealName != null) {
      return new File(result, inputRealName + ".rudi");
    } else {
      return new File(result, className + ".rudi");
    }
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
      throw new InOutException(ex);
    }
    return this;
  }

  public RudimantCompiler append(CharSequence c) {
    try {
      out.append(c);
    } catch (IOException ex) {
      throw new InOutException(ex);
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
    this.inputRealName = inputFile.getName().replace(".rudi", "");
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
    ParseTreeVisitor visitor = new ParseTreeVisitor(inputRealName, _client);

    // walk the parse tree
    RudiTree myTree = visitor.visit(tree);

    // walk the rudi tree
    // look for returns
    rm = new ReturnManagement(className);
    ReturnVisitor vret = new ReturnVisitor(rm);
    vret.visitNode(myTree);

    // do the type checking
    TestTypeVisitor ttv = new TestTypeVisitor(this);
    ttv.visitNode(myTree);

    // generate the output
    GenerationVisitor gv = new GenerationVisitor(this);

    if (myTree instanceof GrammarFile) {
      // tell the file its name (for class definition)
      ((GrammarFile) myTree).setClassName(className);
      // maybe we need to import the class that imported us to use its variables
      try {
        out.append("import " + this.parent.className + ";\n");
      } catch (NullPointerException e) {
        ; // no imported file
      }
      try {
        gv.visitNode(myTree);
      } catch (InOutException e) {
        throw new IOException(e);
      }
    } else {
      throw new UnsupportedOperationException("There is sth going very,very wrong...");
    }

    logger.info("Done parsing" + inputFile.getName());
  }

  /**
   * use this to report a type checking error; it will be handled according to
   * the set typeCheck parameter
   * @param errorMessage
   */
  public void handleTypeError(String errorMessage){
    if(this.typeCheck){
      // throw a real Exception
      throw new TypeException(errorMessage);
    } else {
      // just set a warning into the logger
      logger.warn(errorMessage);
    }
  }
}
