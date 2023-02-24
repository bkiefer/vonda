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

/**
 * this is an arithmetic expression with an operator, two expressions left and
 * right, and eventually if this is no complex term but a single number there is
 * a - in front of it
 *
 * @author Anna Welker
 */
public class ExpArithmetic extends RTBinaryExp {

  /**
   * if the expression consists of only one part, set right and operator to null
   *
   * @param left the left part
   * @param right the right part
   * @param operator the operator in between
   */
  public ExpArithmetic(RTExpression left, RTExpression right, String operator) {
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.type = left.getType();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }
}
