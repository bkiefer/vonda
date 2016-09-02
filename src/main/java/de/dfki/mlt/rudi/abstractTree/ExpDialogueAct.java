/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * this class represents dialogue acts
 *
 * @author Anna Welker
 */
public class ExpDialogueAct implements RudiTree, RTExpression{

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment

  String litGraph;
  //private List<AbstractExpression> exps;
  String rest;

  public ExpDialogueAct(String litGraph, String rest){ // List<AbstractExpression> exps) {
    this.litGraph = litGraph.substring(1, litGraph.length());
    //this.exps = exps;
    this.rest = rest;
  }

  @Override
  public String getType() {
    return "magic";
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
