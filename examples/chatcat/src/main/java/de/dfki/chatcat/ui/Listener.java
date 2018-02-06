package de.dfki.chatcat.ui;

public interface Listener<T> {
  public void listen(T q);
}