package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.RULES_FILE_EXTENSION;
import static de.dfki.mlt.rudimant.io.RobotGrammarParser.JAVA_CODE;
import static de.dfki.mlt.rudimant.io.RobotGrammarParser.MULTI_L_COMMENT;
import static de.dfki.mlt.rudimant.io.RobotGrammarParser.NLWS;
import static de.dfki.mlt.rudimant.io.RobotGrammarParser.ONE_L_COMMENT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.loot.gui.DrawingPanel;
import de.dfki.lt.loot.gui.MainFrame;
import de.dfki.lt.loot.gui.layouts.CompactLayout;
import de.dfki.mlt.rudimant.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.abstractTree.TreeModelAdapter;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;

public class Visualize {

  private static final Logger logger = LoggerFactory.getLogger("viz");

  /**
   * @param args: the file that should be parsed without ending (in args[0])
   * @throws Exception
   */
  public static void main(String[] args) throws IOException {

    File inputFile = new File(args[0]);

    String inputRealName = inputFile.getName().replace(RULES_FILE_EXTENSION, "");

    // initialise the context magic
    // context = new TestContext(log);
    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(
        new ANTLRInputStream(new FileInputStream(inputFile)));

    List<Integer> toCollect = Arrays.asList(
        new Integer[] { JAVA_CODE, ONE_L_COMMENT, MULTI_L_COMMENT, NLWS });
    CollectorTokenSource collector = new CollectorTokenSource(lexer, toCollect);

    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(
        new CommonTokenStream(collector));

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(inputRealName,
        collector.getCollectedTokens());

    // create the abstract syntax tree
    RudiTree myTree = visitor.visit(tree);

    DrawingPanel dp =
        new DrawingPanel(myTree, new CompactLayout(), new TreeModelAdapter());
    MainFrame thismf = new MainFrame(inputRealName, dp);
  }
}
