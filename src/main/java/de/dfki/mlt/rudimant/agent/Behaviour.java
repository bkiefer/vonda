package de.dfki.mlt.rudimant.agent;

/**
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class Behaviour {

  /** default delay between behaviours in milliseconds */
  public static int DEFAULT_DELAY = 250;

  private final String _text;
  private final String _motion;
  private final int _delay;

  public Behaviour(String text, String motion, int delay) {
    _text = text;
    _motion = motion;
    _delay = delay;
  }

  public Behaviour(String text, String motion) {
    this(text, motion, DEFAULT_DELAY);
  }

  public String getText() {
    return _text;
  }

  public String getMotion() {
    return _motion;
  }

  public Integer getDelay() {
    return _delay;
  }
}
