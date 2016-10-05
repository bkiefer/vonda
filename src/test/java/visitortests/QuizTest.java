/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.server.HfcDbServer;
import de.dfki.mlt.rudimant.GrammarMain;
import static de.dfki.mlt.rudimant.GrammarMain.yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class QuizTest {

  private static LinkedHashMap<String, Object> configs;

  public static Yaml yaml = new Yaml();

  public QuizTest() {
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

    @Test
  public void TestQuiz() throws Exception {
    String[] strings = new String[]{"src/test/resources/serious/quiz.rudi",
      "rudi.config.yml", "o=target/test/testfiles", "-d"};
    GrammarMain.main(strings);
  }

//  @Test
//  public void TestGreetUser() throws Exception {
//    String[] strings = new String[]{"src/test/resources/serious/greet_user.rudi",
//      "rudi.config.yml", "o=target/test/testfiles", "-d"};
//    GrammarMain.main(strings);
//  }
//
//  @Test
//  public void FirstRuleTest() throws Exception {
//    String[] strings = new String[]{"src/test/resources/FirstRule.rudi",
//      "rudi.config.yml", "-o=target/test/testfiles", "-d"};
//    GrammarMain.main(strings);
//  }
}
