/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author anna
 */
public class GrammarMain {

  protected static Writer writer;
  private static PrintStream logwriter;
  private static boolean log;
  private static boolean throwExceptions = true;
  public static RobotContext context;

  private static String inputDirectory;
  private static String outputDirectory;

  private static String help = "Hello, this is rudimant.\n"
      + "Currently, the following flags are available:\n"
      + "-log\tTranscribe file in logmode. Text will be added so that "
      + "the outcome of\n"
      + "\tall boolean expressions is being logged as "
      + "soon as they are evaluated.\n"
      + "-e\tDo not crash if there are .rudi files that cannot be translated"
      + "\n\nPlease use this tool as follows:\n"
      + "java rudimant <directory_to_be_searched/> [output_directory/] (-log)\n";

  /**
   *
   * @param args: the file that should be parsed without ending (in args[0])
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int i = 0;
    if (args.length == 0 || args[0].equals("-help")) {
      System.out.println(help);
      System.exit(0);
    }
    if (args.length == 0 || args[0].startsWith("-")) {
      System.out.println("Please use this tool as follows: java rudimant <directory_to_be_searched/> <output_directory/>? (-log)\n"
              + "For help see rumdimant -help\n");
      System.exit(-1);
    }
    inputDirectory = args[0];
    if (args[1].startsWith("-")) {
      outputDirectory = inputDirectory;
    } else {
      outputDirectory = args[1];
      i--;
    }
    for (String arg : args) {
      i++;
      if (i <= 1) {
        continue;
      }
      switch (arg) {
        case "-log":
          log = true;
          break;
        case "-e":
          throwExceptions = false;
          break;
        default:
          System.out.println(help);
      }
    }
    System.out.println("Searching directory " + inputDirectory + " for files to be translated.");

    // find all .rudi files in directory and process them
    File dir = new File(inputDirectory);
    if (throwExceptions) {
      searchAndTranslateDirectory(dir);
    } else {
      // initiate log writer
      logwriter = new PrintStream("rudimant.log");
      searchAndTranslateDirectoryLogEx(dir);
    }
  }

  public static void searchAndTranslateDirectoryLogEx(File directory) {
    File[] contained = directory.listFiles();
    for (File f : contained) {
      if (f.isDirectory()) {
        searchAndTranslateDirectoryLogEx(f);
      } else if (f.getName().endsWith(".rudi")) {
        try {
          processFile(f);
        } catch (Exception e) {
          System.out.println("A " + e.getClass() + "occurred during parsing"
                  + " of file " + f.getName() + ". See rudimant.log for stack"
                  + " trace.");
          e.printStackTrace(logwriter);
          try {
            writer.close();
          } catch (IOException ex) {
          }
        }
      }
    }
  }

  public static void searchAndTranslateDirectory(File directory) throws Exception {
    File[] contained = directory.listFiles();
    for (File f : contained) {
      if (f.isDirectory()) {
        searchAndTranslateDirectory(f);
      } else if (f.getName().endsWith(".rudi")) {
        processFile(f);
      }
    }
  }

  public static void processFile(File file) throws Exception {
    if (inputDirectory.equals(outputDirectory)) {
      outputDirectory = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("/"));
    }
    System.out.println("parsing: " + file);
    System.out.println("to " + outputDirectory);

    // creating output file from input filename;
    String classname = "";
    try {
      classname = file.getName().substring(0, 1).toUpperCase() + file.getName().substring(1, file.getName().indexOf("."));
    } catch (StringIndexOutOfBoundsException e) {
      System.out.println("Could not parse file" + file.getName());
      return;
    }
    String outputFile = outputDirectory + classname + ".java";
    File out = new File(outputFile);
    out.setWritable(true);
    out.setReadable(true);

    // initiate writer
    writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(out)));

    // initialise the context magic
    context = new TestContext(log);

    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(new FileInputStream(file)));

    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(new CommonTokenStream(lexer));

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(context);

    // walk the parse tree
    AbstractTree myTree = visitor.visit(tree);

    // prepare the output file
    writer.write(context.beforeClassName());
    //writer.write("public class " + classname + " extends RuleUnit {\n\n");

    writer.write(myTree + context.atEndOfFile() + "\n}");

    // close the writer
    writer.close();
  }
}
