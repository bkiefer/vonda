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
import static de.dfki.mlt.rudimant.GrammarMain.yaml;
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

  private static LinkedHashMap<String, Object> configs;

  public static Yaml yaml = new Yaml();

  public PrintTest() {
  }

  private static String RESOURCE_DIR;

  private static HfcDbServer server;
  // alternative PORTS
  private static int SERVER_PORT;
  private static int WEBSERVER_PORT;

  @BeforeClass
  public static void setUpClass() throws TTransportException, IOException, FileNotFoundException, WrongFormatException {
    configs = (LinkedHashMap<String, Object>) yaml.load(new FileInputStream("rudi.config.yml"));
    serverConfigs();
    File config = new File(RESOURCE_DIR + "ontos/pal.ini");
    server = new HfcDbServer(SERVER_PORT);
    server.readConfig(config);
    server.runServer();
    server.runHttpService(WEBSERVER_PORT);
    File outDir = new File("target/test/testfiles");
    if (!outDir.exists()) {
      outDir.mkdirs();
    }
  }

  private static void serverConfigs() {
    RESOURCE_DIR = (String) configs.get("resourceDir");
    SERVER_PORT = (int) configs.get("serverPort");
    WEBSERVER_PORT = (int) configs.get("webserverPort");
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

//  @Test(expected = TypeException.class)
//  public void ImportFailTest() throws Exception {
//    String[] strings2 = new String[]{"src/test/resources/test_import/Test2.rudi",
//      "rudi.config.yml", "-o=target/test/testfiles"};
//    GrammarMain.main(strings2);
//  }
//
//  @Test
//  public void ImportTest() throws Exception {
//    String[] strings = new String[]{"src/test/resources/test_import/Test.rudi",
//      "rudi.config.yml", "-o=target/test/testfiles"};
//    GrammarMain.main(strings);
//    //assertFail(GrammarMain.main(strings2));
//  }
//
//  @Test
//  public void ReturnTest() throws Exception {
//    String[] strings = new String[]{"src/test/resources/test_return/aLotOfReturns.rudi",
//      "rudi.config.yml", "-o=target/test/testfiles", "-d"};
//    GrammarMain.main(strings);
//    //assertFail(GrammarMain.main(strings2));
//  }
  
  @Test
  public void miniTest() throws Exception{
    String[] strings = new String[]{"src/test/resources/MiniTest.rudi",
      "rudi.config.yml", "-o=target/test/testfiles", "-d"};
    GrammarMain.main(strings);
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
