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

import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.RESOURCE_DIR;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.CompilerMain;

/**
 *
 * @author Anna Welker
 */
public class CommentTest {

  @Test
  public void Test() throws Exception {
    // enter here the file whose compilation you'd like to debug
    CompilerMain.main(new String[]{
      "-o", "target/generated/",
      "-r", RESOURCE_DIR + "ontologies/inits/pal.inference.ini",
      RESOURCE_DIR + "Comments.rudi",
    });

    assertTrue(new File("target/generated/Comments.java").exists());
  }
}
