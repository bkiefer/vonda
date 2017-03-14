package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

public class TestCast {

  static String header = "boolean firstEncounter(); label: if(true) {"
          + "// hello test\n";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "tests.yml", header, footer);
  }

  @Test
  public void testCast1() {
    String in = "Session c; c.hasActivities.add(new Idle);";
    String s = generate(in);
    String expected = "((Set<Object>)c.getValue(\"<dom:hasActivities>\"))"
        + ".add(_proxy.getClass(\"<dom:Idle>\").getNewInstance(DEFNS));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast1a() {
    String in = "Session c; c.hasActivities += new Idle;";
    String s = generate(in);
    String expected = "((Set<Object>)c.getValue(\"<dom:hasActivities>\"))"
            + ".add(_proxy.getClass(\"<dom:Idle>\").getNewInstance(DEFNS));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast2() {
    String in = "QuizHistory turn; boolean correct = turn.correct;";
    String s = generate(in);
    String expected = "boolean correct = "
        + "((Boolean)turn.getSingleValue(\"<dom:correct>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast3() {
    String in = "String th = myLastDA().theme;";
    String s = generate(in);
    String expected = "String th = ((String)myLastDA().getSlot(\"theme\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast4() {
    String in = "Quiz activity; if "
            + "(activity.tabletOrientation != getCurrentAsker()) {}";
    String s = generate(in);
    String expected = "if ((((String)activity"
            + ".getSingleValue(\"<dom:tabletOrientation>\"))"
        + " != getCurrentAsker())) {}";
    assertEquals(expected, getForMarked(s, expected));
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
    String s = generate(in);
    String expected = "Rdf turn ="
        + " _proxy.getClass(\"<dom:QuizHistory>\").getNewInstance(DEFNS);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test2() {
    String in = "QuizHistory turn; turn.asker = agt;";
    String s = generate(in);
    String expected = "turn.setValue(\"<dom:asker>\", agt);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test3() {
    String in = "Activity activity; activity.status = \"gameProposed\";";
    String s = generate(in);
    String expected = "activity.setValue(\"<dom:status>\", \"gameProposed\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test4() {
     String in = "Activity activity; bool = (activity.status == \"gameProposed\");";
    String s = generate(in);
    String expected = "boolean bool = isEqual(((String)activity"
            + ".getSingleValue(\"<dom:status>\")), \"gameProposed\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test5() {//firstEncounter() vor label if (siehe getGeneration) einfügen (darf nicht im true deklariert werden)
    String in = "daType = firstEncounter() ? \"a\" : \"b\";";
    String s = generate(in);
    String expected = "String daType = (firstEncounter() ? \"a\" : \"b\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test6() {
    String in = "Child user; user.isLocatedAt == \"<dom:Home>\";";
    String s = generate(in);
    String expected = "isEqual(((Rdf)user.getSingleValue(\"<dom:isLocatedAt>\")),"
            + " \"<dom:Home>\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test6a() {
    String in = "Child user; docs = user.isTreatedBy;";
    String s = generate(in);
    String expected = "Set<Object> docs = ((Set<Object>)user"
            + ".getValue(\"<dom:isTreatedBy>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test7() {
    String in = "Child user; b = (user.forename == \"John\");";
    String s = generate(in);
    String expected = "boolean b = isEqual(((String)user"
            + ".getSingleValue(\"<dom:forename>\")), \"John\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test8() {
    String in = "Child user; user.forename = \"John\";";
    String s = generate(in);
    // It's always setValue, even if the property is functional
    String expected = "user.setValue(\"<dom:forename>\", \"John\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test9() {
    String in = "Object foo; foo.slot = 1;";
    String s = generate(in);
    String expected = "foo.slot = 1;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test10() {
    String in = "DialogueAct a; DialogueAct b; boolean isSmaller; isSmaller = a<b;";
    String s = generate(in);
    String expected = "isSmaller = isSmaller(a, b);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test11() {
    String in = "int i; boolean i; i = 2; ";
    String s = generate(in);
    String expected = "i = 2;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test12() {
    String in = "int i; i = false;";
    String s = generate(in);
    String expected = "i = (Object) false;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test13() {
    String in = "DialogueAct a; DialogueAct b; boolean isGreater; isGreater = a>b;";
    String s = generate(in);
    String expected = "isGreater = isGreater(a, b);";
    assertEquals(expected, getForMarked(s, expected));
  }

  // TODO: CONSTRUCT A TEST EXAMPLE WITH AT LEAST THREE RDF ACCESSES
  @Test
  public void testMultipleRdfAccess() {
    String in = "Quiz c ; b = c.hasHistory.correct;";
    String s = generate(in);
    String expected = "boolean b = ((Boolean)((Rdf)c"
            + ".getSingleValue(\"<dom:hasHistory>\"))"
            + ".getSingleValue(\"<dom:correct>\"));";
    assertEquals(expected, getForMarked(s, expected));
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
