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

import java.util.ArrayList;
import java.util.List;

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
    String in = "Set<Child> cs; b = some(cs, (c) -> ((Child)c).forename.equals(\"John\"));";
    String r = generate(in);
    String expected = "public Set<Rdf> cs;public boolean b;/**/b = some(cs, (c) -> "
            + "((String)((Rdf)c).getSingleValue(\"<dom:forename>\")).equals(\"John\"));";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Rdf> cs;"));
  }

  @Test
  public void testComplexLambdaExp() {
    // TODO: why do we test that b = disappears?
    String in = "Set<Child> cs; b = some(cs, (c) -> { return ((Child)c).forename.equals(\"John\"); });";
    String r = generate(in);
    String expected = "public Set<Rdf> cs;public boolean b;/**/b = some(cs, (c) -> { return"
            + " ((String)((Rdf)c).getSingleValue(\"<dom:forename>\")).equals(\"John\"); } );";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Rdf> cs;"));
  }


  @Test
  public void test1() {
    String in = "Quiz p; b = some(p.hasHistory, (c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public boolean b;/**/"
        + "b = some(((Set<Object>)p.getValue(\"<dom:hasHistory>\")), (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test2() {
    String in = "Quiz p; h = p.hasHistory; b = some(h, (c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public Set<Object> h;public boolean b;/**/h = ((Set<Object>)p.getValue(\"<dom:hasHistory>\"));"
        + "b = some(h, (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void test3() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Rdf> h;/**/h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test4() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1); x = h.get(1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Rdf> h;public Rdf x;/**/"
        + "h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);x = (Rdf) ((Rdf)h.get(1));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test5() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1); x = h.get(1); y = h.get(2);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Rdf> h;public Rdf x;public Rdf y;/**/"
        + "h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);"
        + "x = (Rdf) ((Rdf)h.get(1));y = (Rdf) ((Rdf)h.get(2));";
    assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void test5a() {
    String in = "Quiz p; h = filter(p.hasHistory, (QuizHistory c) -> c.turnId == 1); x = h.get(1); y = h.get(2);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Rdf> h;public Rdf x;public Rdf y;/**/"
        + "h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> ((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1);"
        + "x = (Rdf) ((Rdf)h.get(1));y = (Rdf) ((Rdf)h.get(2));";
    // TODO: FIX AND REACTIVATE: the cast is lost
    //assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test6a() {
    // TODO: THINK, SOLVE, AND REACTIVATE
    String in = "List<String> e; h = some(e, (f) -> f); x = h.get(1);";
    String expected = "public List<List<String>> l;public List<List<String>> h;public List<String> x;/**/"
        + "h = filter(l, (e) -> some(e, (f) -> !f.isEmpty()));x = h.get(1);";
    //String r = generate(in);
    //assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test6b() {
    // TODO: THINK, SOLVE, AND REACTIVATE
    String in = "List<List<String>> l; h = filter(l, (e) -> some(e, (f) -> f)); x = h.get(1);";
    String expected = "public List<List<String>> l;public List<List<String>> h;public List<String> x;/**/"
        + "h = filter(l, (e) -> some(e, (f) -> !f.isEmpty()));x = h.get(1);";
    //String r = generate(in);
    //assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testSome() {
    Type a = new Type("A");
    assertTrue(a.isTypeVariable());
    Type b = new Type("b");
    assertFalse(b.isTypeVariable());
    List<Type> subs = new ArrayList<>();
    subs.add(new Type("C"));
    Type c = new Type("Set", subs);
    assertFalse(c.isTypeVariable());
    subs = new ArrayList<>();
    subs.add(new Type("d"));
    Type d = new Type("Set", subs);
    assertFalse(d.isTypeVariable());
  }
}
