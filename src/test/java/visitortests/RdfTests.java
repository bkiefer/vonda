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
import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.UFieldAccess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anna
 */
public class RdfTests {

 private static final String RESOURCE_DIR = "../hfc-database/src/test/resources/";

  private static HfcDbServer server;

  private HfcDbClient client;
  private HfcDbService.Client _client;

  private Mem mem;

  // alternative PORTS
  private static final int SERVER_PORT = 8996;
  private static final int WEBSERVER_PORT = 8995;

  /**
   *
   * @throws TTransportException
   * @throws FileNotFoundException
   * @throws IOException
   * @throws WrongFormatException
   */
  @BeforeClass
  public static void startServer() throws TTransportException, FileNotFoundException, IOException, WrongFormatException {
    File config = new File(RESOURCE_DIR + "ontos/pal.ini");
    server = new HfcDbServer(SERVER_PORT);
    server.readConfig(config);
    server.runServer();
    server.runHttpService(WEBSERVER_PORT);
  }

  @AfterClass
  public static void shutdownServer() {
    server.shutdown();
  }

  @Before
  public void setUp()
      throws IOException, WrongFormatException, TException {
    client = new HfcDbClient();
    client.init("localhost", SERVER_PORT);
    client.readConfig(new File(RESOURCE_DIR + "rifca/rifca.ini"));
    client.readConfig(new File(RESOURCE_DIR + "ontos/pal.ini"));
    _client = client._client;
  }

  @After
  public void tearDown() {
    client.shutdown();
  }

  @Test
  public void testgetType1() throws TException {
    ArrayList<String> elem = new ArrayList<String>() {
      {
        add("child");
        add("forename");
      }
    };
    mem.addAndEnterNewEnvironment();
    mem.addElement("child", "Child", "here");
    UFieldAccess field = new UFieldAccess(elem, _client);
    String result = field.askChristophe(mem);
    assertEquals("get forename", "String", result);
  }

  @Test
  public void testgetType2() throws TException {
    ArrayList<String> elem = new ArrayList<String>() {
      {
        add("child");
        add("hasTreatment");
      }
    };
    mem.addAndEnterNewEnvironment();
    mem.addElement("child", "Child", "here");
    UFieldAccess field = new UFieldAccess(elem, _client);
    String result = field.askChristophe(mem);
    assertEquals("get hasTreatment", "Treatment", result);
  }

  @Test
  public void testgetType3() throws TException {
    ArrayList<String> elem = new ArrayList<String>() {
      {
        add("child");
        add("birthdate");
      }
    };
    mem.addAndEnterNewEnvironment();
    mem.addElement("child", "Child", "here");
    UFieldAccess field = new UFieldAccess(elem, _client);
    String result = field.askChristophe(mem);
    assertEquals("get birthdate", "<xsd:date>", result);
  }
}
