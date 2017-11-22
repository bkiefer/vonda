/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.List;

import de.dfki.mlt.rudimant.compiler.Environment;

/**
 * a class to represent a set of statements, might have curly braces or just be
 * an abstract object for grouping (without showing that grouping in the output)
 *
 * @author Anna Welker
 */
public class StatAbstractBlock extends RTStatement implements RTBlockNode {

  List<RTStatement> statblock;
  final boolean braces;

  public StatAbstractBlock(List<RTStatement> statblock, boolean braces) {
    this.statblock = statblock;
    this.braces = braces;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() { return statblock; }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings, _parentBindings;

  public void setBindings(Environment parent, Environment local) {
    _parentBindings = parent; _localBindings = local;
  }

  public Environment getBindings() { return _localBindings; }

  public Environment getParentBindings() { return _parentBindings; }
}
