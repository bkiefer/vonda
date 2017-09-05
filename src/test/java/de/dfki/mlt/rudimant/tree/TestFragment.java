package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.TypeException;
import de.dfki.mlt.rudimant.tree.ExpConditional;
import de.dfki.mlt.rudimant.tree.RudiTree;

public class TestFragment {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void test1() {
    String conditionalExp = "true ? 1 : 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpConditional);

    String type_boolean = ((ExpConditional) dtr).boolexp.getType().toString();
    assertEquals("boolean part of conditional", "boolean", type_boolean);

    String type_then = ((ExpConditional) dtr).thenexp.getType().toString();
    assertEquals("then part", "int", type_then);

    String type_else = ((ExpConditional) dtr).elseexp.getType().toString();
    assertEquals("else part", "int", type_else);
  }
  
  @Test
  public void testFinal1() {
    String fin = "final boolean b = true;";
    String s = generate(fin);
    String expected = "final boolean b = true;";
    assertEquals(expected, getForMarked(s, expected));
  }
  
  @Test
  public void testFinal2() {
    String fin = "final b = true;";
    String s = generate(fin);
    String expected = "final boolean b = true;";
    assertEquals(expected, getForMarked(s, expected));
  }
  
  // TODO: do we really need to check this?
  /*
  @Test(expected=TypeException.class)
  public void testFinal3() throws Throwable {
    String fin = "final boolean b = true; b = false;";
    getTypeError(fin);
  }*/
}
