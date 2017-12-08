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
    String conditionalExp = "new Activity;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpNew);
    assertTrue(((ExpNew)dtr).type.isRdfType());
  }

  @Test
  public void testJavaType() {
    String conditionalExp = "new Integer();";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpNew);
    assertTrue(((ExpNew)dtr).type.toJava().equals("Integer"));
  }

  @Test
  public void testNewArray() {
    String conditionalExp = "x = new Integer[7];";

    String s = generate(conditionalExp);
    String expected = "Rdf i;if (true) { i = _proxy.getClass(\"<dom:Child>\").getNewInstance(DEFNS); }";
    assertEquals(expected, getForMarked(s, expected));
  }
}
