/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;
import java.util.Objects;

/**
 * FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR
 * loop_statement_block = a special for statement allowing tuples of arbitrary
 * size
 *
 * @author Anna Welker
 */
public class StatFor3 implements RTStatement, RudiTree {

  List<String> variables;
  RudiTree exp;
  StatAbstractBlock statblock;
  String position;

  public StatFor3(List<String> variables, RudiTree exp,
          StatAbstractBlock block, String position) {
    this.variables = variables;
    this.exp = exp;
    this.statblock = block;
    this.position = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 47 * hash + Objects.hashCode(this.variables);
    hash = 47 * hash + Objects.hashCode(this.exp);
    hash = 47 * hash + Objects.hashCode(this.statblock);
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
    final StatFor3 other = (StatFor3) obj;
    if (!Objects.equals(this.variables, other.variables)) {
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
