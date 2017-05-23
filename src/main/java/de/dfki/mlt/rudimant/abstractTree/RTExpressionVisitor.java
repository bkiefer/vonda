/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public interface RTExpressionVisitor {

  public void visitNode(RTExpression node);

  public void visitNode(ExpArithmetic node);

  public void visitNode(ExpAssignment node);

  public void visitNode(ExpBoolean node);

  public void visitNode(ExpCast node);

  public void visitNode(ExpDialogueAct node);

  public void visitNode(ExpConditional node);

  public void visitNode(ExpLambda node);

  public void visitNode(ExpNew node);

  public void visitNode(ExpUFieldAccess node);

  public void visitNode(ExpUFuncCall node);

  public void visitNode(ExpUSingleValue node);

  public void visitNode(ExpUVariable node);
}
