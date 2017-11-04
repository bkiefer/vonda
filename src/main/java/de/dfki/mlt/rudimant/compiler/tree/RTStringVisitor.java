/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

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

  public String visitNode(ExpFieldAccess node);

  public String visitNode(ExpFuncCall node);

  public String visitNode(ExpSingleValue node);

  public String visitNode(ExpVariable node);
}
