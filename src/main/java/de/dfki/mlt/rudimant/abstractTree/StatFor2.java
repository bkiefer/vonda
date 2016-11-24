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
  UVariable var;
  RTExpression exp;
  StatAbstractBlock statblock;
  String position;

  public StatFor2(UVariable var, RTExpression exp, StatAbstractBlock statblock,
          String position) {
    this.var = var;
    this.exp = exp;
    this.statblock = statblock;
    this.varType = null;
    this.position = position;
  }

  public StatFor2(String varType, UVariable var, RTExpression exp,
          StatAbstractBlock statblock, String position) {
    this.var = var;
    this.exp = exp;
    this.statblock = statblock;
    if (varType.equals("var")) {
      this.varType = null;
    } else {
      this.varType = varType;
    }
    this.position = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { var, exp, statblock };
    return Arrays.asList(dtrs);
  }
}
