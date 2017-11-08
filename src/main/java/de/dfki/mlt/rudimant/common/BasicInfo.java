package de.dfki.mlt.rudimant.common;

import java.util.ArrayList;
import java.util.List;

public class BasicInfo {

  protected BasicInfo _parent;
  protected int _line;
  protected String _label;
  protected List<BasicInfo> _children = new ArrayList<>();

  public BasicInfo() { }

  public BasicInfo(String name, int line, BasicInfo parent) {
    _parent = parent;
    if (parent != null) parent._children.add(this);
    _label = name;
    _line = line;
  }

  public BasicInfo getParent() {
    return _parent;
  }

  public void setParent(BasicInfo parent) {
    this._parent = parent;
  }

  public int getLine() {
    return _line;
  }

  public void setLine(int line) {
    this._line = line;
  }

  public String getLabel() {
    return _label;
  }

  public void setLabel(String label) {
    this._label = label;
  }

  public List<BasicInfo> getChildren() {
    return _children;
  }

  public void setChildren(List<BasicInfo> children) {
    this._children = children;
  }

  @Override
  public String toString() { return _label; }
}
