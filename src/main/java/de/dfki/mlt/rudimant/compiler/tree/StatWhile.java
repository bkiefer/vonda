/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

/**
 * representing a while statement
 *
 * @author Anna Welker
 */
public class StatWhile extends RTCondBlockStatement {

  private boolean whileDo;

  public StatWhile(RTExpression cond, RTStatement blk, boolean isWhileDo) {
    condition = cond;
    block = blk;
    whileDo = isWhileDo;
  }

  public boolean isWhileDo() { return whileDo; }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
