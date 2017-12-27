/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.*;

/**
 *
 * @author christophe
 */
public class LambdaTest {
  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  /*
    lambda_exp:
    '(' (DEC_VAR? VARIABLE (',' DEC_VAR? VARIABLE)*)? ')' ARROW exp;
   */

  @Test
  public void testLambdaExp() {
    String in = "Set<Child> cs; cs.contains((c) -> ((Child)c).forename.equals(\"John\"));";
    String r = generate(in);
    String expected = "public Set<Rdf> cs;/**/cs.contains((c) -> "
            + "((String)((Rdf)c).getSingleValue(\"<dom:forename>\")).equals(\"John\"));";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Rdf> cs;"));
  }

  @Test
  public void testComplexLambdaExp() {
    String in = "Set<Child> cs; cs.contains((c) -> {((Child)c).forename.equals(\"John\");});";
    String r = generate(in);
    String expected = "public Set<Rdf> cs;/**/cs.contains((c) -> {"
            + " ((String)((Rdf)c).getSingleValue(\"<dom:forename>\")).equals(\"John\"); } );";
    assertEquals(expected, getForMarked(r, expected));
    assertTrue(r.contains("Set<Rdf> cs;"));
  }

  @Test
  public void test1() {
    String in = "Quiz p; p.hasHistory.contains((c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;/**/((Set<Object>)p.getValue(\"<dom:hasHistory>\"))"
        + ".contains((c) -> (((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test2() {
    String in = "Quiz p; h = p.hasHistory; h.contains((c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public Set<Object> h;/**/h = ((Set<Object>)p.getValue(\"<dom:hasHistory>\"));"
        + "h.contains((c) -> (((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test3() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;/**/h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> (((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test4() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1); x = h.get(1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;/**/h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> (((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1));x = (Rdf) ((Rdf)h.get(1));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void test5() {
    String in = "Quiz p; h = filter(p.hasHistory, (c) -> c.turnId == 1); x = h.get(1); y = h.get(2);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;public Rdf y;/**/h = filter(((Set<Object>)p.getValue(\"<dom:hasHistory>\")),"
        + " (c) -> (((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1));x = (Rdf) ((Rdf)h.get(1));y = (Rdf) ((Rdf)h.get(2));";
    assertEquals(expected, getForMarked(r, expected));
  }
/* TODO: should this work or not?
  @Test
  public void test5() {
    String in = "Quiz p; h = p.hasHistory.filter((c) -> c.turnId == 1); x = h.get(1);";
    String r = generate(in);
    String expected = "public Rdf p;public List<Object> h;public Rdf x;"
        + "/*h = ((Set<Object>)p.getValue(\"<dom:hasHistory>\")).filter("
        + " (c) -> (((Integer)((Rdf)c).getSingleValue(\"<dom:turnId>\")) == 1)); x = h.get(1);";
    assertEquals(expected, getForMarked(r, expected));
  }
*/
}
