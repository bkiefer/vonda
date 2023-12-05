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

import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.TypeException;
import de.dfki.mlt.rudimant.compiler.Visualize;
import de.dfki.mlt.rudimant.compiler.tree.ExpAssignment;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

public class TypeInferenceTest {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test() {
    String boolexp = ""
            + "public void foo() { b = 5 < 1; }";
    String s = generate(boolexp);
    String expected = "public void foo() { boolean b = 5 < 1;";
    assertEquals(expected, getForMarked(s, expected));


    // can't be tested like that because the memory is now handled correctly
    // and after leaving the last block is empty.
    /*
    parseAndTypecheck(boolexp);
    String type = rc.getMem().getVariableType("b");
    assertTrue(type.equals("boolean"));
    */
  }

  @Test
  public void testPredefFn() {
    String in = "boolean firstEncounter(); b = firstEncounter();";
    GrammarFile gf = parseAndTypecheck(in);
    Iterator<? extends RudiTree> it = gf.getDtrs().iterator();
    RudiTree rt = it.next();
    rt = it.next();
    // in empty setup, this is handled as a proper fielddef now
    // TODO: it was, and now again is not. Check the implications
    assertTrue(rt instanceof StatVarDef);
    //assertTrue(rt instanceof StatFieldDef);
    //rt = ((StatFieldDef)rt).varDef;
    ExpAssignment ass = (ExpAssignment)((StatVarDef)rt).toAssign;
    assertEquals("boolean", ass.left.type.toJava());
  }

  @Test
  public void test3() {
    String in = "QuizHistory getCurrentTurn();"
            + " turn = getCurrentTurn(); ";
    String s = generate(in);
    String expected = "public Rdf turn;/**/turn = getCurrentTurn();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public Rdf turn;"));
  }


  @Test
  public void test3a() {
    String in = "QuizHistory getCurrentTurn(); "
            + "Rdf turn = getCurrentTurn();";
    String s = generate(in);
    String expected = "public Rdf turn;/**/turn = getCurrentTurn();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public Rdf turn;"));
  }

  @Test
  public void test4() {
    String in = "Activity a; if (a) { }";
    String s = generate(in);
    String expected = "public Rdf a;/**/if (a != null) { }";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf a;"));
  }

  @Test(expected=TypeException.class)
  public void test5() throws Throwable {
    String in = "String foo(); "
            + "int b = foo(); ";
    String s = getTypeError(in);
    String expected = "b = foo();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public int b;"));
  }

  @Test
  public void test6() {
    String in = " boolean b = true; ";
    String s = generate(in);
    String expected = "public boolean b;/**/b = true;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("boolean b;"));
  }

  @Test
  public void test7() {
    String in = "public int b = 7; ";
    String s = generate(in);
    String expected = "public int b;/**/b = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("int b;"));
  }

  @Test
  public void test8() {
    String in = " QuizHistory q; if(q.correct) i = 7; ";
    String s = generate(in);
    String expected = "public Rdf q;/**/if (q != null && "
        + "exists(q.getBoolean(\"<dom:correct>\"))) int i = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf q;"));
  }

  @Test
  public void test9() {
    String in = "Quiz q;if(q.status) i = 7; ";
    String s = generate(in);
    String expected = "public Rdf q;/**/if (q != null && "
        + "exists(q.getString(\"<dom:status>\")))"
        + " int i = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf q;"));
  }

  /** TODO: Fix this problem (issue #55)
   * aw: wouldn't an entry like [Rdf]. boolean has(String sth) in Agent.rudi fix this?
  @Test
  public void testUnknownBoolean() {
    String in = "Rdf r; if(r.has(\"prop\")) i = 1;";
    String s = generate(in);
    String expected = "if ((r != null && r.has(\"prop\"))) int i = 1; ";
    assertEquals(expected, getForMarked(s, expected));
  }
  */

  @Test
  public void test10() {
    String in = " double f; void fun() { Person c; c.weight = 1.0; }";
    String s = generate(in);
    String expected = "public double f;public void fun() { Rdf c;c.setValue(\"<dom:weight>\", 1.0); }";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("double f;"));
  }

  @Test
  public void test11() throws Throwable {
    String in = " int fun(int i); long l = 1; k = fun(l);";
    getTypeError(in);
  }

  @Test
  public void test12() {
    String in = " long fun(long i); int l = 1; k = fun(l);";
    String s = generate(in);
    String expected = "public int l;public long k;/**/l = 1;k = fun(l);";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public int l;"));
    assertTrue(s.contains("public long k;"));
  }

  @Test(expected=TypeException.class)
  public void test13() throws Throwable {
    String in = " void fun(); l = fun();";
    getTypeError(in);
    int i = 0;
  }

  @Test
  public void testPartUnkDecl() {
    String in = "somevar = getSomething();" +
        "sum1 = 3 + somevar;sum2 = somevar + 3;";
    String s = generate(in);
    String expected = "somevar = getSomething();" +
        "sum1 = 3+somevar;sum2 = somevar+3;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public int sum1;"));
    assertTrue(s.contains("public int sum2;"));
  }

  @Test
  public void test14() {
  // if there are field accesses to unknown Java classes involved, we do want to believe
  // the user when it comes to type assumptions (!no casting to (Object /* (unknown) */) here)
    String in = " SomeUnk nownClass = new Someunk(); String v = nownClass.something.get(i);";
    String s = generate(in);
    String expected = "public SomeUnk nownClass;public String v;/**/nownClass = new Someunk();v = nownClass.something.get(i);";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public SomeUnk nownClass;"));
    assertTrue(s.contains("public String v;"));
  }

  @Test
  public void test15() {
    String in = "Set<String> slotBlacklist; if (slotBlacklist.contains(lastDA().agent)) {}";
    String s = generate(in);
    String expected = "public Set<String> slotBlacklist;/**/"
        + "if (exists(slotBlacklist) && slotBlacklist.contains(lastDA().getValue(\"agent\"))) { }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test16() {
    String in = "Child c; String da = random(c.surname);";
    String s = generate(in);
    String expected = "public Rdf c;public String da;/**/da = (String) random(c.getValue(\"<dom:surname>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test17() {
    String in = "Child c; Rdf a = random(c.hasHobby);";
    String s = generate(in);
    String expected = "public Rdf c;public Rdf a;/**/a = (Rdf) random(c.getValue(\"<dom:hasHobby>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }
  @Test
  public void test18() {
    String in = "SomeClass c; #SomeClass int somevar; s = c.somevar;";
    String s = generate(in);
    String expected = "public SomeClass c;public int s;/**/s = c.somevar;";
    assertEquals(expected, getForMarked(s, expected));
  }
}
