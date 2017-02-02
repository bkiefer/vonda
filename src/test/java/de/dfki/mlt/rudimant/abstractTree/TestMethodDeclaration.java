package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static org.junit.Assert.assertEquals;
import static visitortests.SeriousTest.RESOURCE_DIR;

import java.io.IOException;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.Visualize;


public class TestMethodDeclaration {

  @BeforeClass
  public static void setUpClass()
    throws TTransportException, IOException, WrongFormatException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", "", "");
  }

  @Test
  public void test() throws IOException, WrongFormatException, TException {
    String methdecl = "void foo() { i = 1; }";

    String s = Visualize.generate(methdecl);
    s = normalizeSpaces(s);
    String expected = "void foo() {int i = 1; }} ";
    assertEquals(expected,
        s.substring(s.length() - expected.length()));
  }

}
