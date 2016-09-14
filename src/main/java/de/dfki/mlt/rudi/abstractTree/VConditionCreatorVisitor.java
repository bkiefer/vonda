/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author pal
 */
public class VConditionCreatorVisitor implements RudiVisitor {

  // map the new variables to what they represent
  private Object[] expNames;
  int counter = 0;
  StringBuffer condition;

  public void newMap(Object[] expNames) {
    this.expNames = expNames;
    condition = new StringBuffer();
    counter = 0;
  }

  public StringBuffer getCondition() {
    return this.condition;
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
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(ExpAssignment node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpBoolean node) {
    this.visitNode(node.left);
    if (node.right != null) {
      condition.append(node.operator);
      this.visitNode(node.right);
    }
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(ExpIf node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(ExpLambda node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(GrammarFile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(GrammarRule node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatDoWhile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFor1 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFor2 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFor3 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFunDef node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatIf node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatImport node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatListCreation node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatPropose node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatReturn node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatSetOperation node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatTimeout node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatVarDef node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatWhile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UCharacter node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UComment node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UCommentBlock node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UFieldAccess node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UFuncCall node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UNull node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UNumber node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UString node) {
    System.out.println(counter + " listlenght " + expNames.length);
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UVariable node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UWildcard node) {
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    condition.append(expNames[counter++]);
  }

}
