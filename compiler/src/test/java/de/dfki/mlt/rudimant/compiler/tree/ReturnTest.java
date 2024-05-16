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

import static de.dfki.mlt.rudimant.compiler.tree.TestHelper.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import org.junit.*;

public class ReturnTest {
  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void testReturn1(){
    String in = "public int fn() { return 1; }";
    String r = generate(in);
    String expected = "public int fn() { return 1; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn2(){
    String in = "foo: if (true) { return 1; }";
    String r = generate(in);
    String expected = "// Rule foo {boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); foo: if (__x0[0]){ return 1; } // Rule foo end } }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak1(){
    String in = "foo: if (true) { break foo; }";
    String r = generate(in);
    String expected = "// Rule foo {boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); foo: if (__x0[0]){ break foo; } // Rule foo end }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak2(){
    String in = "bar: if (false) { foo: if (true) { break foo; } }";
    String r = generate(in);
    String expected = "// Rule bar {"
        + "boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = false); logRule(0, __x0); "
        + "bar: if (__x0[0]){ // Rule foo "
        + "boolean[] __x1 = new boolean[2]; __x1[0] = (__x1[1] = true); logRule(1, __x1); "
        + "foo: if (__x1[0]){ break foo; } // Rule foo end } // Rule bar end }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak3(){
    String in = "foo: if (true) { break foo ; }";
    String r = generate(in);
    String expected = "// Rule foo {boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); foo: if (__x0[0]){ break foo; } // Rule foo end } }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak4(){
    String in = "foo: if (true) { bar: if (false) break bar ; }";
    String r = generate(in);
    String expected = "// Rule foo {boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); foo: if (__x0[0]){ // Rule bar boolean[] __x1 = new boolean[2]; __x1[0] = (__x1[1] = false); logRule(1, __x1); bar: if (__x1[0])break bar; // Rule bar end } // Rule foo end }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn7(){
    String in = "foo: if (true) { return test; }";
    String r = generate(in);
    String expected = "// Rule foo {boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); foo: if (__x0[0]){ return test; } // Rule foo end } }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn8(){
    String in = "if (true) { return test; }";
    String r = generate(in);
    String expected = "if (true) { return test; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak(){
    String in = "test_rule: if (true) { second_rule: if (false) { break test_rule; } }";
    String r = generate(in);
    String expected = "// Rule test_rule {"
        + "boolean[] __x0 = new boolean[2]; "
        + "__x0[0] = (__x0[1] = true); "
        + "logRule(0, __x0); "
        + "test_rule: if (__x0[0]){ "
        + "// Rule second_rule "
        + "boolean[] __x1 = new boolean[2]; __x1[0] = (__x1[1] = false); logRule(1, __x1); "
        + "second_rule: if (__x1[0]){"
        + " break test_rule; } // Rule second_rule end } // Rule test_rule end }";
    assertEquals(expected, getForMarked(r, expected));
  }


}
