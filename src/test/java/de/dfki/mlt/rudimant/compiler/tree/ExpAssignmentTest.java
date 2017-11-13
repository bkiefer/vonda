package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;
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
public class ExpAssignmentTest {

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
    String assignmentExp = "boolean test = 4+5;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(assignmentExp));
    assertTrue(dtr instanceof StatVarDef);

    String type_right = ((ExpAssignment)((StatVarDef) dtr).toAssign).left.getType().toJava();
    assertEquals("var type should still be boolean", "boolean", type_right);

//    String type_left = ((RTExpression)((ExpAssignment) dtr).left).getType();
//    assertEquals("right side type of boolean test = 4+5 should be int", "boolean", type_left);

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
  }

}
