package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.compiler.tree.io.RobotGrammarParser.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;
import de.dfki.mlt.rudimant.compiler.tree.ParseTreeVisitor;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;
import de.dfki.mlt.rudimant.compiler.tree.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.compiler.tree.io.RobotGrammarParser;

public class AntlrParser {

  public static final Logger logger = LoggerFactory
      .getLogger(RudimantCompiler.class);

  public static GrammarFile parse(String realName, InputStream in)
      throws IOException {
    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(in));

    List<Integer> toCollect = Arrays.asList(
        new Integer[] { JAVA_CODE, ONE_L_COMMENT, MULTI_L_COMMENT, NLWS });
    CollectorTokenSource collector = new CollectorTokenSource(lexer, toCollect);

    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(
        new CommonTokenStream(collector));
    final boolean[] errorOccured = { false };
    parser.addErrorListener(new BaseErrorListener() {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer,
          Object offendingSymbol, int line, int charPositionInLine, String msg,
          RecognitionException e) {
        errorOccured[0] = true;
        logger.error("{}.rudi:{}:{}: {}", realName, line, charPositionInLine,
            msg);
      }
    });

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();
    if (errorOccured[0])
      return null;

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(realName);

    // create the abstract syntax tree
    RudiTree myTree = visitor.visit(tree);

    if (!(myTree instanceof GrammarFile))
      return null;
    GrammarFile result = (GrammarFile) myTree;
    result.tokens = collector.getCollectedTokens();
    return result;
  }
}
