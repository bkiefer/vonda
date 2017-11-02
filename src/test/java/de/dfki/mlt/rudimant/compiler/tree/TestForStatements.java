/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class TestForStatements {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test1() {
    String ifstat = "List<Child> getSeats();\n" +
                    "Iterator<DialogueAct> lastDAs();\n" +
                    "initiate_greet: if(true){ for(seat : getSeats()){} }";
    String s = generate(ifstat);
    String expected = "public int initiate_greet(){"
            + " initiate_greet: if (true) { for (Object seat_outer : getSeats()) "
            + "{ Rdf seat = (Rdf)seat_outer; { } } } return 0;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test2() {
    String ifstat = "Seat getSeats();\n" +
                    "Iterator<Child> kids();\n" +
                    "initiate_greet:\n" +
                    "  if(true){\n" +
                    "    for(k : kids()){}}";
    String s = generate(ifstat);
    String expected = "public int initiate_greet(){"
            + " initiate_greet: if (true) {"
            + " for (Object k_outer : kids()) { Rdf k = (Rdf)k_outer; { } } }"
            + " return 0;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test3() {
    String ifstat = "Set<Object> getI(); " +
            "for(s : getI()){"
            + "label: if(true) {s = null;}}";
    String s = generate(ifstat);
    String expected = "for (Object s_outer : getI()) {"
            + " Object s = (Object)s_outer; " +
              "{ // Rule label " +
              "label: " +
              "if (true) { s = null; " +
              "}";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test4() {
    String stat = "for (int i = 0; i < 10; i++){}";
    String s = generate(stat);
    String expected = "for ( int i = 0; (i < 10); i = (i+1)){ }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test5() {
    String stat = "List<Object> l; for (QuizHistory q : l){}";
    String s = generate(stat);
    String expected = "for (Object q_outer : l) { Rdf q = (Rdf)q_outer; { } }";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("List<Object> l;"));
  }

  @Test
  public void testComments() {
    String stat = "/*@  public String preBlock() { return \"preBlock\";} @*/"
        + "demo_rule: if (true) break demo_rule; "
        + "/*@ public String postBlock() { return \"postBlock\"; }@*/";
    String s = generate(stat);
    String expected = "public String preBlock() { return \"preBlock\";}"
        + " public int demo_rule(){ demo_rule: if (true) break demo_rule; return 0; } "
        + "public String postBlock() { return \"postBlock\"; }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testWhile() {
    String stat = "{ int n = 0; while ((n = random(7)) == correct) { n++; } }";
    String s = generate(stat);
    String expected = "{ int n = 0; while ((( n = random(7)) == correct)){ n = (n+1); }";
    assertEquals(expected, getForMarked(s, expected));
  }


}
