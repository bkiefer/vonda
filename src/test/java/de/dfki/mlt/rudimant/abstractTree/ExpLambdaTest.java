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
import de.dfki.mlt.rudimant.abstractTree.ExpConditional;
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


    /*
    lambda_exp:
    '(' (DEC_VAR? VARIABLE (',' DEC_VAR? VARIABLE)*)? ')' ARROW exp;
    */

  @Test
  public void test() throws IOException {
    String conditionalExp = "( dingsbums, dingsbams) -> TRUE;";

    // Pair<GrammarFile, LinkedList<Token>> rt = RudimantCompiler.parseInput("conditional", getInput(conditionalExp));
    // RudiTree dtr = getNodeOfInterest(rt.first);
    // assertEquals("Wrong class:", ExpLambda.class, dtr.getClass());
    // according to Anna, this function doesn't work yet (1.12.2016)
  }

}
