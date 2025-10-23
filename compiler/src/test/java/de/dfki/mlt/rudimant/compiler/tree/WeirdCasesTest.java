/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.tree.TestHelper.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.getForMarked;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.setUpEmpty;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.io.BisonParser;

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
  public void testLambda() throws InterruptedException {
    String in = "lab: if(true) {Child c; "
        + "known2 = filter(c.hasHobby, lambda(p) \"someName\".equals((isa(Preference, p)).name));}";
    String r = generate(in);
    String expected = "// Rule lab {boolean[] __x0 = new boolean[2];"
            + " __x0[0] = (__x0[1] = true); logRule(0, __x0); lab: "
            + "if (__x0[0]){ Rdf c;"
            + "List<Object> known2 = "
            + "filter(c.getValue(\"<dom:hasHobby>\"), "
            + "(p) -> \"someName\".equals(((Rdf)p).getString(\"<dom:name>\")));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testPlusPlusFunc() {
     // not in ArithmeticTest because of empty setup
    String in = "int f(int i) {++i; return i++;}";
    String r = generate(in);
    String expected = "public int f(int i) { ++i; return i++;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testDefinedRdfList() {
    String in = "List<Quiz> q(int i); { m = q(i); }";
    String r = generate(in);
    String expected = "/**/{ List<Rdf> m = q(i); }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testVarRedefBlock() {
    String in = "List<String> h; { int h = 0; }";
    String r = generate(in);
    String expected = "public List<String> h;/**/{ int h = 0; } }";
    assertEquals(expected, getForMarked(r, expected));
  }

  /* "repaired" by adding def for containsKey  */
//  @Test
//  public void testStrangeMethCall() {
//    String in = "Map<String, Integer> e; boolean b = !e.containsKey(\"Glycemia\"); ";
//    String r = generate(in);
//    String expected = "public Map<String, Integer> e;public boolean b;/**/"
//        + "b = !(exists(e) && e.containsKey(\"Glycemia\"));";
//    assertEquals(expected, getForMarked(r, expected));
//  }

  @Test
  public void testNormalComment() {
    String in = "int x;\n //This is a comment referring to the assignment \n"
            + " x = 5;";
    String r = generate(in);
    String expected = "public int x;/**/ //This is a comment referring to the assignment"
            + " x = 5;}";
    assertEquals(expected, getForMarked(r, expected));
  }


  @Test
  public void testJavaComment() {
    // problem: how to differentiate between vardefs, which we'd like do be
    // fielddefs, and 'normal' java code that is actually intended to end up in
    // process?
    String in = "int x;\n /*@ String javaVar = \"class variable\";@*/ \n"
            + " x = 5;";
    String r = generate(in);
    String expected = "public int x;/**/ String javaVar = \"class variable\";"
            + "x = 5;}";
    //System.out.println(r);
    assertEquals(expected, getForMarked(r, expected));
  }

}
