package de.dfki.mlt.rudimant.compiler.tree;

import de.dfki.mlt.rudimant.compiler.Environment;

public interface RTBlockNode {
  /** Get the local variable and function bindings, if available */
  public abstract Environment getBindings();

  /** Get the variable and function bindings of the enclosing block */
  public abstract Environment getParentBindings();

  /** Set new local and parent bindings for this block */
  public abstract void setBindings(Environment parent, Environment local);
}
