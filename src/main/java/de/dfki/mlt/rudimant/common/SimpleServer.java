package de.dfki.mlt.rudimant.common;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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

  private ServerSocket serverSocket;

  private Thread serverThread;

  private Callable _callable;

  public SimpleServer(Callable c) throws IOException {
    _callable = c;
  }

  /** starts the debugging service for the agent */
  public synchronized void startServer(int port, String name) {
    try {
      serverSocket = new ServerSocket(port);
      Socket clientSocket = serverSocket.accept();
      InputStream in = clientSocket.getInputStream();
      InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
      serverThread = new Thread(){
        public void run() {
          int c;
          StringBuffer sb = new StringBuffer();
          do {
            try {
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
                sb.append(c);
              }
            } catch (IOException ex) {
              logger.error("Error reading DebuggingService Stream: {}", ex);
            }
          } while (true);
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
}
