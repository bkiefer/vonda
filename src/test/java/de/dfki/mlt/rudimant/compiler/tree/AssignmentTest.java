package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.compiler.tree.ExpAssignment;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author max
 */
public class AssignmentTest {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testAssignment1() throws IOException {
    String assignmentExp = "test = 4;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(assignmentExp));
    assertTrue(dtr instanceof StatVarDef);
    String type = ((ExpAssignment)((StatVarDef) dtr).toAssign).right.getType().toJava();
    assertEquals("right side type of test = 4 should be int", "int", type);
  }

  @Test
  public void testAssignment2() throws IOException {
    String assignmentExp = "test = (4>5);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(assignmentExp));
    assertTrue(dtr instanceof StatVarDef);
    String type = ((ExpAssignment)((StatVarDef) dtr).toAssign).right.getType().toJava();
    assertEquals("right side type of test = (4>5) should be boolean", "boolean", type);

  }

  @Test
  public void testAssignment3() throws IOException {
    String assignmentExp = "boolean test = 4+5 != 0;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(assignmentExp));
    assertTrue(dtr instanceof StatVarDef);

    String type_right = ((ExpAssignment)((StatVarDef) dtr).toAssign).left.getType().toJava();
    assertEquals("var type should still be boolean", "boolean", type_right);
  }

  @Test
  public void testAssignment3a() throws IOException {
    String assignmentExp = "boolean test = 4+5;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(assignmentExp));
    assertTrue(dtr instanceof StatVarDef);

    String type_right = ((ExpAssignment)((StatVarDef) dtr).toAssign).left.getType().toJava();
    assertEquals("var type should still be boolean", "boolean", type_right);
  }

  @Test
  public void testAssignment3b() throws IOException {
    String assignmentExp = "boolean test = 4+5;";
    String out = generate(assignmentExp);
    String exp = "boolean test = (4+5) != 0";

    assertEquals(exp, getForMarked(out, exp));
  }

  @Test
  public void testAssignment4() throws IOException, WrongFormatException {
    String assignmentExp = "test = true; test2 = 1; test3 = test2; test4 = 2;";

    GrammarFile gf = parseAndTypecheck(getInput(assignmentExp));
    RudiTree dtr = getNodeOfInterest(gf, 0); // test = true
    assertTrue(dtr instanceof StatVarDef);

    String type_right = ((ExpAssignment)((StatVarDef) dtr).toAssign).right.getType().toJava();
    assertEquals("right side type test should be boolean", "boolean", type_right);

    dtr = getNodeOfInterest(gf, 1); // test2 = 1
    type_right = ((ExpAssignment)((StatVarDef) dtr).toAssign).right.getType().toJava();
    assertEquals("right side type test2 should be int", "int", type_right);

    dtr = getNodeOfInterest(gf, 2); // test3 = test2
    type_right = ((ExpAssignment)((StatVarDef) dtr).toAssign).right.getType().toJava();
    assertEquals("right side type test3 should be int", "int", type_right);

    dtr = getNodeOfInterest(gf, 3); // test3 = test2
    type_right = ((ExpAssignment)((StatVarDef) dtr).toAssign).right.getType().toJava();
    assertEquals("right side type test4 should be int", "int", type_right);
  }

}
