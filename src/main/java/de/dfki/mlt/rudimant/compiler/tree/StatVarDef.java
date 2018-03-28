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

import java.util.ArrayList;
import java.util.Collections;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * type_spec VARIABLE SEMICOLON = only to get the type of this variable into
 * memory
 *
 * @author Anna Welker
 */
public class StatVarDef extends RTStatement {

  String variable;
  Type type;
  RTExpression toAssign;
  boolean varIsFinal;
  boolean isDefinition;

  public StatVarDef(boolean isFinal, Type type, String variable,
      RTExpression assign) {
    this.varIsFinal = isFinal;
    this.isDefinition = isFinal || !type.isUnspecified();
    this.type = type;
    this.variable = variable;
    this.toAssign = assign;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public String toString() {
    return (varIsFinal ? "final " : "")
        + type + " " + variable
        + (toAssign != null ? ("= " + toAssign.toString()) : "") + ";"; }

  @Override
  @SuppressWarnings("serial")
  public Iterable<? extends RudiTree> getDtrs() {
    return new ArrayList<RudiTree>(){{ add(toAssign); }};
  }
}
