/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VRuleConditionVisitor implements RudiVisitor {

  private String currentRule;
  // map the new variables to what they represent
  private LinkedHashMap<String, String> realLook;
  // map the new variables to how they should be calculated
  private LinkedHashMap<String, String> compiledLook;
  private int counter;

  public void renewMap(String rule, LinkedHashMap<String, String> condLog,
          LinkedHashMap<String, String> compiledLook) {
    this.realLook = condLog;
    this.compiledLook = compiledLook;
    this.currentRule = rule;
    this.counter = 0;
  }

  @Override
  public void visitNode(RudiTree node) {
    throw new UnsupportedOperationException("Not supported yet.");
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
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private String lastbool;
  private String isTrue;

  @Override
  public void visitNode(ExpBoolean node) {
    if (node.right != null) {
      node.left.visit(this);
      node.right.visit(this);
    } else {
      isTrue = node.isTrue + " ";
      node.left.visit(this);
    }
    //System.out.println("bool number " + this.compiledLook.keySet().size());
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, node.look + isTrue +  " ");
    this.realLook.put(lastbool, node.look + isTrue + " ");
    isTrue = "";
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
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, "\'" + node.content + "\'"  + isTrue+ " ");
    this.realLook.put(lastbool, node.content + isTrue);
    isTrue = "";
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
    this.lastbool = this.currentRule + this.counter++;
    // TODO: tell me how the client is named!!!
    String t = (node.representation.get(0));
    for (int i = 1; i < node.representation.size(); i++) {
      t += (".getValue(" + node.representation.get(i) + ", client)" + " ");
    }
    this.compiledLook.put(this.lastbool, t + isTrue);
    this.realLook.put(lastbool, t + isTrue);
    isTrue = "";
  }

  @Override
  public void visitNode(UFuncCall node) {
    this.lastbool = this.currentRule + this.counter++;
    String t = (node.representation + "(");
    for (int i = 0; i < node.exps.size(); i++) {
      node.exps.get(i).visit(this);
      if (i != node.exps.size() - 1) {
        t += (", ");
      }
    }
    t += (")" + " ");
    this.compiledLook.put(this.lastbool, t + isTrue);
    this.realLook.put(lastbool, t + isTrue);
    isTrue = "";
  }

  @Override
  public void visitNode(UNull node) {
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, "null " + isTrue);
    this.realLook.put(lastbool, "null " + isTrue);
    isTrue = "";
  }

  @Override
  public void visitNode(UNumber node) {
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, node.value + isTrue);
    this.realLook.put(lastbool, node.value + isTrue);
    isTrue = "";
  }

  @Override
  public void visitNode(UString node) {
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, 
            node.content.substring(0, node.content.length() - 1) +
            "\"" + " " + isTrue);
      this.realLook.put(lastbool,
              node.content.substring(0, node.content.length() - 1) + 
              "\"" + " " + isTrue);
      isTrue = "";
  }

  @Override
  public void visitNode(UVariable node) {
    this.lastbool = this.currentRule + this.counter++;
    // if the variable is not in the memory,
    if (node.realOrigin != null) {
      this.compiledLook.put(this.lastbool,
              node.realOrigin.toLowerCase() + "." + node.representation + " " + isTrue);
      this.realLook.put(lastbool, node.representation + " " + isTrue);
      return;
    }
    this.compiledLook.put(this.lastbool, node.representation + " " + isTrue);
      this.realLook.put(lastbool, node.representation + " " + isTrue);
      isTrue = "";
  }

  @Override
  public void visitNode(UWildcard node) {
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, "this.wildcard" + " " + isTrue);
      this.realLook.put(lastbool, " _ " + isTrue);
      isTrue = "";
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, node.content + " " + isTrue);
      this.realLook.put(lastbool, node.content + " " + isTrue);
      isTrue = "";
  }
}
