package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.Visualize;
import de.dfki.mlt.rudimant.compiler.tree.ExpNew;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

/**
 *
 * @author max
 */
public class NewTest {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testRdfType(){
    // TODO: Do we allow new_exp to be a statement or not?
    String src = "new Activity;";
    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(src));
    assertTrue(dtr instanceof ExpNew);
    assertTrue(((ExpNew)dtr).type.isRdfType());
  }

  @Test
  public void testJavaType() {
    // TODO: Do we allow new_exp to be a statement or not?
    String src = "new Integer();";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(src));
    assertTrue(dtr instanceof ExpNew);
    assertTrue(((ExpNew)dtr).type.toJava().equals("Integer"));
  }

  @Test
  public void testNewArray() {
    String src = "x = new Integer[7];";

    String s = generate(src);
    String expected = "Integer[] x = new Integer[7];";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testArrayInitialization() {
    String src = "int[] x = { 1, 2 };";

    String s = generate(src);
    String expected = "int[] x = {1, 2};";
    assertEquals(expected, getForMarked(s, expected));
  }
}
