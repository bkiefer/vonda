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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.compiler.Position;
import de.dfki.mlt.rudimant.compiler.Type;

/**
 * a special kind of the RudiTree is an expression; expressions can have types
 *
 * @author Anna Welker
 */
public abstract class RTExpression extends RudiTree {

  public static final Logger logger = LoggerFactory.getLogger(RudiTree.class);

  protected boolean _parens = false;

  protected Type type;

  public Type getType() {
    return type;
  }

  public void generateParens() {
    _parens = true;
  }

  /**
   * duplicate the method from RudiTree to ensure String return
   * @param v
   */
  public void visitWithComments(VisitorGeneration v) {
    Position firstPos = location.getBegin();
    v.out.append(checkComments(v, firstPos));
    if (_parens) v.out.append("(");
    visit(v);
    if (_parens) v.out.append(")");
    Position endPos = location.getEnd();
    v.out.append(checkComments(v, endPos));
  }

  public Type getInnerType() { return type.getInnerType(); }

  public RTExpression ensureBooleanBasic() {
    if (this instanceof ExpBoolean) {
      return this;
    }

    if (type != null && type.isBool()){
      if (!(this instanceof ExpBoolean)){
        return fixFields(new ExpBoolean(this, null, null, true));
      }
      // if is a funccall with type boolean, we'd still like to have it as a
      // boolean, at least wrapped up
      // TODO: don't know if this is necessary.
    }
    return fixFields(new ExpBoolean(this, null, "<>", true));
  }

  public RTExpression ensureBoolean() {
    if (this instanceof ExpFieldAccess) {
      // if it is a fieldaccess kind of funccall, we have to go on testing for
      // each part if it is null
      ExpFieldAccess ufa = (ExpFieldAccess)this;
      return ufa.ensureBooleanUFA();
    }
    return ensureBooleanBasic();
  }

  public abstract void propagateType(Type upperType);

}
