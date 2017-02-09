/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * represents a do ... while statement
 *
 * @author Anna Welker
 */
public class StatDoWhile extends RTCondBlockStatement {

  String currentRule;

  public StatDoWhile(RTExpression condition, StatAbstractBlock statblock, String position) {
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
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }
}
