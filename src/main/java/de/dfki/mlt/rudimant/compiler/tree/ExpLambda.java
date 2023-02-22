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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import de.dfki.mlt.rudimant.common.Location;
import de.dfki.mlt.rudimant.compiler.Environment;
import de.dfki.mlt.rudimant.compiler.Type;

/**
 * This is an "anonymous" method declaration, and as such has strong resemblance
 * ot it
 *
 * @author Anna Welker, Bernd Kiefer
 */
public class ExpLambda extends RTExpression implements RTBlockNode {

  List<String> parameters;
  RudiTree body;

  public ExpLambda(List<?> parmsAndTypes, RudiTree b) {
    parameters = new ArrayList<>(parmsAndTypes.size() / 2);
    List<Type> partypes = new ArrayList<>(parmsAndTypes.size() / 2);
    int freeVar = 0;
    for (int i = 0; i < parmsAndTypes.size();) {
      Type t = (Type)parmsAndTypes.get(i++);
      if (t.isUnspecified())
        t = new Type("" + (char)('A' + freeVar++));
      partypes.add(t);
      parameters.add((String)parmsAndTypes.get(i++));
    }
    type = Type.getFunctionType(Type.getNoType(), null, partypes);
    body = b;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    StringBuilder sb = new StringBuilder();
    sb.append('(').append(parameters.get(0));
    for (int i = 1; i < parameters.size(); ++i)
      sb.append(',').append(parameters.get(i));
    sb.append(")");
    String typeString = type.getRep();
    Location l = new Location(this.getLocation().getBegin(),
        body.getLocation().getBegin());
    ExpLiteral lit = new ExpLiteral(sb.toString(), typeString);
    lit.location = l;
    RudiTree[] dtrs = { lit , body };
    return Arrays.asList(dtrs);
  }

  /** this will occur regularly, and has repercussions on the body, so
   *  this node must be re-visited (where called)
   */
  public void propagateType(Type t, VisitorType v) {
    type = t;
    _localBindings = null;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(type.getReturnedType()).append(" (");
    Iterator<Type> partypes = type.getParameterTypes();
    boolean notFirst = false;
    for(String p : parameters) {
      if (notFirst) sb.append(", ");
      notFirst = true;
      sb.append(partypes.next().getRep()).append(' ').append(p);
    }
    sb.append(") -> ");
    sb.append(body);
    return sb.toString();
  }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings;

  public Environment getParentBindings() { return _localBindings.getParent(); }

  public Environment enterEnvironment(Environment parent) {
    return _localBindings != null ? _localBindings
        : (_localBindings = Environment.getEnvironment(parent));
  }
}
