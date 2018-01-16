package de.dfki.mlt.rudimant.compiler.tree;

import de.dfki.mlt.rudimant.compiler.ClassEnv;
import de.dfki.mlt.rudimant.compiler.Environment;

/** We need this class to unify the enter/leave Environment functions in Mem */
public class ToplevelBlock implements RTBlockNode {

  private ClassEnv _localClass;

  private ToplevelBlock _parentClass;

  public void setClass(ToplevelBlock parent, ClassEnv local) {
    _parentClass = parent; _localClass = local;
  }

  public ClassEnv getClassEnv() { return _localClass; }

  public ToplevelBlock getParentClass() { return _parentClass; }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings;

  public Environment getParentBindings() { return _localBindings.getParent(); }

  public Environment enterEnvironment(Environment parent) {
    return _localBindings != null ? _localBindings
        : (_localBindings = Environment.getEnvironment(parent));
  }

  public Environment getBindings() { return _localBindings; }
}
