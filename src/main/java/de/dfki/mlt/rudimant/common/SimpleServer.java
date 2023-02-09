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

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * establishes an server in the agent to be able to modify which rules are
 * logged, and to see watched objects in the DB in the future
 */
public class SimpleServer implements Runnable {

  public static final int DEFAULT_PORT = 3664;

  private static final Logger logger =
      LoggerFactory.getLogger(SimpleServer.class);

  private ServerSocket serverSocket;

  private int _portNumber;
  private String _name;
  private Consumer<String[]> _consumer;
  private boolean closeRequested;

  private SimpleConnector _conn;

  public SimpleServer(Consumer<String[]> c, int port, String name) {
    _portNumber = port;
    _name = name;
    _consumer = c;
    closeRequested = false;
  }

  public void run() {
    logger.info("Initialize Server");
    //if (! isConnected()) {
    try {
      serverSocket = new ServerSocket(_portNumber);
      while (! closeRequested) {
        if (_conn != null) _conn.close();
        logger.info("Waiting for client to connect...");
        _conn = new SimpleConnector(serverSocket.accept(), _consumer);
        logger.info("Client connected on port {}", _portNumber);
        _conn.listen();
      }
    } catch (IOException ex) {
      logger.error("Server Error: {}", ex.toString());
    }
    if (_conn != null) {
      _conn.close();
      _conn = null;
    }
  }

  /** starts the debugging service for the agent (non-blocking) */
  public boolean startServer() {
    Thread t = new Thread(this);
    t.setDaemon(true);
    t.setName(_name);
    t.start();
    return true;
  }

  public void send(String ...strings) {
    if (isConnected())
      _conn.send(strings);
  }

  public boolean isConnected() {
    return (_conn != null && _conn.isConnected());
  }

  public boolean isRunning() {
    return !closeRequested;
  }

  protected void close()  {
    closeRequested = true;
    if (_conn != null) {
      logger.info("Closing simple connector");
      // this includes closing the socket
      _conn.close();
      _conn = null;
      serverSocket = null;
    } else if (serverSocket != null) {
      logger.info("Closing server socket");
      try {
        serverSocket.close();
      } catch (IOException e) {
        logger.error("Error closing socket: {}", e);
      } finally {
        serverSocket = null;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    final SimpleServer simplServ = new SimpleServer(
        (s) -> {System.out.println(Arrays.toString(s));},
        3664, "testServer"
        );

    simplServ.startServer();
    try {
      while (simplServ.isRunning()) {
        System.out.println("Rödeln...");
        Thread.sleep(500);
        simplServ.send("printLog", "1", "true", "true");
      }
    } catch (InterruptedException v) {
      System.out.println(v);
    }
  }
}
