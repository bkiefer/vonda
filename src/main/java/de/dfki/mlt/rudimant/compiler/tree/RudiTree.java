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
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.dfki.mlt.rudimant.common.Location;

/**
 * all classes that can be created by the ParseTreeVisitor should implement this
 * interface, for it is the return type of the visit methods
 *
 * @author Anna Welker
 */
public abstract class RudiTree {

  /**
   * positions contains the start and stop positions of a Token according to its
   * ParserRuleContext. [0] = start of TokenIndex, [1] = stop of TokenIndex
   */
  public int[] positions;

  /** contains the origin file and the line Rudi Tree started on */
  public Location location;

  /** The input string that is covered by node */
  public String fullexp;

  public void visitWithComments(VisitorGeneration v) {
    int firstPos = positions[0];
    v.out.append(checkComments(v, firstPos));
    visit(v);
    // TODO: as endpos is where this node ends, we will never get to print anything
    //       here, will we?
    int endPos = positions[1];
    v.out.append(checkComments(v, endPos));
  }

  /** visitor method, for double dispatch */
  public abstract void visit(RudiVisitor v);

  /**
   * set positions and fullexp of to those of this object
   * @param Ruditree b
   * @return
   */
  public <T extends RudiTree> T fixFields(T b) {
    b.positions = positions;
    b.fullexp = fullexp;
    return b;
  }

  public RTStatement ensureStatement() {
    if (this instanceof RTExpression) {
      return fixFields(new StatExpression((RTExpression)this));
    }
    return (RTStatement)this;
  }

  public static String removeJavaBrackets(String c){
    // Deal with java code
    if (c.startsWith("/*@")) {
      c = c.substring(3, c.length() - 3);
    }
    return c;
  }

  private List<Token> getTokensOfInterest(VisitorGeneration v, int firstPos) {
    List<Token> tokens = new ArrayList<>();
    while (!v.collectedTokens.isEmpty() && v.collectedTokens.get(0).getTokenIndex() < firstPos) {
      tokens.add(v.collectedTokens.get(0));
      v.collectedTokens.remove();
    }
    return tokens;
  }

  protected String checkComments(VisitorGeneration v, int firstPos) {
    List<Token> ts = getTokensOfInterest(v, firstPos);
    String allcomments = "";
    for (int i = 0; i < ts.size(); i++) {
      String comment = ts.get(i).getText();
      comment = removeJavaBrackets(comment);
      allcomments += comment;
    }
    allcomments = allcomments.trim();
    if(!allcomments.isEmpty()) allcomments += "\n";
    return allcomments;
  }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param context The ParserRuleContext.
   * @return RudiTree
   */
  public RudiTree setPosition(ParserRuleContext context, String originClass) {
    positions = new int[]{
      context.getStart().getTokenIndex(),
      context.getStop().getTokenIndex()
    };
    location = new Location(originClass, context.getStart().getLine());
    fullexp = context.getText();
    return this;
  }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param tn The TerminalNode
   * @return RudiTree
   */
  public RudiTree setPosition(TerminalNode tn, String originClass) {
    positions = new int[]{
      tn.getSymbol().getTokenIndex(),
      tn.getSymbol().getTokenIndex()
    };
    location = new Location(originClass, tn.getSymbol().getTokenIndex());
    fullexp = tn.getText();
    return this;
  }

  public abstract Iterable<? extends RudiTree> getDtrs();

  public Location getLocation() {
    return location;
  }
}
