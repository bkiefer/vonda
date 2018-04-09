/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package visitortests;

import static org.junit.Assert.assertTrue;
import static visitortests.SeriousTest.RESOURCE_DIR;
import static visitortests.SeriousTest.TEST_ONTO_CFG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.client.HfcDbClient;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbHandler;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class RdfTest {

  HfcDbClient client;
  private static RdfProxy _proxy;

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
    HfcDbHandler handler = new HfcDbHandler();
    String ontoFileName = RESOURCE_DIR + TEST_ONTO_CFG;
    handler.readConfig(new File(ontoFileName));
    _proxy = new RdfProxy(handler);
  }

  @Before
  public void setUp()
      throws IOException, WrongFormatException, TException {
    //client = new HfcDbClient();
    //client.init("localhost", SeriousTest.SERVER_PORT);
    //_proxy = new RdfProxy(client._client);
    Map<String, String> resolv = new HashMap<>();
    resolv.put("Child", "<dom:Child>");
    _proxy.setBaseToUri(resolv);
  }

  @After
  public void tearDown() {
    //client.shutdown();
  }

  @Test
  public void emptyTest() {
    assertTrue(true);
  }

  /*
  @Test
  public void testgetType1() throws TException {
    List<String> elem = Arrays.asList(new String[] { "child", "forename" });
    Mem mem = new Mem(_proxy);
    mem.enterEnvironment();
    List<RudiTree> l = new ArrayList<>();
    UVariable v = new UVariable("<dom:Child>", "child", "testclass");
    l.add(v);
    UFieldAccess field = new UFieldAccess(l, elem);
    String result = field.getPropertyType(_proxy, mem, elem);
   //assertEquals("get forename", "String", result);
  }

  @Test
  public void testgetType2() throws TException {
    List<String> elem = Arrays.asList(new String[] { "child", "hasTreatment" });
    Mem mem = new Mem(_proxy);
    mem.enterEnvironment();
    List<RudiTree> l = new ArrayList<>();
    UVariable v = new UVariable("<dom:Child>", "child", "testclass");
    l.add(v);
    UFieldAccess field = new UFieldAccess(l, elem);
    String result = field.getPropertyType(_proxy, mem, elem);
    //assertEquals("get hasTreatment", "<dom:Treatment>", result);
  }

  @Test
  public void testgetType3() throws TException {
    List<String> elem = Arrays.asList(new String[] { "child", "birthdate" });
    Mem mem = new Mem(_proxy);
    mem.enterEnvironment();
    mem.addVariableDeclaration("child", "Child", null);
    List<RudiTree> l = new ArrayList<>();
    UVariable v = new UVariable("<dom:Child>", "child", "testclass");
    l.add(v);
    UFieldAccess field = new UFieldAccess(l, elem);
    String result = field.getPropertyType(_proxy, mem, elem);
    //assertEquals("get birthdate", "<xsd:date>", result);
  }
  */
}
