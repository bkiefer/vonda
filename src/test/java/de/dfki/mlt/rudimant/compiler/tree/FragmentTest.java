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

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.TypeException;
import de.dfki.mlt.rudimant.compiler.tree.ExpConditional;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

public class FragmentTest {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void test1() {
    String conditionalExp = "true ? 1 : 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpConditional);

    String type_boolean = ((ExpConditional) dtr).boolexp.getType().toJava();
    assertEquals("boolean part of conditional", "boolean", type_boolean);

    String type_then = ((ExpConditional) dtr).thenexp.getType().toJava();
    assertEquals("then part", "int", type_then);

    String type_else = ((ExpConditional) dtr).elseexp.getType().toJava();
    assertEquals("else part", "int", type_else);
  }

  @Test
  public void testFinal1() {
    String fin = "final boolean b = true;";
    String s = generate(fin);
    String expected = "final boolean b = true;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testFinal2() {
    String fin = "final b = true;";
    String s = generate(fin);
    String expected = "final boolean b = true;";
    assertEquals(expected, getForMarked(s, expected));
  }

  // TODO: do we really need to check this?
  /*
  @Test(expected=TypeException.class)
  public void testFinal3() throws Throwable {
    String fin = "final boolean b = true; b = false;";
    getTypeError(fin);
  }*/
}
