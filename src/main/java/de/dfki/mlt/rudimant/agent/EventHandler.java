package de.dfki.mlt.rudimant.agent;

public interface EventHandler<T extends Event> {

  public void handleEvent(T o);
}
