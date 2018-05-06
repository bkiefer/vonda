/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.getForMarked;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.setUpEmpty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anna
 */
public class WeirdCasesTest {
  
  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }
  
  @Test
  public void testFunction() {
    // TODO: Why the heck is this not parsing?
    
    String in = "boolean selfDisclose(String someNoun) {\n" +
"        if (p.activity)\n" +
"          da.activity = p.activity;\n" +
"        emitDA(da);\n" +
"        emitHardcodedDA((String) p.reason_da.iterator().next());\n" +
"        // }\n" +
"  return false;\n" +
"}";
    String r = generate(in);
    //String expected = "for (Object seat_outer : getSeats()) {"
    //        + " Object seat = (Object)seat_outer; { } }";
    //assertEquals(expected, getForMarked(r, expected));
  }
}
