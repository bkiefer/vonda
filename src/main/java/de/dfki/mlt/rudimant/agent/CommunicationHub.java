package de.dfki.mlt.rudimant.agent;

import java.util.Set;

public interface CommunicationHub {
  void sendBehaviour(Behaviour b);

  void sendIntentions(Set<String> intentions);
}
