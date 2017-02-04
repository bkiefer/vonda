package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.setUp;
import static de.dfki.mlt.rudimant.abstractTree.ExpAssignmentTest.getNodeOfInterest;
import static org.junit.Assert.*;
import static visitortests.SeriousTest.RESOURCE_DIR;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.Visualize;

public class TestFragment {

  static String header = "label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  @Test
  public void test() throws IOException, WrongFormatException {
    String conditionalExp = "true ? 1 : 2;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpConditional);

    String type_boolean = ((ExpConditional) dtr).boolexp.getType();
    assertEquals("boolean part of conditional", "boolean", type_boolean);

    String type_then = ((ExpConditional) dtr).thenexp.getType();
    assertEquals("then part", "int", type_then);

    String type_else = ((ExpConditional) dtr).elseexp.getType();
    assertEquals("else part", "int", type_else);
  }

}
