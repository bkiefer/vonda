/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.RudimantCompiler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;

/**
 * this visitor calculates the types of nodes and checks whether the types are
 * okay
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class TestTypeVisitor implements RudiVisitor {

  private RudimantCompiler rudi;
  private Mem mem;

  public TestTypeVisitor(RudimantCompiler rudi) {
    this.rudi = rudi;
    this.mem = rudi.getMem();
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
    // ?
    // assert (node.right.getType().equals(node.left.getType()));
  }

  @Override
  public void visitNode(ExpAssignment node) {
    // TODO: think about this, it doesn't seem to make a lot of sense
    //System.out.println("Testing an assignment");
    node.right.visit(this);
    if (node.declaration) {
      mem.addElement(((UVariable) node.left).toString(),
              node.actualType, node.position);
      // do not forget to tell the variable what type we find out it is
      ((UVariable) node.left).type = node.actualType;
      node.testTypeDecl(rudi);
    } else {
      // we have to visit the left part, too, because if this is no declaration
      // that visit method will find out the variable type for us
      node.left.visit(this);
      node.testType(rudi);
    }
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
    } else if (node.operator != null) {
      assert (node.left.getType().equals(node.right.getType()));
    }
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    // no type testing needed (?)
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    node.funccall.visit(this);
  }

  @Override
  public void visitNode(ExpIf node) {
    node.boolexp.visit(this);
    node.thenexp.visit(this);
    node.elseexp.visit(this);
    assert (node.boolexp.getType().equals("boolean"));
    assert (node.thenexp.getType().equals(node.elseexp.getType()));
  }

  @Override
  public void visitNode(ExpLambda node) {
    // nothing to do
  }

  @Override
  public void visitNode(GrammarFile node) {
    mem.addAndEnterNewEnvironment();
    mem.enterClass(rudi.className);
    for (RudiTree t : node.rules) {
      t.visit(this);
    }
    // do not leave the environment, we are still in it!
    //mem.leaveEnvironment();
    mem.goBackToBeginning();
  }

  @Override
  public void visitNode(GrammarRule node) {
    mem.addRule(node.label);
    node.setNumber(mem.getRuleNumber(node.label));
    mem.addAndEnterNewEnvironment();
    node.ifstat.visit(this);
    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    if (node.braces) {
      mem.addAndEnterNewEnvironment();
    }
    for (RudiTree t : node.statblock) {
      t.visit(this);
    }
    if (node.braces) {
      mem.leaveEnvironment();
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
    // the assignment will add the variable to the memory
  }

  @Override
  public void visitNode(StatFor2 node) {
    // TODO: this is a bit more complicated; remember the types of the variables
    // that were declared in the condition
    mem.addElement(node.var.toString(), node.varType, node.position);
  }

  @Override
  public void visitNode(StatFor3 node) {
    // TODO: this is a bit more complicated; remember the types of the variables
    // that were declared in the condition
    for (String s : node.variables) {
      mem.addElement(s, "Object", node.position);
    }
  }

  @Override
  public void visitNode(StatFunDef node) {
    // these are not tested, just added to the memory
    mem.addFunction(node.funcname, node.type,
            node.parameterTypes, node.position);
  }

  @Override
  public void visitNode(StatIf node) {
    node.condition.visit(this);
    node.statblockIf.visit(this);
    if (node.statblockElse != null) {
      node.statblockElse.visit(this);
    }
  }

  @Override
  public void visitNode(StatImport node) {
    // nothing to test; what to do with the memory???
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    mem.addFunction(node.name, node.return_type, node.partypes,
            node.position);
    mem.addAndEnterNewEnvironment();
    if (!node.parameters.isEmpty()) {
      for (int i = 0; i < node.parameters.size(); i++) {
        // add parameters to environment
        mem.addElement(node.parameters.get(i), node.partypes.get(i),
                node.position);
      }
    }
    node.block.visit(this);
    mem.leaveEnvironment();
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
    mem.addElement(node.variable, node.type, node.position);
  }

  @Override
  public void visitNode(StatWhile node) {
    node.condition.visit(this);
    node.statblock.visit(this);
    assert (node.condition.getType().equals("boolean"));
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
      node.type = node.askChristophe(mem);
    } catch (TException ex) {
      Logger.getLogger(UFieldAccess.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void visitNode(UFuncCall node) {
    if (node.type == null) {
      node.type = mem.getFunctionRetType(node.representation);
    }
    // test whether the given parameters are of the correct type
    ArrayList<String> partypes = new ArrayList<String>();
    for (RTExpression e : node.exps) {
      partypes.add(e.getType());
    }
    assert (mem.existsFunction(node.representation, partypes));
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
    node.type = mem.getVariableType(node.representation);
    if (!node.origin.equals(mem.getVariableOrigin(node.representation))
            && !(mem.getVariableOrigin(node.representation) == null)) {
      mem.needsClass(mem.getVariableOrigin(node.representation));
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
