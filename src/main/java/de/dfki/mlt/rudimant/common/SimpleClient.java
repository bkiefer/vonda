package de.dfki.mlt.rudimant.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.function.Consumer;

import org.slf4j.LoggerFactory;

public class SimpleClient extends SimpleConnector {

  static { logger = LoggerFactory.getLogger(SimpleServer.class); }

  private final String _hostName;

  private SocketAddress _addr;

  /**
   * A client that connects to the server on localhost at the given port to send
   * log information to the debugger.
   *
   * @param portNumber
   */
  public SimpleClient(String hostname, int portNumber,
      Consumer<String[]> c, String name) {
    super(portNumber, c, name);
    _hostName = hostname;
    _addr = new InetSocketAddress(_hostName, _portNumber);
  }

  // msecs
  private long nextTryToConnect = 0 ;
  private long nextLog;

  // both in msec
  private long noLogInterval = 30000;
  private long reconnectInterval = 1000;

  public boolean init() {
    long currentTime = System.currentTimeMillis();
    if (currentTime - nextTryToConnect > 0) {
      try {
        /* make sure that only one try per reconnectInterval occurs */
        nextTryToConnect = currentTime + reconnectInterval;
        Socket s = new Socket();
        s.connect(_addr);
        startReading(s);
        logger.debug("Client has been connected.");
        return true;
      } catch (UnknownHostException e) {
        logger.error("Unknown host {}: {}", _hostName, e.toString());
        return false;
      } catch (IOException e) {
        close();
        // make sure only every noLogInterval milliseconds this will be logged
        if (currentTime - nextLog > 0) {
          nextLog = currentTime + noLogInterval;

          logger.error("Debug client could not connect (Reconnect every "
              + reconnectInterval / 1000.0 + " second(s), no log for "
              + noLogInterval / 1000.0 + " second(s)): {}", e.getMessage());
        }
        return false;
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    SimpleClient scl = new SimpleClient(
        "localhost", 3664,
        (s) -> {System.out.println("Client: " + Arrays.toString(s));},
        "testClient");
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
