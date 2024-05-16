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

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import de.dfki.mlt.rudimant.compiler.Type;
import de.dfki.mlt.rudimant.compiler.io.BisonParser;

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
    String in = "Set<Child> cs; b = some(cs, lambda(c) (isa(Child, c)).forename.equals(\"John\"));";
    String r = generate(in);
    String expected = "public Set<Rdf> cs;public boolean b;/**/b = some(cs, (c) -> "
            + "((Rdf)c).getString(\"<dom:forename>\").equals(\"John\"));";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Rdf> cs;"));
  }

  @Test
  public void testComplexLambdaExp() {
    // TODO: why do we test that b = disappears?
    String in = "Set<Child> cs; b = some(cs, lambda(c) { return (isa(Child, c)).forename.equals(\"John\"); });";
    String r = generate(in);
    String expected = "public Set<Rdf> cs;public boolean b;/**/b = some(cs, (c) -> { return"
            + " ((Rdf)c).getString(\"<dom:forename>\").equals(\"John\"); } );";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Rdf> cs;"));
  }


  @Test
  public void test1() {
    String in = "Quiz p; b = some(p.hasHistory, lambda(c) c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public boolean b;/**/"
        + "b = some(p.getValue(\"<dom:hasHistory>\"), (c) -> ((Rdf)c).getInteger(\"<dom:turnId>\") == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test2() {
    String in = "Quiz p; h = p.hasHistory; b = some(h, lambda(c) c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public Set<Object> h;public boolean b;/**/h = p.getValue(\"<dom:hasHistory>\");"
        + "b = some(h, (c) -> ((Rdf)c).getInteger(\"<dom:turnId>\") == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void test3() {
    String in = "Quiz p; h = filter(p.hasHistory, lambda(c) c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;/**/h = filter(p.getValue(\"<dom:hasHistory>\"),"
        + " (c) -> ((Rdf)c).getInteger(\"<dom:turnId>\") == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test4() {
    String in = "Quiz p; h = filter(p.hasHistory, lambda(c) c.turnId == 1); x = h.get(1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;/**/"
        + "h = filter(p.getValue(\"<dom:hasHistory>\"),"
        + " (c) -> ((Rdf)c).getInteger(\"<dom:turnId>\") == 1);"
        + "x = (Rdf) h.get(1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test5() {
    String in = "Quiz p; h = filter(p.hasHistory, lambda(c) c.turnId == 1); x = h.get(1); y = h.get(2);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;public Rdf y;/**/"
        + "h = filter(p.getValue(\"<dom:hasHistory>\"),"
        + " (c) -> ((Rdf)c).getInteger(\"<dom:turnId>\") == 1);"
        + "x = (Rdf) h.get(1);y = (Rdf) h.get(2);";
    assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void test5a() {
    String in = "Quiz p; h = filter(p.hasHistory, lambda(QuizHistory c)c.turnId == 1); x = h.get(1); y = h.get(2);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;public Rdf y;/**/"
        + "h = filter(p.getValue(\"<dom:hasHistory>\"),"
        + " (c) -> ((Rdf)c).getInteger(\"<dom:turnId>\") == 1);"
        + "x = (Rdf) h.get(1);y = (Rdf) h.get(2);";
    assertEquals(expected, getForMarked(r, expected));
  }

  //@Test
  // pathological, can never work.
  public void test6a() {
    String in = "List<String> e; h = filter(e, (f) -> f); x = h.get(1);";
    String expected = "public List<String> e;public List<String> h;public String x;/**/"
        + "h = filter(l, (e) -> some(e, (f) -> ((boolean)f)));x = h.get(1);";
    String r = generate(in);
    assertEquals(expected, getForMarked(r, expected));
  }

  //@Test
  // pathological, can never work.
  public void test6b() {
    String in = "List<List<String>> l; h = filter(l, (e) -> some(e, (f) -> f)); x = h.get(1);";
    String expected = "public List<List<String>> l;public List<List<String>> h;public List<String> x;/**/"
        + "h = filter(l, (e) -> some(e, (f) -> !f.isEmpty()));x = h.get(1);";
    String r = generate(in);
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test6c() {
    String in = "List<String> l; h = filter(l, lambda(f) !f.isEmpty());x = h.get(1);";
    String expected = "public List<String> l;public List<String> h;public String x;/**/"
        + "h = filter(l, (f) -> !(exists(f) && f.isEmpty()));x = h.get(1);";
    String r = generate(in);
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test6d() {
    String in = "List<List<String>> l; h = filter(l, lambda(e) some(e, lambda(f) !f.isEmpty())); x = h.get(1);";
    String r = generate(in);
    String expected = "public List<List<String>> l;public List<List<String>> h;public List<String> x;/**/"
        + "h = filter(l, (e) -> some(e, (f) -> !(exists(f) && f.isEmpty())));x = h.get(1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  // Still: wrong, since the cast ((Rdf)h).getClazz is missing if not given
  // explicitely
  public void test7() {
    String in = "Quiz p; List<Object> z = filter(p.hasHistory, lambda(h) (isa(Rdf, h) <= QuizHistory));";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> z;/**/z ="
      + " filter(p.getValue(\"<dom:hasHistory>\"),"
      + " (h) -> (((Rdf)h).getClazz().isSubclassOf(getRdfClass(\"QuizHistory\"))));}";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  // Still: wrong, since the cast ((Rdf)h).getClazz is missing if not given
  // explicitely
  public void test7a() {
    String in = "Quiz p; List<Object> z = filter(p.hasHistory, lambda(h) isa(Rdf, h) <= QuizHistory);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> z;/**/z ="
      + " filter(p.getValue(\"<dom:hasHistory>\"),"
      + " (h) -> ((Rdf)h).getClazz().isSubclassOf(getRdfClass(\"QuizHistory\")));}";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test7b() {
    String in = "Quiz p; List<Object> z = filter(p.hasHistory, lambda(h) h <= QuizHistory);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> z;/**/z ="
      + " filter(p.getValue(\"<dom:hasHistory>\"),"
      + " (h) -> ((Rdf)h).getClazz().isSubclassOf(getRdfClass(\"QuizHistory\")));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test7c() {
    String in = "Quiz p; z = filter(p.hasHistory, lambda(h) h <= QuizHistory);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> z;/**/z ="
      + " filter(p.getValue(\"<dom:hasHistory>\"),"
      + " (h) -> ((Rdf)h).getClazz().isSubclassOf(getRdfClass(\"QuizHistory\")));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test7d() {
    String in = "Integer h; Quiz p; z = filter(p.hasHistory, lambda(h) h <= QuizHistory);";
    String r = generate(in);
    String expected = "public Integer h;public Rdf p;public List<Object> z;/**/z ="
      + " filter(p.getValue(\"<dom:hasHistory>\"),"
      + " (h) -> ((Rdf)h).getClazz().isSubclassOf(getRdfClass(\"QuizHistory\")));";
    assertEquals(expected, getForMarked(r, expected));
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
