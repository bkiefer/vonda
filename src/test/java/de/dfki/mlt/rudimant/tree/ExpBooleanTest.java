package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.tree.*;

/**
 *
 * @author max
 */
public class ExpBooleanTest {

  /*
  /// boolean operators:
NOT: '!';
EQUAL: '==';
AND1: '&'; TODO deprecated?
OR1: '|'; TODO deprecated?
AND2: '&&';
OR2: '||';
NOT_EQUAL: '!=';
SMALLER_EQUAL: '<=';
SMALLER: '<';
GREATER_EQUAL: '>=';
GREATER: '>';

   */

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testBoolean() {
    String booleanExp = "4 < 5;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean2() {
    String booleanExp = "4 == 5;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean3() {
    String booleanExp = "false;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    // TODO dtr is instanceof UVariable. Is this correct?
    // dtr.getType is "boolean" as String. Is this correct?

     assertTrue(dtr instanceof ExpSingleValue);
     assertEquals("false should be of type Boolean", "boolean", (((RTExpression) dtr).getType().get_name()));
  }

  @Test
  public void testBoolean4() {
    String booleanExp = "!(5>5);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean5() {
    String booleanExp = "(false && false || true);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testArith6() {
    String intExp = "(3 & 1 | 8);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(intExp));

    assertTrue(dtr instanceof ExpArithmetic);
  }

  @Test
  public void testBoolean7() {
    String booleanExp = "(var1 != var2);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean8() {
    String booleanExp = "(var1 >= var2 && var1 <= var2);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

}
