package de.dfki.mlt.rudimant.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClient {

  private static Logger logger = LoggerFactory.getLogger(SimpleClient.class);

  private Socket socket;

  private final int _portNumber;

  public static String hostName = "localhost";

  private OutputStreamWriter out;

  /**
   * A client that connects to the server on localhost at the given port to send
   * log information to the debugger.
   *
   * @param portNumber
   */
  public SimpleClient(int portNumber) {
    _portNumber = portNumber;
  }

  private Date nextTryToConnect;
  private Date nextLog;

  private final int noLogInterval = 10;
  private final int reconnectInterval = 1;

  private boolean initClient() {
    Date currentTime = new Date();
    if (nextTryToConnect == null || currentTime.after(nextTryToConnect)) {
      try {
        /* make sure that only one try per second occurs */
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, reconnectInterval);
        nextTryToConnect = cal.getTime();

        socket = new Socket(hostName, _portNumber);
        out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        logger.debug("Client has been connected.");
        return true;
      } catch (UnknownHostException e) {
        logger.error(e.toString());
        return false;
      } catch (IOException e) {
        if (nextLog == null || currentTime.after(nextLog)) {
          /* make sure only every 10 seconds this will be logged */
          Calendar cal = Calendar.getInstance();
          cal.setTime(new Date());
          cal.add(Calendar.SECOND, noLogInterval);
          nextLog = cal.getTime();

          logger.error("Client could not connect (Reconnect every "
            + reconnectInterval + " second(s), no log for " + noLogInterval
            + " second(s)): " + e.toString());
        }
        return false;
      }
    }
    return false;
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

  public void send(String ... s) throws IOException {
    if (! isConnected()) {
      if (! initClient()) return;
    }
    boolean first = true;
    for (String o : s) {
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
      while (i++ < 50) {
        scl.send("test", "345", "12345", "14");
        Thread.sleep(250);
//        scl.send("printLog", Integer.toString(i), "234", "12");
//        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      System.out.println(e);
    } finally {
      scl.disconnect();
    }
  }
}
