package de.dfki.mlt.rudimant.agent;

import de.dfki.mlt.rudimant.common.LogPrinter;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.common.SimpleServer;

public class RemoteLogger implements LogPrinter {
  private SimpleServer _server;

  /** A client that connects to the server on localhost at the given port to
   *  send log information to the debugger.
   */
  public RemoteLogger(SimpleServer server) {
    _server = server;
  }

  @Override
  public void printLog(RuleInfo ruleId, boolean[] result) {
    String[] toSend = new String[result.length + 2];
    toSend[0] = "printLog";
    toSend[1] = Integer.toString(ruleId.getId());
    for (int i = 0; i < result.length; ++i) {
      toSend[i + 2] = Boolean.toString(result[i]);
    }
    _server.send(toSend);
  }

}
