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
 * represents an include statement; each include statement will cause the
 * included.rudi file to be parsed and translated. While parsing the included
 * file, rudimant will remember the variables from the original file, but the
 * namespace of the included file will not be visible from the original file
 * thereafter.
 *
 * @author Anna Welker
 */
public class Include extends RudiTree {

  // INCLUDE PATH.NAME SEMICOLON
  String[] path;
  String name;

  public Include(String n, String[] dirSpec) {
    name = n;
    path = dirSpec;
  }

  @Override
  public void visit(RudiVisitor v) {
    throw new UnsupportedOperationException("visit is special");
  };

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("include ");
    for (String p : path) {
      sb.append(p).append('.');
    }
    sb.append(name);
    return sb.toString();
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }
}
