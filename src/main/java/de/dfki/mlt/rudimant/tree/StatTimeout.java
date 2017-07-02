
package de.dfki.mlt.rudimant.tree;

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

  public StatTimeout(RTExpression label, RTExpression time, StatAbstractBlock block) {
    this.label = label;
    this.time = time;
    this.block = block;
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

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { label, time, block };
    return Arrays.asList(dtrs);
  }
}
