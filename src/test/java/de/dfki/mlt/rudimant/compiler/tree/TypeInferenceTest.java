package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.TypeException;
import de.dfki.mlt.rudimant.compiler.Visualize;
import de.dfki.mlt.rudimant.compiler.tree.ExpAssignment;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

public class TypeInferenceTest {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test() {
    String boolexp = ""
            + "public void foo() { b = 5 < 1; }";
    String s = generate(boolexp);
    String expected = "public void foo() { boolean b = (5 < 1);";
    assertEquals(expected, getForMarked(s, expected));


    // can't be tested like that because the memory is now handled correctly
    // and after leaving the last block is empty.
    /*
    parseAndTypecheck(boolexp);
    String type = rc.getMem().getVariableType("b");
    assertTrue(type.equals("boolean"));
    */
  }

  @Test
  public void testPredefFn() {
    String in = "boolean firstEncounter(); b = firstEncounter();";
    GrammarFile gf = parseAndTypecheck(in);
    Iterator<? extends RudiTree> it = gf.getDtrs().iterator();
    RudiTree rt = it.next();
    rt = it.next();
    assertTrue(rt instanceof StatVarDef);
    ExpAssignment ass = (ExpAssignment)((StatVarDef)rt).toAssign;
    assertEquals("boolean", ass.left.type.toJava());
  }

  @Test
  public void test3() {
    String in = "QuizHistory getCurrentTurn();"
            + " turn = getCurrentTurn(); ";
    String s = generate(in);
    String expected = "turn = getCurrentTurn();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public Rdf turn;"));
  }


  @Test
  public void test3a() {
    String in = "QuizHistory getCurrentTurn(); "
            + "Rdf turn = getCurrentTurn();";
    String s = generate(in);
    String expected = "turn = getCurrentTurn();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public Rdf turn;"));
  }

  @Test
  public void test4() {
    String in = "Activity a; if (a) { }";
    String s = generate(in);
    String expected = "public Rdf a;/**/if (a != null) { }";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf a;"));
  }

  @Test(expected=TypeException.class)
  public void test5() throws Throwable {
    String in = "String foo(); "
            + "int b = foo(); ";
    String s = getTypeError(in);
    String expected = "b = foo();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public int b;"));
  }

  @Test
  public void test6() {
    String in = " boolean b = true; ";
    String s = generate(in);
    String expected = "b = true;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("boolean b;"));
  }

  @Test
  public void test7() {
    String in = "public int b = 7; ";
    String s = generate(in);
    String expected = "b = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("int b;"));
  }

  @Test
  public void test8() {
    String in = " QuizHistory q; if(q.correct) i = 7; ";
    String s = generate(in);
    String expected = "public Rdf q;/**/if ((q != null && "
        + "((Boolean)q.getSingleValue(\"<dom:correct>\")))) int i = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf q;"));
  }

  @Test
  public void test9() {
    String in = "DiabetesEducation q;if(q.shortCode) i = 7; ";
    String s = generate(in);
    String expected = "public Rdf q;/**/if ((q != null && "
        + "exists(((String)q.getSingleValue(\"<edu:shortCode>\")))))"
        + " int i = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf q;"));
  }

  /** TODO: Fix this problem (issue #55)
   * aw: wouldn't an entry like [Rdf]. boolean has(String sth) in Agent.rudi fix this?
  @Test
  public void testUnknownBoolean() {
    String in = "Rdf r; if(r.has(\"prop\")) i = 1;";
    String s = generate(in);
    String expected = "if ((r != null && r.has(\"prop\"))) int i = 1; ";
    assertEquals(expected, getForMarked(s, expected));
  }
  */

  @Test
  public void test10() {
    String in = " double f; void fun() { EducationObjective c; c.hasProgress = 1.0; }";
    String s = generate(in);
    String expected = "public double f;/**/public void fun() { Rdf c;c.setValue(\"<edu:hasProgress>\", 1.0); } }";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("double f;"));
  }

  @Test(expected=TypeException.class)
  public void test11() throws Throwable {
    String in = " int fun(int i); long l = 1; k = fun(l);";
    getTypeError(in);
  }

  @Test
  public void test12() {
    String in = " long fun(long i); int l = 1; k = fun(l);";
    String s = generate(in);
    String expected = "l = 1;k = fun(l);";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public int l;"));
    assertTrue(s.contains("public long k;"));
  }

  @Test(expected=TypeException.class)
  public void test13() throws Throwable {
    String in = " void fun(); l = fun();";
    getTypeError(in);
    int i = 0;
  }

  @Test
  public void testPartUnkDecl() {
    String in = "somevar = getSomething();" +
        "sum1 = 3 + somevar;sum2 = somevar + 3;";
    String s = generate(in);
    String expected = "somevar = (Object /* (unknown) */) getSomething();" +
        "sum1 = (3+somevar);sum2 = (somevar+3);";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("public int sum1;"));
    assertTrue(s.contains("public int sum2;"));
  }
}
