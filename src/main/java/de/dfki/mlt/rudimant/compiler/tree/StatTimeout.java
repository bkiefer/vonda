
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Arrays;

/**
 * represents a propose statement, creating a proposal
 *
 * @author Anna Welker
 */
public class StatTimeout extends RTStatement {

  RTExpression label;
  RTExpression time;
  StatAbstractBlock block;

  /** if label is null, then this represents a behaviourFinishedTimeout */
  public StatTimeout(RTExpression label, RTExpression time, StatAbstractBlock block) {
    this.label = label;
    this.time = time;
    this.block = block;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { label, time, block };
    return Arrays.asList(dtrs);
  }
}
