package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.setUp;
import static de.dfki.mlt.rudimant.abstractTree.TestTypeInference.getNodeOfInterest;
import static org.junit.Assert.*;
import static visitortests.SeriousTest.RESOURCE_DIR;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.Visualize;

/**
 *
 * @author max
 */
public class ExpArithmeticTest {

  static String header = "label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  public InputStream getInput(String input) {
    String toParse = header + input + footer;
    return new ByteArrayInputStream(toParse.getBytes());
  }

  @Test
  public void testArithmetic() throws IOException {
    String arithmeticExp = "1 + 2;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(getInput(arithmeticExp)));
    assertTrue(dtr instanceof ExpArithmetic);
  }

  @Test
  public void testArithmetic2() throws IOException {
    String arithmeticExp = "1 * 2;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(getInput(arithmeticExp)));
    assertTrue(dtr instanceof ExpArithmetic);
  }

  @Test
  public void testArithmetic3() throws IOException {
    String arithmeticExp = "1 / 2;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(getInput(arithmeticExp)));
    assertTrue(dtr instanceof ExpArithmetic);
  }

  @Test
  public void testArithmetic4() throws IOException {
    String arithmeticExp = "1 - 2;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(getInput(arithmeticExp)));
    assertTrue(dtr instanceof ExpArithmetic);

    String type = ((ExpArithmetic) dtr).getType();
    assertEquals("type of 1 - 2", "int", type);
  }

}
