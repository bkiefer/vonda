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

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class VarDeclarationTest {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testPOD() {
    String decl = " int i; if (true) { i = i + 1; }";
    String s = generate(decl);
    String expected = "int i;if (true) { i = i+1; }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testRdf() {
    String decl = " Child i; if (true) { i = new Child; }";
    String s = generate(decl);
    String expected_dom = "Rdf i;if (true) { i = _proxy.getClass(\"<dom:Child>\").getNewInstance(DEFNS); }";
    String marked = getForMarked(s, expected_dom);
    marked = marked.replaceAll("<edu:", "<dom:");
    assertEquals(expected_dom, marked);
  }

}
