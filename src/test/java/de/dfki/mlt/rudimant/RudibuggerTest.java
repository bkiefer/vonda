package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.compiler.CompilerMain.configs;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.RESOURCE_DIR;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.setUpEmpty;
import static org.junit.Assert.assertEquals;

import java.io.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.common.*;

import static de.dfki.mlt.rudimant.compiler.Visualize.generateAndGetRulesInfo;

/**
 * These tests check if the harvesting of all rules and imports and its
 * locations works as it needs to for rudibugger.
 *
 * @author Christophe Biwer (yoshegg) christophe.biwer@dfki.de
 */
public class RudibuggerTest {

  static String RUDIBUGGER_TEST_RES_DIR = RESOURCE_DIR + "rudibugger/";

  @BeforeClass
  public static void setUpRudimant() throws FileNotFoundException {
    setUpEmpty();
    configs.put("outputDirectory", "../../../target/generatedTestFiles");
    configs.put("ontologyFile", "ontologies/inits/pal.inference.ini");
    configs.put("failOnError", false);
  }

  @Test
  public void testRulesOnly() {

    /* actual rootImport */
    ImportInfo actualRootImport = (ImportInfo) generateAndGetRulesInfo(
            new File(RUDIBUGGER_TEST_RES_DIR + "rulesTest/Rules.rudi"));

    /* excepted ImportInfo */
    ImportInfo expectedRootImport = new ImportInfo("Rules", null, -1, null);
    RuleInfo rule_one = new RuleInfo("rule_one", 1, expectedRootImport);
    RuleInfo rule_two = new RuleInfo("rule_two", 5, expectedRootImport);

    /* test for equality */
    assertEquals(expectedRootImport, actualRootImport);
  }

  @Test
  public void testImportsOnly() {

    /* actual rootImport */
    ImportInfo actualRootImport = (ImportInfo) generateAndGetRulesInfo(
            new File(RUDIBUGGER_TEST_RES_DIR
            + "importTest/Root.rudi"));

    /* excepted ImportInfo */
    ImportInfo expectedRootImport = new ImportInfo("Root", null, -1, null);
    RuleInfo rule_one = new RuleInfo("rule_one", 1, expectedRootImport);
    ImportInfo import1 = new ImportInfo("Import1", null, 6, expectedRootImport);
    RuleInfo rule_import = new RuleInfo("rule_import", 1, import1);
    RuleInfo rule_two = new RuleInfo("rule_two", 8, expectedRootImport);

    /* test for equality */
    assertEquals(expectedRootImport, actualRootImport);
  }

  @Test
  public void testNestedRules() {

    /* actual rootImport */
    ImportInfo actualRootImport = (ImportInfo) generateAndGetRulesInfo(
            new File(RUDIBUGGER_TEST_RES_DIR
            + "nestedRulesTest/NestedRules.rudi"));

    /* excepted ImportInfo */
    ImportInfo expectedRootImport = new ImportInfo("NestedRules", null, -1, null);
    RuleInfo rule_one = new RuleInfo("rule_one", 3, expectedRootImport);
    RuleInfo rule_one_a = new RuleInfo("rule_one_a", 6, rule_one);
    RuleInfo rule_two = new RuleInfo("rule_two", 12, expectedRootImport);

    /* test for equality */
    assertEquals(expectedRootImport, actualRootImport);
  }

