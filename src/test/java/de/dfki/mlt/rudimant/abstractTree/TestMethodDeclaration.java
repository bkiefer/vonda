package de.dfki.mlt.rudimant.abstractTree;

import static visitortests.SeriousTest.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.Visualize;
import de.dfki.mlt.rudimant.abstractTree.GrammarFile;
import de.dfki.mlt.rudimant.abstractTree.GrammarRule;
import de.dfki.mlt.rudimant.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.abstractTree.StatAbstractBlock;
import de.dfki.mlt.rudimant.abstractTree.StatIf;

public class TestMethodDeclaration {

  String header = "";
  String footer = "";

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

  @BeforeClass
  public static void setUpClass()
    throws TTransportException, IOException, WrongFormatException {
    setupClass("dipal/ontologies/pal.ini");
  }

  @AfterClass
  public static void tearDown() {
    tearDownClass();
  }

  public static String normalizeSpaces(String in) {
    return in.replaceAll("[ \n\r\t]+", " ");
  }

  @Test
  public void test() throws IOException, WrongFormatException, TException {
    String methdecl = "Quiz foo() { i = 1; }";

    String s = Visualize.generate("methdecl", getInput(methdecl),
        RESOURCE_DIR + "dipal/dipal.yml");
    s = normalizeSpaces(s);
    String expected = "Quiz foo() {int i = 1; }} ";
    assertEquals(expected,
        s.substring(s.length() - expected.length()));
  }

}
