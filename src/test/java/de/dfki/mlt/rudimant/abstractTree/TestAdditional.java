package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;
import de.dfki.mlt.rudimant.TypeException;

public class TestAdditional {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testDontKnowType(){
    String in = "for(seat : getSeats()){}";
    String r = getGeneration(in);
    assertEquals("for (Object seat: getSeats()) {}", r);
  }

  // check for type error
  @Test(expected=TypeException.class)
  public void testDontKnowType2() throws Throwable{
    String in = "for(seat : getSeats()){}";
    String r = getTypeError(in);
    assertEquals("for (Object seat: getSeats()) {}", r);
  }
}
