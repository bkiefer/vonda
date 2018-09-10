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

public interface RudiVisitor {

  public void visit(RudiTree node);

  // Statements
  public void visit(StatGrammarRule node);

  public void visit(StatAbstractBlock node);

  public void visit(StatFor1 node);

  public void visit(StatFor2 node);

  public void visit(StatFor3 node);

  public void visit(StatIf node);

  public void visit(StatMethodDeclaration node);

  public void visit(StatPropose node);

  public void visit(StatTimeout node);

  public void visit(StatReturn node);

  public void visit(StatSetOperation node);

  public void visit(StatSwitch node);

  public void visit(StatVarDef node);

  public void visit(StatFieldDef node);

  public void visit(StatWhile node);

  public void visit(StatExpression node);

  // Expressions
  public void visit(ExpArithmetic node);

  public void visit(ExpArrayAccess node);

  public void visit(ExpAssignment node);

  public void visit(ExpBoolean node);

  public void visit(ExpCast node);

  public void visit(ExpDialogueAct node);

  public void visit(ExpConditional node);

  public void visit(ExpLambda node);

  public void visit(ExpListLiteral node);

  public void visit(ExpNew node);

  public void visit(ExpFieldAccess node);

  public void visit(ExpFuncCall node);

  public void visit(ExpSingleValue node);

  public void visit(ExpVariable node);

  public void visit(ExpPropertyAccess node);
}
