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
  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VisitorGeneration v) {
    v.visitNode(this);
  }
}
