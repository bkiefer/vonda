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

import java.io.FileNotFoundException;

import org.junit.*;

import de.dfki.mlt.rudimant.compiler.TypeException;

public class CastTest {

  static String header = "boolean firstEncounter(); label: if(true) {"
          + "// hello test\n";
  static String footer = "// end of test\n}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "tests.yml", header, footer);
  }

  @Test
  public void testCast1() {
    String in = "Quiz c; c.hasHistory.add(new QuizHistory);";
    String s = generate(in);
    String expected = "Rdf c;((Set<Object>)c.getValue(\"<dom:hasHistory>\"))"
        + ".add(_proxy.getClass(\"<dom:QuizHistory>\").getNewInstance(DEFNS));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast1a() {
    String in = "Quiz c; c.hasHistory += new QuizHistory;";
    String s = generate(in);
    String expected = "Rdf c;((Set<Object>)c.getValue(\"<dom:hasHistory>\"))"
            + ".add(_proxy.getClass(\"<dom:QuizHistory>\").getNewInstance(DEFNS));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast2() {
    String in = "QuizHistory turn; boolean correct = turn.correct;";
    String s = generate(in);
    String expected = "Rdf turn;boolean correct = "
        // TODO: THIS IS CURRENTLY MISSING
        //+ "turn != null && exists(((Set<Object>)turn.getValue() "
        + "((Boolean)turn.getSingleValue(\"<dom:correct>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast3() {
    String in = "String th = myLastDA().theme;";
    String s = generate(in);
    String expected = "String th = ((String)myLastDA().getValue(\"theme\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast4() {
    String in = "Child activity; String currentAsker = \"bla\"; if "
            + "(activity.forename != currentAsker) {}";
    String s = generate(in);
    String expected = "Rdf activity;String currentAsker = \"bla\";if (! "
        + "(((String)activity.getSingleValue(\"<dom:forename>\"))"
        + ".equals(currentAsker))) { }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test1() {
    String in = "QuizHistory turn = new QuizHistory;";
    String s = generate(in);
    String expected = "Rdf turn ="
        + " _proxy.getClass(\"<dom:QuizHistory>\").getNewInstance(DEFNS);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test2() {
    String in = "QuizHistory turn; int agt; turn.turnId = agt;";
    String s = generate(in);
    String expected = "Rdf turn;int agt;turn.setValue(\"<dom:turnId>\", agt);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test3() {
    String in = "Activity activity; activity.status = \"gameProposed\";";
    String s = generate(in);
    String expected = "Rdf activity;activity.setValue(\"<dom:status>\", \"gameProposed\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test4() {
     String in = "Activity activity; bool = (activity.status == \"gameProposed\");";
    String s = generate(in);
    String expected = "Rdf activity;boolean bool = (((String)activity"
            + ".getSingleValue(\"<dom:status>\")).equals(\"gameProposed\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test5() {//firstEncounter() vor label if (siehe getGeneration) einfügen (darf nicht im true deklariert werden)
    String in = "daType = firstEncounter() ? \"a\" : \"b\";";
    String s = generate(in);
    String expected = "String daType = (firstEncounter() ? \"a\" : \"b\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test6() {
    String in = "Child user; b = user.hasFather == toRdf(\"<dom:Father01>\");";
    String s = generate(in);
    String expected = "Rdf user;boolean b = ((Rdf)user.getSingleValue(\"<dom:hasFather>\"))"
        + ".equals(toRdf(\"<dom:Father01>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test6a() {
    String in = "Child user; hobbies = user.hasHobby;";
    String s = generate(in);
    String expected = "Rdf user;Set<Object> hobbies = ((Set<Object>)user"
            + ".getValue(\"<dom:hasHobby>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test7() {
    String in = "Child user; b = (user.forename == \"John\");";
    String s = generate(in);
    String expected = "Rdf user;boolean b = (((String)user"
            + ".getSingleValue(\"<dom:forename>\")).equals(\"John\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test8() {
    String in = "Child user; user.forename = \"John\";";
    String s = generate(in);
    // It's always setValue, even if the property is functional
    String expected = "Rdf user;user.setValue(\"<dom:forename>\", \"John\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test9() {
    String in = "Object foo; foo.slot = 1;";
    String s = generate(in);
    String expected = "Object foo;foo.slot = 1;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test10() {
    String in = "DialogueAct a; DialogueAct b; boolean isSmaller; isSmaller = a<b;";
    String s = generate(in);
    String expected = "DialogueAct a;DialogueAct b;boolean isSmaller;isSmaller = a.strictlySubsumes(b);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test(expected=TypeException.class)
  public void test11() throws Throwable {
    // TODO: find out whether i is int or boolean at the end, and put that into the
    // documentation: NO, this is a REDEFINITION
    String in = "int i; boolean i; i = 2; ";
    String s = getTypeError(in);
    String expected = "int i;boolean i; i = 2;";
    //assertEquals(expected, getForMarked(s, expected));
  }

  @Test(expected=TypeException.class)
  public void test12() throws Throwable{
    // TODO: When i is int, this is definitely an error in java; how to deal with it?
    //      in this case, a type error is thrown; we should test that here
    String in = "int i; i = false;";
    String s = getTypeError(in);
    // String expected = "i = (Object /* (unknown) */) false;";
    // assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test13() {
    String in = "DialogueAct a; DialogueAct b; boolean isGreater; isGreater = a>b;";
    String s = generate(in);
    String expected = "DialogueAct a;DialogueAct b;boolean isGreater;isGreater = a.isStrictlySubsumedBy(b);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test14(){
    // Test cast with parameterized types
    String in = "LinkedList<String> b; LinkedList<String> l = (LinkedList<String>) b;";
    String s = generate(in);
    String expected = "LinkedList<String> b;LinkedList<String> l = ((LinkedList<String>)b);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test15() {
    String in = "Agent c; String s = ((Child)c).forename;";
    String s = generate(in);
    String expected = "Rdf c;String s = ((String)((Rdf)c).getSingleValue(\"<dom:forename>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  // TODO: CONSTRUCT A TEST EXAMPLE WITH AT LEAST THREE RDF ACCESSES
  @Test
  public void testMultipleRdfAccess() {
    String in = "QuizHistory c ; b = c.hasChildId.hasFather.forename;";
    String s = generate(in);
    String expected = "Rdf c;String b = ((String)((Rdf)((Rdf)c"
            + ".getSingleValue(\"<dom:hasChildId>\"))"
            + ".getSingleValue(\"<dom:hasFather>\"))"
            + ".getSingleValue(\"<dom:forename>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  /* TODO: find a long Rdf sequence in current ontology to do this test
  @Test
public void testMultipleRdfAccess2() {
  // Test set field with POD type
  String in = "Clazz c; if (c.a.a.a.b) return true;";
  String s = generate(in);
  String expected = "Rdf c;if (((((c != null "
      + "&& ((Rdf)c.getSingleValue(\"<dom:a>\")) != null) "
      + "&& ((Rdf)((Rdf)c.getSingleValue(\"<dom:a>\")).getSingleValue(\"<dom:a>\")) != null) "
      + "&& ((Rdf)((Rdf)((Rdf)c.getSingleValue(\"<dom:a>\")).getSingleValue(\"<dom:a>\")).getSingleValue(\"<dom:a>\")) != null) "
      + "&& exists(((Integer)((Rdf)((Rdf)((Rdf)c.getSingleValue(\"<dom:a>\"))"
      + ".getSingleValue(\"<dom:a>\")).getSingleValue(\"<dom:a>\"))"
      + ".getSingleValue(\"<dom:b>\"))))) return true;";
  assertEquals(expected, getForMarked(s, expected));
}
*/

  /* TODO: FIX THE TEST/CODE ITSELF, NOT SURE IF THE INPUT IS LEGAL AT ALL.
  @Test
  public void test666() {
    String in = "QuizHistory turn; turn.responder = (agt == user) ? I_ROBOT : user;";
    String r = getGeneration(in);
    String exp = " turn.setValue(\"<dom:responder>\", (isEqual(agt, user) ? I_ROBOT : user)); ";
    assertEquals(exp, r);
  }
  */

  @Test
  public void testSetPOD() {
    // Test set field with POD type
    String in = "QuizHistory c; c.turnId = 10;";
    String s = generate(in);
    String expected = "Rdf c;c.setValue(\"<dom:turnId>\", 10)";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testRdfCast() {
    // Test set field with POD type
    String in = "if (((Rdf)d) <= Child) return true;";
    String s = generate(in);
    String expected = "if (((Rdf)d).getClazz().isSubclassOf(getRdfClass(\"Child\"))) return true;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testStringAdd1() {
    // Test set field with POD type
    String in = "String c = \"foo\"; c = c + 10;";
    String s = generate(in);
    String expected = "String c = \"foo\";c = c+Integer.toString(10);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testStringAdd2() {
    // Test set field with POD type
    String in = "String c = \"foo\"; c = 10 + c;";
    String s = generate(in);
    String expected = "String c = \"foo\";c = Integer.toString(10)+c;";
    assertEquals(expected, getForMarked(s, expected));
  }
  @Test
  public void testStringAdd3() {
    // Test set field with Rdf type
    String in = "Child c; String s = \"foo\" + c;";
    String s = generate(in);
    String expected = "Rdf c;String s = \"foo\"+c.toString();";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testStringAdd4() {
    // Test set field with POD type
    String in = "String c = \"foo\"; c = 10.0 + c;";
    String s = generate(in);
    String expected = "String c = \"foo\";c = Double.toString(10.0)+c;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testToStringAssign() {
    // Test set string var with POD type
    String in = "String c = 10.0;";
    String s = generate(in);
    String expected = "String c = Double.toString(10.0);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testToStringAssignFloat() {
    // Test set string var with POD type
    String in = "String c = 10.0f;";
    String s = generate(in);
    String expected = "String c = Float.toString(10.0f);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testToStringAssignLong() {
    // Test set string var with POD type
    String in = "String c = 10l;";
    String s = generate(in);
    String expected = "String c = Long.toString(10l);";
    assertEquals(expected, getForMarked(s, expected));
  }

}
