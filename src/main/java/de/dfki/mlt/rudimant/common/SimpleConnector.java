package de.dfki.mlt.rudimant.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleConnector implements Runnable {

  private static final char EOM_CHAR = '\t';
  private static final char EOF_CHAR = '\0';
  protected static final String EOF = "" + EOF_CHAR;

  private static final Logger logger =
      LoggerFactory.getLogger(SimpleConnector.class);

  private Socket _socket;

  protected boolean closeRequested = false;

  private OutputStreamWriter out;
  private InputStreamReader in;

  private Thread readerThread;

  protected final Consumer<String[]> _callable;

  /**
   * A client that connects to the server on localhost at the given port to send
   * log information to the debugger.
   *
   * @param portNumber
   * @throws IOException
   * @throws UnsupportedEncodingException
   */
  protected SimpleConnector(Socket s, Consumer<String[]> c)
      throws UnsupportedEncodingException, IOException {
    _socket = s;
    _callable = c;
    in = new InputStreamReader(_socket.getInputStream(), "UTF-8");
    out = new OutputStreamWriter(_socket.getOutputStream(), "UTF-8");
  }

  public void run() {
    int c;
    StringBuffer sb = new StringBuffer();
    try {
      while (isConnected()) {
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
      logger.error("Error reading stream: {}", ex);
      return;
    }
    close();
    logger.info("Stopping read loop");
  }

  public boolean isAlive() {
    return readerThread == null || readerThread.isAlive();
  }

  public boolean isConnected() {
    return _socket != null && _socket.isConnected() && ! closeRequested;
  }

  public void close() {
    try {
      if (_socket != null) _socket.close();
    } catch (IOException ex) {
    }
    _socket = null;
  }

  public void disconnect() throws IOException {
    if (_socket != null && _socket.isConnected()) {
      send(EOF);
    }
    closeRequested = true;
  }

  public boolean send(String ... s) {
    try {
      if (! isConnected()) return false;
      boolean first = true;
      for (String o : s) {
        if (! first) out.write(";");
        else first = false;
      out.write(o.toString());
      }
      out.write(EOM_CHAR);
      out.flush();
    } catch (IOException ex) {
      logger.error("Closing socket: {}", ex.toString());
      close();
      return false;
    }
    return true;
  }
}
