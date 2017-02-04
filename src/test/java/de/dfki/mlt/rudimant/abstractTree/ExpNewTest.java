package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.setUp;
import static de.dfki.mlt.rudimant.abstractTree.ExpAssignmentTest.getNodeOfInterest;
import static org.junit.Assert.assertTrue;
import static visitortests.SeriousTest.RESOURCE_DIR;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.Visualize;

/**
 *
 * @author max
 */
public class ExpNewTest {

  static String header = "label: if(true) {";
  static String footer = "}";


  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  @Test
  public void testRdfType() throws IOException, WrongFormatException {
    String conditionalExp = "new Activity;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpNew);
    assertTrue(Mem.isRdfType(((ExpNew)dtr).type));
  }

  @Test
  public void testJavaType() throws IOException, WrongFormatException {
    String conditionalExp = "new JavaType();";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpNew);
  }

}
