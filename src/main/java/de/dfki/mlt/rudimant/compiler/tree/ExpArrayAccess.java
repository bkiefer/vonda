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
    if (type != null && ! type.isUnspecified()) {
      logger.error("Why didn't this type percolate up? " + fullexp + " " + type);
      return;
    }
    type = upperType;
    if (array.type.isArray()) {
      array.getType().setInnerType(type);
    }
  }

}
