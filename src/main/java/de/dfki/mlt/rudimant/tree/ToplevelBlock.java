package de.dfki.mlt.rudimant.tree;

import de.dfki.mlt.rudimant.ClassEnv;
import de.dfki.mlt.rudimant.Environment;

public class ToplevelBlock implements RTBlockNode {

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings, _parentBindings;

  private ClassEnv _localClass;

  private ToplevelBlock _parentClass;

  public void setBindings(Environment parent, Environment local) {
    _parentBindings = parent; _localBindings = local;
  }

  public Environment getBindings() { return _localBindings; }

  public Environment getParentBindings() { return _parentBindings; }

  public void setClass(ToplevelBlock parent, ClassEnv local) {
    _parentClass = parent; _localClass = local;
  }

  public ClassEnv getClassEnv() { return _localClass; }

  public ToplevelBlock getParentClass() { return _parentClass; }

}
