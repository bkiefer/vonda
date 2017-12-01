package de.dfki.mlt.rudimant.agent;

import java.io.IOException;

import de.dfki.mlt.rudimant.common.LogPrinter;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.common.SimpleClient;

public class RemoteLogger implements LogPrinter {
  private SimpleClient client;

  /** A client that connects to the server on localhost at the given port to
   *  send log information to the debugger.
   */
  public RemoteLogger(int portNumber) {
    client = new SimpleClient(portNumber);
  }

  @Override
  public void printLog(RuleInfo ruleId, boolean[] result) {
    try {
      client.send(ruleId.getId(), result);
    } catch (IOException e) {
      Agent.logger.error(e.getMessage());
    }
  }

}
