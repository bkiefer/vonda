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

/**
 * represents an import statement; each import statement will cause the imported
 * .rudi file to be parsed and translated. While parsing the imported file,
 * rudimant will remember the variables from the original file, but the
 * namespace of the imported file will not be visible from the original file
 * thereafter.
 *
 * @author Anna Welker
 */
public class Import extends RudiTree {

  // IMPORT VARIABLE SEMICOLON
  String[] path;
  String name;

  public Import(String n, String[] dirSpec) {
    name = n;
    path = dirSpec;
  }

  public void visit(RudiVisitor v) {
    throw new UnsupportedOperationException("visit is special");
  };

  public String toString() { return "import " + path; }

  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }
}
