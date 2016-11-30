package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.*;
import static de.dfki.mlt.rudimant.io.RobotGrammarParser.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.loot.gui.DrawingPanel;
import de.dfki.lt.loot.gui.MainFrame;
import de.dfki.lt.loot.gui.Style;
import de.dfki.lt.loot.gui.layouts.CompactLayout;
import de.dfki.lt.loot.gui.util.ObjectHandler;
import de.dfki.mlt.rudimant.abstractTree.GrammarFile;
import de.dfki.mlt.rudimant.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.abstractTree.TreeModelAdapter;
import de.dfki.mlt.rudimant.abstractTree.VTestTypeVisitor;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;

public class Visualize {

  private static final Logger logger = LoggerFactory.getLogger("viz");

  static Map<String, Object> configs = null;

  public static RudiTree parseInput(String realName, InputStream in)
      throws IOException {
    // initialise the context magic
    // context = new TestContext(log);
    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(in));

    List<Integer> toCollect = Arrays.asList(
        new Integer[] { JAVA_CODE, ONE_L_COMMENT, MULTI_L_COMMENT, NLWS });
    CollectorTokenSource collector = new CollectorTokenSource(lexer, toCollect);

    // initialise the parser
    RobotGrammarParser parser = new RobotGrammarParser(
        new CommonTokenStream(collector));

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(realName,
        collector.getCollectedTokens());

    // create the abstract syntax tree
    RudiTree myTree = visitor.visit(tree);
    return myTree;
  }

  public static RudiTree typeCheck(RudiTree root, Map<String, Object> configs)
      throws IOException {
    // do the type checking
    if (root instanceof GrammarFile && configs != null) {
      GrammarFile gf = (GrammarFile)root;
      try {
        RudimantCompiler rc = GrammarMain.initCompiler(configs);
        VTestTypeVisitor ttv = new VTestTypeVisitor(rc);
        ttv.visitNode(gf);
      }
      catch (TException|WrongFormatException ex) {
        // throw new RuntimeException(ex);
      }
    }
    return root;
  }

  public static void show(RudiTree root, String realName, MainFrame mf) {
    CompactLayout cl = new CompactLayout();
    cl.setTreeHorizontal(true);

    DrawingPanel dp = new DrawingPanel(root, cl, new TreeModelAdapter());
    mf.setContentArea(dp);
    mf.setTitle(realName);
  }

  public static void show(RudiTree root, String realName) {
    MainFrame mf = new MainFrame("foo");
    mf.addFileAssociation(new RudiFileHandler(), "rudi");
    show(root, realName, mf);
  }

  public static class RudiFileHandler implements ObjectHandler {
    public boolean process(File f, InputStream in, MainFrame mf) throws IOException {
      String inputRealName = f.getName().replace(RULES_FILE_EXTENSION, "");

      // create the abstract syntax tree
      RudiTree myTree = parseInput(inputRealName, in);

      // do the type checking
      typeCheck(myTree, configs);

      // show tree
      show(myTree, inputRealName, mf);
      return true;
    }
  }

  public static void init() {
    Style.increaseDefaultFontSize(1.5);
  }


  /**
   * @param args: the file that should be parsed without ending (in args[0])
   * @throws WrongFormatException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public static void main(String[] args)
      throws IOException, TException, WrongFormatException {

    File inputFile = new File(args[0]);

    if (args.length > 1) {
      Yaml yaml = new Yaml();
      configs = (Map<String, Object>)yaml.load(new FileReader(args[1]));
    }

    Style.increaseDefaultFontSize(1.5);
    MainFrame root = new MainFrame("foo");
    root.addFileAssociation(new RudiFileHandler(), "rudi");
    new RudiFileHandler().process(inputFile, new FileInputStream(inputFile),
        root);
  }
}
