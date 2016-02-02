/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;

import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author anna
 */
public class Main {
    
    protected static Writer writer;
    
    /**
     * 
     * @param args: the file that should be parsed (in args[0])
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
    // TODO: do something if there are no input arguments

    System.out.println("parsing: " + args[0]);
    
    // name and location of output file; TODO: what location?
    String outputFile = "loc" + args[0].split(".")[0] + ".java";
    writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(outputFile)));
    

    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRFileStream(args[0]));
    
    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(new CommonTokenStream(lexer));
    
    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();
    
    // initialise the visitor that will do all the work
    RGVisitor visitor = new RGVisitor();
    
    // walk the parse tree, (create output file here?)
    visitor.visit(tree);
    }
}
