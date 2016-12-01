/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.abstractTree.ExpIf;
import de.dfki.mlt.rudimant.abstractTree.GrammarFile;
import de.dfki.mlt.rudimant.abstractTree.GrammarRule;
import de.dfki.mlt.rudimant.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.abstractTree.StatAbstractBlock;
import de.dfki.mlt.rudimant.abstractTree.StatIf;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author christophe
 */
public class ExpLambdaTest {

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

    /*
    lambda_exp:
    '(' (DEC_VAR? VARIABLE (',' DEC_VAR? VARIABLE)*)? ')' ARROW exp;
    */

  @Test
  public void test() throws IOException {
    String conditionalExp = "( dingsbums, dingsbams) -> TRUE;";

    Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("conditional", getInput(conditionalExp));
    RudiTree dtr = getNodeOfInterest(rt.first);
    //assertEquals("Wrong class:", ExpLambda.class, dtr.getClass());
  }

}
