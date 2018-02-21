/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class ForStatementsTest {

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
    String expected = "public int initiate_greet(){ boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); initiate_greet: if (__x0[0]){ for (Object seat_outer : getSeats()) { Rdf seat = (Rdf)seat_outer; { } } } return 0; } }";
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
    String expected = "public int initiate_greet(){ boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); initiate_greet: if (__x0[0]){ for (Object k_outer : kids()) { Rdf k = (Rdf)k_outer; { } } } return 0; } }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test3() {
    String ifstat = "Set<Object> getI(); " +
            "for(s : getI()){"
            + "label: if(true) {s = null;}}";
    String s = generate(ifstat);
    String expected = "for (Object s_outer : getI()) { Object s = (Object)s_outer; { // Rule label boolean[] __x0 = new boolean[2]; __x0[0] = (__x0[1] = true); logRule(0, __x0); label: if (__x0[0]){ s = null; } } } }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test4() {
    String stat = "for (int i = 0; i < 10; ++i){}";
    String s = generate(stat);
    String expected = "for ( int i = 0;i < 10;i = (i+1)){ }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test5() {
    String stat = "List<Object> l; for (QuizHistory q : l){}";
    String s = generate(stat);
    String expected = "public List<Object> l;/**/for (Object q_outer : l) { Rdf q = (Rdf)q_outer; { } }";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("List<Object> l;"));
  }

  @Test
  public void testWhile() {
    String stat = "{ int n = 0; while ((n = random(7)) == correct) { n++; } }";
    String s = generate(stat);
    String expected = "{ int n = 0;while ((n = random(7)) == correct){ n = (n+1); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  // exp to statement in for
  @Test
  public void testForExp() {
    String in = "public int lab(){ for(s : child.sessions) 23; }";
    String exp = "public int lab() { for (Object s_outer : child.sessions) {"
            + " Object s = (Object)s_outer; 23; } }";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  // TODO: COMPLETE FOR ALL FOR LOOPS, WHILE, AND DO ... WHILE

}
