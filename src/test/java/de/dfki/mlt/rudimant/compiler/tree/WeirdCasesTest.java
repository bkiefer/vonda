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
  public void testCastInFunction() {
    
    String in = "boolean selfDisclose(String someNoun) {\n" +
"        if (p.activity)\n" +
"          da.activity = p.activity;\n" +
"        emitDA(da);\n" +
"        emitHardcodedDA((String) p.reason_da.iterator().next());\n" +
"        // }\n" +
"  return false;\n" +
"}";
    //String r = generate(in);
    //String expected = "for (Object seat_outer : getSeats()) {"
    //        + " Object seat = (Object)seat_outer; { } }";
    //assertEquals(expected, getForMarked(r, expected));
  }
  
  @Test
  public void testLambda() {
    
    String in = "lab: if(true) {Child c; known2 = filter(c.hasActivities,\n" +
"                     (p) -> \"someName\".equals(((Activity)p).name));}";
    String r = generate(in);
    String expected = "public int lab(){ boolean[] __x0 = new boolean[2];"
            + " __x0[0] = (__x0[1] = true); logRule(0, __x0); lab: " +
              "if (__x0[0]){ Rdf c;Object /* (unknown) */ known2 = (Object "
            + "/* (unknown) */) filter(((Set<Object>)c.getValue(\"hasActivities\")), "
            + "(p) -> \"someName\".equals(((Set<Object>)((Rdf)p).getValue(\"<upper:name>\"))));";
    assertEquals(expected, getForMarked(r, expected));
  }
  
  @Test
  public void testPlusPlus() {
    String in = "int f(int i) {++i; return i++;}";
    String r = generate(in);
    String expected = "public int f(int i) { i = (i+1); return i = (i+1);";
    assertEquals(expected, getForMarked(r, expected));
  }
}
