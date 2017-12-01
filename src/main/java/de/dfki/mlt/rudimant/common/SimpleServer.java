package de.dfki.mlt.rudimant.common;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * establishes an server in the agent to be able to modify which rules are
 * logged, and to see watched objects in the DB in the future
 */
public class SimpleServer  {

  private static Logger logger = LoggerFactory.getLogger(SimpleServer.class);

  public interface Callable {
    public void execute(String[] args);
  }

  private Thread serverThread;

  private Callable _callable;

  Socket clientSocket;

  public SimpleServer(Callable c) throws IOException {
    _callable = c;
  }

  public boolean isAlive() {
    return serverThread == null || serverThread.isAlive();
  }

  /** starts the debugging service for the agent */
  public void startServer(int port, String name) {
    try {
      serverThread = new Thread() {
        public void run() {
          try (ServerSocket serverSocket = new ServerSocket(port)) {
            clientSocket = serverSocket.accept();
            InputStream in = clientSocket.getInputStream();
            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            int c;
            StringBuffer sb = new StringBuffer();
            do {
              c = inReader.read();
              switch (c) {
              case '\t':
                  String s = sb.toString();
                  String[] args = s.split(";");
                  _callable.execute(args);
                  sb = new StringBuffer();
                  break;
                case '\0':
                  return;
                default:
                  sb.append((char)c);
              }
            } while (clientSocket.isConnected() && ! clientSocket.isClosed());
            serverSocket.close();
          } catch (IOException ex) {
            logger.error("Error reading DebuggingService Stream: {}", ex);
          }
        }
      };
      serverThread.setName(name);
      serverThread.setDaemon(true);
      serverThread.start();
      logger.info("Agent debug server started");
    } catch (Exception exception) {
      logger.error("Agent debug server: " + exception.toString());
    }
  }

  public static void main(String[] args) throws IOException {
    final SimpleServer simplServ = new SimpleServer(
        (s) -> {System.out.println(Arrays.toString(s));}
        );

    Thread sideThread = new Thread() {
      public void run() {
        try {
          int i =0;
          while (i++ < 20 && simplServ.isAlive()) {
            System.out.println("Rödeln...");
            Thread.sleep(5000);
          }
        } catch (InterruptedException v) {
          System.out.println(v);
        }
      }
    };

    sideThread.start();
    simplServ.startServer(3665, "testServer");
  }
}
