package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.tree.ExpDialogueAct;
import de.dfki.mlt.rudimant.compiler.tree.ExpFuncCall;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

/**
 *
 * @author max
 */
public class DialogueActTest {

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUpNonEmpty();
  }

  @Test
  public void test() throws IOException {
    String in = "emitDA(#Inform(Answer, what=solution));";
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(in));
    assertTrue(dtr instanceof ExpFuncCall);
    assertTrue(((ExpFuncCall)dtr).getDtrs().iterator().next()
        instanceof ExpDialogueAct);

  }

  @Test
  public void testDA1() {
    String in = "inf = \"Inform\"; emitDA(#^inf(Answer, what=solution));";
    String r = generate(in);
    String expected = "String inf = \"Inform\";"
        + "emitDA(new DialogueAct(inf, \"Answer\", \"what\", \"solution\"));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testDA2() {
    String in = "emitDA(#Inform(Answer, what=^random(10)));";
    String r = generate(in);
    String expected = "emitDA(new DialogueAct(\"Inform\", \"Answer\", \"what\", Integer.toString(random(10))));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testToStringDA() {
    // Test set string var with POD type
    String in = "DialogueAct c = #Confirm(Correct, value=^10);";
    String s = generate(in);
    String expected =
        "DialogueAct c = new DialogueAct(\"Confirm\", \"Correct\", \"value\", Integer.toString(10))";
    assertEquals(expected, getForMarked(s, expected));
  }

}
