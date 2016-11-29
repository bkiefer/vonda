/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Collections;

/**
 * the lowest, simplest nodes in the AbstractTree
 *
 * @author Anna Welker
 */
public abstract class RTLeaf extends RudiTree {

  String content;

  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }

  public String toString() {
    return (content == null) ? "<null>" : content;
  }
}
