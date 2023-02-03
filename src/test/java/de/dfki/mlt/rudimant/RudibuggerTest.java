/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.compiler.CompilerMain.configs;
import static de.dfki.mlt.rudimant.compiler.Visualize.generateAndGetRulesInfo;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import de.dfki.mlt.rudimant.common.*;

/**
 * These tests check if the harvesting of all rules and includes and its
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
    configs.put("ontologyFile", "ontologies/inits/pal.inference.yml");
    configs.put("failOnError", false);
  }

  @Test
  public void testRulesOnly() {

    /* actual rootInclude */
    IncludeInfo actualRootInclude = (IncludeInfo) generateAndGetRulesInfo(
            new File(RUDIBUGGER_TEST_RES_DIR + "rulesTest/Rules.rudi"));

    /* excepted IncludeInfo */
    IncludeInfo expectedRootInclude = new IncludeInfo("Rules", null, -1, null);
    RuleInfo rule_one = new RuleInfo("rule_one", 1, expectedRootInclude);
    RuleInfo rule_two = new RuleInfo("rule_two", 5, expectedRootInclude);

    /* test for equality */
    assertEquals(expectedRootInclude, actualRootInclude);
  }

  @Test
  public void testIncludesOnly() {

    /* actual rootInclude */
    IncludeInfo actualRootInclude = (IncludeInfo) generateAndGetRulesInfo(
            new File(RUDIBUGGER_TEST_RES_DIR
            + "includeTest/Root.rudi"));

    /* excepted IncludeInfo */
    IncludeInfo expectedRootInclude = new IncludeInfo("Root", null, -1, null);
    RuleInfo rule_one = new RuleInfo("rule_one", 1, expectedRootInclude);
    IncludeInfo include1 = new IncludeInfo("Include1", null, 6, expectedRootInclude);
    RuleInfo rule_include = new RuleInfo("rule_include", 1, include1);
    RuleInfo rule_two = new RuleInfo("rule_two", 8, expectedRootInclude);

    /* test for equality */
    assertEquals(expectedRootInclude, actualRootInclude);
  }

  @Test
  public void testNestedRules() {

    /* actual rootInclude */
    IncludeInfo actualRootInclude = (IncludeInfo) generateAndGetRulesInfo(
            new File(RUDIBUGGER_TEST_RES_DIR
            + "nestedRulesTest/NestedRules.rudi"));

    /* excepted IncludeInfo */
    IncludeInfo expectedRootInclude = new IncludeInfo("NestedRules", null, -1, null);
    RuleInfo rule_one = new RuleInfo("rule_one", 3, expectedRootInclude);
    RuleInfo rule_one_a = new RuleInfo("rule_one_a", 6, rule_one);
    RuleInfo rule_two = new RuleInfo("rule_two", 12, expectedRootInclude);
    RuleInfo rule_three = new RuleInfo("rule_three", 16, expectedRootInclude);

    /* test for equality */
    assertEquals(expectedRootInclude, actualRootInclude);
    RuleInfo r = (RuleInfo)actualRootInclude.getChildren().get(2);
    assertEquals("a != \"1\\t\\\"\\n\"", r.getBaseTerm(0));
    assertEquals("b != \"\\n\"", r.getBaseTerm(1));
    assertEquals("d != 7.0", r.getBaseTerm(2));
  }

  @Test
  public void testEverything() {

    /* actual rootInclude */
    IncludeInfo actualRootInclude = (IncludeInfo) generateAndGetRulesInfo(
            new File(RUDIBUGGER_TEST_RES_DIR
            + "everythingTest/Root.rudi"));

    /* excepted IncludeInfo */
    IncludeInfo expectedRootInclude = new IncludeInfo("Root", null, -1, null);
    RuleInfo rule_one = new RuleInfo("rule_one", 1, expectedRootInclude);
    RuleInfo rule_one_a = new RuleInfo("rule_one_a", 4, rule_one);
    IncludeInfo include1 = new IncludeInfo("Include1", null, 10, expectedRootInclude);
    RuleInfo rule_include = new RuleInfo("rule_include", 1, include1);
    RuleInfo rule_include_a = new RuleInfo("rule_include_a", 4, rule_include);
    RuleInfo rule_two = new RuleInfo("rule_two", 12, expectedRootInclude);
    RuleInfo rule_two_a = new RuleInfo("rule_two_a", 15, rule_two);

    /* test for equality */
    assertEquals(expectedRootInclude, actualRootInclude);
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
    assertEquals("(rule_one) FALSE: (([true: a != b]||[unk: b != c])&&[false: a == c])\n", output);
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
    assertEquals("(rule_one_a) FALSE: ([true: b == b]&&[true: ! (a + b) < (b + c)])\n", output);
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

  @Test
  public void testYamlDump() throws IOException {
    String[] path = {"a", "b", "c"};
    IncludeInfo i = new IncludeInfo("bla", path, 100, null);
    ErrorInfo e = new ErrorInfo("err",
        new Location(new Position(1,1,1, "foo"), new Position(2,2,2, "foo")),
        ErrorInfo.ErrorType.PARSE_ERROR);
    i.setErrors(new ArrayList<ErrorInfo>(){{ add(e); }});
    DumperOptions options = new DumperOptions();
    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    //options.setAllowReadOnlyProperties(true);
    Yaml y = new Yaml(options);
    //y.setBeanAccess(BeanAccess.FIELD);
    FileWriter w = new FileWriter(new File("target/test.yaml"));
    y.dump(i, w);

    Yaml x = new Yaml();
    //x.setBeanAccess(BeanAccess.FIELD);
    FileReader r = new FileReader(new File("target/test.yaml"));
    Object z = x.loadAs(r, IncludeInfo.class);
    assertEquals(i, z);
  }
}
