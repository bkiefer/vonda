/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author pal
 */
public class VNullVisitor implements RudiVisitor {

  public void visitNode(RTExpression node) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpAssignment node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpBoolean node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpIf node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpLambda node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(GrammarFile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(GrammarRule node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatDoWhile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor1 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor2 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor3 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatIf node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatImport node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatListCreation node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatPropose node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatReturn node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatSetOperation node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatVarDef node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatWhile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UFieldAccess node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UFuncCall node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(USingleValue node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UVariable node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UWildcard node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatSwitch node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
