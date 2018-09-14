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

/**
 * representation of a variable
 *
 * @author Anna Welker
 */
public class ExpVariable extends RTExpLeaf {

  public ExpVariable(String representation, Type t) {
    content = representation;
    type = t;
  }

  public ExpVariable(String representation) {
    this(representation, Type.getNoType());
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
    // it's not an error if the type is null
    type = upperType;
  }
}
