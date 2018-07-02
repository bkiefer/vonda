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

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.common.SimpleServer;

/**
 * establishes an server in the agent to be able to modify which rules are
 * logged, and to see watched objects in the DB in the future
 */
public class DebuggingService  {

  public static final Logger logger = LoggerFactory.getLogger("DebuggingService");

  SimpleServer server;

  /** A debugging service for the given agent */
  public DebuggingService(Agent agent, int port) throws IOException {
    server = new SimpleServer(
        (args) -> {
          String command = args[0];
          //String[] parameters = Arrays.copyOfRange(args, 1, args.length);
          switch (command) {
            case "setLogStat":
              int ruleId = Integer.parseInt(args[1]);
              int logMask = Integer.parseInt(args[2]);
              agent.logRule(ruleId, logMask);
              logger.debug("set rule {} to {} ", ruleId, logMask);
              break;
            case "reqFieldInfo":
              String fieldName = args[1];
              logger.debug("requesting info must be implemented yet");
              break;
            default:
             Agent.logger.error("Illegal DebuggingService Call: {}",
                Arrays.toString(args));
          }
        }, port, "DebuggingService");
  }

  /** starts the debugging service for the agent */
  public synchronized SimpleServer startServer() {
    server.startServer();
    return server;
  }
}
