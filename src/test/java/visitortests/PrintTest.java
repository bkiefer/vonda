/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.lt.hfc.db.client.HfcDbClient;
import de.dfki.lt.hfc.db.server.HfcDbServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.GrammarMain;
import de.dfki.mlt.rudimant.TypeException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.apache.thrift.transport.TTransportException;

import static org.junit.Assert.*;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Anna Welker
 */
public class PrintTest {


  private static HfcDbServer server;

  @BeforeClass
  public static void setUpClass()
      throws TTransportException, IOException, WrongFormatException {
    SeriousTest.setUpClass();
  }

  @AfterClass
  public static void tearDownClass() {
    SeriousTest.tearDownClass();
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test(expected = TypeException.class)
  public void ImportFailTest() throws Exception {
    String[] strings2 = new String[]{
      "-c=src/test/resources/rudi.config.yml",
      "-o=target/test/testfiles",
      "src/test/resources/test_import/Test2.rudi"};
    GrammarMain.main(strings2);
  }

  @Test
  public void ImportTest() throws Exception {
    String[] strings = new String[]{
      "-c=src/test/resources/rudi.config.yml", "-o=target/test/testfiles",
      "src/test/resources/test_import/Test.rudi"};
    GrammarMain.main(strings);
    //assertFail(GrammarMain.main(strings2));
  }

  @Test
  public void ReturnTest() throws Exception {
    String[] strings = new String[]{
      "-c", "src/test/resources/rudi.config.yml",
      "-o=target/test/testfiles", "-d",
      "src/test/resources/test_return/aLotOfReturns.rudi"};
    GrammarMain.main(strings);
    //assertFail(GrammarMain.main(strings2));
  }

//  @Test
//  public void howDoesReturnWorkTest() {
//
//    int test = 0;
//    int marker = 0 | 8;
//    test = marker | test;
//    if ((test | 0) == 0) {
//      System.out.println("here");
//    }
//    if ((test | marker) == 0) {
//      System.out.println("there");
//    } else {
//      System.out.println("marker set");
//    }
//
//  }

  /*
    @Test
    public void SecondRuleTest() throws Exception {
        String[] strings = new String[]{"SecondRule.txt"};
        GrammarMain.main(strings);
    }

    @Test
    public void ThirdRuleTest() throws Exception {
        String[] strings = new String[]{"ThirdRule.txt"};
        GrammarMain.main(strings);
    }

    @Test
    public void TupleForTest() throws Exception {
        String[] strings = new String[]{"TupleFor.txt"};
        GrammarMain.main(strings);
    }

    @Test
    public void WholeQuizTest() throws Exception {
        String[] strings = new String[]{"RulesQuiz.txt"};
        GrammarMain.main(strings);
    }

    @Test
    public void LogTest() throws Exception {
        String[] strings = new String[]{"LogThis.txt", "-log"};
        GrammarMain.main(strings);
    }

    /*
    @Test
    public void FourthAndFifthRuleTest() throws Exception {
        String[] strings = new String[]{"TwoMoreRules"};
        Main.main(strings);
    }*/
}
