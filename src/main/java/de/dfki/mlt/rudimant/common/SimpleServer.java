package de.dfki.mlt.rudimant.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

  public SimpleServer(Consumer<String[]> c, int port, String name) throws IOException {
    super(port, c, name);
  }

  public boolean isAlive() {
    return readerThread == null || readerThread.isAlive();
  }

  /** starts the debugging service for the agent */
  public boolean startServer() {
    Thread t = new Thread() {
      public void run() {
        try {
          serverSocket = new ServerSocket(_portNumber);
          socket = serverSocket.accept();
          in = new InputStreamReader(socket.getInputStream(), "UTF-8");
          out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
          startReading();
          logger.info("Agent debug server started");

        } catch (IOException exception) {
          logger.error("Agent debug server: " + exception.toString());
        }
      }
    };
    t.setDaemon(true);
    t.setName("StartServer");
    t.start();
    return true;
  }

  protected boolean init() { return false; }

  public void stop()  {
    if (serverSocket == null) return;
    closeRequested = true;
    try {
      serverSocket.close();
      socket.close();
    } catch (IOException ex) {
      logger.error("Error closing socket: {}", ex);
    } finally {
      serverSocket = null;
      socket = null;
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
            Thread.sleep(5000);
            simplServ.send("one", "two");
          }
        } catch (InterruptedException v) {
          System.out.println(v);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    };

    sideThread.start();
    simplServ.startServer();
  }
}
