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

import de.dfki.mlt.rudimant.compiler.ClassEnv;
import de.dfki.mlt.rudimant.compiler.Environment;

/** We need this class to unify the enter/leave Environment functions in Mem */
public class ToplevelBlock implements RTBlockNode {

  private ClassEnv _localClass;

  private ToplevelBlock _parentClass;

  public void setClass(ToplevelBlock parent, ClassEnv local) {
    _parentClass = parent; _localClass = local;
  }

  public ClassEnv getClassEnv() { return _localClass; }

  public ToplevelBlock getParentClass() { return _parentClass; }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings;

  public Environment getParentBindings() { return _localBindings.getParent(); }

  public Environment enterEnvironment(Environment parent) {
    return _localBindings != null ? _localBindings
        : (_localBindings = Environment.getEnvironment(parent));
  }

  public Environment getBindings() { return _localBindings; }
}
