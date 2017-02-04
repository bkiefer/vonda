package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static visitortests.SeriousTest.RESOURCE_DIR;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;

public class TestCast {

  static int prefix = 678, suffix = 5;

  public static String getGeneration(String in) {
    StringWriter out = new StringWriter();
    parseAndTypecheck(in, out);
    StringBuffer sb = out.getBuffer();
    return normalizeSpaces(sb.subSequence(prefix, sb.length() - suffix).toString());
  }

  static String header = "label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  @Test
  public void testCast1() throws IOException, WrongFormatException {
    String in = "Session c; c.hasActivities.add(new Idle);";
    String r = getGeneration(in);
    assertEquals(
        "((Set<Object>)c.getValue(\"<dom:hasActivities>\"))"
        + " .add(_proxy.getClass(\"<dom:Idle>\").getNewInstance(DEFNS)) ; ",
        r);
  }

  @Test
  public void testCast2() throws IOException, WrongFormatException {
    String in = "QuizHistory turn; boolean correct = turn.correct;";
    String r = getGeneration(in);
    String exp = "boolean correct = (boolean) "
        + "((Boolean)turn.getSingleValue(\"<dom:correct>\")) ; ";
    assertEquals(exp, r);
  }

  @Test
  public void testCast3() throws IOException, WrongFormatException {
    String in = "String th = myLastDA().theme;";
    String r = getGeneration(in);
    assertEquals("String th = ((String)myLastDA() .getSlot(\"theme\")) ; ", r);
  }

  @Test
  public void testCast4() throws IOException, WrongFormatException {
    String in = "Quiz activity; if (activity.tabletOrientation != getCurrentAsker()) {}";
    String r = getGeneration(in);
    String exp = "if ((((String)activity.getSingleValue(\"<dom:tabletOrientation>\"))"
        + " != getCurrentAsker() )) {}";
    assertEquals(exp, r);
  }

  @Test
  public void test1() throws IOException, WrongFormatException {
    String in = "QuizHistory turn = new QuizHistory;";
    String r = getGeneration(in);
    String exp = "Rdf turn ="
        + " _proxy.getClass(\"<dom:QuizHistory>\").getNewInstance(DEFNS); ";
    assertEquals(exp, r);
  }

  @Test
  public void test2() throws IOException, WrongFormatException {
    String in = "QuizHistory turn; turn.asker = agt;";
    String r = getGeneration(in);
    String exp = " turn.setValue(\"<dom:asker>\", agt); ";
    assertEquals(exp, r);
  }


  /* TODO: FIX THE TEST/CODE ITSELF, NOT SURE IF THE INPUT IS LEGAL AT ALL.
  @Test
  public void test666() throws IOException, WrongFormatException {
    String in = "QuizHistory turn; turn.responder = (agt == user) ? I_ROBOT : user;";
    String r = getGeneration(in);
    String exp = " turn.setValue(\"<dom:responder>\", (isEqual(agt, user) ? I_ROBOT : user)); ";
    assertEquals(exp, r);
  }
  */
}
