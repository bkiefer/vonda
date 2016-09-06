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
      ArrayList<String> a = new ArrayList<>();
      a.add(node.position);
      a.add(mem.getCurrentTopRule());
      boolean worked = mem.addElement(node.left.toString(),
              node.actualType, a);
      if (!worked) {
        rudi.handleTypeError("You are trying to re-declare the variable "
                + node.left.toString() + ", you really shouldn't do this");
      }
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
    if(node.right == null){
      // TODO: there is nothing else to do?
      return;
    }
    node.right.visit(this);
    if (node.operator != null && node.left.getType().equals("magic")) {
      if (node.left.getType().equals(node.right.getType())) {
        if (node.operator.equals("<=")) {
          node.isSubsumed = true;
        } else if (node.operator.equals("=>")) {
          node.doesSubsume = true;
        }
      }
    } else if (node.operator != null) {
      if (!node.left.getType().equals(node.right.getType())) {
        rudi.handleTypeError(node.fullexp + " s a boolean expression with type "
                + node.left.getType() + " on the one and type " + node.right.getType()
                + " on the other hand");
      }
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
    if (!node.boolexp.getType().equals("boolean")){
      rudi.handleTypeError(node.fullexp + " is an if expression where the condition does not "
              + "resolve to boolean!");
    }
    if (!node.thenexp.getType().equals(node.elseexp.getType())){
      rudi.handleTypeError(node.fullexp + " is an if expression where the else expression "
              + "does not have the same type as the right expression!\n("
              + "comparing types " + node.thenexp.getType() + " on left and " +
              node.elseexp.getType() + " on right)");
    }
  }

  @Override
  public void visitNode(ExpLambda node) {
    // nothing to do
  }

  @Override
  public void visitNode(GrammarFile node) {
    mem.addAndEnterNewEnvironment();
    String oldname = mem.getClassName();
    String oldrule = mem.getCurrentRule();
    String oldTrule = mem.getCurrentTopRule();
    mem.enterClass(rudi.className);
    for (RudiTree t : node.rules) {
      t.visit(this);
    }
    for (String s : mem.getTopLevelRules(rudi.className)) {
      for (String n : mem.getNeededClasses(s)) {
        mem.needsClass(rudi.className, n);
      }
    }
    // do not leave the environment, we are still in it!
    //mem.leaveEnvironment();
    mem.leaveClass(oldname, oldrule, oldTrule);
  }

  @Override
  public void visitNode(GrammarRule node) {
    mem.addRule(node.label, node.toplevel);
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
    if(!node.condition.getType().equals("boolean")){
      rudi.handleTypeError("This is a while statement where the condition does not "
              + "resolve to boolean!");
    }
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
    ArrayList<String> a = new ArrayList<>();
    a.add(node.position);
    a.add(mem.getCurrentTopRule());
    mem.addElement(node.var.toString(), node.varType, a);
  }

  @Override
  public void visitNode(StatFor3 node) {
    // TODO: this is a bit more complicated; remember the types of the variables
    // that were declared in the condition
    for (String s : node.variables) {
      ArrayList<String> a = new ArrayList<>();
      a.add(node.position);
      a.add(mem.getCurrentTopRule());
      mem.addElement(s, "Object", a);
    }
  }

  @Override
  public void visitNode(StatFunDef node) {
    // these are not tested, just added to the memory
    ArrayList<String> a = new ArrayList<>();
    a.add(node.position);
    a.add(node.position);
    mem.addFunction(node.funcname, node.type,
            node.parameterTypes, a);
  }

  @Override
  public void visitNode(StatIf node) {
    node.condition.visit(this);
    if(!node.condition.getType().equals("boolean")){
      rudi.handleTypeError("This is an if statement where the condition: " +
              node.conditionString + ", does not resolve to boolean!");
    }
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
    ArrayList<String> a = new ArrayList<>();
    a.add(node.position);
    a.add(mem.getCurrentTopRule());
    mem.addFunction(node.name, node.return_type, node.partypes,
            a);
    mem.addAndEnterNewEnvironment();
    if (!node.parameters.isEmpty()) {
      for (int i = 0; i < node.parameters.size(); i++) {
        // add parameters to environment
        a.add(node.position);
        a.add(mem.getCurrentTopRule());
        mem.addElement(node.parameters.get(i), node.partypes.get(i), a);
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
    ArrayList<String> a = new ArrayList<>();
    a.add(node.position);
    a.add(node.position);
    mem.addElement(node.variable, node.type, a);
  }

  @Override
  public void visitNode(StatWhile node) {
    node.condition.visit(this);
    if(!node.condition.getType().equals("boolean")){
      rudi.handleTypeError("This is a while statement where the condition does not "
              + "resolve to boolean!");
    }
    node.statblock.visit(this);
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
    if (!mem.existsFunction(node.representation, partypes)){
      rudi.handleTypeError("The function call to " + node.representation + " referrs"
              + " to a function that wasn't declared");
    }
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
    String o = mem.getVariableOriginClass(node.representation);
    if (o == null) {
      rudi.handleTypeError("The variable " + node.representation
              + " is used but was not declared");
      node.type = "Object";
      return;
    }
    if (!node.originClass.equals(o)) {
      mem.needsClass(mem.getCurrentTopRule(), o);
      node.realOrigin = o;
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
