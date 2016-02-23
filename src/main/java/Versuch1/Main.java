///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Versuch1;
//
//import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
//import de.dfki.mlt.rudimant.io.RobotGrammarParser;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import org.antlr.v4.runtime.ANTLRInputStream;
//import org.antlr.v4.runtime.CommonTokenStream;
//import org.antlr.v4.runtime.tree.ParseTree;
//
///**
// *
// * @author anna
// */
//public class Main {
//
//  protected static Writer writer;
//
//  /**
//   * 
//   * @param args: the file that should be parsed without ending (in args[0])
//   * @throws Exception 
//   */
//  public static void main(String[] args) throws Exception {
//
//  // TODO: do something if there are no input arguments
//
//  System.out.println("parsing: " + args[0]);
//
//  // creating input file & make it readable
//  File in = new File("src/test/testfiles/" + args[0] + ".txt");
//  in.setReadable(true);
//
//  // creating output file from input filename; TODO: what location?
//  String classname = args[0].substring(0, 1).toUpperCase() + args[0].substring(1);
//  String outputFile = "src/test/testfiles/" + classname + ".java";
//  File out = new File(outputFile);
//  out.setWritable(true);
//  out.setReadable(true);
//  
//  // initiate writer
//  writer = new BufferedWriter(new OutputStreamWriter(
//            new FileOutputStream(out)));
//
//  // initialise the context magic
//  RobotContext context = new ExampleContext();
//  
//  // prepare the output file
//  writer.write(context.beforeClassName());
//  classname = args[0].substring(0, 1).toUpperCase() + args[0].substring(1);
//  writer.write("public class " + classname + " extends RuleUnit {\n\n");
//  writer.write(context.afterClassName());
//  
//  // initialise the lexer with given input file
//  RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(new FileInputStream(in)));
//
//  // initialise the parser
//  RobotGrammarParser parser = new RobotGrammarParser(new CommonTokenStream(lexer));
//
//  // create a parse tree; grammar_file is the start rule
//  ParseTree tree = parser.grammar_file();
//
//  // initialise the visitor that will do all the work
//  RGVisitor visitor = new RGVisitor(writer, context);
//
//  // walk the parse tree, (create output file here?)
//  visitor.visit(tree);
//  
//  writer.write(context.atEndOfFile() + "\n}");
//  
//  // close the writer
//  writer.close();
//  }
//}
