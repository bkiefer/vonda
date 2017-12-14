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

}
