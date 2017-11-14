package de.dfki.mlt.rudimant;

import de.dfki.mlt.rudimant.common.ImportInfo;
import de.dfki.mlt.rudimant.common.RuleInfo;
import static de.dfki.mlt.rudimant.compiler.CompilerMain.configs;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.RESOURCE_DIR;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.setUpEmpty;
import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
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
    configs.put("outputDirectory", "rudibugger/generatedTestFiles");
    configs.put("ontologyFile", "ontos/pal.quads.ini");
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
    RuleInfo rule_one = new RuleInfo("rule_one", 1, expectedRootImport);
    RuleInfo rule_one_a = new RuleInfo("rule_one_a", 4, rule_one);
    RuleInfo rule_two = new RuleInfo("rule_two", 10, expectedRootImport);

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

}
