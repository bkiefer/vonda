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

/**
 *
 * @author anna
 */
public class StatIf extends RTStatement {

  RTExpression condition;
  RTStatement statblockIf;
  RTStatement statblockElse;

  /**
   * if there is no else case, set statblockElse to null
   *
   * @param conditionString
   * @param condition the condition
   * @param statblockIf the if block
   * @param statblockElse the else block if existing
   * @param position
   */
  public StatIf(RTExpression cond, RTStatement statIf, RTStatement statElse) {
    condition = cond;
    statblockIf = statIf;
    statblockElse = statElse;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    return Arrays.asList(
        (statblockElse != null)
        ? new RudiTree[]{ condition, statblockIf, statblockElse }
        : new RudiTree[]{ condition, statblockIf });
  }
}
