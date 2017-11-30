package de.dfki.mlt.rudimant.common;

import de.dfki.mlt.rudimant.agent.Agent;

/**
 * the server API that is separated from the server;
 */
public class DebugAgentApi {
  private Agent _agent;

  /**
   * Start logging a specific rule
   *
   * @param id
   * @param what
   */
  public void setLoggingStatus(int id, int what) {
    _agent.logRule(id, what);
  }
}