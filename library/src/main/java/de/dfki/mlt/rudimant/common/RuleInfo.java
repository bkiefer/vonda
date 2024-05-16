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

package de.dfki.mlt.rudimant.common;

import java.util.ArrayList;
import java.util.List;

public class RuleInfo extends BasicInfo {
  protected int _id;

  protected List<Integer> _expr;

  protected List<String> _baseTerms;

  protected static final String[] opId2Op = { "&&", "||", "!" };

  public static boolean isNot(int opId) { return opId == -3;  }

  public static String getOp(int opId) { return opId2Op[-(opId + 1)]; }

  // to simplify tests
  public static void resetIdGenerator() {
    ruleId = 0;
  }

  public RuleInfo() {}

  public RuleInfo(String name, int line, BasicInfo parent) {
    super(name, line, parent);
    _id = ruleId++;
    _expr = new ArrayList<>();
    _baseTerms = new ArrayList<>();
  }

  public void setId(int id) {
    _id = id;
    ruleId = Math.max(ruleId, id + 1);
  }

  public int getId() { return _id; }

  public List<Integer> getExpression() {
    return _expr;
  }

  public void setExpression(List<Integer> _expr) {
    this._expr = _expr;
  }

  public int getExpression(int pos) {
    return _expr.get(pos);
  }

  public List<String> getBaseterms() {
    return _baseTerms;
  }

  public void setBaseterms(List<String> _baseTerms) {
    this._baseTerms = _baseTerms;
  }

  public String getBaseTerm(int i) {
    return this._baseTerms.get(i);
  }

  public void addOp(String op) {
    int opId = -99;
    switch (op.charAt(0)) {
    case '&': opId = -1; break;
    case '|': opId = -2; break;
    case '!': opId = -3; break;
    default:
      throw new IllegalArgumentException("Unknown boolean operator " + op);
    }
    _expr.add(opId);
  }

  public String opId2Op(int opId) {
    return opId2Op[(-opId + 1)];
  }

  /** 1. add the String of the node to the base term list in the rule info
   *  2. add the number of the arg to the infix representation of the expr
   *     in the rule info
   */
  public void addBaseTerm(String term) {
    _baseTerms.add(term);
    _expr.add(_baseTerms.size() - 1);
  }

  public String resultVarName() {
    return "__x" + _id;
  }

  public int noBaseTerms() {
    return _baseTerms.size();
  }
}
