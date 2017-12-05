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
    // TODO: this is now working and already tested in TestTypes.testLambdaExp, add examples that make sense here!
  }

}
