package de.dfki.mlt.rudimant.compiler.tree;

import static org.junit.Assert.*;

import org.junit.*;

import de.dfki.mlt.rudimant.compiler.TypeException;

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;

public class TestAdditional {
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
    String pref = "public int rule(){ boolean[] __x0 = new boolean[2]; __x0[0] = (lastPref.pref_score != 0); logRule(0, __x0); rule: if (__x0[0]){ } return 0; }";
    assertEquals(pref, getForMarked(r, pref));
 }
}
