package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static visitortests.SeriousTest.RESOURCE_DIR;
import static de.dfki.mlt.rudimant.abstractTree.TestTypeInference.*;

import de.dfki.lt.hfc.WrongFormatException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.Visualize;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author max
 */
public class ExpAssignmentTest {

  static String header = "label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  static public InputStream getInput(String input) {
    String toParse = header + input + footer;
    return new ByteArrayInputStream(toParse.getBytes());
  }

  @Test
  public void testAssignment1() throws IOException {
    String assignmentExp = "test = 4;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(assignmentExp));
    assertTrue(dtr instanceof ExpAssignment);
    String type = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type of test = 4 should be int", "int", type);
  }

  @Test
  public void testAssignment2() throws IOException {
    String assignmentExp = "test = (4>5);";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(assignmentExp));
    assertTrue(dtr instanceof ExpAssignment);
    String type = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type of test = (4>5) should be boolean", "boolean", type);

  }

  @Test
  public void testAssignment3() throws IOException {
    String assignmentExp = "boolean test = 4+5;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(assignmentExp));
    assertTrue(dtr instanceof ExpAssignment);

    String type_right = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type of boolean test = 4+5 should be int", "int", type_right);

//    String type_left = ((RTExpression)((ExpAssignment) dtr).left).getType();
//    assertEquals("right side type of boolean test = 4+5 should be int", "boolean", type_left);

  }

  @Test
  public void testAssignment4() throws IOException, WrongFormatException {
    String assignmentExp = "test = true; test2 = 1; test3 = test2; test4 = 2;";

    GrammarFile gf = Visualize.parseAndTypecheck(getInput(assignmentExp));
    RudiTree dtr = getNodeOfInterest(gf, 0); // test = true
    assertTrue(dtr instanceof ExpAssignment);

    String type_right = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type test should be boolean", "boolean", type_right);

    dtr = getNodeOfInterest(gf, 1); // test2 = 1
    type_right = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type test2 should be int", "int", type_right);

    dtr = getNodeOfInterest(gf, 2); // test3 = test2
    type_right = ((ExpAssignment) dtr).right.getType();
    // TODO this should be possible.
    assertEquals("right side type test3 should be int", "int", type_right);
  }

}
