package de.dfki.mlt.rudimant.compiler.tree;

import java.util.ArrayList;
import java.util.List;

public class StatExpression extends RTStatement {

  RTExpression expression;

  public StatExpression(RTExpression exp) {
    expression = exp;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @SuppressWarnings("serial")
  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return new ArrayList<RudiTree>(){{ add(expression); }};
  }

}
