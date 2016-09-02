/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.RudimantCompiler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class testTypeVisitor implements RudiVisitor {

  private RudimantCompiler rudi;

  public testTypeVisitor (RudimantCompiler rudi){
    this.rudi = rudi;
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
    node.right.visit(this);
    assert(node.right.getType().equals(node.left.getType()));
  }

  @Override
  public void visitNode(ExpAssignment node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpBoolean node) {
    node.left.visit(this);
    node.right.visit(this);
    if (node.operator != null && node.left.getType().equals("rdf")) {
        if (node.left.getType().equals(node.right.getType())) {
          if (node.operator.equals("<=")) {
            node.isSubsumed = true;
          } else if (node.operator.equals("=>")) {
            node.doesSubsume = true;
          }
        }
      }
    else if(node.operator != null) {
      assert(node.left.getType().equals(node.right.getType()));
    }
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    // no type testing needed (?)
  }

  @Override
  public void visitNode(ExpIf node) {
    node.boolexp.visit(this);
    node.thenexp.visit(this);
    node.elseexp.visit(this);
    assert(node.boolexp.getType().equals("boolean"));
    assert(node.thenexp.getType().equals(node.elseexp.getType()));
  }

  @Override
  public void visitNode(ExpLambda node) {
    // nothing to do
  }

  @Override
  public void visitNode(GrammarFile node) {
    for (RudiTree t : node.rules) {
        t.visit(this);
      }
  }

  @Override
  public void visitNode(GrammarRule node) {
    node.ifstat.visit(this);
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    for (RudiTree t : node.statblock){
      t.visit(this);
    }
  }

  @Override
  public void visitNode(StatDoWhile node) {
    node.condition.visit(this);
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor1 node) {
    // TODO: this is a bit more complicated; remember the types of the variables
    // that were declared in the condition
  }

  @Override
  public void visitNode(StatFor2 node) {
    // TODO: this is a bit more complicated; remember the types of the variables
    // that were declared in the condition
  }

  @Override
  public void visitNode(StatFor3 node) {
    // TODO: this is a bit more complicated; remember the types of the variables
    // that were declared in the condition
  }

  @Override
  public void visitNode(StatFunDef node) {
    // these are not tested
  }

  @Override
  public void visitNode(StatIf node) {
    node.condition.visit(this);
    node.statblockElse.visit(this);
    node.statblockIf.visit(this);
  }

  @Override
  public void visitNode(StatImport node) {
    // nothing to test
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    rudi.getMem().addFunction(node.name, node.return_type, node.partypes, node.position);
    // TODO: add all parameter and their types to mem
  }

  @Override
  public void visitNode(StatPropose node) {
    // nothing to do (?)
  }

  @Override
  public void visitNode(StatReturn node) {
    // nothing to do (?)
  }

  @Override
  public void visitNode(StatSetOperation node) {
    // TODO: test whether the set accepts variables of this type??
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
    node.condition.visit(this);
    node.statblock.visit(this);
    assert(node.condition.getType().equals("boolean"));
  }

  @Override
  public void visitNode(UCharacter node) {
    // everything okay
  }

  @Override
  public void visitNode(UComment node) {
    // everything okay
  }

  @Override
  public void visitNode(UCommentBlock node) {
    // everything okay
  }

  @Override
  public void visitNode(UFieldAccess node) {
      try {
        node.type = node.askChristophe(rudi.getMem());
      } catch (TException ex) {
        Logger.getLogger(UFieldAccess.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  @Override
  public void visitNode(UFuncCall node) {
    if(node.type == null){
      node.type = rudi.getMem().getFunctionRetType(node.representation);
    }
    // test whether the given parameters are of the correct type
    ArrayList<String> partypes = new ArrayList<String>();
    for (RTExpression e : node.exps){
      partypes.add(e.getType());
    }
    assert(rudi.getMem().existsFunction(node.representation, partypes));
  }

  @Override
  public void visitNode(UNull node) {
    // nothing to do
  }

  @Override
  public void visitNode(UNumber node) {
    // nothing to do
  }

  @Override
  public void visitNode(UString node) {
    // nothing to do
  }

  @Override
  public void visitNode(UVariable node) {
    node.type = rudi.getMem().getVariableType(node.representation);
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
