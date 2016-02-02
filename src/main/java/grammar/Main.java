/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;

import de.dfki.mlt.rudimant.io.DialogueCombinedGrammarLexer;
import de.dfki.mlt.rudimant.io.DialogueCombinedGrammarParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author anna
 */
public class Main {
    
    /**
     * 
     * @param args: the file that should be parsed (in args[0])
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
    // TODO: do something if there are no input arguments

    System.out.println("parsing: " + args[0]);

    // initialise the lexer with given input file
    DialogueCombinedGrammarLexer lexer = new DialogueCombinedGrammarLexer(new ANTLRFileStream(args[0]));
    
    // initialise the parser
    DialogueCombinedGrammarParser parser = new DialogueCombinedGrammarParser(new CommonTokenStream(lexer));
    
    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();
    
    // initialise the visitor that will do all the work
    TestVisitor visitor = new TestVisitor();
    
    // walk the parse tree, (create output file here?)
    visitor.visit(tree);
    }
}
