package de.dfki.mlt.rudimant.agent;

import de.dfki.mlt.rudimant.agent.Intention;
import de.dfki.mlt.rudimant.agent.Agent;
import de.dfki.mlt.rudimant.agent.EventHandler;

public class IntentionHandler implements EventHandler<Intention> {

  private Agent agent;

  public IntentionHandler(Agent a) {
    agent = a;
  }

  @Override
  public void handleEvent(Intention intention) {
    agent.executeProposal(intention.getContent());
    agent.proposedRobotMood = intention.getMood();
    agent.proposalsSent = false;
  }

}
