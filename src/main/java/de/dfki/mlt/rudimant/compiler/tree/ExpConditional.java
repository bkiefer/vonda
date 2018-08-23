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
 * this is a conditional expression, i.e., something like a ? b : c
 *
 * @author Anna Welker
 */
public class ExpConditional extends RTExpression {

  RTExpression boolexp;
  RTExpression thenexp;
  RTExpression elseexp;

  // TODO: WHY IS THE FIRST NOT EXPBOOLEAN (AS LONG AS WE KEEP THAT)
  public ExpConditional(RTExpression bool, RTExpression thn,
          RTExpression els) {
    boolexp = bool;
    thenexp = thn;
    elseexp = els;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { boolexp, thenexp, elseexp };
    return Arrays.asList(dtrs);
  }

  public void propagateType(Type upperType, VisitorType v) {
    if (type != null && ! type.isUnspecified()) {
      logger.error("Why didn't this type percolate up? "
          + v.getFullText(this) + " " + type);
      return;
    }
    thenexp.propagateType(upperType, v);
    elseexp.propagateType(upperType, v);
  }

}
