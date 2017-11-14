package de.dfki.mlt.rudimant.common;

public class RuleInfo extends BasicInfo {
  private int _id;

  private int _state = 0;

  public RuleInfo() {}

  public RuleInfo(String name, int line, BasicInfo parent) {
    super(name, line, parent);
    _id = ruleId++;
  }

  public void setId(int id) {
    _id = id;
    ruleId = Math.max(ruleId, id + 1);
  }

  public int getId() { return _id; }

  public void setState(int state) {
    _state = state;
  }

  public int getState() { return _state; }

}
