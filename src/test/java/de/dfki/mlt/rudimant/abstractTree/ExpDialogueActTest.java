package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.setUp;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.RESOURCE_DIR;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author max
 */
public class ExpDialogueActTest {

  static String header = "label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  @Test
  public void test() throws IOException {
    // TODO
//    String conditionalExp = "DialogueAct emitDA(DialogueAct dadirgendwas); \n emitDA(#Inform(Answer, what=solution));";

//    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("DialogueAct", getInput(conditionalExp));
//    RudiTree dtr = getNodeOfInterest(rt.first);
//    assertTrue(dtr instanceof ExpDialogueAct);

  }

}
