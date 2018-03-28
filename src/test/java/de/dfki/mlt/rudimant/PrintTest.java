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

package de.dfki.mlt.rudimant;

import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.CompilerMain;

/**
 *
 * @author Anna Welker
 */
public class PrintTest {

  @Test
  public void Test() throws Exception {
    // enter here the file whose compilation you'd like to debug
    String[] strings = new String[]{
      "-c",
      //"src/test/resources/tests.yml",
      "../herbea/poc/herbea.yml",
      "src/test/resources/Test.rudi",
      //"../herbea/poc/src/main/rudi/Kaefer.rudi"
      //"../dipal/src/main/rudi/PalAgent.rudi"
    };
    //GrammarMain.main(strings);
  }
}
