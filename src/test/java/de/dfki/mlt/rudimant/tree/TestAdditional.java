package de.dfki.mlt.rudimant.tree;

import static org.junit.Assert.*;

import org.junit.*;
import de.dfki.mlt.rudimant.TypeException;
import static de.dfki.mlt.rudimant.Visualize.generate;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;

public class TestAdditional {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
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
}
