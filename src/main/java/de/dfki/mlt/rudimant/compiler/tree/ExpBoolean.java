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

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * this is a boolean expression; might also be a subsumes relation (but will in
 * sum always be a boolean)
 *
 * @author Anna Welker
 */
public class ExpBoolean extends RTBinaryExp {
  // if this is true, it means that this ExpBoolean has been created during
  // code generation, and does not directly come from parsing
  public boolean synthetic;

  /**
   * if the expression consists of only one part, set right and operator to null
   *
   * @param fullexp the String representation of the whole expression
   * @param left left part
   * @param right right part
   * @param operator operator in between
   */
  public ExpBoolean(RTExpression left,
          RTExpression right, String operator) {
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.type = new Type("boolean");
  }

  /**
   * if the expression consists of only one part, set right and operator to null
  *
  * @param fullexp the String representation of the whole expression
  * @param left left part
  * @param right right part
  * @param operator operator in between
  */
 public ExpBoolean(RTExpression left,
         RTExpression right, String operator, boolean synth) {
   this(left, right, operator);
   synthetic = synth;
 }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public String toString() {
    if (right != null) {
      return left + " " + operator + " " + right;
    }
    return operator + " " + left;
  }
}
