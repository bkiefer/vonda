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

import java.io.IOException;
import java.io.Writer;
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
  boolean staticImport = false;

  public Import(List<String> p) {
    path = p;
  }

  public Import(List<String> p, boolean stat) {
    path = p;
    staticImport = stat;
  }

  @Override
  public void visit(RudiVisitor v) {
    throw new UnsupportedOperationException("visit is special");
  };

  @Override
  public String toString() {
    return "import" + (staticImport ? " static" : " ") + path;
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }

  public void gen(Writer out) throws IOException {
    if (path.size() > 0) {
      out.append("import ");
      if (staticImport) {
        out.append("static ");
      }
      out.append(path.get(0));
      for (int j = 1; j < path.size(); ++j) {
        out.append('.').append(path.get(j));
      }
      out.append(";").append(System.lineSeparator());
    }
  }
}
