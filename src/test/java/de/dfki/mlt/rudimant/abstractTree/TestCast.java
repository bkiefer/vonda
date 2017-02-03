package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.abstractTree.TestTypeInference.getNodeOfInterest;
import static visitortests.SeriousTest.RESOURCE_DIR;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;

public class TestCast {

  static String header = "label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  @Test
  public void testCast1() throws IOException, WrongFormatException {
    String in = "Session c; c.hasActivities.add(new Idle);";
    StringWriter out = new StringWriter();
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(in, out), 1);
    assertTrue(dtr instanceof UFieldAccess);
    String r = out.toString();
    // search for correct line and analyse
  }

}
