package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

import de.dfki.mlt.rudimant.TypeException;
import de.dfki.mlt.rudimant.Visualize;

public class TestCast {

  static String header = "boolean firstEncounter(); label: if(true) {"
          + "// hello test\n";
  static String footer = "// end of test\n}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "tests.yml", header, footer);
  }

  @Test
  public void testCast1() {
    String in = "Session c; c.hasActivities.add(new Idle);";
    String s = generate(in);
    String expected = "Rdf c;((Set<Object>)c.getValue(\"<dom:hasActivities>\"))"
        + ".add(_proxy.getClass(\"<dom:Idle>\").getNewInstance(DEFNS));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast1a() {
    String in = "Session c; c.hasActivities += new Idle;";
    String s = generate(in);
    String expected = "Rdf c;((Set<Object>)c.getValue(\"<dom:hasActivities>\"))"
            + ".add(_proxy.getClass(\"<dom:Idle>\").getNewInstance(DEFNS));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast2() {
    String in = "QuizHistory turn; boolean correct = turn.correct;";
    String s = generate(in);
    String expected = "Rdf turn;boolean correct = "
        + "((Boolean)turn.getSingleValue(\"<dom:correct>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast3() {
    String in = "String th = myLastDA().theme;";
    String s = generate(in);
    String expected = "String th = ((String)myLastDA().getValue(\"theme\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast4() {
    String in = "Quiz activity; if "
            + "(activity.tabletOrientation != getCurrentAsker()) {}";
    String s = generate(in);
    String expected = "Rdf activity;if ((! "
        + "(((String)activity.getSingleValue(\"<dom:tabletOrientation>\"))"
        + ".equals(getCurrentAsker())))) { }";
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
    String expected = "Rdf turn; turn.setValue(\"<dom:asker>\", agt);";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test3() {
    String in = "Activity activity; activity.status = \"gameProposed\";";
    String s = generate(in);
    String expected = "Rdf activity; activity.setValue(\"<dom:status>\", \"gameProposed\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test4() {
     String in = "Activity activity; bool = (activity.status == \"gameProposed\");";
    String s = generate(in);
    String expected = "Rdf activity;boolean bool = ((((String)activity"
            + ".getSingleValue(\"<dom:status>\")).equals(\"gameProposed\")));";
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
    String in = "Child user; user.isLocatedAt == toRdf(\"<dom:Home>\");";
    String s = generate(in);
    String expected = "Rdf user;(((Rdf)user.getSingleValue(\"<dom:isLocatedAt>\"))"
        + ".equals(toRdf(\"<dom:Home>\")));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test6a() {
    String in = "Child user; docs = user.isTreatedBy;";
    String s = generate(in);
    String expected = "Rdf user;Set<Object> docs = ((Set<Object>)user"
            + ".getValue(\"<dom:isTreatedBy>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test7() {
    String in = "Child user; b = (user.forename == \"John\");";
    String s = generate(in);
    String expected = "Rdf user;boolean b = ((((String)user"
            + ".getSingleValue(\"<dom:forename>\")).equals(\"John\")));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test8() {
    String in = "Child user; user.forename = \"John\";";
    String s = generate(in);
    // It's always setValue, even if the property is functional
    String expected = "Rdf user; user.setValue(\"<dom:forename>\", \"John\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test9() {
    String in = "Object foo; foo.slot = 1;";
    String s = generate(in);
    String expected = "Object foo; foo.slot = 1;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test10() {
    String in = "DialogueAct a; DialogueAct b; boolean isSmaller; isSmaller = a<b;";
    String s = generate(in);
    String expected = "DialogueAct a;DialogueAct b;boolean isSmaller; isSmaller = (a.strictlySubsumes(b));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test11() {
    // TODO: find out whether i is int or boolean at the end, and put that into the
    // documentation
    String in = "int i; boolean i; i = 2; ";
    String s = generate(in);
    String expected = "int i;boolean i; i = 2;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test(expected=TypeException.class)
  public void test12() throws Throwable{
    // TODO: When i is int, this is definitely an error in java; how to deal with it?
    //      in this case, a type error is thrown; we should test that here
    String in = "int i; i = false;";
    String s = getTypeError(in);
    // String expected = "i = (Object /* (unknown) */) false;";
    // assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test13() {
    String in = "DialogueAct a; DialogueAct b; boolean isGreater; isGreater = a>b;";
    String s = generate(in);
    String expected = "DialogueAct a;DialogueAct b;boolean isGreater; isGreater = (a.isStrictlySubsumedBy(b));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test14(){
    // Test cast with parameterized types
    String in = "LinkedList<String> b; LinkedList<String> l = (LinkedList<String>) b;";
    String s = generate(in);
    String expected = "LinkedList<String> b;LinkedList<String> l = (LinkedList<String>)b;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test15() {
    String in = "Agent c; String s = ((Child)c).forename;";
    String s = generate(in);
    System.out.println(s);
    String expected = "Rdf c;String s = ((String)((Rdf)c).getSingleValue(\"<dom:forename>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

  // TODO: CONSTRUCT A TEST EXAMPLE WITH AT LEAST THREE RDF ACCESSES
  @Test
  public void testMultipleRdfAccess() {
    String in = "Quiz c ; b = c.hasHistory.correct;";
    String s = generate(in);
    String expected = "Rdf c;Boolean b = ((Boolean)((Rdf)c"
            + ".getSingleValue(\"<dom:hasHistory>\"))"
            + ".getSingleValue(\"<dom:correct>\"));";
    assertEquals(expected, getForMarked(s, expected));
  }

    @Test
  public void testMultipleRdfAccess2() {
    // Test set field with POD type
    String in = "Clazz c; if (c.a.a.a.b) return true;";
    String s = generate(in);
    String expected = "Rdf c;if (((((c != null "
        + "&& ((Rdf)c.getSingleValue(\"<dom:a>\")) != null) "
        + "&& ((Rdf)((Rdf)c.getSingleValue(\"<dom:a>\")).getSingleValue(\"<dom:a>\")) != null) "
        + "&& ((Rdf)((Rdf)((Rdf)c.getSingleValue(\"<dom:a>\")).getSingleValue(\"<dom:a>\")).getSingleValue(\"<dom:a>\")) != null) "
        + "&& exists(((Integer)((Rdf)((Rdf)((Rdf)c.getSingleValue(\"<dom:a>\"))"
        + ".getSingleValue(\"<dom:a>\")).getSingleValue(\"<dom:a>\"))"
        + ".getSingleValue(\"<dom:b>\"))))) return true;";
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

  @Test
  public void testSetPOD() {
    // Test set field with POD type
    String in = "Child c; c.age = 10;";
    String s = generate(in);
    String expected = "Rdf c; c.setValue(\"<dom:age>\", 10)";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testRdfCast() {
    // Test set field with POD type
    String in = "if (((Rdf)d) <= Child) return true;";
    String s = generate(in);
    String expected = "if ((((Rdf)d).getClazz().isSubclassOf(getRdfClass(\"Child\")))) return true;";
    assertEquals(expected, getForMarked(s, expected));
  }

}
