/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;

/**
 * FOR LPAR VARIABLE COLON exp RPAR loop_statement_block a 'modern' for
 * statement
 *
 * @author Anna Welker
 */
public class StatFor2 extends RTStatement {

  String varType;
  ExpUVariable var;
  RTExpression initialization;
  RTStatement statblock;
  String position;

  public StatFor2(ExpUVariable var, RTExpression exp, RTStatement statblock,
      String position) {
    this.var = var;
    this.initialization = exp;
    this.statblock = statblock;
    this.varType = null;
    this.position = position;
  }

  public StatFor2(String varType, ExpUVariable var, RTExpression exp,
      RTStatement statblock, String position) {
    this.var = var;
    this.initialization = exp;
    this.statblock = statblock;
    if (varType.equals("var")) {
      this.varType = null;
    } else {
      this.varType = varType;
    }
    this.position = position;
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
    RudiTree[] dtrs = { var, initialization, statblock };
    return Arrays.asList(dtrs);
  }
}
