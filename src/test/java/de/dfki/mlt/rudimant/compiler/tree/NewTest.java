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

import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.Visualize;
import de.dfki.mlt.rudimant.compiler.tree.ExpNew;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

/**
 *
 * @author max
 */
public class NewTest {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testRdfType(){
    String src = "a = new Activity;";
    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(src));
    assertTrue(dtr instanceof ExpNew);
    assertTrue(((ExpNew)dtr).type.isRdfType());
  }

  @Test
  public void testJavaType() {
    String src = "i = new Integer();";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(src));
    assertTrue(dtr instanceof ExpNew);
    assertTrue(((ExpNew)dtr).type.toJava().equals("Integer"));
  }

  @Test
  public void testNewArray() {
    String src = "x = new Integer[7];";

    String s = generate(src);
    String expected = "Integer[] x = new Integer[7];";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testArrayInitialization() {
    String src = "int[] x = { 1, 2 };";

    String s = generate(src);
    String expected = "int[] x = {1, 2};";
    assertEquals(expected, getForMarked(s, expected));
  }
}
