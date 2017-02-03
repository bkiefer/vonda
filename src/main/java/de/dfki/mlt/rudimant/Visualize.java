package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.RULES_FILE_EXTENSION;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
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


public class Visualize {

  static String header = "";
  static String footer = "";

  public static void setUp(String configFile, String h, String f)
      throws FileNotFoundException {
    readConfig(configFile);
    header = h;
    footer = f;
  }

  public static InputStream getInput(String input) {
    String toParse = header + input + footer;
    return new ByteArrayInputStream(toParse.getBytes());
  }

  public static Map<String, Object> configs = new HashMap<>();
  public static File confDir = null;

  public static String generate(String in)
      throws IOException, WrongFormatException, TException {
    RudimantCompiler rc = RudimantCompiler.init(confDir, configs);
    StringWriter sw = new StringWriter();
    rc.processForReal(getInput(in), sw);
    rc.flush();
    return sw.toString();
  }

  public static String normalizeSpaces(String in) {
    return in.replaceAll("[ \n\r\t]+", " ");
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
      GrammarFile gf = null;

      // do the type checking
      // create the abstract syntax tree
      gf = parseAndTypecheck(in);

      // show tree
      show(gf, inputRealName, mf);
      return true;
    }
  }

  public static void init() {
    Style.increaseDefaultFontSize(1.5);
  }

  public static GrammarFile parseAndTypecheck(InputStream in) {
    try {
      // create the abstract syntax tree
      RudimantCompiler rc = RudimantCompiler.init(confDir, configs);
      return rc.processForReal(in, null);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static GrammarFile parseAndTypecheck(String in) {
    return parseAndTypecheck(getInput(in));
  }

  public static GrammarFile parseAndTypecheck(InputStream in, Writer out) {
    try {
      // create the abstract syntax tree
      RudimantCompiler rc = RudimantCompiler.init(confDir, configs);
      return rc.processForReal(in, out);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static GrammarFile parseAndTypecheck(String in, Writer out) {
    return parseAndTypecheck(getInput(in), out);
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
  public static void main(String[] args)
      throws IOException, WrongFormatException {

    File inputFile = new File(args[0]);

    if (args.length > 1) { readConfig(args[1]); }

    Style.increaseDefaultFontSize(1.5);
    MainFrame root = new MainFrame("foo");
    root.addFileAssociation(new RudiFileHandler(), "rudi");
    new RudiFileHandler().process(inputFile, new FileInputStream(inputFile),
        root);
  }
}
