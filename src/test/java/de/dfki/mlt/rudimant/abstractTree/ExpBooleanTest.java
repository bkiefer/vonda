package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.setUp;
import static de.dfki.mlt.rudimant.abstractTree.TestTypeInference.getNodeOfInterest;
import static org.junit.Assert.*;
import static visitortests.SeriousTest.RESOURCE_DIR;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.Visualize;

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
  static String header = "label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  @Test
  public void testBoolean() throws IOException {
    String booleanExp = "4 < 5;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(booleanExp));
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean2() throws IOException {
    String booleanExp = "4 == 5;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(booleanExp));
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean3() throws IOException {
    String booleanExp = "false;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(booleanExp));
    // TODO dtr is instanceof UVariable. Is this correct?
    // dtr.getType is "boolean" as String. Is this correct?

     assertTrue(dtr instanceof USingleValue);
     assertEquals("false should be of type Boolean", "boolean", (((RTExpression) dtr).getType()));
  }

  @Test
  public void testBoolean4() throws IOException {
    String booleanExp = "!(5>5);";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean5() throws IOException {
    String booleanExp = "(False && False || True);";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean6() throws IOException {
    String booleanExp = "(False & False | True);";
// TODO should this work? we get a nullpointer here.

//    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", booleanExp));
//    RudiTree dtr = getNodeOfInterest(rt.first);
//     assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean7() throws IOException {
    String booleanExp = "(var1 != var2);";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean8() throws IOException {
    String booleanExp = "(var1 >= var2 && var1 <= var2);";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

}
