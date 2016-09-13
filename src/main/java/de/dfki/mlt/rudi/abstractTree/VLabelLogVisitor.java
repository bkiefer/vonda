/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.LabelsToLog;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VLabelLogVisitor implements RudiVisitor {

  private LabelsToLog ll;
  private String currentRule;

  public VLabelLogVisitor(LabelsToLog ll) {
    this.ll = ll;
  }

  @Override
  public void visitNode(RudiTree node) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    node.exp.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);
    }
  }

  @Override
  public void visitNode(ExpAssignment node) {
    // I assume there is nothing to do for us here
  }

  @Override
  public void visitNode(ExpBoolean node) {
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);
    }
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    // this could be used in a condition; we need to collect all those arguments
    // that are variables!!!
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    // this, again, could be used in a condition; collect the variable the function
    // is called on as well as any possible arguments
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpIf node) {
    // let's just assume noone would put this in a condition
    if (currentRule != null) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  @Override
  public void visitNode(ExpLambda node) {
    // let's just assume noone would put this in a condition
    if (currentRule != null) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
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
  public void visitNode(StatFunDef node) {
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
  public void visitNode(StatTimeout node) {
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
  public void visitNode(UCharacter node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UComment node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UCommentBlock node) {
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
  public void visitNode(UNull node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UNumber node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UString node) {
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
  public void visitNode(UnaryBoolean node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
