package de.dfki.mlt.agent;

import de.dfki.mlt.agent.Intention;
import de.dfki.mlt.agent.Agent;
import de.dfki.mlt.agent.EventHandler;

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
