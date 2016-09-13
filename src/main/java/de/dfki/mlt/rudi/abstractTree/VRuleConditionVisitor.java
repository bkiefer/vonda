/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.RuleConditionLog;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VRuleConditionVisitor implements RudiVisitor {

  private RuleConditionLog ll;
  private String currentRule;

  public VRuleConditionVisitor(RuleConditionLog ll) {
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
    for (RTExpression e : node.exps) {
      e.visit(this);
    }
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    // this, again, could be used in a condition; collect the variable the function
    // is called on as well as any possible arguments
    node.on.visit(this);
    node.funccall.visit(this);
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
    // nothing to do here, but visit the children
    for (RudiTree t : node.rules) {
      t.visit(this);
    }
  }

  @Override
  public void visitNode(GrammarRule node) {
    currentRule = node.label;
    node.ifstat.visit(this);
    currentRule = null;
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    for (RudiTree t : node.statblock) {
      t.visit(this);
    }
  }

  @Override
  public void visitNode(StatDoWhile node) {
    // do we even need to do this?
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor1 node) {
    // do we even need to do this?
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor2 node) {
    // do we even need to do this?
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor3 node) {
    // do we even need to do this?
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFunDef node) {
    // nothing to do
  }

  @Override
  public void visitNode(StatIf node) {
    ll.addRule2condition(currentRule, node.condition);
    node.condition.visit(this);
    // we are done with the rule-if, so reset the flag
    currentRule = null;
    node.statblockIf.visit(this);
    // normally, should there be an else block?
    if (node.statblockElse != null) {
      node.statblockElse.visit(this);
    }
  }

  @Override
  public void visitNode(StatImport node) {
    // nothing to do
  }

  @Override
  public void visitNode(StatListCreation node) {
    // this won't happen in a condition
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    if (currentRule != null) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
    node.block.visit(this);
  }

  @Override
  public void visitNode(StatPropose node) {
    // nothing to do (?)
  }

  @Override
  public void visitNode(StatReturn node) {
    // nothing to do
  }

  @Override
  public void visitNode(StatSetOperation node) {
    // nothing to do
  }

  @Override
  public void visitNode(StatTimeout node) {
    // nothing to do
  }

  @Override
  public void visitNode(StatVarDef node) {
    // nothing to do
  }

  @Override
  public void visitNode(StatWhile node) {
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(UCharacter node) {
    // this is no variable, everything okay
  }

  @Override
  public void visitNode(UComment node) {
    // we are not interested in comments
  }

  @Override
  public void visitNode(UCommentBlock node) {
    // we are not interested in comments
  }

  @Override
  public void visitNode(UFieldAccess node) {
    // TODO: what to do here? nothing?
  }

  @Override
  public void visitNode(UFuncCall node) {
    // we might need those arguments
    for (RudiTree t : node.exps) {
      t.visit(this);
    }
  }

  @Override
  public void visitNode(UNull node) {
    // nothing to do
  }

  @Override
  public void visitNode(UNumber node) {
    // this is no variable, everything okay
  }

  @Override
  public void visitNode(UString node) {
    // this is no variable, everything okay
  }

  @Override
  public void visitNode(UVariable node) {
    // we might need to tell the logger method it needs this variable
    if (currentRule != null) {
      ll.addRule2conditionArgs(currentRule, node.representation, node.type);
    }
  }

  @Override
  public void visitNode(UWildcard node) {
    // nothing to do
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    // nothing to do
  }

}
