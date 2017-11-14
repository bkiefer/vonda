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
    String pref = "public int rule(){ "
        + "boolean rule0 = false; "
        + "rule0 = (lastPref.pref_score != 0); "
        + "if (shouldLog(";
    String suff =
        " Map<String, Boolean> rule = new LinkedHashMap<>(); "
        + "rule.put(\"lastPref.pref_score!=0\", rule0); "
        + "rule.put(\"lastPref.pref_score!=0\", rule0); "
        + "logRule(rule, \"rule\", \"Test\"); } "
        + "rule: "
        + "if (rule0) { } "
        + "return 0; }";
    String foo = getForMarked(r, pref + "_________________" + suff);
    assertTrue(foo.startsWith(pref));
    assertTrue(foo.contains(suff));
  }
}
