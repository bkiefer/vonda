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

import java.util.Arrays;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * A Java-like cast
 * @author pal
 */
public class ExpCast extends RTExpression {

  // the java object creation as a function call
  RTExpression expression;

  /**
   * @param t set to the type that the exp is casted to
   * @param exp set to the expression that is casted
   */
  public ExpCast(String t, RTExpression exp){
    this(new Type(t), exp);
  }

  public ExpCast(Type t, RTExpression exp){
    type = t;
    expression = exp;
  }

  @Override
  public void propagateType(Type upperType, VisitorType v) {
    // TODO: does this make sense here????
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { expression };
    return Arrays.asList(dtrs);
  }


}
