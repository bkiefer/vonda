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

  public void visitNode(RudiTree node);

  // Statements
  public void visitNode(StatGrammarRule node);

  public void visitNode(StatAbstractBlock node);

  public void visitNode(StatFor1 node);

  public void visitNode(StatFor2 node);

  public void visitNode(StatFor3 node);

  public void visitNode(StatIf node);

  public void visitNode(StatMethodDeclaration node);

  public void visitNode(StatPropose node);

  public void visitNode(StatTimeout node);

  public void visitNode(StatReturn node);

  public void visitNode(StatSetOperation node);

  public void visitNode(StatSwitch node);

  public void visitNode(StatVarDef node);

  public void visitNode(StatFieldDef node);

  public void visitNode(StatWhile node);

  public void visitNode(StatExpression node);

  // Expressions
  public void visitNode(ExpArithmetic node);

  public void visitNode(ExpArrayAccess node);

  public void visitNode(ExpAssignment node);

  public void visitNode(ExpBoolean node);

  public void visitNode(ExpCast node);

  public void visitNode(ExpDialogueAct node);

  public void visitNode(ExpConditional node);

  public void visitNode(ExpLambda node);

  public void visitNode(ExpListLiteral node);

  public void visitNode(ExpNew node);

  public void visitNode(ExpFieldAccess node);

  public void visitNode(ExpFuncCall node);

  public void visitNode(ExpSingleValue node);

  public void visitNode(ExpVariable node);
}
