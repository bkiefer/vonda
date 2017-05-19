package de.dfki.mlt.rudimant.agent;

/**
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class Behaviour {

  /** default delay between behaviours in milliseconds */
  public static int DEFAULT_DELAY = 0;

  private final String _id;
  private final String _text;
  private final String _motion;
  private final int _delay;

  /** Create a new behaviour
   *
   * @param text
   * @param motion
   * @param delay the minimum delay from or to the next speech act. A negative
   *              delay means that the delay should be before this behaviour,
   *              a positive after this.
   */
  public Behaviour(String id, String text, String motion, int delay) {
    _id = id;
    _text = text;
    _motion = motion;
    _delay = delay;
  }

  public Behaviour(String text, String motion) {
    this(Long.toHexString(System.currentTimeMillis()),
        text, motion, DEFAULT_DELAY);
  }

  public Behaviour(String text, String motion, int delay) {
    this(Long.toHexString(System.currentTimeMillis()), text, motion, delay);
  }

  public String getId() {
    return _id;
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
