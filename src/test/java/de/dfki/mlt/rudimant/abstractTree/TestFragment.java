package de.dfki.mlt.rudimant.abstractTree;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import de.dfki.mlt.rudimant.Visualize;
import de.dfki.mlt.rudimant.abstractTree.ExpIf;
import de.dfki.mlt.rudimant.abstractTree.GrammarFile;
import de.dfki.mlt.rudimant.abstractTree.GrammarRule;
import de.dfki.mlt.rudimant.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.abstractTree.StatAbstractBlock;
import de.dfki.mlt.rudimant.abstractTree.StatIf;

public class TestFragment {

  String header = "label: if(true) {";
  String footer = "}";

  public RudiTree getNodeOfInterest(RudiTree rt) {
    assertTrue(rt instanceof GrammarFile);
    GrammarFile gf = (GrammarFile) rt;
    GrammarRule dtr = (GrammarRule) gf.getDtrs().iterator().next();
    StatIf _if = (StatIf) dtr.getDtrs().iterator().next();
    StatAbstractBlock blk = (StatAbstractBlock)((StatIf) _if).statblockIf;
    return blk.getDtrs().iterator().next();
  }

  public InputStream getInput(String input) {
    String toParse = header + input + footer;
    return new ByteArrayInputStream(toParse.getBytes());
  }

  @Test
  public void test() throws IOException {
    String conditionalExp = "true ? 1 : 2;";

    RudiTree rt = Visualize.parseInput("conditional", getInput(conditionalExp));
    RudiTree dtr = getNodeOfInterest(rt);
    assertTrue(dtr instanceof ExpIf);
  }

}