  @Test
  public void testEverything() {

    /* actual rootImport */
    ImportInfo actualRootImport = (ImportInfo) generateAndGetRulesInfo(
            new File(RUDIBUGGER_TEST_RES_DIR
            + "everythingTest/Root.rudi"));

    /* excepted ImportInfo */
    ImportInfo expectedRootImport = new ImportInfo("Root", null, -1, null);
    RuleInfo rule_one = new RuleInfo("rule_one", 1, expectedRootImport);
    RuleInfo rule_one_a = new RuleInfo("rule_one_a", 4, rule_one);
    ImportInfo import1 = new ImportInfo("Import1", null, 10, expectedRootImport);
    RuleInfo rule_import = new RuleInfo("rule_import", 1, import1);
    RuleInfo rule_import_a = new RuleInfo("rule_import_a", 4, rule_import);
    RuleInfo rule_two = new RuleInfo("rule_two", 12, expectedRootImport);
    RuleInfo rule_two_a = new RuleInfo("rule_two_a", 15, rule_two);

    /* test for equality */
    assertEquals(expectedRootImport, actualRootImport);
  }

  @Test
  public void testDefaultPrinter() {
    BasicInfo i = generateAndGetRulesInfo(new File(RUDIBUGGER_TEST_RES_DIR
        + "nestedRulesTest/NestedRules.rudi"));
    RuleLogger rl = new RuleLogger();
    rl.setRootInfo(i);
    rl.registerPrinter(new DefaultLogger());
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream pri = new PrintStream(out);
    PrintStream old = System.out;
    System.setOut(pri);
    rl.logAllRules();
    boolean[] res = { false, true, false, false } ;
    rl.logRule(0, res);
    System.setOut(old);
    String output = out.toString();
    assertEquals("(rule_one) FALSE: (([true: (a!=b)]||[unk: (b!=c)])&&[false: (a==c)])\n", output);
  }

  @Test
  public void testDefaultPrinter2() {
    BasicInfo i = generateAndGetRulesInfo(new File(RUDIBUGGER_TEST_RES_DIR
        + "nestedRulesTest/NestedRules.rudi"));
    RuleLogger rl = new RuleLogger();
    rl.setRootInfo(i);
    rl.registerPrinter(new DefaultLogger());
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream pri = new PrintStream(out);
    PrintStream old = System.out;
    System.setOut(pri);
    rl.logAllRules();
    boolean[] res = { false, true, true } ;
    rl.logRule(1, res);
    System.setOut(old);
    String output = out.toString();
    assertEquals("(rule_one_a) FALSE: ([true: b==b]&&![true: (a+b)<(b+c)])\n", output);
  }

  @Test
  public void testDefaultPrinter3() {
    BasicInfo i = generateAndGetRulesInfo(new File(RUDIBUGGER_TEST_RES_DIR
        + "nestedRulesTest/NestedRules.rudi"));
    RuleLogger rl = new RuleLogger();
    rl.setRootInfo(i);
    rl.registerPrinter(new DefaultLogger());
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream pri = new PrintStream(out);
    PrintStream old = System.out;
    System.setOut(pri);
    rl.logAllRules();
    boolean[] res = { false, true } ;
    rl.logRule(2, res);
    System.setOut(old);
    String output = out.toString();
    assertEquals("(rule_two) FALSE: ![true: user]\n", output);
  }

  @Test
  public void testConvertedToBoolean() {
    BasicInfo i = generateAndGetRulesInfo(new File(RUDIBUGGER_TEST_RES_DIR
        + "convertedBoolTest/ConvertedBooleans.rudi"));
    RuleLogger rl = new RuleLogger();
    rl.setRootInfo(i);
    rl.registerPrinter(new DefaultLogger());
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream pri = new PrintStream(out);
    PrintStream old = System.out;
    System.setOut(pri);
    rl.logAllRules();
    boolean[] res = { true, true } ;
    rl.logRule(0, res);
    System.setOut(old);
    String output = out.toString();
    assertEquals("(rule_one) TRUE: [true: a]\n", output);
    out = new ByteArrayOutputStream();
    pri = new PrintStream(out);
    System.setOut(pri);
    rl.logAllRules();
    boolean[] res2 = { false, false } ;
    rl.logRule(1, res2);
    System.setOut(old);
    output = out.toString();
    assertEquals("(rule_two) FALSE: [false: b.forename]\n", output);
  }

}
