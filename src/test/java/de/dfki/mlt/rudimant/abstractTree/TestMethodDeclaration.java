package de.dfki.mlt.rudimant.abstractTree;

import static visitortests.SeriousTest.*;
import static de.dfki.mlt.rudimant.abstractTree.TestTypeInference.*;
import static de.dfki.mlt.rudimant.Visualize.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.antlr.v4.runtime.Token;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.Visualize;
import de.dfki.mlt.rudimant.abstractTree.GrammarFile;
import de.dfki.mlt.rudimant.abstractTree.GrammarRule;
import de.dfki.mlt.rudimant.abstractTree.RudiTree;
import de.dfki.mlt.rudimant.abstractTree.StatAbstractBlock;
import de.dfki.mlt.rudimant.abstractTree.StatIf;
import de.dfki.mlt.rudimant.agent.nlg.Pair;

public class TestMethodDeclaration {

  @BeforeClass
  public static void setUpClass()
    throws TTransportException, IOException, WrongFormatException {
    readConfig(RESOURCE_DIR + "dipal/dipal.yml");
  }

  public static String normalizeSpaces(String in) {
    return in.replaceAll("[ \n\r\t]+", " ");
  }

  @Test
  public void test() throws IOException, WrongFormatException, TException {
    String methdecl = "void foo() { i = 1; }";

    String s = Visualize.generate("methdecl", getInput(methdecl),
        RESOURCE_DIR + "dipal/dipal.yml");
    s = normalizeSpaces(s);
    String expected = "void foo() {int i = 1; }} ";
    assertEquals(expected,
        s.substring(s.length() - expected.length()));
  }

}
