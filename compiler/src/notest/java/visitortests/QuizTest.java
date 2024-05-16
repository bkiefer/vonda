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
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class QuizTest {


  @Test
  public void TestQuiz() throws Exception {
    String[] strings = new String[]{
      // "-c", "rudi.config.yml",
      "-o", "target/test/testfiles",
      "-r", "src/test/resources/ontos/pal.ini",
      "-d", "src/test/resources/serious/quiz.rudi",};
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
