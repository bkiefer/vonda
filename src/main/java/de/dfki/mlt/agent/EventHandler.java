package de.dfki.mlt.agent;

public interface EventHandler<T extends Event> {

  public void handleEvent(T o);
}
