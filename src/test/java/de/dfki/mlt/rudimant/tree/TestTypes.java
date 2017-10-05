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

import de.dfki.mlt.rudimant.TypeException;

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
    String expected = "reply = myLastDA().copy();";
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
  public void testReturnSetType() {
    String in = "Child c;  docs = c.isTreatedBy;";
    String r = generate(in);
    String expected = "Rdf c; docs = ((Set<Object>)c.getValue(\"<dom:isTreatedBy>\"));";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Object> docs;"));
  }

  @Test
  public void testLambdaExp() {
    String in = "Set<Child> cs; cs.contains((c) -> c.foreName.equals(\"John\"));";
    String r = generate(in);
    String expected = "Set<Rdf> cs;cs.contains((c) -> "
            + "((Set<Object>)c.getValue(\"foreName\")).equals(\"John\"));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testComplexLambdaExp() {
    String in = "Set<Child> cs; cs.contains((c) -> {c.foreName.equals(\"John\");});";
    String r = generate(in);
    String expected = "Set<Rdf> cs;cs.contains((c) -> {"
            + " ((Set<Object>)c.getValue(\"foreName\")).equals(\"John\"); } );";
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
        "Rdf c;if (!((c != null && ((Rdf)c.getSingleValue(\"<dom:hasMother>\")) != null))) int i = 0;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBooleanRdfExists2() {
    String in = "Child c; a: if (!c.hasMother) int i = 0;";
    String r = generate(in);
    String expected =
        "Rdf c;res = a(); if (res != 0) return (res - 1); return 0; }"
        + " public int a(){ boolean a0 = false; a0 = !((c != null && "
        + "((Rdf)c.getSingleValue(\"<dom:hasMother>\")) != null));";
    assertEquals(expected, getForMarked(r, expected));
  }

  /* TODO: THIS PRODUCES TOTAL JUNK. To be fixed.
  @Test
  public void testBooleanRdfExists3() {
    String in = "Child c; a: if (true && false) int i = 0;";
    String r = generate(in);
    String expected =
        "a(); return false; } public boolean a(){ boolean a0 = false; a0 = true if (null) { false a0 = null && null } else { a0 = lasdjf;laksjf;lasjdflja;sldkfj;laskjdf;laskjdf;lk||";
    assertEquals(expected, getForMarked(r, expected));
  }
  */

  @Test
  public void testWrongMapperAccess() {
    String in = "Child getChild(int i); List<Child> out() { List<Child> raw = { }; " +
        "for (int i = 1; i < 5 && raw.size() < 3; i++) " +
        " { Child w = getChild(i); if (w) raw += w; } return raw; }";
    String r = generate(in);
    String expected =
        "List<Rdf> out() { List<Rdf> raw = new ArrayList<>();"
        + " for ( int i = 1; ((i < 5) && (raw.size() < 3)); i = (i+1)){"
        + " Rdf w = getChild(i); if (w != null) raw.add(w); } return raw;";
    assertEquals(expected, getForMarked(r, expected));
  }

  //
  @Test(expected=TypeException.class)
  public void testVoidFunction() throws Throwable {
    String in = " void fun(); k = fun();";
    //String r = generate(in);
    //System.out.println(r);
    getTypeError(in);
  }
}
