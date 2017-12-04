package de.dfki.mlt.rudimant.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClient {

  private static Logger logger = LoggerFactory.getLogger(SimpleClient.class);

  private Socket socket;

  private int _portNumber;

  public static String hostName = "localhost";

  private OutputStreamWriter out;

  /**
   * A client that connects to the server on localhost at the given port to send
   * log information to the debugger.
   */
  public SimpleClient(int portNumber) {
    _portNumber = portNumber;
  }

  private boolean initClient() {
    try {
      socket = new Socket(hostName, _portNumber);
      out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
      logger.debug("Client has been connected.");
      return true;
    } catch (UnknownHostException e) {
      logger.error(e.toString());
      return false;
    } catch (IOException e) {
      logger.error(e.toString());
      return false;
    }
  }

  public boolean isConnected() {
    return socket != null && socket.isConnected();
  }

  public void disconnect() throws IOException {
    if (socket == null) return;
    out.write("\0");
    out.flush();
    socket.close();
    socket = null;
  }

  public void send(Object ... s) throws IOException {
    if (! isConnected()) {
      if (! initClient()) return;
    }
    boolean first = true;
    for (Object o : s) {
      if (! first) out.write(";");
      else first = false;
      out.write(o.toString());
    }
    out.write("\t");
    out.flush();
  }


  public static void main(String[] args) throws IOException, InterruptedException {
    SimpleClient scl = new SimpleClient(3665);
    try {
      int i = 0;
      while (i++ < 10) {
        scl.send("test", 345, 12345, 14);
        Thread.sleep(1000);
        scl.send("printLog", i, 234, 12);
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      System.out.println(e);
    } finally {
      scl.disconnect();
    }
  }
}
