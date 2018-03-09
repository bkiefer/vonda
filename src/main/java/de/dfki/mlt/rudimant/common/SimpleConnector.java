package de.dfki.mlt.rudimant.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.function.Consumer;

import org.slf4j.Logger;

public abstract class SimpleConnector {

  private static final char EOM_CHAR = '\t';
  private static final char EOF_CHAR = '\0';
  protected static final String EOF = "" + EOF_CHAR;

  protected static Logger logger;

  private Socket socket;

  protected final int _portNumber;

  private final String _name;

  protected boolean closeRequested = false;

  private OutputStreamWriter out;
  private InputStreamReader in;

  private Thread readerThread;

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

  protected void startReading(Socket s)
      throws UnsupportedEncodingException, IOException {
    socket = s;
    in = new InputStreamReader(socket.getInputStream(), "UTF-8");
    out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
    readerThread = new Thread() {
      public void run() {
        while (! closeRequested) {
          int c;
          StringBuffer sb = new StringBuffer();
          try {
            while (isConnected()
                && (! socket.isClosed() || closeRequested)) {
              c = in.read();
              switch (c) {
              case EOM_CHAR:
                String s = sb.toString();
                String[] args = s.split(";");
                _callable.accept(args);
                sb = new StringBuffer();
                break;
              case EOF_CHAR:
                return;
              default:
                sb.append((char)c);
                break;
              }
            }
          } catch (IOException ex) {
            logger.error("Error reading {} Stream: {}", _name, ex);
            return;
          }
        }
        close();
      }
    };
    readerThread.setName(_name);
    readerThread.setDaemon(true);
    readerThread.start();
  }

  public boolean isAlive() {
    return readerThread == null || readerThread.isAlive();
  }

  public boolean isConnected() {
    return socket != null && socket.isConnected();
  }

  protected void close() {
    try {
      if (socket != null) socket.close();
    } catch (IOException ex) {
    }
    socket = null;
  }

  public void disconnect() throws IOException {
    if (! isConnected()) {
      closeRequested = true;
      return;
    }
    send(EOF);
    closeRequested = true;
  }

  public void send(String ... s) {
    try {
      if (! isConnected()) {
        if (! init()) return;
      }
      boolean first = true;
      for (String o : s) {
        if (! first) out.write(";");
        else first = false;
      out.write(o.toString());
      }
      out.write(EOM_CHAR);
      out.flush();
    } catch (IOException ex) {
      close();
    }
  }
}
