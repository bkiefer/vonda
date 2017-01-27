/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * representing a while statement
 *
 * @author Anna Welker
 */
public class StatWhile extends RTCondBlockStatement {

  String currentRule;

  public StatWhile(RTExpression condition, StatAbstractBlock statblock, String position) {
    this.condition = condition;
    this.block = statblock;
    this.currentRule = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }
}
