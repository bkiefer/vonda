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

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class ExpPropertyAccess extends RTExpLeaf {

  /** The identifier or URI for the access is in the content field*/

  enum Access {
      get, incr, pincr, decr, pdecr, setValue, clearValue, hasSlot
  };

  /** Is content a variable/identifier or an URI */
  boolean propertyVariable = false;
  /** Is the property represented by content a functional property */
  boolean functional;
  /** Function to apply: clear, get, set, incr or pincr */
  Access acc;
  /** If the function has a second argument (set...), it's here */
  RTExpression secondArg;

  public ExpPropertyAccess(String fieldName, boolean var, Type rt,
      boolean func) {
    // an access will always return sth of type Object, so to not get null
    // I'll set the type of this to Object by default
    type = rt;
    propertyVariable = var;
    functional = func;
    content = fieldName;
    acc = Access.get;
  }

  public ExpPropertyAccess copy() {
    ExpPropertyAccess epa =
        new ExpPropertyAccess(content, propertyVariable, type, functional);
    epa.acc = acc;
    return epa;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }

  String getFunctionName() {
    if (acc == Access.setValue || acc == Access.clearValue
        || acc ==Access.hasSlot)
      return acc.toString();
    if (!functional)
      return acc + "Value"; // set with Object inner type

    Type t = type;
    // incr and pincr are only applicable to POD or NumberContainer
    if (t.isPODType()) t = t.getContainer();
    if (t.isJavaConvertible()) {
      return acc + t.toJava();
    }
    // so only get remains, with an unknown type
    return "getSingleValue";
  }

  String getPropertyName() {
    String ret = "";
    if(!propertyVariable) ret += ('"');
    ret += content;
    if(!propertyVariable) ret += ('"');
    return ret;
  }

  public String toString() {
    return (propertyVariable ? "." + acc + "(" + content + ")"
        : "." + content) +
        (type != null ? "[" + type + "]" : "");
  }
}
