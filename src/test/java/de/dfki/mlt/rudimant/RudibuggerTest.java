package de.dfki.mlt.rudimant;

import de.dfki.lt.hfc.WrongFormatException;
import static de.dfki.mlt.rudimant.GrammarMain.configs;
import static de.dfki.mlt.rudimant.Visualize.generateAndGetRulesLocMap;
import static de.dfki.mlt.rudimant.tree.TstUtils.RESOURCE_DIR;
import static de.dfki.mlt.rudimant.tree.TstUtils.setUpEmpty;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

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
  public void testRulesOnly() throws WrongFormatException, FileNotFoundException {

    /* define to RuleLocation.yml's location */
    configs.put("ruleLocationFile", RUDIBUGGER_TEST_RES_DIR
            + "generatedTestFiles/RulesLocation.yml");

    /* actual Map */
    Map actualMap;
    actualMap = generateAndGetRulesLocMap(new File(RUDIBUGGER_TEST_RES_DIR
            + "rulesTest/Rules.rudi"));

    /* excepted Map */
    LinkedHashMap<String, Map> expectedMap = new LinkedHashMap() {
      {
        put("rule_one", new LinkedHashMap() {
          {
            put("%InLine", 1);
          }
        });
        put("rule_two", new LinkedHashMap() {
          {
            put("%InLine", 5);
          }
        });
      }
    };

    /* test for equality */
    assertEquals(expectedMap, actualMap);
  }

  @Test
  public void testImportsOnly() {

    /* define to RuleLocation.yml's location */
    configs.put("ruleLocationFile", RUDIBUGGER_TEST_RES_DIR
            + "generatedTestFiles/ImportLocation.yml");

    /* actual Map */
    Map actualMap;
    actualMap = generateAndGetRulesLocMap(new File(RUDIBUGGER_TEST_RES_DIR
            + "importTest/Root.rudi"));

    /* excepted Map */
    LinkedHashMap<String, Map> expectedMap = new LinkedHashMap() {
      {
        put("rule_one", new LinkedHashMap() {
          {
            put("%InLine", 1);
          }
        });
        put("Import1", new LinkedHashMap() {
          {
            put("%ImportWasInLine", 6);
            put("rule_import", new LinkedHashMap() {
              {
                put("%InLine", 1);
              }
            });
          }
        });
        put("rule_two", new LinkedHashMap() {
          {
            put("%InLine", 8);
          }
        });
      }
    };

    /* test for equality */
    assertEquals(expectedMap, actualMap);

  }

  @Test
  public void testNestedRules() {

    /* define to RuleLocation.yml's location */
    configs.put("ruleLocationFile", RUDIBUGGER_TEST_RES_DIR
            + "generatedTestFiles/NestedRulesLocation.yml");

    /* actual Map */
    Map actualMap;
    actualMap = generateAndGetRulesLocMap(new File(RUDIBUGGER_TEST_RES_DIR
            + "nestedRulesTest/NestedRules.rudi"));

    /* excepted Map */
    LinkedHashMap<String, Map> expectedMap = new LinkedHashMap() {
      {
        put("rule_one", new LinkedHashMap() {
          {
            put("%InLine", 1);
            put("rule_one_a", new LinkedHashMap() {
              {
                put("%InLine", 4);
              }
            });
          }
        });
        put("rule_two", new LinkedHashMap() {
          {
            put("%InLine", 10);
          }
        });
      }
    };

    /* test for equality */
    assertEquals(expectedMap, actualMap);
  }

  @Test
  public void testEverything() {

    /* define to RuleLocation.yml's location */
    configs.put("ruleLocationFile", RUDIBUGGER_TEST_RES_DIR
            + "generatedTestFiles/EverythingLocation.yml");

    /* actual Map */
    Map actualMap;
    actualMap = generateAndGetRulesLocMap(new File(RUDIBUGGER_TEST_RES_DIR
            + "everythingTest/Root.rudi"));

    /* excepted Map */
    LinkedHashMap<String, Map> expectedMap = new LinkedHashMap() {
      {
        put("rule_one", new LinkedHashMap() {
          {
            put("%InLine", 1);
            put("rule_one_a", new LinkedHashMap() {
              {
                put("%InLine", 4);
              }
            });
          }
        });
        put("Import1", new LinkedHashMap() {
          {
            put("%ImportWasInLine", 10);
            put("rule_import", new LinkedHashMap() {
              {
                put("%InLine", 1);
                put("rule_import_a", new LinkedHashMap() {
                  {
                    put("%InLine", 4);
                  }
                });
              }
            });
          }
        });
        put("rule_two", new LinkedHashMap() {
          {
            put("%InLine", 12);
            put("rule_two_a", new LinkedHashMap() {
              {
                put("%InLine", 15);
              }
            }
            );
          }
        }
        );
      }
    };

    /* test for equality */
    assertEquals(expectedMap, actualMap);

  }

}
