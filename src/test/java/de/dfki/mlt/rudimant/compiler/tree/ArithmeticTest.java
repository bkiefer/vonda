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

package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.tree.*;

/**
 *
 * @author max
 */
public class ArithmeticTest {

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUpNonEmpty();
  }

  public void assertArithmetic(RudiTree dtr) {
    assertTrue(dtr instanceof StatVarDef);
    dtr = ((StatVarDef)dtr).toAssign;
    assertTrue(dtr instanceof ExpAssignment);
    dtr = ((ExpAssignment)dtr).right;
    assertTrue(dtr instanceof ExpArithmetic);
  }

  @Test
  public void testArithmetic() throws IOException {
    String arithmeticExp = "int i = 1 + 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(getInput(arithmeticExp)));
    assertArithmetic(dtr);
  }

  @Test
  public void testArithmetic2() throws IOException {
    String arithmeticExp = "int i = 1 * 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(getInput(arithmeticExp)));
    assertArithmetic(dtr);
  }

  @Test
  public void testArithmetic3() throws IOException {
    String arithmeticExp = "int i = 1 / 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(getInput(arithmeticExp)));
    assertArithmetic(dtr);
  }

  @Test
  public void testArithmetic4() throws IOException {
    String arithmeticExp = "int i = 1 - 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(getInput(arithmeticExp)));
    assertArithmetic(dtr);

    dtr = ((StatVarDef)dtr).toAssign;
    dtr = ((ExpAssignment)dtr).right;
    String type = ((ExpArithmetic) dtr).getType().toJava();
    assertEquals("type of 1 - 2", "int", type);
  }

  @Test
  public void testArith6() {
    String intExp = "int i = (3 & 1 | 8);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(intExp));
    assertArithmetic(dtr);
  }

  @Test
  public void testAssignment5() throws IOException {
    String in = "Activity a; a.status = \"proposed\";";
    GrammarFile gf = parseAndTypecheck(getInput(in));
    RudiTree dtr = getNodeOfInterest(gf, 0);
    assertTrue(dtr instanceof StatVarDef);
    assertEquals("Activity", ((StatVarDef)dtr).type.get_name());
    assertEquals("Rdf", ((StatVarDef)dtr).type.toJava());
    dtr = getNodeOfInterest(gf, 1);
    assertTrue(dtr instanceof ExpAssignment);
    assertEquals("String", ((ExpAssignment)dtr).type.toJava());
  }

  @Test
  public void testArithmetic5() {
    String in = "int i = -(1 + 2);";
    String r = generate(in);
    String expected = "int i = -(((1+2)));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testArithmetic6() {
    String in = "Child user; user.hasIntimacyLevel -= 0.2;";
    String r = generate(in);
    String expected = "Rdf user;user.setValue(\"<dom:hasIntimacyLevel>\","
            + " (((Double)user.getSingleValue(\"<dom:hasIntimacyLevel>\"))-0.2));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testArithmetic7() {
    String in = "int x; x += 3;";
    String r = generate(in);
    String expected = "int x;x = (x+3);";
    assertEquals(expected, getForMarked(r, expected));
  }
}
