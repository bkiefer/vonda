/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.List;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class ExpListLiteral extends RTExpression {

  List<RTExpression> objects;

  public ExpListLiteral(List<RTExpression> objs) {
    objects = objs;
    // don't know what this is.
    type = Type.getNoType();
    type.setInnerType(Type.getNoType());
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    return objects;
  }

  @Override
  public void propagateType(Type upperType) {
    type = upperType;
  }
}
