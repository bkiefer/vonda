/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.LinkedHashMap;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 *
 * Collects data to generate the complex boolean conditions to log the rules
 * properly.
 */
public class VConditionLogVisitor implements RTStringVisitor {

  private VGenerationVisitor genV;
  private boolean enteringCondition;
  // a counter to help naming the booleans that we create
  private int counter = 0;
  private String currentRule;
  // the name of the last boolean we created
  private String lastbool;
  // map the new variables to what they represent
  private LinkedHashMap<String, String> realLook;
  // map the new variables to the rudi expressions they come from
  private LinkedHashMap<String, String> rudiLook;

  public VConditionLogVisitor(VGenerationVisitor v) {
    genV = v;
  }

  public void newInit(String rule, LinkedHashMap<String, String> real,
          LinkedHashMap<String, String> rudi) {
    enteringCondition = true;
    currentRule = rule;
    counter = 0;
    realLook = real;
    rudiLook = rudi;
  }

  @Override
  public String visitNode(ExpBoolean node) {
    StringBuilder retS = new StringBuilder();
    if (enteringCondition) {
      enteringCondition = false;
    }
    if (node.right != null) {
      if (!(node.operator.equals("||") || node.operator.equals("&&"))) {
        // we do not want to go deeper, both sides must be a "basic boolean"
        String resulting = genV.visitNode(node);
        lastbool = currentRule + counter++;
        realLook.put(lastbool, resulting);
        rudiLook.put(lastbool, node.fullexp);
        retS.append(lastbool + " = " + resulting + ";\n");
        return retS.toString();
      } else {
        // visit the left node, remember its "number"
        retS.append(visitNode(node.left));
        String left = lastbool;
        // visit the right node, remember its "number"
        String resultRight = visitNode(node.right);
        String right = lastbool;
        lastbool = currentRule + counter++;
        // TODO: we are not interested in this, are we?
//        realLook.put(lastbool, left + node.operator + right);
        if (node.operator.equals("||")) {
          retS.append("if (!");
        } else { // then we are in && mode
          retS.append("if (");
        }
        retS.append(left + ") {\n");
        retS.append(resultRight);
        retS.append(lastbool + " = " + left + node.operator + right + ";\n");
        realLook.put(lastbool, left + node.operator + right);
        retS.append("} else {\n");
        retS.append(lastbool + " = " + left + ";\n}\n");
      }
    } else {
      String resulting = "";
      /*
      if ("!".equals(node.operator)) {
        resulting = "!";
      }
      */
      String left = genV.visitNode(node);
      if (left.contains(currentRule) && left.contains(" = ")) {
        // TODO: find a better way to differ between a complex boolean and a
        // simple one
        retS.append(left);
        resulting += lastbool;
      } else {
        resulting += left;
      }
      lastbool = currentRule + counter++;
      realLook.put(lastbool, resulting);
      rudiLook.put(lastbool, node.fullexp);
      retS.append(lastbool + " = " + resulting + ";\n");
    }

    return retS.toString();
  }

  @Override
  public String visitNode(RTExpression node) {
    return node.visitStringV(this);
  }

  /**
   * to get the bool variable describing the whole condition this visitor went
   * through
   *
   * @return
   */
  public String getLastBool() {
    return lastbool;
  }

  private String visitMyExp(RTExpression node) {
    return genV.visitNode(node);
  }

  @Override
  public String visitNode(ExpArithmetic node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpAssignment node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpCast node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpDialogueAct node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpConditional node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpLambda node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpNew node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpUFieldAccess node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpUFuncCall node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpUSingleValue node) {
    return visitMyExp(node);
  }

  @Override
  public String visitNode(ExpUVariable node) {
    return visitMyExp(node);
  }
}
