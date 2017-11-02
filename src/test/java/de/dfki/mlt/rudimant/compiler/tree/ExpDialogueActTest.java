package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;
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
public class ExpDialogueActTest {

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
    String expected = "String inf = \"Inform\"; "
        + "emitDA(new DialogueAct(inf, \"Answer\", \"what\", \"solution\"));";
    assertEquals(expected, getForMarked(r, expected));
  }

}
