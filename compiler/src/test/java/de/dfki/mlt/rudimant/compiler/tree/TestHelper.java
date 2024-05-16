package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.common.Constants.CFG_CONFIG_DIRECTORY;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.common.BasicInfo;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.compiler.CompilerMain;
import de.dfki.mlt.rudimant.compiler.RudimantCompiler;
import de.dfki.mlt.rudimant.compiler.TypeException;
import de.dfki.mlt.rudimant.compiler.Visualize;

public class TestHelper {

  static String header = "";
  static String footer = "";

  private static File confDir;
  public static Map<String, Object> configs;

  public static void readConfig(String configFile) throws FileNotFoundException {
    configs = CompilerMain.readConfig(configFile);
    confDir = (File)configs.get(CFG_CONFIG_DIRECTORY);
  }

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

  private static RudimantCompiler initRc(boolean fakeReal)
      throws IOException, WrongFormatException {
    RuleInfo.resetIdGenerator();
    RudimantCompiler rc = new RudimantCompiler(confDir, configs);
    CompilerMain.INFO_DIR = "target/generatedTestFiles/";
    if (fakeReal) {
      String[] pkg = {};
      rc.getMem().enterClass("Test", pkg, null);
      rc.readAgentSpecs();
    }
    return rc;
  }

  private static RudimantCompiler initRc()
      throws IOException, WrongFormatException {
    return initRc(true);
  }

  public static String generate(String in, boolean show) {
    RudimantCompiler rc = null;
    try {
      rc = initRc();
      if (show) rc.showTree();
      StringWriter sw = new StringWriter();
      parseAndGenerate(rc, getInput(in), sw, "test");
      sw.flush();
      return sw.toString();
    } catch (IOException | WrongFormatException e) {
      throw new RuntimeException(e);
    } finally {
      if (rc != null) {
        rc.shutdown();
      }
    }
  }

  public static String generate(String in) { return generate(in, false); }

  public static BasicInfo generateAndGetRulesInfo(File input) {
    RudimantCompiler rc = null;
    try {
      rc = initRc(false);
      rc.processToplevel(input);
    } catch (IOException | WrongFormatException e) {
      throw new RuntimeException(e);
    } finally {
      if (rc != null) {
        rc.shutdown();
      }
    }
    return rc.getMem().getInfo();
  }

  public static GrammarFile parseAndTypecheck(InputStream in) {
    try {
      // create the abstract syntax tree
      RudimantCompiler rc = initRc();
      GrammarFile result = GrammarFile.parseAndTypecheck(rc, in, "test");
      return result;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static GrammarFile parseAndTypecheck(String in) {
    return parseAndTypecheck(getInput(in));
  }

  private static GrammarFile parseAndGenerate(RudimantCompiler rc,
      InputStream in, Writer out, String name) throws IOException {
    GrammarFile gf = GrammarFile.parseAndTypecheck(rc, in, name);
    if (gf == null)
      throw new UnsupportedOperationException("Parsing failed.");
    if (rc.visualise())
      Visualize.show(gf, name);
    gf.generate(rc, out);
    return gf;
  }

  public static GrammarFile parseAndTypecheckWithError(InputStream in, Writer out)
      throws Throwable {
    try {
      // create the abstract syntax tree
      RudimantCompiler rc = initRc();
      rc.throwTypeErrors();
      GrammarFile result = parseAndGenerate(rc, in, out, "test");
      return result;
    } catch (RuntimeException ex) {
      if (ex.getCause() instanceof TypeException)
        throw ex.getCause();
      else
        throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static GrammarFile parseAndTypecheckWithError(String in, Writer out)
      throws Throwable {
    return parseAndTypecheckWithError(getInput(in), out);
  }

}
