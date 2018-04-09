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
public class ExpFieldAccess extends RTExpLeaf {

  List<RTExpression> parts;
  List<String> representation;

  public ExpFieldAccess(List<RTExpression> parts, List<String> representation) {
    this.parts = parts;
    this.representation = representation;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() { return parts; }

  public RTExpression ensureBooleanUFA() {
    int s = parts.size() - 1;
    if (s == 0) {
      return parts.get(s).ensureBooleanBasic();
    }

    List<RTExpression> smaller = parts.subList(0, s);
    List<String> smallerRep = representation.subList(0, s);
    ExpFieldAccess first = fixFields(new ExpFieldAccess(smaller, smallerRep));
    first.type = parts.get(s - 1).type;
    RTExpression right = this.ensureBooleanBasic();
    return fixFields(new ExpBoolean(first.ensureBooleanUFA(), right, "&&", true));
  }
}
