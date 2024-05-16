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

package basictests;

import org.junit.Test;

import de.dfki.mlt.rudimant.GrammarMain;

/**
 *
 * @author mawo
 */
public class MaxTests {

  private static final String RESOURCE_DIR = "src/test/resources/";


  private String[] enterName(String filename){
    String[] name = {
        "-c", RESOURCE_DIR + "rudi.config.yml",
        "-d", RESOURCE_DIR + "basic/" + filename};

    return name;
  }

  @Test
  public void maxTest1() throws Exception {
//    String[] strings = new String[]{
//      "-c", "src/test/resources/rudi.config.yml", "-d",
//      "src/test/resources/MiniTest.rudi",};

    // TODO: enter user.GivenName into ontology to make this work
    GrammarMain.main(enterName("MaxTest1.rudi"));
  }

  @Test
  public void maxTest2() throws Exception {
    // TODO: rudimant needs to know what gameLogic.isTurnBased() is supposed to be
    GrammarMain.main(enterName("MaxTest2.rudi"));
  }
}
