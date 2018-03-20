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
        _conn = new SimpleConnector(serverSocket.accept(), _consumer);
        _conn.run();
        logger.info("Agent debug server started on port {}", _portNumber);
      }
    } catch (IOException ex) {
      logger.error("Server Error: {}", ex.toString());
    }
    serverSocket = null;
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
    if (_conn != null && _conn.isConnected())
      _conn.send(strings);
  }

  public boolean isRunning() {
    return !closeRequested;
  }

  protected void close()  {
    closeRequested = true;
    if (serverSocket == null) return;
    try {
      logger.info("Closing Server Socket");
      serverSocket.close();
      logger.info("Closing Socket");
      _conn.close();
      _conn = null;
    } catch (IOException ex) {
      logger.error("Error closing socket: {}", ex);
    } finally {
      serverSocket = null;
    }
  }

  public static void main(String[] args) throws IOException {
    final SimpleServer simplServ = new SimpleServer(
        (s) -> {System.out.println(Arrays.toString(s));},
        3664, "testServer"
        );

    Thread sideThread = new Thread() {
      public void run() {
        simplServ.startServer();
        try {
          while (simplServ.isRunning()) {
            System.out.println("Rödeln...");
            Thread.sleep(500);
            simplServ.send("one", "two");
          }
        } catch (InterruptedException v) {
          System.out.println(v);
        }
      }
    };

    sideThread.start();
  }
}
