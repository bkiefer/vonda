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

import de.dfki.mlt.rudimant.compiler.Type;

public class ExpArrayAccess extends RTExpLeaf {

  RTExpression index;
  RTExpression array;

  public ExpArrayAccess(RTExpression array, RTExpression index) {
    this.array = array;
    this.index = index;
  }

  @Override
  public String toString() {
    return this.content;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visit(this);
  }

  public void propagateType(Type upperType, VisitorType v) {
    if (type != null && ! type.isUnspecified()) {
      logger.error("Why didn't this type percolate up? {} {}",
          v.getFullText(this), type);
      return;
    }
    type = upperType;
    if (array.type.isArray()) {
      array.getType().setInnerType(type);
    }
  }

}
