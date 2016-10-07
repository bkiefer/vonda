package de.dfki.mlt.rudimant.agent;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class Behaviour {

  private final String _text;
  private final String _motion;
  private Integer _delay;

  public Behaviour(String text, String motion, Integer delay) {
    _text = text;
    _motion = motion;
    _delay = delay;
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

  public void setDelay(int delay) {
    _delay = delay;
  }

}
