package de.dfki.mlt.rudimant.common;

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
          _conn = new SimpleConnector(s, _consumer);
          _conn.run();
          logger.debug("Client has been connected.");
        } catch (UnknownHostException e) {
          logger.error("Unknown host {}: {}", _hostName, e.toString());
        } catch (IOException e) {
          if (_conn != null) _conn.close();
          _conn = null;
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
    if (isConnected())
      _conn.send(s);
  }

  public boolean isConnected() {
    return (_conn != null && _conn.isConnected());
  }

  public void disconnect() {
    closeRequested = true;
    if (_conn != null) {
      _conn.close();
      _conn = null;
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    SimpleClient scl = new SimpleClient(
        "localhost", 3664,
        (s) -> {System.out.println("Client: " + Arrays.toString(s));},
        "testClient");
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
