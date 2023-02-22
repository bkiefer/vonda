/*
 * The Creative Commons CC-BY-NC c4.0 License
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

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * Either used to encode a normal java creation using new or to create a new
 * rdf object
 * @author pal
 */
public class ExpNew extends RTExpression {

  // the java object creation as a function call
  List<RTExpression> params;

  /**
   * @param toCreate the class of the object to create
   * @param params the parameters to the constructor call
   */
  public ExpNew(String toCreate, List<RTExpression> params){
    this(new Type(toCreate), params);
  }

  public ExpNew(String toCreate){
    this(toCreate, null);
  }

  public ExpNew(Type toCreate){
    this(toCreate, null);
  }

  public ExpNew(Type type, List<RTExpression> params) {
    this.type = type;
    this.params = params;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return params == null ? Collections.emptyList() : params;
  }
}
