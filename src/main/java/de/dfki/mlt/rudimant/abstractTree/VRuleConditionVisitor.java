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
public class VRuleConditionVisitor implements RTStringVisitor {

  private String currentRule;
  // map the new variables to what they represent
  private LinkedHashMap<String, String> realLook;
  // map the new variables to how they should be calculated
  private LinkedHashMap<String, String> compiledLook;
  private int counter;
  private boolean enteringCondition;

  private RudimantCompiler rudi;
  private Mem mem;
  
  // a genv we can use to get our generated parts
  private VGenerationVisitor genV;
  
  /**
   * 
   * @param v a generation visitor to help with node realization
   */
  public VRuleConditionVisitor(VGenerationVisitor v){
    this.genV = v;
  }

  public void renewMap(String rule, LinkedHashMap<String, String> condLog,
          LinkedHashMap<String, String> compiledLook, RudimantCompiler rudi) {
    this.realLook = condLog;
    this.compiledLook = compiledLook;
    this.currentRule = rule;
    this.counter = 0;
    this.rudi = rudi;
    this.mem = rudi.getMem();
    this.enteringCondition = true;
    this.lastbool = null;
  }

  @Override
  public String visitNode(ExpArithmetic node) {
    return genV.visitNode(node);
  }

  private String lastbool;

  @Override
  public String visitNode(ExpBoolean node) {
    if (this.enteringCondition) {
      this.enteringCondition = false;
    }
    String n = "";
    if ("!".equals(node.operator)) {
      n = "!";
    }
    String collectElements = "";
    if (node.right != null) {
      if (!(node.operator.equals("||") || node.operator.equals("&&"))) {
        collectElements = genV.visitNode(node);
        this.lastbool = this.currentRule + this.counter++;
        this.compiledLook.put(this.lastbool, collectElements);
        this.realLook.put(lastbool, collectElements.replaceAll("\\\\\"", "\\\""));
        // TODO: test: correct? (yes, as I see it)
        return null;
      }
      // if the operator is and or or, then we have to create two single
      // bool variables out of this
      node.left.visitCondPart(this);
      String l = this.lastbool;
      node.right.visitCondPart(this);
      String r = this.lastbool;
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, n + "("
              + l + node.operator + r + ")");
      this.realLook.put(lastbool, n + "(" + l + node.operator + r + ")");
    } else {
      if("!".equals(node.operator)){
        node.left.visitCondPart(this);
        String l = this.lastbool;
        this.lastbool = this.currentRule + this.counter++;
        this.compiledLook.put(this.lastbool, n + l);
        this.realLook.put(lastbool, n + l);
        return null;
      }
      String left = node.left.visitStringV(genV);
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, left);
      this.realLook.put(lastbool, left);
    }
    return null;
  }



  @Override
  public String visitNode(ExpDialogueAct node) {
    return genV.visitNode(node);
  }

  @Override
  public String visitNode(UFieldAccess node) {
    return genV.visitNode(node);
    // it should be correct to never create a bool variable here, because if
    // this was a single expression resulting in a boolean, that should be
    // caught/handled with in RTExpression
  }

  @Override
  public String visitNode(UFuncCall node) {
    return genV.visitNode(node);
    // as to why not create a bool variable here, see UFieldAccess
  }

  @Override
  public String visitNode(USingleValue node) {
    return genV.visitNode(node);
  }

  @Override
  public String visitNode(UVariable node) {
    return genV.visitNode(node);
    // as to why not create a bool variable here, see UFieldAccess
  }

  @Override
  public String visitNode(UWildcard node) {
    return genV.visitNode(node);
  }

  @Override
  public void visitNode(RudiTree node) {
    node.visitCondPart(this);
  }


  @Override
  public String visitNode(RTExpression node) {
// TODO: should we do this or not?
//    if (this.enteringCondition) {
//      // we are in a case where this is a condition with one single element
//      this.enteringCondition = false;
//      String ret = this.visitNode(exp);
//      this.lastbool = this.currentRule + this.counter++;
//      this.compiledLook.put(this.lastbool, ret);
//      this.realLook.put(lastbool, ret);
//      return null;
//    }
    node.visitCondPart(this);
    return null;
  }

  @Override
  public String visitNode(ExpAssignment node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitNode(ExpConditional node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitNode(ExpLambda node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitNode(ExpNew node) {
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
