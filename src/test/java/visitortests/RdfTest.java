/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.client.HfcDbClient;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.abstractTree.UFieldAccess;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class RdfTest {

  private static final String RESOURCE_DIR = "src/test/resources/";

  HfcDbClient client;
  private RdfProxy _proxy;

  // alternative PORTS
  private static final int SERVER_PORT = 8996;

  /**
   *
   * @throws FileNotFoundException
   * @throws IOException
   * @throws WrongFormatException
   * @throws TException
   */
  @BeforeClass
  public static void startServer()
      throws FileNotFoundException, IOException, WrongFormatException, TException {
    SeriousTest.setUpClass();

    HfcDbClient client = new HfcDbClient();
    client.init("localhost", SERVER_PORT);
    client.readConfig(new File(RESOURCE_DIR + "rifca/rifca.ini"));
    client.shutdown();
  }

  @AfterClass
  public static void shutdownServer() {
    SeriousTest.tearDownClass();
  }

  @Before
  public void setUp()
      throws IOException, WrongFormatException, TException {
    client = new HfcDbClient();
    client.init("localhost", SERVER_PORT);
    _proxy = new RdfProxy(client._client);
    Map<String, String> resolv = new HashMap<>();
    resolv.put("Child", "<dom:Child>");
    _proxy.setBaseToUri(resolv);
  }

  @After
  public void tearDown() {
    client.shutdown();
  }

  @Test
  public void testgetType1() throws TException {
    List<String> elem = Arrays.asList(new String[] { "child", "forename" });
    Mem mem = new Mem();
    mem.enterEnvironment();
    mem.addElement("child", "Child", null);
    UFieldAccess field = new UFieldAccess(elem);
    String result = field.getPredicateType(_proxy, mem);
    assertEquals("get forename", "String", result);
  }

  @Test
  public void testgetType2() throws TException {
    List<String> elem = Arrays.asList(new String[] { "child", "hasTreatment" });
    Mem mem = new Mem();
    mem.enterEnvironment();
    mem.addElement("child", "Child", null);
    UFieldAccess field = new UFieldAccess(elem);
    String result = field.getPredicateType(_proxy, mem);
    assertEquals("get hasTreatment", "<dom:Treatment>", result);
  }

  @Test
  public void testgetType3() throws TException {
    List<String> elem = Arrays.asList(new String[] { "child", "birthdate" });
    Mem mem = new Mem();
    mem.enterEnvironment();
    mem.addElement("child", "Child", null);
    UFieldAccess field = new UFieldAccess(elem);
    String result = field.getPredicateType(_proxy, mem);
    assertEquals("get birthdate", "<xsd:date>", result);
  }
}
