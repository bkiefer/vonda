/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public interface RTStringVisitor {

  public String visitNode(RTExpression node);

  public String visitNode(ExpArithmetic node);

  public String visitNode(ExpAssignment node);

  public String visitNode(ExpBoolean node);

  public String visitNode(ExpCast node);

  public String visitNode(ExpDialogueAct node);

  public String visitNode(ExpConditional node);

  public String visitNode(ExpLambda node);

  public String visitNode(ExpNew node);

  public String visitNode(ExpUFieldAccess node);

  public String visitNode(ExpUFuncCall node);

  public String visitNode(ExpUSingleValue node);

  public String visitNode(ExpUVariable node);
}
