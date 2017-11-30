package de.dfki.mlt.rudimant.agent;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import de.dfki.mlt.rudimant.common.LogPrinter;
import de.dfki.mlt.rudimant.common.RuleInfo;

public class RemoteLogger implements LogPrinter {
  private Socket socket;

  public static String hostName = "localhost";

  private OutputStreamWriter out;

  /** A client that connects to the server on localhost at the given port to
   *  send log information to the debugger.
   */
  public RemoteLogger(int portNumber) {
    try {
      socket = new Socket(hostName, portNumber);
      out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
    } catch (UnknownHostException e) {
      Agent.logger.error("Unkown host {}: {}", hostName, e);
    } catch (IOException e) {
      Agent.logger.error("IOException {}: {}", hostName, e);
    }
  }

  @Override
  public void printLog(RuleInfo ruleId, boolean[] result) {
    try {
      out.write(Integer.toString(ruleId.getId()));
      for (boolean b : result) {
        out.write(";");
        out.write(Boolean.toString(b));
      }
      out.write("\t");
      out.flush();
    } catch (IOException e) {
      Agent.logger.error(e.getMessage());
    }
  }

}
