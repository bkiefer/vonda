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

package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Collections;
import java.util.List;

/**
 * represents a Java import statement, can only occur at the beginning of a
 * .rudi file; each import statement will transferred verbatim to the generated
 * java source file.
 *
 * @author Bernd Kiefer
 */
public class Import extends RudiTree {

  // IMPORT PATH
  List<String> path;

  public Import(List<String> p) {
    path = p;
  }

  public void visit(RudiVisitor v) {
    throw new UnsupportedOperationException("visit is special");
  };

  public String toString() { return "import " + path; }

  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }
}
