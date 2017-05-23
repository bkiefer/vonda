package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.Type;
import de.dfki.mlt.rudimant.Visualize;
import de.dfki.mlt.rudimant.tree.ExpNew;
import de.dfki.mlt.rudimant.tree.RudiTree;

/**
 *
 * @author max
 */
public class ExpNewTest {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testRdfType(){
    String conditionalExp = "new Activity;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpNew);
    assertTrue(Type.isRdfType(((ExpNew)dtr).type));
  }

  @Test
  public void testJavaType() {
    String conditionalExp = "new JavaType();";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpNew);
  }

}
