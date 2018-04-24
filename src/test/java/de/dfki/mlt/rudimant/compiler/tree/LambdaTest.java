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

import java.util.Set;

import org.junit.*;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 *
 * @author christophe
 */
public class LambdaTest {
  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  /*
    lambda_exp:
    '(' (DEC_VAR? VARIABLE (',' DEC_VAR? VARIABLE)*)? ')' ARROW exp;
   */

  @Test
  public void testLambdaExp() {
    String in = "Set<Child> cs; b = cs.contains((c) -> ((Child)c).forename.equals(\"John\"));";
    String r = generate(in);
    String expected = "public Set<Rdf> cs;public boolean b;/**/b = cs.contains((c) -> "
            + "((String)((Rdf)c).getSingleValue(\"<dom:forename>\")).equals(\"John\"));";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Rdf> cs;"));
  }

  @Test
  public void testComplexLambdaExp() {
    // TODO: why do we test that b = disappears?
    String in = "Set<Child> cs; b = cs.contains((c) -> { return ((Child)c).forename.equals(\"John\"); });";
    String r = generate(in);
    String expected = "public Set<Rdf> cs;public boolean b;/**/b = cs.contains((c) -> { return"
            + " ((String)((Rdf)c).getSingleValue(\"<dom:forename>\")).equals(\"John\"); } );";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Rdf> cs;"));
  }


  @Test
  public void test1() {
    String in = "Quiz p; b = p.hasHistory.contains((c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;/**/((Set<Object>)p.getValue(\"<dom:hasHistory>\"))"
        + ".contains((c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);";
    // TODO: REACTIVATE
    //assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test2() {
    String in = "Quiz p; h = p.hasHistory; b = h.contains((c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public Set<Object> h;/**/h = ((Set<Object>)p.getValue(\"<dom:hasHistory>\"));"
        + "h.contains((c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);";
    // TODO: REACTIVATE
    //assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void test3() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;/**/h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test4() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1); x = h.get(1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;/**/h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);x = (Rdf) ((Rdf)h.get(1));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test5() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1); x = h.get(1); y = h.get(2);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;public Rdf y;/**/h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);x = (Rdf) ((Rdf)h.get(1));y = (Rdf) ((Rdf)h.get(2));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test6() {
    String in = "List<List<String>> l; h = filter(l, (e) -> contains(e, (f) -> f)); x = h.get(1);";
    String r = generate(in);
    String expected = "public List<List<String>> l;public List<String> h;public String x;/**/"
        + "h = filter(l, (e) -> contains(e, (f) -> !f.isEmpty));x = h.get(1);";
    // assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void testSome() {
    Type a = new Type("A");
    assertTrue(a.isUnaryGeneric());
    Type b = new Type("b");
    assertFalse(b.isUnaryGeneric());
    Type c = new Type("Set<C>");
    assertTrue(c.containsGeneric());
    assertFalse(c.isUnaryGeneric());
    Type d = new Type("Set<d>");
    assertFalse(d.containsGeneric());
  }

/* TODO: should this work or not?
  @Test
  public void test5() {
    String in = "Quiz p; h = p.hasHistory.filter((c) -> c.turnId == 1); x = h.get(1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;"
        + "/*h = ((Set<Object>)p.getValue(\"<dom:hasHistory>\")).filter("
        + " (c) -> (((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1)); x = h.get(1);";
    assertEquals(expected, getForMarked(r, expected));
  }
*/
}
