package de.dfki.mlt.rudimant.abstractTree;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author max
 */
public class ExpAssignmentTest {

  String header = "label: if(true) {";
  String footer = "}";

  public RudiTree getNodeOfInterest(RudiTree rt) {
    assertTrue(rt instanceof GrammarFile);
    GrammarFile gf = (GrammarFile) rt;
    GrammarRule dtr = (GrammarRule) gf.getDtrs().iterator().next();
    StatIf _if = (StatIf) dtr.getDtrs().iterator().next();
    StatAbstractBlock blk = (StatAbstractBlock) ((StatIf) _if).statblockIf;
    return blk.getDtrs().iterator().next();
  }

  public InputStream getInput(String input) {
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

    String type_rigt = ((ExpAssignment) dtr).right.getType();
    assertEquals("right side type of boolean test = 4+5 should be int", "int", type_rigt);

//    String type_left = ((RTExpression)((ExpAssignment) dtr).left).getType();
//    assertEquals("right side type of boolean test = 4+5 should be int", "boolean", type_left);

  }

}