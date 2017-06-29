package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.tree.*;

/**
 *
 * @author max
 */
public class ExpArithmeticTest {

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUpNonEmpty();
  }

  @Test
  public void testArithmetic() throws IOException {
    String arithmeticExp = "1 + 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(getInput(arithmeticExp)));
    assertTrue(dtr instanceof ExpArithmetic);
  }

  @Test
  public void testArithmetic2() throws IOException {
    String arithmeticExp = "1 * 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(getInput(arithmeticExp)));
    assertTrue(dtr instanceof ExpArithmetic);
  }

  @Test
  public void testArithmetic3() throws IOException {
    String arithmeticExp = "1 / 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(getInput(arithmeticExp)));
    assertTrue(dtr instanceof ExpArithmetic);
  }

  @Test
  public void testArithmetic4() throws IOException {
    String arithmeticExp = "1 - 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(getInput(arithmeticExp)));
    assertTrue(dtr instanceof ExpArithmetic);

    String type = ((ExpArithmetic) dtr).getType().get_name();
    assertEquals("type of 1 - 2", "int", type);
  }

  @Test
  public void testAssignment5() throws IOException {
    String in = "Activity a; a.status = \"proposed\";";
    GrammarFile gf = parseAndTypecheck(getInput(in));
    RudiTree dtr = getNodeOfInterest(gf, 0);
    assertTrue(dtr instanceof StatVarDef);
    assertEquals("Activity", ((StatVarDef)dtr).type.get_name());
    dtr = getNodeOfInterest(gf, 1);
    assertTrue(dtr instanceof ExpAssignment);
    assertEquals("String", ((ExpAssignment)dtr).type.get_name());
  }
}
