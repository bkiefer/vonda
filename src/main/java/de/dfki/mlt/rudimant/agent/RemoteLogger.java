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

package de.dfki.mlt.rudimant.agent;

import de.dfki.mlt.rudimant.common.LogPrinter;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.common.SimpleServer;

public class RemoteLogger implements LogPrinter {
  private SimpleServer _server;

  /** A client that connects to the server on localhost at the given port to
   *  send log information to the debugger.
   */
  public RemoteLogger(SimpleServer server) {
    _server = server;
  }

  @Override
  public void printLog(RuleInfo ruleId, boolean[] result) {
    String[] toSend = new String[result.length + 2];
    toSend[0] = "printLog";
    toSend[1] = Integer.toString(ruleId.getId());
    for (int i = 0; i < result.length; ++i) {
      toSend[i + 2] = Boolean.toString(result[i]);
    }
    _server.send(toSend);
  }

}
