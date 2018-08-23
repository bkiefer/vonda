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
import java.util.Collections;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 *
 * @author Anna Welker
 */
public class StatReturn extends RTStatement {

  String command;
  RTExpression returnExp;
  String ruleLabel;

  /**
   * give me the expression after 'return' as well as the String it is, so if
   * this is a return to a rule I can transform accordingly
   *
   * @param exp
   * @param lit
   */
  public StatReturn(RTExpression exp) {
    command = "return";
    returnExp = exp;
    ruleLabel = null;
  }

  /** A break with label */
  public StatReturn(String cmd, String label) {
    command = cmd;
    ruleLabel = label;
    returnExp = null;
  }

  /** A break without label, a cancel or cancelAll */
  public StatReturn(String cmd) {
    command = cmd;
    ruleLabel = null;
    returnExp = null;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public String toString() {
    String result = command;
    if (ruleLabel != null) result = result + " " + ruleLabel;
    if (returnExp != null) result = result + " " + returnExp.toString();
    return result;
  }

  public Iterable<? extends RudiTree> getDtrs() {
    if (returnExp == null) return Collections.emptyList();
    return Arrays.asList(new RudiTree[]{ returnExp });
  }
}
