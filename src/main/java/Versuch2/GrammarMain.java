/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2;

import Versuch2.abstractTree.AbstractTree;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author anna
 */
public class GrammarMain {

  protected static Writer writer;
  private static boolean log;
  public static RobotContext context;

  /**
   *
   * @param args: the file that should be parsed without ending (in args[0])
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int i = 0;
    for(String arg : args){
      i++;
      if(i == 1){continue;}
      switch(arg){
        case "-log": log = true; break;
        default: System.out.println("Hello, this is rudimant. You typed in a command I do not"
                + "know. Currently, the following flags are available:\n"
                + "-log\tTranscribe file in logmode. Text will be added so that "
                + "the outcome of all boolean expressions is being logged as "
                + "soon as they are evaluated."
                + "\n\nPlease use this tool as follows: java rudimant <name_of_file_without_ending> (-log)\n");
      }
    }
    if(args.length == 0){
      System.out.println("Please use this tool as follows: java rudimant <name_of_file_without_ending> (-log)\n");
    }
  // TODO: do something if there are no input arguments
    System.out.println("parsing: " + args[0]);

    // creating input file & make it readable
    File in = new File("src/test/testfiles/" + args[0] + ".txt");
    in.setReadable(true);

    // creating output file from input filename; TODO: what location?
    String classname = args[0].substring(0, 1).toUpperCase() + args[0].substring(1);
    String outputFile = "src/test/testfiles/" + classname + ".java";
    File out = new File(outputFile);
    out.setWritable(true);
    out.setReadable(true);

    // initiate writer
    writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(out)));

    // initialise the context magic
    context = new TestContext(log);

    // prepare the output file
    writer.write(context.beforeClassName());
    //writer.write("public class " + classname + " extends RuleUnit {\n\n");

    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(new FileInputStream(in)));

    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(new CommonTokenStream(lexer));

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(context);

    // walk the parse tree
    AbstractTree myTree = visitor.visit(tree);

    writer.write(myTree + context.atEndOfFile() + "\n}");

    // close the writer
    writer.close();
  }
}
