package de.dfki.mlt.agent;

import pal.TECS.EventHandler;
import pal.TECS.LowLevelNaoCommand;

public class LowLevelNaoCommandHandler implements EventHandler<LowLevelNaoCommand> {

  private Agent agent;

  public LowLevelNaoCommandHandler(Agent a) { agent = a; }

  @Override
  public void handleEvent(LowLevelNaoCommand command) {
    if (command.getCommand().equals("finished")) {
      agent.logger.info("-> LowLevelNao {}", command.getCommand());
      agent.setBehaviourFinished(); 
    }
  }

}
