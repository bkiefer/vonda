/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Arrays;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * FOR LPAR VARIABLE COLON exp RPAR loop_statement_block
 * a 'modern' for statement
 *
 * @author Anna Welker
 */
public class StatFor2 extends RTStatement {

  Type varType;
  ExpVariable var;
  RTExpression initialization;
  RTStatement statblock;

  public StatFor2(ExpVariable v, RTExpression e, RTStatement stat) {
    var = v;
    initialization = e;
    statblock = stat;
    varType = null;
  }

  public StatFor2(String vType, ExpVariable v, RTExpression exp,
      RTStatement stat) {
    var = v;
    initialization = exp;
    statblock = stat;
    if (vType.equals("var")) {
      varType = null;
    } else {
      varType = new Type(vType);
    }
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { var, initialization, statblock };
    return Arrays.asList(dtrs);
  }
}
