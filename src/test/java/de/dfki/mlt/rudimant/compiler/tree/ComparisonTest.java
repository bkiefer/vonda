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

import org.junit.*;

/**
 *
 * @author anna
 */
public class ComparisonTest {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testGenerationComparisonPODPOD() {
    String in = "int i; long j; if (i == j) return true;";
    String r = generate(in);
    String expected = "int i;long j;if (i == j) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonPODContainer() {
    String in = "int i; Long j; if (i == j) return true;";
    String r = generate(in);
    String expected = "int i;Long j;if (i == j) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringString() {
    String in = "String i; String j; if (i <= j) return true;";
    String r = generate(in);
    String expected = "String i;String j;if (i.compareTo(j) <= 0) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringDA() {
    // maybe check all variants of comparison operators in the end
    String in = "String i; if (i <= #Accept(Return)) return true;";
    String r = generate(in);
    String expected = "String i;if (new DialogueAct(i).subsumes(new DialogueAct(\"Accept\", \"Return\"))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringRdf() {
    String in = "String i; Child j; if (i <= j) return true;";
    String r = generate(in);
    String expected = "String i;Rdf j;if (i.getClazz().isSubclassOf(j.getClazz())) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringRdfClass() {
    String in = "String j; if (Child <= j) return true;";
    String r = generate(in);
    String expected = "String j;if (getRdfClass(\"Child\").isSubclassOf(j.getClazz())) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonRdfClassRdfClass() {
    String in = "if (Child >= Object) return true;";
    String r = generate(in);
    String expected = "if (getRdfClass(\"Child\").isSuperclassOf(getRdfClass(\"Object\"))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonRdfClassRdfClassSub() {
    String in = "if (Child <= Object) return true;";
    String r = generate(in);
    String expected = "if (getRdfClass(\"Child\").isSubclassOf(getRdfClass(\"Object\"))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonRdfRdf() {
    // other operators than == don't make sense here.
    String in = "Child a; Child b; if (a == b) return true;";
    String r = generate(in);
    String expected = "Rdf a;Rdf b;if (a.equals(b)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonRdfRdfClass() {
    String in = "Child j; if (Child <= j) return true;";
    String r = generate(in);
    String expected = "Rdf j;if (getRdfClass(\"Child\").isSubclassOf(j.getClazz())) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonDADA() {
    // maybe check all variants of comparison operators in the end
    String in = "DialogueAct d; if (d >= #Accept(Return)) return true;";
    String r = generate(in);
    String expected = "DialogueAct d;if (d.isSubsumedBy(new DialogueAct(\"Accept\", \"Return\"))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationRdfExists() {
    // other operators than == don't make sense here.
    String in = "Child a; if (a.hasFather) return true;";
    String r = generate(in);
    String expected = "Rdf a;if (a != null &&"
        + " a.getRdf(\"<dom:hasFather>\") != null) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationIntegerExists() {
    // other operators than == don't make sense here.
    String in = "final Child a; if (a.age) return true;";
    String r = generate(in);
    String expected = "final Rdf a;if (a != null && "
        + "exists(a.getInteger(\"<dom:age>\"))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationXsdDateExists() {
    // other operators than == don't make sense here.
    String in = "Child a; if (a.birthdate) return true;";
    String r = generate(in);
    String expected = "Rdf a;if (a != null && a.getDate(\"<dom:birthdate>\") != null) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationStringExists() {
    // other operators than == don't make sense here.
    String in = "final Activity a; if (a.status) return true;";
    String r = generate(in);
    String expected = "final Rdf a;if (a != null && exists(a.getString(\"<dom:status>\"))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationNotEqual() {
    // other operators than == don't make sense here.
    String in = "Activity a; if (a.status != \"foo\") return true;";
    String r = generate(in);
    String expected = "Rdf a;if (! (a.getString(\"<dom:status>\").equals(\"foo\"))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationNotEqual2() {
    // other operators than == don't make sense here.
    String in = "final Child c; if (c.hasBrother != null) return true;";
    String r = generate(in);
    String expected = "final Rdf c;if (c.getValue(\"<dom:hasBrother>\") != null) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }}
