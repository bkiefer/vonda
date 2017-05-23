/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Collections;

/**
 * the lowest, simplest nodes in the AbstractTree
 *
 * @author Anna Welker
 */
public abstract class RTExpLeaf extends RTExpression {

  String content;

  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }

  public String toString() {
    return (content == null) ? fullexp : content;
  }

  public void propagateType(String upperType) {
    if (type != null) {
      logger.error("Why didn't this type percolate up? " + fullexp + " " + type);
      return;
    }
    type = upperType;
  }

}
