/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

/**
 *
 * @author anna
 */
public class TestTypes {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void testType1(){
    String in = " DialogueAct reply = myLastDA().copy();";
    String r = generate(in);
    String expected = "reply = (DialogueAct) myLastDA().copy();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("DialogueAct reply;"));
  }

  @Test
  public void testType2(){
    String in = " Rdf turn = getCurrentTurn(activity);";
    String r = generate(in);
    String expected = "turn = getCurrentTurn(activity);";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Rdf turn;"));
  }

  @Test
  public void testType3(){
    String in = " propose(\"continue_quiz\") {\n" +
              "      Rdf turn = getCurrentTurn(activity);}";
    String r = generate(in);
    String expected = "propose(\"continue_quiz\",new Proposal() {"
            + "public void run() {"
            + "Rdf turn = getCurrentTurn(activity); }});";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testType4(){
    String in = " int correct = q.getWhichCorrect();";
    String r = generate(in);
    String expected = "correct = (int) q.getWhichCorrect();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("int correct;"));
  }

  @Test
  public void testType5(){
    String in = "List<String> a = new List<String>();";
    String r = generate(in);
    String expected = "a = new List<String>();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("List<String> a;"));
  }

  @Test
  public void testReturnSetType() {
    String in = "Child c;  docs = c.isTreatedBy;";
    String r = generate(in);
    String expected = "docs = ((Set<Object>)c.getValue(\"<dom:isTreatedBy>\"));";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Object> docs;"));
  }

  @Test
  public void testLambdaExp() {
    String in = "Set<Child> cs; cs.contains((c) -> c.foreName.equals(\"John\"));";
    String r = generate(in);
    String expected = "cs.contains((c) -> "
            + "((Set<Object>)c.getValue(\"foreName\")).equals(\"John\"));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testComplexLambdaExp() {
    String in = "Set<Child> cs; cs.contains((c) -> {c.foreName.equals(\"John\");});";
    String r = generate(in);
    String expected = "cs.contains((c) -> {"
            + "((Set<Object>)c.getValue(\"foreName\")).equals(\"John\"); });";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testListRdftype() {
    String in = "List<Quiz> q = {};";
    String r = generate(in);
    String expected = "List<Rdf> q = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBooleanRdfExists() {
    String in = "Child c; if (!c.hasMother) int i = 0;";
    String r = generate(in);
    String expected =
        "if (!((c != null) && (((Rdf)c.getSingleValue(\"<dom:hasMother>\")) != null))) int i = 0;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBooleanRdfExists2() {
    String in = "Child c; a: if (!c.hasMother) int i = 0;";
    String r = generate(in);
    String expected =
        "a(); return false; } public boolean a(){ boolean a0 = false; a0 = !((c != null) && "
        + "(((Rdf)c.getSingleValue(\"<dom:hasMother>\")) != null));";
    assertEquals(expected, getForMarked(r, expected));
  }

  /* TODO: THIS PRODUCES TOTAL JUNK. To be fixed tomorrow
  @Test
  public void testBooleanRdfExists3() {
    String in = "Child c; a: if (true && false) int i = 0;";
    String r = generate(in);
    String expected =
        "a(); } public void a(){ boolean a0 = false; a0 = !(((Rdf)c.getSingleValue(\"<dom:hasMother>\")) != null)) int i = 0;";
    assertEquals(expected, getForMarked(r, expected));
  }*/
}
