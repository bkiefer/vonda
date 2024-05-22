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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClient implements Runnable {

  private static final Logger logger =
      LoggerFactory.getLogger(SimpleClient.class);

  private final String _hostName;

  private SocketAddress _addr;

  private SimpleConnector _conn;

  private Consumer<String[]> _consumer;
  private String _name;

  private boolean closeRequested;

  private final PropertyChangeSupport support;
  public enum ConnStatus {
    ONLINE,
    OFFLINE,
    TRYING
  }
  private ConnStatus connStatus;


  /**
   * A client that connects to the server on localhost at the given port to send
   * log information to the debugger.
   *
   * @param portNumber
   */
  public SimpleClient(String hostname, int portNumber,
      Consumer<String[]> c, String name) {
    _name = name;
    _consumer = c;
    _hostName = hostname;
    _addr = new InetSocketAddress(_hostName, portNumber);
    closeRequested = false;
    support = new PropertyChangeSupport(this);
    connStatus = ConnStatus.OFFLINE;
  }

  private void setConnStatus(ConnStatus connStatus) {
    this.support.firePropertyChange("connStatus", this.connStatus, connStatus);
    this.connStatus = connStatus;
  }

  public ConnStatus getConnStatus() {
    return this.connStatus;
  }

  // msecs
  private long nextTryToConnect = 0 ;
  private long nextLog;

  // both in msec
  private long noLogInterval = 30000;
  private long reconnectInterval = 1000;

  public void run() {
    while (! closeRequested) {
      long currentTime = System.currentTimeMillis();
      if (currentTime - nextTryToConnect > 0) {
        try {
          /* make sure that only one try per reconnectInterval occurs */
          nextTryToConnect = currentTime + reconnectInterval;
          Socket s = new Socket();
          s.connect(_addr);
          // connecting has succeeded
          logger.debug("Client has been connected");
          _conn = new SimpleConnector(s, _consumer);
          setConnStatus(ConnStatus.ONLINE);
          // call the run method of the runnable, i.e. this method does NOT return as long
          // as _conn is working fine
          _conn.listen();
          logger.debug("Connection lost");
          setConnStatus(ConnStatus.OFFLINE);
        } catch (UnknownHostException e) {
          logger.error("Unknown host {}: {}", _hostName, e.toString());
        } catch (IOException e) {
          if (_conn != null) _conn.close();
          _conn = null;
          setConnStatus(ConnStatus.TRYING);
          // make sure only every noLogInterval milliseconds this will be logged
          if (currentTime - nextLog > 0) {
            nextLog = currentTime + noLogInterval;

            logger.error("No connection (Reconnect every "
                + reconnectInterval / 1000.0 + " second(s), no log for "
                + noLogInterval / 1000.0 + " second(s)): {}", e.getMessage());
          }
          try {
            Thread.sleep(reconnectInterval);
          } catch (InterruptedException e1) {
            return;
          }
        }
      }
    }
  }

  public void startClient() {
    Thread t = new Thread(this);
    t.setDaemon(true);
    t.setName(_name);
    t.start();
  }

  public void send(String ... s) {
    if (this.connStatus == ConnStatus.ONLINE)
      _conn.send(s);
  }

  public void disconnect() {
    closeRequested = true;
    if (_conn != null) {
      if (_conn.isConnected()) {
        try {
          _conn.disconnect();
        } catch (IOException e) {
          logger.error("Error while disconnecting from server: {}",
                       e.toString());
        }
      }
      _conn.close();
      _conn = null;
    }
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    SimpleClient scl = new SimpleClient(
        "localhost", 3664,
        (s) -> {System.out.println("Client: " + Arrays.toString(s));},
        "testClient");
    scl.addPropertyChangeListener(e ->
      System.out.println("CONNECTION STATUS CHANGED: " + e.getNewValue()));
    scl.startClient();
    try {
      int i = 0;
      while (i++ < 500) {
        scl.send("test", "345", "12345", "14");
        Thread.sleep(250);
      }
    } catch (InterruptedException e) {
      System.out.println(e);
    } finally {
      scl.disconnect();
    }
  }
}
