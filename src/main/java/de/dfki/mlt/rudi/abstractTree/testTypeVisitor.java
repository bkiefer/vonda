/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.RudimantCompiler;

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
    throw new UnsupportedOperationException("Not supported yet.");
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
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UFuncCall node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UNull node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UNumber node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UString node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UVariable node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UWildcard node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
