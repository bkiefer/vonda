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
public class VConditionCreatorVisitor extends VNullVisitor {

  // map the new variables to what they represent
  private Object[] expNames;
  int counter = 0;
  StringBuffer condition;
  StringBuffer creation;

  LinkedHashMap<String, String> compiledLook;

  public void newMap(Object[] expNames, LinkedHashMap<String, String> compiledLook) {
    this.expNames = expNames;
    condition = new StringBuffer();
    creation = new StringBuffer();
    counter = 0;
    this.compiledLook = compiledLook;
    this.enteringCondition = true;
  }

  public StringBuffer getBoolCreation() {
    return this.creation;
  }

  public StringBuffer getCondition() {
    return this.condition;
  }

  @Override
  public void visitNode(RudiTree node) {
    node.visit(this);
  }

  private boolean enteringCondition;

  @Override
  public void visitNode(ExpBoolean node) {
    if (this.enteringCondition) {
      // we are at the beginning; initialize all boolean vars of this condition
      this.enteringCondition = false;
      int i = 0;
      for (Object e : expNames) {
        this.creation.append("boolean " + expNames[i++] + " = false;\n");
      }
    }
    String n = "";
    if ("!".equals(node.operator)) {
      this.condition.append("!");
      n = "!";
    }
    if (node.doesSubsume || node.isSubsumed) {
      String subnot = "";
      if (node.notIfSubsume) {
        subnot = "!";
      }
      this.condition.append(subnot + n + expNames[counter]);
      this.creation.append(expNames[counter] + " = " + subnot + n
              + compiledLook.get(expNames[counter++]) + ";\n");
      return;
    }
    if (node.right != null) {
      //      if (node.left.getType() == null // then this is probably an rdf
      //              || !node.left.getType().equals("boolean")) {
      //        this.condition.append(expNames[counter++]);
      //        return;
      //      }
      if (!(node.operator.equals("||") || node.operator.equals("&&"))) {
        // we do not go deeper
        this.creation.append(expNames[counter] + " = " + n
                + compiledLook.get(expNames[counter]) + ";\n");
        this.condition.append(expNames[counter++]);
        return;
      } // else
      if (node.operator.equals("||")) {
        this.condition.append("(");
        this.visitNode(node.left);
        this.creation.append("if (!" + expNames[counter - 1] + "){\n");
        condition.append(node.operator);
        this.visitNode(node.right);
        this.condition.append(")");
        this.creation.append("}\n");
        this.creation.append(expNames[counter] + " = " + n
                + compiledLook.get(expNames[counter++]) + ";\n");
        //        this.condition.append(expNames[counter++]);
        return;
      } else if (node.operator.equals("&&")) {
        this.condition.append("(");
        this.visitNode(node.left);
        this.creation.append("if (" + expNames[counter - 1] + "){\n");
        condition.append(node.operator);
        this.visitNode(node.right);
        this.condition.append(")");
        this.creation.append("}\n");
        this.creation.append(expNames[counter] + " = " + n
                + compiledLook.get(expNames[counter++]) + ";\n");
        //        this.condition.append(expNames[counter++]);
        return;
      } else {
        this.condition.append("(");
        this.creation.append("(");
        this.visitNode(node.left);
        condition.append(node.operator);
        creation.append(node.operator);
        this.visitNode(node.right);
        this.condition.append(")");
        this.creation.append(")");
      }
    } else {
      if ("!".equals(node.operator)) {
        //        this.condition.append("!");
        this.visitNode(node.left);
        this.creation.append(expNames[counter] + " = " + compiledLook.get(expNames[counter++]) + ";\n");
        return;
      }
      this.visitNode(node.left);
    }
  }

  private void myVisitNode(RTExpression node) {
    creation.append(expNames[counter] + " = " + compiledLook.get(expNames[counter]) + ";\n");
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    myVisitNode(node);
  }

  @Override
  public void visitNode(ExpIf node) {
    myVisitNode(node);
  }

  @Override
  public void visitNode(UFieldAccess node) {
    myVisitNode(node);
  }

  @Override
  public void visitNode(UFuncCall node) {
    myVisitNode(node);
  }

  @Override
  public void visitNode(USingleValue node) {
    myVisitNode(node);
  }

  @Override
  public void visitNode(UVariable node) {
    myVisitNode(node);
  }

  @Override
  public void visitNode(UWildcard node) {
    myVisitNode(node);
  }

}
