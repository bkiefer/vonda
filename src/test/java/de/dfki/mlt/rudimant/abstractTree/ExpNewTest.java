package de.dfki.mlt.rudimant.abstractTree;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.Visualize;
import de.dfki.mlt.rudimant.agent.nlg.Pair;

/**
 *
 * @author max
 */
public class ExpNewTest {

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
  public void testRdfType() throws IOException, WrongFormatException {
    String conditionalExp = "new RdfType;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(getInput(conditionalExp)));
    assertTrue(dtr instanceof ExpNew);
  }

  @Test
  public void testJavaType() throws IOException, WrongFormatException {
    String conditionalExp = "new JavaType;";

    RudiTree dtr = getNodeOfInterest(Visualize.parseAndTypecheck(getInput(conditionalExp)));
    assertTrue(dtr instanceof ExpNew);
  }

}
