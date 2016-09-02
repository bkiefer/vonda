/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.List;


/**
 * a class to represent a set of statements, might have curly braces or just be
 * an abstract object for grouping (without showing that grouping in the output)
 *
 * @author Anna Welker
 */
public class StatAbstractBlock implements RTStatement, RudiTree {

  List<RudiTree> statblock;
  final boolean braces;
  int environmentPosition;

  public StatAbstractBlock(List<RudiTree> statblock, boolean braces) {
    this.statblock = statblock;
    this.braces = braces;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
