package de.dfki.mlt.agent;

import pal.TECS.EventHandler;
import pal.TECS.Intention;

public class IntentionHandler implements EventHandler<Intention> {

  private Agent agent;

  public IntentionHandler(Agent a) { agent = a; }

  @Override
  public void handleEvent(Intention intention) {
    agent.executeProposal(intention.getContent());
    agent.proposedRobotMood = intention.getMood();
    agent.proposalsSent = false;
  }

}
