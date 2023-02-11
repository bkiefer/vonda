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

import org.junit.*;

import de.dfki.mlt.rudimant.compiler.tree.ExpIdentifier;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;
import de.dfki.mlt.rudimant.compiler.tree.StatReturn;

/**
 *
 * @author sophie
 */
public class StatementTest {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void StatementTest1() {
    String in = "Object foo;"
            + "while (foo.slot == 1){foo.slot = 2;}";
    String r = generate(in);
    String expected = "Object foo;while (foo.slot == 1){ foo.slot = 2; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest2(){
    String in = " List<String> slots = { \"Hobby\", \"Color\" };"
            + " for (String slot : slots){}";
    String r = generate(in);
    String expected = "List<String> slots = new ArrayList<>();slots.add(\"Hobby\"); "
        + "slots.add(\"Color\"); "
        + "for (Object slot_outer : slots) { String slot = (String)slot_outer; { } }";
    assertEquals(expected, getForMarked(r, expected));
    // TODO: make this work again, it was nicer
//    assertEquals("List<String> slots = new ArrayList<>();slots.add(\"Hobby\"); "
//        + "slots.add(\"Color\"); "
//        + "for (String slot_outer : slots) { String slot = (String)slot_outer; {}}", r);
  }


  @Test
  public void StatementTest3(){
    String in = "Object foo;  if (foo.slot == 1){"
            + "foo.slot = 2;} else {foo.slot = 1;}";
    String r = generate(in);
    String expected = "Object foo;if (foo.slot == 1) { foo.slot = 2; } else { foo.slot = 1; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest4(){
    String in = "Object foo;  "
            + "do {foo.slot = 2;} while (foo.slot == 1)";
    String r = generate(in);
    String expected = "Object foo;do{ foo.slot = 2; } while (foo.slot == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest5(){
    // explanation: the statement "int i;" is supposed to tell rudimant that
    // the variable i already exists somewhere
    String in = "int i;  i = 1; return i;";
    String r = generate(in);
    String expected = "int i;i = 1;return i;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest5Iterator(){
    String in = "int i;  i = 1; return i;";
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(in), 2);
    assertTrue(dtr instanceof StatReturn);
    assertTrue(((StatReturn)dtr).getDtrs().iterator().next()
        instanceof ExpIdentifier);
  }

 @Test
 public void StatementTest6(){
   // TODO: the empty statement after case is not wrong, but it is kind of funny
   String in = "int i; boolean truth; "
       + "switch(truth){case true: i=2;  case false: i=1;}";
   String r = generate(in);
   String expected = "int i;boolean truth;switch (truth){ case true:; "
       + "i = 2;case false:; i = 1; }";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void StatementTest7(){
   String in = "List<String> a = {};";
    String r = generate(in);
    String expected = "List<String> a = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
 }

 @Test
  public void StatementTestx(){
    String in = "Object foo;  "
            + "for (int i = 1; i <= 2; i = i+1){foo.slot = 1;}";
   String r = generate(in);
   String expected = "Object foo;for ( int i = 1;i <= 2;i = i+1){ foo.slot = 1; }";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
  public void TimeoutTest(){
   String in = "time = 2; timeout(\"label\", time) { i = 4; }";
   String r = generate(in);
   String expected = "int time = 2;"
       + "newTimeout(\"label\",time,new Proposal() {public void run() { int i = 4; } });";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
  public void BehaviourTimeoutTest1(){
   String in = "time = 2; timeout(#Inform(Test), time) { i = 4; }";
   String r = generate(in);
   String expected = "int time = 2;"
       + "behaviourTimeout(new DialogueAct(\"Inform\", \"Test\"),time,new Proposal() {public void run() { int i = 4; } });";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void BehaviourTimeoutTest2(){
  String in = "time = 2; da = #Inform(Test); timeout(da, time) { i = 4; }";
  String r = generate(in);
  String expected = "int time = 2;DialogueAct da = new DialogueAct(\"Inform\", \"Test\");"
      + "behaviourTimeout(da,time,new Proposal() {public void run() { int i = 4; } });";
  assertEquals(expected, getForMarked(r, expected));
}

 @Test
 public void IfTest1(){
   String in = "String s = \"bla\"; if (s == null) {}";
   String r = generate(in);
   String expected = "String s = \"bla\";if (s == null) {";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void IfTest2(){
   String in = "String s = \"bla\"; if (s != null) {}";
   String r = generate(in);
   String expected = "String s = \"bla\";if (s != null) {";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void IfTest3(){
   String in = "String s = \"bla\"; if (s) {}";
   String r = generate(in);
   String expected = "String s = \"bla\";if (exists(s)) {";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void SwitchTest(){
   String in = "switch(5 % 3) { case 0: return true; case 1: return false;}";
   String r = generate(in);
   String expected = "switch (5%3){ case 0:; return true; case 1:; return false;";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void AssignConditionTest(){
   String in = "Child user; Agent agent; Robot I_ROBOT; agent = (agent == user) ? (Agent)I_ROBOT : (Agent)user;";
   String r = generate(in);
   String expected = "Rdf user;Rdf agent;Rdf I_ROBOT;agent = ((agent.equals(user)) ? (Rdf)I_ROBOT : (Rdf)user);";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void StatementTest8(){
   String in = " Child user; n = user.forename; List<String> names = { n };";
   String r = generate(in);
   String expected = "Rdf user;String n = user.getString(\"<dom:forename>\");"
       + "List<String> names = new ArrayList<>();names.add(n);";
   assertEquals(expected, getForMarked(r, expected));
 }

//   @Test
//   public void StatementTestxy(){
//     String in = "Object foo; for (int i = 1; i <= 2; ++i){foo.slot = 1;}";
//     String r = getGeneration(in);
//     assertEquals("for (int i = 1; i <= 2; ++i) {foo.slot =1;}", r);
//  }
//
//  @Test
//  public void StatementTestxx(){
//    String in = "Object foo; for (int i = 1; i <= 2; i++){foo.slot = 1;}";
//    String r = getGeneration(in);
//    assertEquals("", r);
//  }
}
