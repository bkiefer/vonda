package de.dfki.mlt.rudimant.agent;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.common.SimpleServer;

/**
 * establishes an server in the agent to be able to modify which rules are
 * logged, and to see watched objects in the DB in the future
 */
public class DebuggingService  {

  public static final Logger logger = LoggerFactory.getLogger("DebuggingService");

  SimpleServer server;

  /** A debugging service for the given agent */
  public DebuggingService(Agent agent, int port) throws IOException {
    server = new SimpleServer(
        (args) -> {
          try {
            agent.logRule(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            logger.debug("set rule " + Integer.parseInt(args[0]) + " to " + Integer.parseInt(args[1]));
          } catch (Throwable ex) {
            Agent.logger.error("Illegal DebuggingService Call: {}",
                Arrays.toString(args));
          }
        }, port, "DebuggingService");
  }

  /** starts the debugging service for the agent */
  public synchronized SimpleServer startServer() {
    server.startServer();
    return server;
  }
}
