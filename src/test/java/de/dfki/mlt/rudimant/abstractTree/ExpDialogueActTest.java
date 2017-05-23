package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

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
    assertTrue(dtr instanceof ExpUFuncCall);
    assertTrue(((ExpUFuncCall)dtr).getDtrs().iterator().next()
        instanceof ExpDialogueAct);

  }

}
