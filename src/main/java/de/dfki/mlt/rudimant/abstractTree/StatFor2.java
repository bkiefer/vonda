/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * FOR LPAR VARIABLE COLON exp RPAR loop_statement_block
 * a 'modern' for statement
 *
 * @author Anna Welker
 */
public class StatFor2 implements RTStatement, RudiTree {

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

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 37 * hash + Objects.hashCode(this.var);
    hash = 37 * hash + Objects.hashCode(this.exp);
    hash = 37 * hash + Objects.hashCode(this.statblock);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StatFor2 other = (StatFor2) obj;
    if (!Objects.equals(this.var, other.var)) {
      return false;
    }
    if (!Objects.equals(this.exp, other.exp)) {
      return false;
    }
    if (!Objects.equals(this.statblock, other.statblock)) {
      return false;
    }
    return true;
  }


}
