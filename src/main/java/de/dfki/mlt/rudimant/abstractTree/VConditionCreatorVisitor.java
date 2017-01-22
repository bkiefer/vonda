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

  private boolean enteringCondition;

  @Override
  public String visitNode(ExpBoolean node) {
    if (this.enteringCondition) {
      // we are at the beginning; initialize all boolean vars of this condition
      this.enteringCondition = false;
      int i = 0;
      for (Object e : expNames) {
        this.creation.append("boolean ").append(expNames[i++]).append(" = false;\n");
      }
    }
    String n = "";
    if ("!".equals(node.operator)) {
      this.condition.append("!");
      n = "!";
    }
    if (node.right != null) {
      if (!(node.operator.equals("||") || node.operator.equals("&&"))) {
        // we do not go deeper
        this.creation.append(expNames[counter] + " = " + n
                + compiledLook.get(expNames[counter]) + ";\n");
        this.condition.append(expNames[counter++]);
        return null;
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
        return null;
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
        return null;
      }
    } else {
//      if ("!".equals(node.operator)) {
        //        this.condition.append("!");
        this.visitNode(node.left);
        this.creation.append(expNames[counter] + " = " + compiledLook.get(expNames[counter++]) + ";\n");
//        return;
//      }
//      this.visitNode(node.left);
    }
    return null;
  }

  private String myVisitNode(RTExpression node) {
    if(this.enteringCondition){
//       we are in a case where this is a condition with one single element
      this.enteringCondition = false;
      creation.append("boolean ").append(expNames[counter]).append(" = ")
            .append(compiledLook.get(expNames[counter])).append(";\n");
    }
//    creation.append(expNames[counter] + " = " + compiledLook.get(expNames[counter]) + ";\n");
//    condition.append(expNames[counter++]);
    return null;
  }

  @Override
  public String visitNode(ExpConditional node) {
    return myVisitNode(node);
  }

  @Override
  public String visitNode(UFieldAccess node) {
    return myVisitNode(node);
  }

  @Override
  public String visitNode(UFuncCall node) {
    return myVisitNode(node);
  }

  @Override
  public String visitNode(USingleValue node) {
    return myVisitNode(node);
  }

  @Override
  public String visitNode(UVariable node) {
    return myVisitNode(node);
  }

  @Override
  public String visitNode(UWildcard node) {
    return myVisitNode(node);
  }

}
