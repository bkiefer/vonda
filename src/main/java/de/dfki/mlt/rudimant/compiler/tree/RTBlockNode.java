package de.dfki.mlt.rudimant.compiler.tree;

import de.dfki.mlt.rudimant.compiler.Environment;

public interface RTBlockNode {
  /** Get the variable and function bindings of the enclosing block */
  public abstract Environment getParentBindings();

  /** Enter a new environment, or return the environment associated with
   *  this node, if available.
   */
  public abstract Environment enterEnvironment(Environment parent);
}
