package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

public class TestCast {

  static String header = "boolean firstEncounter(); label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "tests.yml", header, footer);
  }

  @Test
  public void testCast1() {
    String in = "Session c; c.hasActivities.add(new Idle);";
    String r = getGeneration(in);
    assertEquals(
        "((Set<Object>)c.getValue(\"<dom:hasActivities>\"))"
        + " .add(_proxy.getClass(\"<dom:Idle>\").getNewInstance(DEFNS)); ",
        r);
  }

  @Test
  public void testCast1a() {
    String in = "Session c; c.hasActivities += new Idle;";
    String r = getGeneration(in);
    assertEquals(
        "((Set<Object>)c.getValue(\"<dom:hasActivities>\")) .add(_proxy.getClass(\"<dom:Idle>\").getNewInstance(DEFNS));",
        r);
  }

  @Test
  public void testCast2() {
    String in = "QuizHistory turn; boolean correct = turn.correct;";
    String r = getGeneration(in);
    String exp = "boolean correct = "
        + "((boolean)turn.getSingleValue(\"<dom:correct>\")) ; ";
    assertEquals(exp, r);
  }

  @Test
  public void testCast3() {
    String in = "String th = myLastDA().theme;";
    String r = getGeneration(in);
    assertEquals("String th = ((String)myLastDA().getSlot(\"theme\")) ; ", r);
  }

  @Test
  public void testCast4() {
    String in = "Quiz activity; if (activity.tabletOrientation != getCurrentAsker()) {}";
    String r = getGeneration(in);
    String exp = "if ((((String)activity.getSingleValue(\"<dom:tabletOrientation>\"))"
        + " != getCurrentAsker())) {}";
    assertEquals(exp, r);
  }

//  @Test
//  public void testCast5() {
//    String in = "t = (QuizHistory) a;";
//    String r = getGeneration(in);
//    String exp = "Rdf t = a;";
//    assertEquals(exp, r);
//  }

  @Test
  public void test1() {
    String in = "QuizHistory turn = new QuizHistory;";
    String r = getGeneration(in);
    String exp = "Rdf turn ="
        + " _proxy.getClass(\"<dom:QuizHistory>\").getNewInstance(DEFNS); ";
    assertEquals(exp, r);
  }

  @Test
  public void test2() {
    String in = "QuizHistory turn; turn.asker = agt;";
    String r = getGeneration(in);
    String exp = " turn.setValue(\"<dom:asker>\", agt); ";
    assertEquals(exp, r);
  }

  @Test
  public void test3() {
    String in = "Activity activity; activity.status = \"gameProposed\";";
    String r = getGeneration(in);
    String exp = " activity.setValue(\"<dom:status>\", \"gameProposed\"); ";
    assertEquals(exp, r);
  }

  @Test
  public void test4() {
     String in = "Activity activity; bool = (activity.status == \"gameProposed\");";
     String r = getGeneration(in);
     String exp = "boolean bool = isEqual(((String)activity.getSingleValue(\"<dom:status>\")) , \"gameProposed\"); ";
     assertEquals(exp, r);
  }

  @Test
  public void test5() {//firstEncounter() vor label if (siehe getGeneration) einfügen (darf nicht im true deklariert werden)
    String in = "daType = firstEncounter() ? \"a\" : \"b\";";
    String r = getGeneration(in);
    String exp = "String daType = (firstEncounter() ? \"a\" : \"b\"); ";
    assertEquals(exp, r);
  }

  @Test
  public void test6() {
    String in = "Child user; user.isLocatedAt == \"<dom:Home>\";";
    String r = getGeneration(in);
    String exp = "isEqual(((Rdf)user.getSingleValue(\"<dom:isLocatedAt>\")) , \"<dom:Home>\"); ";
    assertEquals(exp, r);
  }

  @Test
  public void test6a() {
    String in = "Child user; docs = user.isTreatedBy;";
    String r = getGeneration(in);
    String exp = "Set<Object> docs = ((Set<Object>)user.getValue(\"<dom:isTreatedBy>\")) ; ";
    assertEquals(exp, r);
  }

  @Test
  public void test7() {
    String in = "Child user; b = (user.forename == \"John\");";
    String r = getGeneration(in);
    String exp = "boolean b = isEqual(((String)user.getSingleValue(\"<dom:forename>\")) , \"John\"); ";
    assertEquals(exp, r);
  }

  @Test
  public void test8() {
    String in = "Child user; user.forename = \"John\";";
    String r = getGeneration(in);
    // It's always setValue, even if the property is functional
    String exp = " user.setValue(\"<dom:forename>\", \"John\"); ";
    assertEquals(exp, r);
  }

  @Test
  public void test9() {
    String in = "Object foo; foo.slot = 1;";
    String r = getGeneration(in);
    String exp = " foo.slot = 1; ";
    assertEquals(exp, r);
  }

  @Test
  public void test10() {
    String in = "DialogueAct a; DialogueAct b; boolean isSmaller; isSmaller = a<b;";
    String r = getGeneration(in);
    String exp = " isSmaller = isSmaller(a, b); ";
    assertEquals(exp, r);
  }

  @Test
  public void test11() {
    String in = "int i; boolean i; i = 2; ";
    String r = getGeneration(in);
    String exp = " i = 2; ";
    assertEquals(exp, r);
  }

  @Test
  public void test12() {
    String in = "int i; i = false;";
    String r= getGeneration(in);
    String exp = " i = (Object) false; ";
    assertEquals(exp, r);
  }

  @Test
  public void test13() {
    String in = "DialogueAct a; DialogueAct b; boolean isGreater; isGreater = a>b;";
    String r = getGeneration(in);
    String exp = " isGreater = isGreater(a, b); ";
    assertEquals(exp, r);
  }

  // TODO: CONSTRUCT A TEST EXAMPLE WITH AT LEAST THREE RDF ACCESSES
  @Test
  public void testMultipleRdfAccess() {
    String in = "Quiz c ; b = c.hasHistory.correct;";
    String r = getGeneration(in);
    String exp = "boolean b = ((boolean)((Rdf)c.getSingleValue(\"<dom:hasHistory>\")) .getSingleValue(\"<dom:correct>\")) ; ";
    assertEquals(exp, r);
  }

  /* TODO: FIX THE TEST/CODE ITSELF, NOT SURE IF THE INPUT IS LEGAL AT ALL.
  @Test
  public void test666() {
    String in = "QuizHistory turn; turn.responder = (agt == user) ? I_ROBOT : user;";
    String r = getGeneration(in);
    String exp = " turn.setValue(\"<dom:responder>\", (isEqual(agt, user) ? I_ROBOT : user)); ";
    assertEquals(exp, r);
  }
  */
}
