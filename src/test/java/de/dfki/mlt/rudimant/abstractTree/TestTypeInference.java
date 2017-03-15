package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static org.junit.Assert.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;

import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestTypeInference {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test() {
    String boolexp = ""
            + "public void foo() { b = 5 < 1; }";
    String s = generate(boolexp);
    String expected = "public void foo() {boolean b = (5 < 1);";
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
    RudiTree rt = parseAndTypecheck(in, null);
    Iterator<? extends RudiTree> it = rt.getDtrs().iterator();
    it.next();
    it.next();
    rt = it.next();
    assertTrue(rt instanceof ExpAssignment);
    ExpAssignment ass = (ExpAssignment)rt;
    assertEquals("boolean", ass.left.type);
  }

  @Test
  public void test3() {
    String in = "QuizHistory getCurrentTurn();"
            + " turn = getCurrentTurn(); ";
    String s = generate(in);
    String expected = "turn = getCurrentTurn();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf turn;"));
  }


  @Test
  public void test3a() {
    String in = "QuizHistory getCurrentTurn(); "
            + "Rdf turn = getCurrentTurn();";
    String s = generate(in);
    String expected = "turn = getCurrentTurn();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf turn;"));
  }

  @Test
  public void test4() {
    String in = "Activity a; "
            + "if (a == null) { }";
    String s = generate(in);
    String expected = "if ((a == null)) {}";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test5() {
    String in = "String foo(); "
            + "boolean b = foo(); ";
    String s = generate(in);
    String expected = "b = exists(foo());";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("boolean b;"));
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
    String in = " int b = 7; ";
    String s = generate(in);
    String expected = "b = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("int b;"));
  }

  @Test
  public void test8() {
    String in = " Quiz q; if(q.quizbool) i = 7; ";
    String s = generate(in);
    String expected = "if (exists(((Boolean)q.getSingleValue(\"<dom:quizbool>\")))) int i = 7;";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void test9() {
    String in = " Quiz q; if(q.tabletOrientation) i = 7; ";
    String s = generate(in);
    String expected = "if (exists(((String)q.getSingleValue(\"<dom:tabletOrientation>\"))))"
        + " int i = 7;";
    assertEquals(expected, getForMarked(s, expected));
  }

  /** TODO: Fix this problem (issue #55)
  @Test
  public void testUnknownBoolean() {
    String in = "Rdf r; if(r.has(\"prop\")) i = 1;";
    String s = generate(in);
    String expected = "if (r.has(\"prop\")) int i = 1; ";
    assertEquals(expected, getForMarked(s, expected));
  }
  */

}
