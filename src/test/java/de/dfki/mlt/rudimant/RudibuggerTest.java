package de.dfki.mlt.rudimant;

import de.dfki.lt.hfc.WrongFormatException;
import static de.dfki.mlt.rudimant.Visualize.setUp;
import static de.dfki.mlt.rudimant.tree.TstUtils.RESOURCE_DIR;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;


/**
 *
 * @author Christophe Biwer (yoshegg) christophe.biwer@dfki.de
 */
public class RudibuggerTest {

//  static String header = "// Starting rudibugger tests\n";
//  static String footer = "// End of rudibugger tests\n}";

  static String RUDIBUGGER_TEST_RES_DIR = RESOURCE_DIR + "rudibugger/";

  static Yaml yaml;

  @BeforeClass
  public static void setUpRudimant() throws FileNotFoundException {
//    setUp(RESOURCE_DIR + "rudibugger/rudibuggerTests.yml", header, footer);
  yaml = new Yaml();
  }

  @Test
  public void testRulesOnly() throws WrongFormatException, FileNotFoundException {
    GrammarMain.main(new String[]{
        "-b",
        "-c", RUDIBUGGER_TEST_RES_DIR + "rulesTest/rulesTest.yml",
        "-o", "target/generated/",
        RUDIBUGGER_TEST_RES_DIR + "rulesTest/Rules.rudi"
    });

    /* actual Map */
    File ruleLocationYaml = new File(RUDIBUGGER_TEST_RES_DIR
            + "rulesTest/rulesTestRuleLocation.yml");
    LinkedHashMap<String, Object> actualMap
            = (LinkedHashMap<String, Object>) yaml.load(new FileReader(ruleLocationYaml));

    /* excepted Map */
    LinkedHashMap<String, Map> expectedMap = new LinkedHashMap() {
      {
        put("Rules", new LinkedHashMap() {
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
        });
      }
    };

    /* test for equality */
    assertEquals(expectedMap, actualMap);
  }


  @Test
  public void testImportsOnly() {

  }

  @Test
  public void testNestedRules() {

  }

  @Test
  public void testEverything() {

  }

}
