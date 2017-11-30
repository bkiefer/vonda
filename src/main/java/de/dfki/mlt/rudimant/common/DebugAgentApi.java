package de.dfki.mlt.rudimant.common;

import de.dfki.mlt.rudimant.agent.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the server API that is separated from the server;
 */
public class DebugAgentApi {
  private Agent _agent;

  private static Logger logger = LoggerFactory.getLogger(DebugServer.class);

  /**
   * Start logging a specific rule
   *
   * @param id
   * @param what
   */
  public void setLoggingStatus(int id, int what) {
    _agent.logRule(id, what);
    logger.debug("set logging state of rule [" + id + "] to state " + what);
  }
}