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

}
