/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.thrift.transport.TTransportException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.server.HfcDbServer;
import de.dfki.mlt.rudimant.GrammarMain;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class SeriousTest {
  static HfcDbServer server;

  @BeforeClass
  public static void setUpClass()
      throws TTransportException, IOException, WrongFormatException {
    // start the HFC server
    server = new HfcDbServer(8996);
    server.readConfig(new File("src/test/resources/ontos/pal.ini"));
    server.runServer();
  }

  @AfterClass
  public static void tearDownClass() {
    server.shutdown();
  }

//  @Test
//  public void TestGreetUser() throws Exception {
//    String[] strings = new String[]{"src/test/resources/serious/greet_user.rudi",
//      "target/test/testfiles", "-nt"};
//    GrammarMain.main(strings);
//  }
  @Test
  public void FirstRuleTest() throws Exception {
    String[] strings = new String[]{"-c", "rudi.config.yml", "-o", "target/test/testfiles", "-d",
        "src/test/resources/FirstRule.rudi"};
    GrammarMain main = new GrammarMain();
    Yaml yaml = new Yaml();
    main.setConfig((LinkedHashMap<String, Object>)
        yaml.load(new FileReader("src/test/resources/rudi.config.yml")));
    main.initCompiler();
    String[] inFiles = { "src/test/resources/FirstRule.rudi" };
    main.process(Arrays.asList(inFiles), new File("target/test/testfiles/"));
  }
}
