package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.Map;

import org.antlr.v4.runtime.Token;
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
import de.dfki.mlt.rudimant.agent.nlg.Pair;


public class Visualize {

  private static final Logger logger = LoggerFactory.getLogger("viz");

  public static Map<String, Object> configs = null;
  public static File confDir = null;

  public static String generate(String realName, InputStream in,
      String confname)
      throws IOException, WrongFormatException, TException {
    readConfig(confname);
    RudimantCompiler rc = RudimantCompiler.init(confDir, configs);
    StringWriter sw = new StringWriter();
    rc.processForReal(in, sw);
    rc.flush();
    return sw.toString();
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
    public boolean process(File f, InputStream in, MainFrame mf)
        throws IOException {
      String inputRealName = f.getName().replace(RULES_FILE_EXTENSION, "");

      // create the abstract syntax tree
      Pair<GrammarFile, LinkedList<Token>> myTree =
          RudimantCompiler.parseInput(inputRealName, in);

      // do the type checking
      try {
        RudimantCompiler rc = RudimantCompiler.init(confDir, configs);
        new VTestTypeVisitor(rc).visitNode(myTree.first);
      } catch (WrongFormatException|TException ex) {
        throw new RuntimeException(ex);
      }

      // show tree
      show(myTree.first, inputRealName, mf);
      return true;
    }
  }

  public static void init() {
    Style.increaseDefaultFontSize(1.5);
  }

  @SuppressWarnings("unchecked")
  public static void readConfig(String confname)
      throws FileNotFoundException {
    Yaml yaml = new Yaml();
    File confFile = new File(confname);
    confDir = confFile.getParentFile();
    configs = (Map<String, Object>) yaml.load(new FileReader(confFile));
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

    if (args.length > 1) { readConfig(args[1]); }

    Style.increaseDefaultFontSize(1.5);
    MainFrame root = new MainFrame("foo");
    root.addFileAssociation(new RudiFileHandler(), "rudi");
    new RudiFileHandler().process(inputFile, new FileInputStream(inputFile),
        root);
  }
}
