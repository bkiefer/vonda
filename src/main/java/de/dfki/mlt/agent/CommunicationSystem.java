package de.dfki.mlt.agent;

import de.dfki.tecs.Event;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
interface CommunicationSystem {

  public boolean isOnline();
  public boolean canReceive();
  public Event receive();
  public void subscribe(String event);
  public void disconnect();
  public void connect();
  
}
