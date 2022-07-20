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

package de.dfki.mlt.rudimant.agent.nlp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.lt.tr.dialogue.cplan.UtterancePlanner;
import de.dfki.lt.tr.dialogue.cplan.functions.Function;

public class InfoStateFunction implements Function {

  private Map<String, BaseInfoStateAccess> _accessMap;

  public InfoStateFunction() {
    _accessMap = new HashMap<String, BaseInfoStateAccess>();
  }

  /** This method gives mapping rules for proto LFs access to information state
   *  variables coming from the user model or the game move generator
   * @param name the name of the variable to retrieve
   * @return
   */
  protected Object getInfoVar(String name) {
    for (BaseInfoStateAccess access : _accessMap.values()) {
      Object result = access.getInfoVar(name);
      if (result != null)
        return result;
    }
    return null;
  }

  @Override
  public Object apply(List<DagNode> arg0) {
    String name = arg0.get(0).toString();
    Object value = getInfoVar(name);
    return (value == null ? "unknown" : value.toString());
  }

  @Override
  public int arity() { return 1; }

  @Override
  public String name() { return "getInfoVar"; }

  @Override
  public void register(UtterancePlanner arg0) {}

  public void setAccess(String what, BaseInfoStateAccess access) {
    _accessMap.put(what, access);
  }

}
