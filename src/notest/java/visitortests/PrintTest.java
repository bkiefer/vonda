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

import org.junit.Test;

import de.dfki.mlt.rudimant.GrammarMain;

/**
 *
 * @author Anna Welker
 */
public class PrintTest {

//  @Test(expected = TypeException.class)
//  public void ImportFailTest() throws Exception {
//    String[] strings = new String[]{
//        "-c=src/test/resources/rudi.config.yml",
//        "src/test/resources/test_import/Test2.rudi"
//    };
//    GrammarMain.main(strings);
//  }

//  @Test
//  public void ImportTest() throws Exception {
//    String[] strings = {
//        "-c", "src/test/resources/rudi.config.yml",
//        "src/test/resources/test_import/Test.rudi"
//    };
//    GrammarMain.main(strings);
////    assertFail(GrammarMain.main(strings));
//  }

//  @Test
//  public void SingleFileTest() throws Exception {
//    String[] strings = {
//        "-c", "src/test/resources/rudi.config.yml",
//        "src/test/resources/basic/agentMethods.rudi"
//    };
//    GrammarMain.main(strings);
////    assertFail(GrammarMain.main(strings));
//  }

//  @Test
//  public void ReturnTest() throws Exception {
//    String[] strings = new String[]{
//      "-c", "src/test/resources/rudi.config.yml", "-d",
//      "src/test/resources/test_return/aLotOfReturns.rudi"};
//    GrammarMain.main(strings);
//    //assertFail(GrammarMain.main(strings2));
//  }
//
//  @Test
//  public void miniTest() throws Exception {
//    String[] strings = new String[]{
//      "-c", "src/test/resources/rudi.config.yml", "-d",
//      "src/test/resources/MiniTest.rudi",
//    };
//    GrammarMain.main(strings);
//  }
  
  @Test
  public void TestCast1() throws Exception {
    String[] strings = new String[]{
      "-c", "src/test/resources/rudi.config.yml", "-d",
      "src/test/resources/tinytests/cast1.rudi",
    };
    GrammarMain.main(strings);
  }
  
  @Test
  public void TestTest() throws Exception {
    String[] strings = new String[]{
      "-c", "src/test/resources/rudi.config.yml", "-d",
      "src/test/resources/tinytests/test.rudi",
    };
    GrammarMain.main(strings);
  }
}
