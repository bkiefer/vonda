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
public class ExpDialogueAct implements RudiTree, RTExpression {

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment
  String litGraph;
  List<RTExpression> exps;
  String rest;
  String type;

  public ExpDialogueAct(String litGraph, String rest, List<RTExpression> exps) {
    this.litGraph = litGraph;
    this.exps = exps;
    this.rest = rest;
  }

  @Override
  public String getType() {
    return "DialogueAct";
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.litGraph);
    hash = 89 * hash + Objects.hashCode(this.rest);
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
    if (!Objects.equals(this.litGraph, other.litGraph)) {
      return false;
    }
    if (!Objects.equals(this.rest, other.rest)) {
      return false;
    }
    return true;
  }

  @Override
  public void setType(String to) {
    this.type = to;
  }

}
