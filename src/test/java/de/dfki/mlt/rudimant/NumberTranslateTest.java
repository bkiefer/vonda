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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.dfki.mlt.rudimant.agent.nlp.CPlannerNlg;

/**
 *
 * @author Bernd Kiefer
 */
public class NumberTranslateTest {

  @Test
  public void Test() throws Exception {
    String[] langs = { "it", "nl_NL", "en", "de" };
    String[][] results = {
        { "zero", "uno", "due", "tre", "quattro" },
        { "nul", "een", "twee", "drie", "vier" },
        { "zero", "one", "two", "three", "four" },
        { "null", "eins", "zwei", "drei", "vier" } };
    int l = 0;
    for (String lang : langs) {
      for (int i = 0; i < 5; ++i) {
        String res = CPlannerNlg.numbersToText(Integer.toString(i), lang);
        assertEquals(results[l][i], res);
      }
      ++l;
    }

  }
}
