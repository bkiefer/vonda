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

import de.dfki.mlt.rudimant.compiler.TypeException;

/**
 *
 * @author anna
 */
public class TypesTest {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void testType1(){
    String in = " DialogueAct reply = myLastDA().copy();";
    String r = generate(in);
    String expected = "public DialogueAct reply;/**/reply = myLastDA().copy();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("DialogueAct reply;"));
  }

  @Test
  public void testType2(){
    String in = "QuizHistory getCurrentTurn(); Rdf turn = getCurrentTurn(activity);";
    String r = generate(in);
    String expected = "public Rdf turn;/**/turn = getCurrentTurn(activity);";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Rdf turn;"));
  }

  @Test
  public void testType3(){
    String in = "QuizHistory getCurrentTurn();  propose(\"continue_quiz\") {\n" +
              "      Rdf turn = getCurrentTurn(activity);}";
    String r = generate(in);
    String expected = "/**/propose(\"continue_quiz\",new Proposal() {"
            + "public void run() {"
            + " Rdf turn = getCurrentTurn(activity); } });";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testType4(){
    String in = " int correct = q.getWhichCorrect();";
    String r = generate(in);
    String expected = "public int correct;/**/correct = q.getWhichCorrect();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("int correct;"));
  }

  @Test
  public void testType5(){
    String in = "List<String> a = new List<String>();";
    String r = generate(in);
    String expected = "public List<String> a;/**/a = new List<String>();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("List<String> a;"));
  }

  @Test
  public void testType6(){
    String in = "if(true) { String s = null;}";
    String r = generate(in);
    String expected = "if (true) { String s = null;";
    assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void testType7(){
    String in = "String s = \"something\"; boolean empty = s;";
    String r = generate(in);
    String expected = "public String s;public boolean empty;/**/s = \"something\";empty = exists(s);";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("String s"));
    assertTrue(r.contains("boolean empty"));
  }

  @Test
  public void testReturnSetType() {
    String in = "Child c;  docs = c.hasHobby;";
    String r = generate(in);
    String expected = "public Rdf c;public Set<Object> docs;/**/docs = c.getValue(\"<dom:hasHobby>\");";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Object> docs;"));
    assertTrue(r.contains("Rdf c;"));
  }

  @Test
  public void testListRdftype() {
    String in = "List<Quiz> q = {};";
    String r = generate(in);
    String expected = "public List<Rdf> q;/**/q = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("List<Rdf> q;"));
  }

  @Test
  public void testInitDefinedCollection() {
    String in = "List<Quiz> q; q = {};";
    String r = generate(in);
    String expected = "public List<Rdf> q;/**/q = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("List<Rdf> q;"));
  }

  @Test
  public void testInitDefinedCollection2() {
    String in = "List<Quiz> q; void f() { q = {}; }";
    String r = generate(in);
    String expected = "public List<Rdf> q;public void f() { q = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("List<Rdf> q;"));
  }

  @Test
  public void testBooleanRdfExists() {
    String in = "Child c; if (!c.hasFather) int i = 0;";
    String r = generate(in);
    String expected =
        "public Rdf c;/**/if (!(c != null && c.getRdf(\"<dom:hasFather>\") != null)) int i = 0;";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Rdf c;"));
  }

  @Test
  public void testBooleanRdfExists2() {
    String in = "Child c; void a() { if (!c.hasFather) int i = 0; }";
    String r = generate(in);
    String expected =
        "public Rdf c;public void a() { if (!(c != null && "
        + "c.getRdf(\"<dom:hasFather>\") != null)) int i = 0; }";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Rdf c;"));
  }

  @Test
  public void testWrongMapperAccess() {
    String in = "Child getChild(int i); List<Child> out() { List<Child> raw = { }; " +
        "for (int i = 1; i < 5 && raw.size() < 3; i++) " +
        " { Child w = getChild(i); if (w) raw += w; } return raw; }";
    String r = generate(in);
    String expected =
        "public List<Rdf> out() { List<Rdf> raw = new ArrayList<>();"
        + "for ( int i = 1;i < 5 && raw.size() < 3;i++){"
        + " Rdf w = getChild(i);if (w != null) raw.add(w); } return raw;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testCast5() {
    String in = "t = isa(QuizHistory, a);";
    String r = generate(in);
    String exp = "t = ((Rdf)a);";
    assertEquals(exp, getForMarked(r, exp));
    assertTrue(r.contains("Rdf t;"));
  }

  @Test
  public void testCast6() {
    String methdecl = " void foo(List<Child> cs) { c = cs.get(0); }";
    String s = generate(methdecl);
    String expected = "public void foo(List<Rdf> cs) { Rdf c = cs.get(0); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast7() {
    String methdecl = " void foo(List<Child> cs) { s = cs.get(0).forename; }";
    String s = generate(methdecl);
    String expected = "public void foo(List<Rdf> cs)"
        + " { String s = cs.get(0).getString(\"<dom:forename>\"); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast8() {
    String methdecl = "Collection<Child> foo(Quiz m) {"
        + " map(m.hasHistory, lambda(f) (isa(QuizHistory, f)).hasChildId); }";
    String s = generate(methdecl);
    String expected = "public Collection<Rdf> foo(Rdf m)"
        + " { map(m.getValue(\"<dom:hasHistory>\"), (f) -> ((Rdf)f).getRdf(\"<dom:hasChildId>\")); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast8a() {
    String methdecl = "Collection<Child> foo(Quiz m) {"
        + " map(m.hasHistory, lambda(f) { return (isa(QuizHistory, f)).hasChildId; }); }";
    String s = generate(methdecl);
    String expected = "public Collection<Rdf> foo(Rdf m)"
        + " { map(m.getValue(\"<dom:hasHistory>\"), (f) -> { return ((Rdf)f).getRdf(\"<dom:hasChildId>\"); } ); }"
        + "";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testArrayAccess1() {
    String methdecl = "int[] test; x = test[1];";
    String s = generate(methdecl);
    String expected = "public int[] test;public int x;/**/x = test[1]";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test(expected=TypeException.class)
  public void testArrayAccess2() throws Throwable {
    // this should complain that a non-array type is accessed in an array way
    String in = "String test; x = test[1];";
    //String s = generate(in);
    getTypeError(in);
  }

  //
  @Test(expected=TypeException.class)
  public void testVoidFunction() throws Throwable {
    String in = " void fun(); k = fun();";
    getTypeError(in);
  }

  @Test(expected=TypeException.class)
  public void testFunctionCall1() throws Throwable {
    String in = " void fun(String s); fun(3);";
    getTypeError(in);
  }

  @Test(expected=TypeException.class)
  public void testFunctionCall2() throws Throwable {
    String in = " void fun(String[] s); fun(3);";
    getTypeError(in);
  }

  @Test(expected=TypeException.class)
  public void testFunctionCall3() throws Throwable {
    String in = " void fun(String[] s); fun(\"test\");";
    getTypeError(in);
  }

  @Test(expected=TypeException.class)
  public void testFunctionCall4() throws Throwable {
    String in = " void fun(Set<Integer> s); fun(\"test\");";
    getTypeError(in);
  }

  @Test
  public void testFunctionCall5() throws Throwable {
    String in = "p = myLastDA().getProposition();";
    String s = generate(in);
    // String p; should be found somewhere in the class, but not here (global
    // initialization)
    String expected = "p = myLastDA().getProposition();";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testFunctionCall6() throws Throwable {
    String in = "void fun() { p = myLastDA().getProposition();}";
    String s = generate(in);
    String expected = "public void fun() { String p = myLastDA().getProposition(); }";
    assertEquals(expected, getForMarked(s, expected));
  }
}
