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

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * type_spec VARIABLE SEMICOLON = only to get the type of this variable into
 * memory
 *
 * @author Anna Welker
 */
public class StatFieldDef extends RTStatement {

  String visibility;
  StatVarDef varDef;

  public StatFieldDef(String visibility, StatVarDef varDef) {
    if (visibility != null)
      this.visibility = visibility;
    else
      // set to public as default
      this.visibility = "public";
    this.varDef = varDef;
  }

  public StatFieldDef(String visibility, StatVarDef varDef, Type calledUpon) {
    // Actually, visibility shouldn't matter as this is only for Mem-info
    this.visibility = visibility != null? visibility : "public";
    this.varDef = varDef;
    this.varDef.type = Type.getFieldType(calledUpon, varDef.type);
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public String toString() {
    return (visibility != null ? visibility + " " : "") + varDef;
  }

  @SuppressWarnings("serial")
  public Iterable<? extends RudiTree> getDtrs() {
    return new ArrayList<RudiTree>(){{ add(varDef); }};
  }
}
