/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

/**
 *
 * @author christophe
 */
public class ExpLambdaTest {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  /*
    lambda_exp:
    '(' (DEC_VAR? VARIABLE (',' DEC_VAR? VARIABLE)*)? ')' ARROW exp;
   */

  @Test
  public void test() {
    String in = "a = (dingsbums, dingsbams) -> true;";
    // according to Anna, this function doesn't work yet (1.12.2016)
    // RudiTree dtr = getNodeOfInterest(parseAndTypecheck(in));
    // assertTrue(dtr instanceof ExpLambda);

  }

}
