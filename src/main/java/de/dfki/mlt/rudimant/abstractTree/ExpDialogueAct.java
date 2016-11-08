/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;
import java.util.Objects;

/**
 * this class represents dialogue acts
 *
 * @author Anna Welker
 */
public class ExpDialogueAct extends RTExpression {

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment
  RTExpression daType;
  RTExpression proposition;
  List<RTExpression> exps;
  public ExpDialogueAct(RTExpression daType, RTExpression proposition,
      List<RTExpression> exps) {
    this.daType = daType;
    this.proposition = proposition;
    this.exps = exps;
  }

  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.daType);
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
    final ExpDialogueAct other = (ExpDialogueAct) obj;
    if (!Objects.equals(this.daType, other.daType)) {
      return false;
    }
    return true;
  }

}
