package de.dfki.mlt.rudimant.agent;

import java.io.IOException;
import java.util.Arrays;

import de.dfki.mlt.rudimant.common.SimpleServer;

/**
 * establishes an server in the agent to be able to modify which rules are
 * logged, and to see watched objects in the DB in the future
 */
public class DebuggingService  {

  SimpleServer server;

  /** A debugging service for the given agent */
  public DebuggingService(Agent agent) throws IOException {
    server = new SimpleServer(new SimpleServer.Callable() {
      @Override
      public void execute(String[] args) {
        try {
          agent.logRule(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (Throwable ex) {
          Agent.logger.error("Illegal DebuggingService Call: {}",
              Arrays.toString(args));
        }
      }
    });
  }

  /** starts the debugging service for the agent */
  public synchronized void startServer(int port) {
    server.startServer(port, "DebuggingService");
  }
}
