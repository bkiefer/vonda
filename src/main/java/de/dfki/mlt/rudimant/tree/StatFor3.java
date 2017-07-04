/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;
import java.util.List;

/**
 * FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR
 * loop_statement_block = a special for statement allowing tuples of arbitrary
 * size
 *
 * @author Anna Welker
 */
public class StatFor3 extends RTStatement {

  List<String> variables;
  RTExpression initialization;
  RTStatement statblock;

  public StatFor3(List<String> vars, RTExpression exp, RTStatement block) {
    variables = vars;
    initialization = exp;
    statblock = block;
  }

  @Override
  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public void visitVoidV(VisitorGeneration v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { initialization, statblock };
    return Arrays.asList(dtrs);
  }
}
