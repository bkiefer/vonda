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

import static org.junit.Assert.*;

import org.junit.*;

import de.dfki.mlt.rudimant.compiler.TypeException;

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;

public class AdditionalTest {
  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void testDontKnowType(){
    String in = " for(seat : getSeats()){}";
    String r = generate(in);
    String expected = "for (Object seat_outer : getSeats()) {"
            + " Object seat = (Object)seat_outer; { } }";
    assertEquals(expected, getForMarked(r, expected));
  }

  // check for type error
  @Test(expected=TypeException.class)
  public void testDontKnowType2() throws Throwable{
    String in = "for(seat : getSeats()){}";
    String r = getTypeError(in);
    assertEquals("for (Object seat: getSeats()) {}", r);
  }

  @Test
  public void testParenthesis() {
    String in = "rule: if (lastPref.pref_score != 0) {}";
    String r = generate(in);
    String pref = "public int rule(){ boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = lastPref.pref_score != 0); logRule(0, __x0); rule: if (__x0[0]){ } return 0; }";
    assertEquals(pref, getForMarked(r, pref));
  }

  // exp to statement in if
  @Test
  public void testIfThenExp() {
    String in = "public int lab(){ if(true) emitDA(#InitialGreeting(Greet)); }";
    String exp = "public int lab() { if (true) "
            + "emitDA(new DialogueAct(\"InitialGreeting\", \"Greet\")); }";

    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  // exp to statement in else of if
  @Test
  public void testIfThenElseExp() {
    String in = "public int lab(){ if(true) b=1; else emitDA(#InitialGreeting(Greet)); }";
    String exp = "public int lab() { if (true) int b = 1; "
            + "else emitDA(new DialogueAct(\"InitialGreeting\", \"Greet\")); }";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }


  @Test
  public void testComments() {
    String stat = "/*@  public String preBlock() { return \"preBlock\";} @*/"
        + "demo_rule: if (true) break demo_rule; "
        + "/*@ public String postBlock() { return \"postBlock\"; }@*/";
    String s = generate(stat);
    String expected = "public String preBlock() { return \"preBlock\";} public int demo_rule(){ boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); demo_rule: if (__x0[0])break demo_rule; return 0; } public String postBlock() { return \"postBlock\"; } }";
    assertEquals(expected, getForMarked(s, expected));
  }

  // exp to statement in else of if
  @Test
  public void testDialogueAct() {
    String in = "int x=27;emitDA(#I(Greet, val={x}));";
    String exp = "x = 27;emitDA(new DialogueAct(\"I\", \"Greet\", \"val\", Integer.toString(x)));";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
    assertTrue(s.contains("int x;"));
  }

  // test single synthetic boolean in rule: check for base term handling
  @Test
  public void testSyntheticBaseTerm() {
    String in = "a = 1; rule: if(a) a=7;";
    String exp = "a = 1;res = rule(); if (res != 0) return (res - 1); public int rule(){ "
        + "boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = a != 0); logRule(0, __x0); rule: if (__x0[0])a = 7; return 0; } }";

    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  @Test
  public void testPlainMethodCall() {
    String in = "v = \"3\"; toInt(v);";
    String exp = "v = \"3\";toInt(v);";

    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  @Test
  public void testNewArrayOnToplevel() {
    String in = "v = { 1, 2, 3 };";
    String exp = "v = new int[]{1, 2, 3};";

    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  @Test
  public void testCharacterLiteral() {
    String in = "v = '2';";
    String exp = "v = '2';";

    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }
}
