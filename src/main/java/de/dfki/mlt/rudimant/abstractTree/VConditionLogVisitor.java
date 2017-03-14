/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;

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

  public VConditionLogVisitor(VGenerationVisitor v) {
    genV = v;
  }

  public void newInit(String rule, LinkedHashMap<String, String> realLook) {
    this.enteringCondition = true;
    this.currentRule = rule;
    this.counter = 0;
    this.realLook = realLook;
  }

  @Override
  public String visitNode(ExpBoolean node) {
    StringBuilder retS = new StringBuilder();

    if (this.enteringCondition) {
      this.enteringCondition = false;
    }
    if (node.right != null) {
      if (!(node.operator.equals("||") || node.operator.equals("&&"))) {
        // we do not want to go deeper, both sides must be a "basic boolean"
        String resulting = genV.visitNode(node);
        this.lastbool = this.currentRule + this.counter++;
        this.realLook.put(this.lastbool, resulting);
        retS.append(this.lastbool + " = " + resulting + ";\n");
        return retS.toString();
      } else {
        // visit the left node, remember its "number"
        retS.append(this.visitNode(node.left));
        String left = this.lastbool;
        // visit the right node, remember its "number"
        String resultRight = this.visitNode(node.right);
        String right = this.lastbool;
        this.lastbool = this.currentRule + this.counter++;
        // TODO: we are not interested in this, are we?
//        this.realLook.put(this.lastbool, left + node.operator + right);
        if (node.operator.equals("||")) {
          retS.append("if (!");
        } else { // then we are in && mode
          retS.append("if (");
        }
        retS.append(left + ") {\n");
        retS.append(resultRight);
        retS.append(this.lastbool + " = " + left + node.operator + right + ";\n");
        this.realLook.put(this.lastbool, left + node.operator + right);
        retS.append("}\n");
      }
    } else {
      String resulting = "";
      /*
      if ("!".equals(node.operator)) {
        resulting = "!";
      }
      */
      String left = genV.visitNode(node);
      if (left.contains(this.currentRule) && left.contains(" = ")) {
        // TODO: find a better way to differ between a complex boolean and a
        // simple one
        retS.append(left);
        resulting += this.lastbool;
      } else {
        resulting += left;
      }
      this.lastbool = this.currentRule + this.counter++;
      this.realLook.put(this.lastbool, resulting);
      retS.append(this.lastbool + " = " + resulting + ";\n");
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
    return this.lastbool;
  }

  private String visitMyExp(RTExpression node) {
    return genV.visitNode(node);
  }

  @Override
  public String visitNode(ExpArithmetic node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(ExpAssignment node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(ExpDialogueAct node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(ExpConditional node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(ExpLambda node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(ExpNew node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(UFieldAccess node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(UFuncCall node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(USingleValue node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(UVariable node) {
    return this.visitMyExp(node);
  }

  @Override
  public String visitNode(UWildcard node) {
    return this.visitMyExp(node);
  }

  @Override
  public void visitNode(RudiTree node) {
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
  public void visitNode(StatIf node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UImport node) {
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
  public void visitNode(StatSwitch node) {
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
}
