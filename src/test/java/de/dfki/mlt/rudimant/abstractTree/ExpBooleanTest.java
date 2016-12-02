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
public class ExpBooleanTest {

  /*
  /// boolean operators:
NOT: '!';
EQUAL: '==';
AND1: '&'; TODO deprecated?
OR1: '|'; TODO deprecated?
AND2: '&&';
OR2: '||';
NOT_EQUAL: '!=';
SMALLER_EQUAL: '<=';
SMALLER: '<';
GREATER_EQUAL: '>=';
GREATER: '>';

   */
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
  public void testBoolean() throws IOException {
    String booleanExp = "4 < 5;";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", getInput(booleanExp));
    RudiTree dtr = getNodeOfInterest(rt.first);
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean2() throws IOException {
    String booleanExp = "4 == 5;";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", getInput(booleanExp));
    RudiTree dtr = getNodeOfInterest(rt.first);
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean3() throws IOException {
    String booleanExp = "false;";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", getInput(booleanExp));
    RudiTree dtr = getNodeOfInterest(rt.first);
    // TODO dtr is instanceof UVariable. Is this correct?
    // dtr.getType is "boolean" as String. Is this correct?

     assertTrue(dtr instanceof USingleValue);
     assertEquals("false should be of type Boolean", "boolean", (((RTExpression) dtr).getType()));
  }

  @Test
  public void testBoolean4() throws IOException {
    String booleanExp = "!(5>5);";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", getInput(booleanExp));
    RudiTree dtr = getNodeOfInterest(rt.first);

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean5() throws IOException {
    String booleanExp = "(False && False || True);";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", getInput(booleanExp));
    RudiTree dtr = getNodeOfInterest(rt.first);

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean6() throws IOException {
    String booleanExp = "(False & False | True);";
// TODO should this work? we get a nullpointer here.

//    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", getInput(booleanExp));
//    RudiTree dtr = getNodeOfInterest(rt.first);
//     assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean7() throws IOException {
    String booleanExp = "(var1 != var2);";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", getInput(booleanExp));
    RudiTree dtr = getNodeOfInterest(rt.first);

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean8() throws IOException {
    String booleanExp = "(var1 >= var2 && var1 <= var2);";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("boolean", getInput(booleanExp));
    RudiTree dtr = getNodeOfInterest(rt.first);

    assertTrue(dtr instanceof ExpBoolean);
  }

}
