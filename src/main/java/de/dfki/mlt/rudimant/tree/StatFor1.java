/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

/**
 * FOR LPAR assignment SEMICOLON exp SEMICOLON exp? RPAR loop_statement_block =
 * a 'normal' for statement containing three ;
 *
 * @author Anna Welker
 */
public class StatFor1 extends RTStatement {

  ExpAssignment initialization;
  RTExpression condition;
  RTExpression increment;
  RTStatement statblock;

  public StatFor1(ExpAssignment assignment, ExpBoolean condition,
          RTExpression arithmetic, RTStatement statblock) {
    this.initialization = assignment;
    this.condition = condition;
    this.increment = arithmetic;
    this.statblock = statblock;
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
    RudiTree[] dtrs = { initialization, condition, increment, statblock };
    return Arrays.asList(dtrs);
  }
}
