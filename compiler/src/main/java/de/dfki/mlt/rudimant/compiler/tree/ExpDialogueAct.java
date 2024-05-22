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

import static de.dfki.mlt.rudimant.compiler.Constants.DIALOGUE_ACT_TYPE;

import java.util.ArrayList;
import java.util.List;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * this class represents dialogue acts
 *
 * @author Anna Welker
 */
public class ExpDialogueAct extends RTExpression {

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment
  RTExpression daType;
  RTExpression proposition;
  List<RTExpression> exps;

  public ExpDialogueAct(RTExpression da,
      RTExpression prop, List<RTExpression> args) {
    daType = da;
    proposition = prop;
    exps = args;
    type = new Type(DIALOGUE_ACT_TYPE);
  }

  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    List<RudiTree> dtrs = new ArrayList<>();
    dtrs.add(daType);
    dtrs.add(proposition);
    dtrs.addAll(exps);
    return dtrs;
  }
}
