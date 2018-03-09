package de.dfki.mlt.rudimant.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.function.Consumer;

import org.slf4j.LoggerFactory;

/**
 * establishes an server in the agent to be able to modify which rules are
 * logged, and to see watched objects in the DB in the future
 */
public class SimpleServer extends SimpleConnector {

  static { logger = LoggerFactory.getLogger(SimpleServer.class); }

  private ServerSocket serverSocket;

  public SimpleServer(Consumer<String[]> c, int port, String name)
      throws IOException {
    super(port, c, name);
  }

  /** starts the debugging service for the agent (non-blocking) */
  public boolean startServer() {
    Thread t = new Thread() { public void run() { init(); } };
    t.setDaemon(true);
    t.setName("StartServer");
    t.start();
    return true;
  }

  protected boolean init() {
    try {
      if (! isConnected()) {
        close();
        serverSocket = new ServerSocket(_portNumber);
        startReading(serverSocket.accept());
        logger.info("Agent debug server started on port {}", _portNumber);
      }
    }
    catch (IOException ex) {
      return false;
    }
    return true;
  }

  protected void close()  {
    if (serverSocket == null) return;
    try {
      serverSocket.close();
      super.close();
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
        try {
          while (true && simplServ.isAlive()) {
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
    simplServ.startServer();
  }
}
