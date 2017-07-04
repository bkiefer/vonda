/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.List;

import de.dfki.mlt.rudimant.Environment;

/**
 * a class to represent a set of statements, might have curly braces or just be
 * an abstract object for grouping (without showing that grouping in the output)
 *
 * @author Anna Welker
 */
public class StatAbstractBlock extends RTStatement {

  List<RTStatement> statblock;
  final boolean braces;

  private Environment _localBindings;

  public StatAbstractBlock(List<RTStatement> statblock, boolean braces) {
    this.statblock = statblock;
    this.braces = braces;
  }

  @Override
  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() { return statblock; }

  public void setBindings(Environment local) {
    _localBindings = local;
  }

  public Environment getBindings() { return _localBindings; }
}
