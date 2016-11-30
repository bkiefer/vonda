/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import java.io.File;
import java.io.IOException;

import org.apache.thrift.transport.TTransportException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.server.HfcDbServer;
import de.dfki.mlt.rudimant.GrammarMain;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class SeriousTest {
  static HfcDbServer server;

  public static final String RESOURCE_DIR = "src/test/resources/";

  public static final int SERVER_PORT = 8996;

  @BeforeClass
  public static void setUpClass()
      throws TTransportException, IOException, WrongFormatException {
    setupClass("ontos/pal.ini");
  }

  public static void setupClass(String iniFileName)
      throws TTransportException, IOException, WrongFormatException {
    // start the HFC server
    server = new HfcDbServer(SERVER_PORT);
    server.readConfig(new File(RESOURCE_DIR + iniFileName));
    server.runServer();
  }

  @AfterClass
  public static void tearDownClass() {
    server.shutdown();
  }

  @Test
  public void PalAgentTest() throws Exception {
    String[] strings = new String[]{
        "-c", "rudi.config.yml",
        "-o", "target/test/testfiles", "-d",
        "src/test/resources/serious/palAgent.rudi"};
    GrammarMain.main(strings);
  }
}
