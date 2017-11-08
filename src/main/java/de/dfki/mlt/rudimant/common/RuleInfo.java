package de.dfki.mlt.rudimant.common;

public class RuleInfo extends BasicInfo {
  private int _id;

  private int _state = 0;

  public RuleInfo() {}

  public RuleInfo(int id, String name, int line, BasicInfo parent) {
    super(name, line, parent);
    _id = id;
  }

  public void setId(int id) {
    _id = id;
  }

  public int getId() { return _id; }

  public void setState(int state) {
    _state = state;
  }

  public int getState() { return _state; }

}
