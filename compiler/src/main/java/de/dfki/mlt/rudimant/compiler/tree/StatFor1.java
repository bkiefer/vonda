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

import de.dfki.mlt.rudimant.compiler.Environment;

/**
 * FOR LPAR assignment SEMICOLON exp SEMICOLON exp? RPAR loop_statement_block =
 * a 'normal' for statement containing three ;
 *
 * @author Anna Welker
 */
public class StatFor1 extends RTStatement implements RTBlockNode {

  StatVarDef initialization;
  RTExpression condition;
  RTExpression increment;
  RTStatement statblock;

  public StatFor1(StatVarDef assignment, RTExpression condition,
          RTExpression arithmetic, RTStatement statblock) {
    this.initialization = assignment;
    this.condition = condition;
    this.increment = arithmetic;
    this.statblock = statblock;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { initialization, condition, increment, statblock };
    return Arrays.asList(dtrs);
  }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings;

  public Environment getParentBindings() { return _localBindings.getParent(); }

  public Environment enterEnvironment(Environment parent) {
    return _localBindings != null ? _localBindings
        : (_localBindings = Environment.getEnvironment(parent));
  }
}
