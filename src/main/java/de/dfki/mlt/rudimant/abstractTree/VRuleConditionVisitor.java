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
public class VRuleConditionVisitor extends VNullVisitor {

  private String currentRule;
  // map the new variables to what they represent
  private LinkedHashMap<String, String> realLook;
  // map the new variables to how they should be calculated
  private LinkedHashMap<String, String> compiledLook;
  private int counter;
  private boolean enteringCondition;

  private RudimantCompiler rudi;
  private Mem mem;

  public void renewMap(String rule, LinkedHashMap<String, String> condLog,
          LinkedHashMap<String, String> compiledLook, RudimantCompiler rudi) {
    this.realLook = condLog;
    this.compiledLook = compiledLook;
    this.currentRule = rule;
    this.counter = 0;
    this.rudi = rudi;
    this.mem = rudi.getMem();
    this.enteringCondition = true;
  }

  @Override
  public String visitNode(ExpArithmetic node) {
    String r = "";
    if (node.right != null) {
      r = node.right.visitStringV(this);
    }
    return node.left.visitStringV(this) + r;
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
        if (node.operator.contains("(")) {
          collectElements = node.operator;
          collectElements += node.left.visitStringV(this);
          collectElements += (", ");
          collectElements += node.right.visitStringV(this);
          collectElements += (")");
        } else {
          collectElements = n + "(";
          collectElements += node.left.visitStringV(this);
          collectElements += node.operator + " ";
          collectElements += node.right.visitStringV(this);
          collectElements += (")");
        }
        this.lastbool = this.currentRule + this.counter++;
        this.compiledLook.put(this.lastbool, collectElements);
        this.realLook.put(lastbool, collectElements.replaceAll("\\\\\"", "\\\""));
        // TODO: test: correct? (yes, as I see it)
        return null;
      }
      // if the operator is and or or, then we have to create two single
      // bool variables out of this
      node.left.visitStringV(this);
      String l = this.lastbool;
      node.right.visitStringV(this);
      String r = this.lastbool;
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, n + "("
              + l + node.operator + r + ")");
      this.realLook.put(lastbool, n + "(" + l + node.operator + r + ")");
    } else {
      node.left.visitStringV(this);
      String l = this.lastbool;
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, n + l);
      this.realLook.put(lastbool, n + l);
    }
    return null;
  }

  public String visitDaToken(RTExpression exp) {
    if (this.enteringCondition) {
      // we are in a case where this is a condition with one single element
      this.enteringCondition = false;
      String ret = this.visitNode(exp);
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, ret);
      this.realLook.put(lastbool, ret);
      return null;
    }
    if (exp instanceof UVariable) {
      return ((UVariable) exp).content;
    } else if (exp instanceof USingleValue
            && ((USingleValue) exp).type.equals("String")) {
      return ((USingleValue) exp).content;
    } else {
      return "\" + " + this.visitNode(exp) + " + \"";
    }
  }

  @Override
  public String visitNode(ExpDialogueAct node) {
    String ret = "new DialogueAct(\"";
    ret += visitDaToken(node.daType) + '(';
    ret += visitDaToken(node.proposition);
    for (int i = 0; i < node.exps.size(); i += 2) {
      ret += ", " + visitDaToken(node.exps.get(i));
      ret += " = " + visitDaToken(node.exps.get(i + 1));
    }
    ret += ")\"))";
    return ret;
    // normally, this should not be needed here
//    this.lastbool = this.currentRule + this.counter++;
//    this.compiledLook.put(this.lastbool, result);
//    this.realLook.put(lastbool, result);
  }

  @Override
  public String visitNode(UFieldAccess node) {
    String fieldAccessPart = node.parts.get(0).visitStringV(this);
    for (int i = 1; i < node.parts.size(); i++) {
      if (node.parts.get(i) instanceof UPropertyAccess) {
        UPropertyAccess pa = (UPropertyAccess) node.parts.get(i);
        // then we are in the case that this is actually an rdf operation
        fieldAccessPart += pa.functional ? ".getSingleValue(\"" : ".getValue(\"";
        fieldAccessPart += node.representation.get(i) + "\") ";
      } else {
        fieldAccessPart += (".");
        fieldAccessPart += node.parts.get(i).visitStringV(this);
      }
    }
    // it should be correct to never create a bool variable here, because if
    // this was a single expression resulting in a boolean, that should be
    // caught/handled with in RTExpression
    return fieldAccessPart;
  }

  @Override
  public String visitNode(UFuncCall node) {
    String funcargs = "";
    if (node.realOrigin != null) {
      String t = node.realOrigin;
      funcargs = t.substring(0, 1).toLowerCase() + t.substring(1) + ".";
    }
    funcargs += node.content + "(";
    for (int i = 0; i < node.exps.size(); i++) {
      if (i > 0) {
        funcargs += (", ");
      }
      funcargs += node.exps.get(i).visitStringV(this);
    }
    funcargs += (")");
    // as to why not create a bool variable here, see UFieldAccess
    return funcargs;
  }

  @Override
  public String visitNode(USingleValue node) {
    return node.content;
  }

  @Override
  public String visitNode(UVariable node) {
    // we might need to import this variable from elsewhere
    if (node.realOrigin != null) {
      String t = node.realOrigin;
      return t.substring(0, 1).toLowerCase() + t.substring(1)
              + "." + node.content;
    } else {
      return node.content;
    }
    // as to why not create a bool variable here, see UFieldAccess
  }

  @Override
  public String visitNode(UWildcard node) {
    return "this.wildcard";
  }
}
