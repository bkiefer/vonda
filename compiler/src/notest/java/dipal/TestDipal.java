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

package dipal;

import static visitortests.SeriousTest.RESOURCE_DIR;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.GrammarMain;


public class TestDipal {

  private static String[] enterName(String filename){
    String[] name = {"-c", RESOURCE_DIR + "dipal/dipal.yml",
        RESOURCE_DIR + "dipal/" + filename};

    return name;
  }

  @Test
  public void testDipal() throws TTransportException, FileNotFoundException, IOException, WrongFormatException, TException {
    GrammarMain.main(enterName("PalAgent.rudi"));
  }

  public static void main(String[] args) throws TTransportException, FileNotFoundException, IOException, WrongFormatException, TException {
    GrammarMain.main(enterName("PalAgent.rudi"));
  }

}
