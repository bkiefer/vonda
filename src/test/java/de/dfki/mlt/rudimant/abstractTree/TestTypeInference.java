package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.confDir;
import static de.dfki.mlt.rudimant.Visualize.configs;
import static de.dfki.mlt.rudimant.Visualize.generate;
import static de.dfki.mlt.rudimant.Visualize.readConfig;
import static org.junit.Assert.*;
import static visitortests.SeriousTest.RESOURCE_DIR;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.antlr.v4.runtime.Token;
import org.apache.thrift.TException;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.agent.nlg.Pair;

public class TestTypeInference {

  String header = "";
  String footer = "";
  RudimantCompiler rc = null;

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

  public RudiTree parseAndTypecheck(String input) throws IOException {
    String inputRealName = "";

    // create the abstract syntax tree
    Pair<GrammarFile, LinkedList<Token>> myTree
      = RudimantCompiler.parseInput(inputRealName, getInput(input));

    // do the type checking
    try {
      rc = RudimantCompiler.init(confDir, configs, null);
      new VTestTypeVisitor(rc).visitNode(myTree.first);
    } catch (WrongFormatException ex) {
      throw new RuntimeException(ex);
    }
    return myTree.first;
  }

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    readConfig(RESOURCE_DIR + "dipal/dipal.yml");
  }

  public static String normalizeSpaces(String in) {
    return in.replaceAll("[ \n\r\t]+", " ");
  }

  @Test
  public void test() throws IOException, WrongFormatException, TException {
    String boolexp = "void foo() { b = 5 < 1; }";

    String s = generate("boolexp", getInput(boolexp),
      RESOURCE_DIR + "dipal/dipal.yml");
    s = normalizeSpaces(s);
    String expected = "void foo() {boolean b = (5 < 1); }} ";
    expected = normalizeSpaces(expected);
    assertEquals(expected,
    s.substring(s.length() - expected.length()));


    // can't be tested like that because the memory is now handled correctly
    // and after leaving the last block is empty.
    /*
    parseAndTypecheck(boolexp);
    String type = rc.getMem().getVariableType("b");
    assertTrue(type.equals("boolean"));
    */
  }

}
