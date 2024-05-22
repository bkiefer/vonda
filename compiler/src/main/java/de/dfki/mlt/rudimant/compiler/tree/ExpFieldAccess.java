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

import java.util.List;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class ExpFieldAccess extends RTExpression {

  List<RTExpression> parts;

  public ExpFieldAccess(List<RTExpression> parts) {
    this.parts = parts;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public Iterable<? extends RudiTree> getDtrs() { return parts; }

  /** Create non-null tests for all elements of a field access */
  public RTExpression ensureBooleanUFA() {
    int s = parts.size() - 1;
    if (s == 0) {
      return parts.get(s).ensureBooleanBasic();
    }

    ExpFieldAccess first = fixFields(new ExpFieldAccess(parts.subList(0, s)));
    first.type = parts.get(s - 1).type;
    RTExpression right = this.ensureBooleanBasic();
    return fixFields(new ExpBoolean(first.ensureBooleanUFA(), right, "&&", true));
  }

  private RTExpression ensureBooleanUFARec(int origSize) {
    int s = parts.size() - 1;
    if (s == 0) {
      return parts.get(s).ensureBooleanBasic();
    }

    ExpFieldAccess first = fixFields(new ExpFieldAccess(parts.subList(0, s)));
    // when this type is an RDF type, we want to do something
    first.type = parts.get(s - 1).type;
    RTExpression sub = first.ensureBooleanUFARec(origSize);
    if (first.type.isRdfType() || parts.size() == origSize) {
      RTExpression right = this.ensureBooleanBasic();
      return fixFields(new ExpBoolean(sub, right, "&&", true));
    }
    return sub;
  }

  // This is a more restricive variant, which only creates tests when the
  // field access is an RDF access, currently unused
  public RTExpression ensureBooleanUFARdfOnly() {
    RTExpression sub = ensureBooleanUFARec(parts.size());
    return sub;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (RTExpression p : parts) sb.append(p);
    return sb.toString();
  }
}
