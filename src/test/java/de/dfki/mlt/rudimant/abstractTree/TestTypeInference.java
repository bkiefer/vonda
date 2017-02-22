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
    String boolexp = "void foo() { b = 5 < 1; }";

    String s = generate(boolexp);
    s = normalizeSpaces(s);
    String expected = "void foo() {boolean b = (5 < 1); }} ";
    expected = normalizeSpaces(expected);
    assertEquals(expected, s.substring(s.length() - expected.length()));


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
    rt = it.next();
    assertTrue(rt instanceof ExpAssignment);
    ExpAssignment ass = (ExpAssignment)rt;
    assertEquals("boolean", ass.left.type);
  }

  @Test
  public void test3() {
    String in = "QuizHistory getCurrentTurn(); turn = getCurrentTurn();";
    String s = generate(in); s = normalizeSpaces(s);
    String expected = "Rdf turn = getCurrentTurn();} ";
    expected = normalizeSpaces(expected);
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }


  @Test
  public void test3a() {
    String in = "QuizHistory getCurrentTurn(); Rdf turn = getCurrentTurn();";
    String s = generate(in); s = normalizeSpaces(s);
    String expected = "Rdf turn = getCurrentTurn();} ";
    expected = normalizeSpaces(expected);
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

  @Test
  public void test4() {
    String in = "Activity a; if (a == null) { }";
    String s = generate(in); s = normalizeSpaces(s);
    String expected = "if ((a == null)) {}} ";
    expected = normalizeSpaces(expected);
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

  @Test
  public void test5() {
    String in = "String foo(); boolean b = foo(); ";
    String s = generate(in); s = normalizeSpaces(s);
    String expected = "boolean b = foo().isEmpty();} ";
    expected = normalizeSpaces(expected);
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

  @Test
  public void test6() {
    String in = "boolean b = true; ";
    String s = generate(in); s = normalizeSpaces(s);
    String expected = "boolean b = true;} ";
    expected = normalizeSpaces(expected);
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

  @Test
  public void test7() {
    String in = "int b = 7; ";
    String s = generate(in); s = normalizeSpaces(s);
    String expected = "int b = 7;} ";
    expected = normalizeSpaces(expected);
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

}
