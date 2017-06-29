package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.tree.ExpConditional;
import de.dfki.mlt.rudimant.tree.RudiTree;

public class TestFragment {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void test() {
    String conditionalExp = "true ? 1 : 2;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(conditionalExp));
    assertTrue(dtr instanceof ExpConditional);

    String type_boolean = ((ExpConditional) dtr).boolexp.getType().get_name();
    assertEquals("boolean part of conditional", "boolean", type_boolean);

    String type_then = ((ExpConditional) dtr).thenexp.getType().get_name();
    assertEquals("then part", "int", type_then);

    String type_else = ((ExpConditional) dtr).elseexp.getType().get_name();
    assertEquals("else part", "int", type_else);
  }

}
