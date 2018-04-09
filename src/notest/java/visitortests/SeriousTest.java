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
  public static final String TEST_CONFIG = "rudi.config.yml";
  public static final String TEST_ONTO_CFG = "ontos/pal.ini";

  public static final int SERVER_PORT = 8996;
  
  @Test
  public void dummyTest(){
    ;
  }

//  @Test
//  public void PalAgentTest() throws Exception {
//    String[] strings = new String[]{
//        "-c", RESOURCE_DIR + TEST_CONFIG,
//        "-o", "target/test/testfiles",
//        "-d", "src/test/resources/serious/palAgent.rudi"};
//    GrammarMain.main(strings);
//  }
}
