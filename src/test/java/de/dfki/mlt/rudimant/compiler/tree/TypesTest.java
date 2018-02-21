/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import org.junit.*;

import de.dfki.mlt.rudimant.compiler.TypeException;

/**
 *
 * @author anna
 */
public class TypesTest {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void testType1(){
    String in = " DialogueAct reply = myLastDA().copy();";
    String r = generate(in);
    String expected = "reply = myLastDA().copy();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("DialogueAct reply;"));
  }

  @Test
  public void testType2(){
    String in = "QuizHistory getCurrentTurn(); Rdf turn = getCurrentTurn(activity);";
    String r = generate(in);
    String expected = "turn = getCurrentTurn(activity);";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Rdf turn;"));
  }

  @Test
  public void testType3(){
    String in = "QuizHistory getCurrentTurn();  propose(\"continue_quiz\") {\n" +
              "      Rdf turn = getCurrentTurn(activity);}";
    String r = generate(in);
    String expected = "propose(\"continue_quiz\",new Proposal() {"
            + "public void run() {"
            + " Rdf turn = getCurrentTurn(activity); } });";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testType4(){
    String in = " int correct = q.getWhichCorrect();";
    String r = generate(in);
    String expected = "correct = q.getWhichCorrect();";
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
  public void testType6(){
    String in = "if(true) { String s = null;}";
    String r = generate(in);
    String expected = "if (true) { String s = null;";
    assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void testType7(){
    String in = "String s = \"something\"; boolean empty = s;";
    String r = generate(in);
    String expected = "s = \"something\";empty = exists(s);";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("String s"));
    assertTrue(r.contains("boolean empty"));
  }

  @Test
  public void testReturnSetType() {
    String in = "Child c;  docs = c.isTreatedBy;";
    String r = generate(in);
    String expected = "public Rdf c;public Set<Object> docs;/**/docs = ((Set<Object>)c.getValue(\"<dom:isTreatedBy>\"));";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Object> docs;"));
    assertTrue(r.contains("Rdf c;"));
  }

  @Test
  public void testListRdftype() {
    String in = "List<Quiz> q = {};";
    String r = generate(in);
    String expected = "q = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("List<Rdf> q;"));
  }

  @Test
  public void testInitDefinedCollection() {
    String in = "List<Quiz> q; q = {};";
    String r = generate(in);
    String expected = "public List<Rdf> q;/**/q = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("List<Rdf> q;"));
  }

  @Test
  public void testInitDefinedCollection2() {
    String in = "List<Quiz> q; void f() { q = {}; }";
    String r = generate(in);
    String expected = "public List<Rdf> q;/**/public void f() { q = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("List<Rdf> q;"));
  }

  @Test
  public void testBooleanRdfExists() {
    String in = "Child c; if (!c.hasMother) int i = 0;";
    String r = generate(in);
    String expected =
        "public Rdf c;/**/if (!(c != null && ((Rdf)c.getSingleValue(\"<dom:hasMother>\")) != null)) int i = 0;";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Rdf c;"));
  }

  @Test
  public void testBooleanRdfExists2() {
    String in = "Child c; void a() { if (!c.hasMother) int i = 0; }";
    String r = generate(in);
    String expected =
        "public Rdf c;/**/public void a() { if (!(c != null && "
        + "((Rdf)c.getSingleValue(\"<dom:hasMother>\")) != null)) int i = 0; } }";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Rdf c;"));
  }

  @Test
  public void testWrongMapperAccess() {
    String in = "Child getChild(int i); List<Child> out() { List<Child> raw = { }; " +
        "for (int i = 1; i < 5 && raw.size() < 3; i++) " +
        " { Child w = getChild(i); if (w) raw += w; } return raw; }";
    String r = generate(in);
    String expected =
        "public List<Rdf> out() { List<Rdf> raw = new ArrayList<>();"
        + "for ( int i = 1;i < 5 && raw.size() < 3;i = (i+1)){"
        + " Rdf w = getChild(i);if (w != null) raw.add(w); } return raw;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testCast5() {
    String in = "t = (QuizHistory) a;";
    String r = generate(in);
    String exp = "t = ((Rdf)a);";
    assertEquals(exp, getForMarked(r, exp));
    assertTrue(r.contains("Rdf t;"));
  }

  @Test
  public void testCast6() {
    String methdecl = " void foo(List<Child> cs) { c = cs.get(0); }";
    String s = generate(methdecl);
    String expected = "public void foo(List<Rdf> cs) { Rdf c = cs.get(0); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast7() {
    String methdecl = " void foo(List<Child> cs) { s = cs.get(0).forename; }";
    String s = generate(methdecl);
    String expected = "public void foo(List<Rdf> cs)"
        + " { String s = ((String)cs.get(0).getSingleValue(\"<dom:forename>\")); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCast8() {
    String methdecl = "Collection<LearningGoalType> foo(Child m) {"
        + " map(m.hasLearningGoal, (f) -> (((LearningGoal)f).goalType)); }";
    String s = generate(methdecl);
    String expected = "public Collection<Rdf> foo(Rdf m)"
        + " { map(((Set<Object>)m.getValue(\"<edu:hasLearningGoal>\")), (f) -> ((Rdf)((Rdf)f).getSingleValue(\"<edu:goalType>\"))); } }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testArrayAccess1() {
    String methdecl = "int[] test; x = test[1];";
    String s = generate(methdecl);
    String expected = "int x = test[1]";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test(expected=TypeException.class)
  public void testArrayAccess2() throws Throwable {
    // this should complain that a non-array type is accessed in an array way
    String in = "String test; x = test[1];";
    //String s = generate(in);
    getTypeError(in);
  }

  //
  @Test(expected=TypeException.class)
  public void testVoidFunction() throws Throwable {
    String in = " void fun(); k = fun();";
    getTypeError(in);
  }
}
