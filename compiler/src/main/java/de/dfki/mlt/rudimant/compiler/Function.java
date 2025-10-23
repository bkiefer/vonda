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

package de.dfki.mlt.rudimant.compiler;

import java.util.Iterator;

public class Function {

  private String _name;
  private String _origin;
  private Type _type;

  public Function(String name, String origin, Type funType) {
    _name = name;
    _origin = origin;
    _type = funType;
  }

  // TODO: NOT USED
  public String getName() {
    return _name;
  }

  public Type getType() {
    return _type;
  }

  public String getOrigin(){
    return _origin;
  }

  public boolean signatureMatches(Type fType) {
    return _type.signatureMatches(fType);
  }

  @Override
  public int hashCode() {
    return _type.hashCode() + _name.hashCode();
  }

  @Override
  public boolean equals(Object fun) {
    if (!(fun instanceof Function)) {
      return false;
    }
    return _name.equals(((Function) fun)._name)
        && _type.equals(((Function) fun)._type);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(_type.getReturnedType().getRep()).append(' ');
    if (_type.isMethod()) {
      sb.append(_type.getClassOf().getRep()).append('.');
    }
    sb.append(_name).append('(');
    boolean notfirst = false;
    Iterator<Type> it =  _type.getParameterTypes();
    while(it.hasNext()) {
      if (notfirst) sb.append(", ");
      notfirst = true;
      sb.append(it.next().getRep());
    }
    sb.append(')');
    return sb.toString();
  }
}
