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

public class DefaultLogger implements LogPrinter {

  protected void print(String s) {
    System.out.print(s);
  }

  protected void printTerm(String term, boolean value, boolean shortCut) {
    print("[" + (shortCut ? "unk" : value) + ": " + term + "]");
  }

  protected void printResult(String label, boolean value) {
    print("(" + label + ") " + Boolean.toString(value).toUpperCase() + ": ");
  }

  private boolean printRec(RuleInfo r, boolean[] result, int[] pos, boolean shortCut) {
    int elt = r.getExpression(pos[0]);
    ++pos[0]; // move to next element
    if (elt < 0) {
      if (RuleInfo.isNot(elt)) { // unary not
        print(RuleInfo.getOp(elt));
        return ! printRec(r, result, pos, shortCut);
      } // binary operator
      print("(");
      boolean left = printRec(r, result, pos, shortCut);
      print(RuleInfo.getOp(elt));
      boolean right = printRec(r, result, pos,
          shortCut || (elt == -1 && !left) || (elt == -2 && left));
      print(")");
      return (elt == -1) ? left && right : left || right;
    }
    // print term
    boolean termResult = result[elt + 1];
    printTerm(r.getBaseTerm(elt), termResult, shortCut);
    return termResult;
  }

  @Override
  public void printLog(RuleInfo ruleId, boolean[] result) {
    int[] pos = { 0 };
    printResult(ruleId.getLabel(), result[0]);
    printRec(ruleId, result, pos, false); print("\n");
  }
}
