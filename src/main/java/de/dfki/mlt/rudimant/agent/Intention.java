/*

 */
package de.dfki.mlt.rudimant.agent;

/**
 *
 * @author pal
 */
public class Intention {
  private String _content;
  private double _mood;

  public Intention(String content, double mood) {
    _content = content;
    _mood = mood;
  }

  // public String getType() { return null; }

  // public Object getSource() { return null; }

  public String getContent() { return _content; }

  public double getMood() { return _mood; }
}
