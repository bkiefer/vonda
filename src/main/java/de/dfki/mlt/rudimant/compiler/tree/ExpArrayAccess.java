package de.dfki.mlt.rudimant.compiler.tree;

import de.dfki.mlt.rudimant.compiler.Type;

public class ExpArrayAccess extends RTExpLeaf {

  RTExpression index;
  RTExpression array;

  public ExpArrayAccess(RTExpression array, RTExpression index) {
    this.array = array;
    this.index = index;
  }

  @Override
  public String toString() {
    return this.content;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public void propagateType(Type upperType) {
    // it's not an error if the type is null
    type = upperType;
  }

}
