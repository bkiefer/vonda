package de.dfki.mlt.rudimant.abstractTree;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import java.util.Iterator;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author max
 */
public class ExpAssignmentTest {

  static String header = "label: if(true) {";
  static String footer = "}";

  static public RudiTree getNodeOfInterest(RudiTree rt) {
    return getNodeOfInterest(rt, 0);
  }

  /**
   * Returns the nth node of interest.
   * @param rt RudiTree
   * @param n Specifies which node to return.
   * @return node of interest
   */
  static public RudiTree getNodeOfInterest(RudiTree rt, int n) {
    assertTrue(rt instanceof GrammarFile);
    GrammarFile gf = (GrammarFile) rt;
    GrammarRule dtr = (GrammarRule) gf.getDtrs().iterator().next();
    StatIf _if = (StatIf) dtr.getDtrs().iterator().next();
    StatAbstractBlock blk = (StatAbstractBlock) ((StatIf) _if).statblockIf;
    Iterator<? extends RudiTree> it = blk.getDtrs().iterator();
    for (int i=0; i<n; i++){
      it.next();
    }
    return it.next();
  }

  static public InputStream getInput(String input) {
    String toParse = header + input + footer;
    return new ByteArrayInputStream(toParse.getBytes());
  }

  @Test
  public void testAssignment1() throws IOException {
    String assignmentExp = "test = 4;";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("assignment", getInput(assignmentExp));
    RudiTree dtr = getNodeOfInterest(rt.first);
    assertTrue(dtr instanceof ExpAssignment);
    String type = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type of test = 4 should be int", "int", type);
  }

  @Test
  public void testAssignment2() throws IOException {
    String assignmentExp = "test = (4>5);";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("assignment", getInput(assignmentExp));
    RudiTree dtr = getNodeOfInterest(rt.first);
    assertTrue(dtr instanceof ExpAssignment);
    String type = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type of test = (4>5) should be boolean", "boolean", type);

  }

  @Test
  public void testAssignment3() throws IOException {
    String assignmentExp = "boolean test = 4+5;";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("assignment", getInput(assignmentExp));
    RudiTree dtr = getNodeOfInterest(rt.first);
    assertTrue(dtr instanceof ExpAssignment);

    String type_right = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type of boolean test = 4+5 should be int", "int", type_right);

//    String type_left = ((RTExpression)((ExpAssignment) dtr).left).getType();
//    assertEquals("right side type of boolean test = 4+5 should be int", "boolean", type_left);

  }

  @Test
  public void testAssignment4() throws IOException {
    String assignmentExp = "test = true; test2 = 1; test3 = test2; test4 = 2;";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("assignment", getInput(assignmentExp));
    RudiTree dtr = getNodeOfInterest(rt.first, 0); // test = true
    assertTrue(dtr instanceof ExpAssignment);

    String type_right = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type test should be boolean", "boolean", type_right);

    dtr = getNodeOfInterest(rt.first, 1); // test2 = 1
    type_right = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type test2 should be int", "int", type_right);

    dtr = getNodeOfInterest(rt.first, 2); // test3 = test2
    type_right = ((ExpAssignment) dtr).right.getType();
    // TODO this should be possible.
    assertEquals("right side type test3 should be int", "int", type_right);
  }

}
