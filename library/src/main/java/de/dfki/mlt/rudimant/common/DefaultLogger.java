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

import java.io.OutputStream;
import java.io.PrintStream;

public class DefaultLogger implements LogPrinter {

  PrintStream _out;
  
  protected static String RED="-";
  protected static String GREEN="+";
  protected static String GRAY="_";

  public DefaultLogger(OutputStream out) {
    if (out instanceof PrintStream) {
      _out = (PrintStream)out;
    } else {
      _out = new PrintStream(out);
    }
  }
  
  public DefaultLogger() {
    this(System.out);
  }
  
  protected void print(String s) {
    _out.print(s);
    _out.flush();
  }

  protected void printWithTags(String s, String pref, String suff) {
    print(pref + s + suff);
  }

  protected String getPref(boolean value, boolean shortCut) {
    return shortCut ? GRAY : value ? GREEN : RED;
  }

  protected String getSuff(boolean value, boolean shortCut) {
    return getPref(value, shortCut);
  }

  protected void printTerm(String term, boolean value, boolean shortCut) {
    printWithTags(term, getPref(value, shortCut), getSuff(value, shortCut));
  }

  protected void printResult(String label, boolean value) {
    printWithTags(label, getPref(value, false), getSuff(value, false) + ": ");
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
