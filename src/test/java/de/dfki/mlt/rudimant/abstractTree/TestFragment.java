package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

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

    String type_boolean = ((ExpConditional) dtr).boolexp.getType();
    assertEquals("boolean part of conditional", "boolean", type_boolean);

    String type_then = ((ExpConditional) dtr).thenexp.getType();
    assertEquals("then part", "int", type_then);

    String type_else = ((ExpConditional) dtr).elseexp.getType();
    assertEquals("else part", "int", type_else);
  }

}
