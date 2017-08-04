package de.dfki.mlt.rudimant.agent;

import java.util.Set;

public interface CommunicationHub {
  /** Send a behaviour (text/motion) to the outside world */
  void sendBehaviour(Behaviour b);

  /** Send a set of proposals to the outside world for selection of the best */
  void sendIntentions(Set<String> intentions);
}
