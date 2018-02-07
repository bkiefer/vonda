package de.dfki.mlt.rudimant.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.function.Consumer;

import org.slf4j.Logger;

public abstract class SimpleConnector {

  protected static Logger logger;

  protected Socket socket;

  protected final int _portNumber;

  protected final String _name;

  protected boolean closeRequested = false;

  protected OutputStreamWriter out;

  protected InputStreamReader in;

  protected Thread readerThread;

  protected final Consumer<String[]> _callable;

  protected abstract boolean init();

  /**
   * A client that connects to the server on localhost at the given port to send
   * log information to the debugger.
   *
   * @param portNumber
   */
  protected SimpleConnector(int portNumber,
      Consumer<String[]> c, String name) {
    _portNumber = portNumber;
    socket = new Socket();
    _name = name;
    _callable = c;
  }

  protected void startReading() {
    readerThread = new Thread() {
      public void run() {
        int c;
        StringBuffer sb = new StringBuffer();
        try {
          while (socket.isConnected()
              && (! socket.isClosed() || closeRequested)) {
            c = in.read();
            switch (c) {
            case '\t':
              String s = sb.toString();
              String[] args = s.split(";");
              _callable.accept(args);
              sb = new StringBuffer();
              break;
            case '\0':
              return;
            default:
              sb.append((char)c);
            }
          }
        } catch (IOException ex) {
          logger.error("Error reading {} Stream: {}", _name, ex);
        }
      }
    };
    readerThread.setName(_name);
    readerThread.setDaemon(true);
    readerThread.start();
  }

  public boolean isConnected() {
    return socket != null && socket.isConnected();
  }

  public void send(String ... s) throws IOException {
    if (! isConnected()) {
      if (! init()) return;
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
}
