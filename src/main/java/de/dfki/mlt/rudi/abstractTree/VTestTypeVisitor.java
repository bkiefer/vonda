/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.RudimantCompiler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.slf4j.LoggerFactory;

/**
 * this visitor calculates the types of nodes and checks whether the types are
 * okay
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VTestTypeVisitor implements RudiVisitor {

  public static org.slf4j.Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private RudimantCompiler rudi;
  private Mem mem;

  public VTestTypeVisitor(RudimantCompiler rudi) {
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
    if (node.right != null) {
      node.right.visit(this);
    }
    // ?
    // assert (node.right.getType().equals(node.left.getType()));
  }

  @Override
  public void visitNode(ExpAssignment node) {
    logger.trace("Testing an assignment");
    node.right.visit(this);
    if (node.declaration) {
      boolean worked = mem.addElement(node.left.toString(),
              node.actualType, node.position);
      if (!worked) {
        rudi.handleTypeError("You are trying to re-declare the variable "
                + node.left.toString() + ", you really shouldn't do this");
      }
      // do not forget to tell the variable what type we find out it is
      ((UVariable) node.left).type = node.actualType;
      node.testTypeDecl(rudi);
      node.position = mem.getCurrentTopRule();
//      node.left.visit(this);
    } else {
      // we have to visit the left part, too, because if this is no declaration
      // that visit method will find out the variable type for us
      node.left.visit(this);
      node.testType(rudi);
    }
  }

  @Override
  public void visitNode(ExpBoolean node) {
    node.rule = mem.getCurrentRule();
    node.left.visit(this);
    if (node.right == null) {
      this.conditionHandling(node);
      return;
    }
    node.right.visit(this);
    if (node.operator != null && node.left.getType().equals("DialogueAct")) {
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
      } else {
        node.type = "boolean";
      }
    }
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    // no type testing needed (?)
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    node.on.visit(this);
    node.funccall.visit(this);
  }

  @Override
  public void visitNode(ExpIf node) {
    node.boolexp.visit(this);
    node.thenexp.visit(this);
    node.elseexp.visit(this);
//      rudi.handleTypeError(node.fullexp + " is an if expression where the condition does not "
//              + "resolve to boolean!");
    if (!node.thenexp.getType().equals(node.elseexp.getType())) {
      rudi.handleTypeError(node.fullexp + " is an if expression where the else expression "
              + "does not have the same type as the right expression!\n("
              + "comparing types " + node.thenexp.getType() + " on left and "
              + node.elseexp.getType() + " on right)");
    }
  }

  @Override
  public void visitNode(ExpLambda node) {
    // nothing to do
  }

  @Override
  public void visitNode(GrammarFile node) {
    mem.enterEnvironment();
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
//    mem.enterEnvironment();
    node.ifstat.visit(this);
//    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    if (node.braces) {
      mem.enterEnvironment();
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
    /*if (!node.condition.getType().equals("boolean")) {
      rudi.handleTypeError("This is a while statement where the condition does not "
              + "resolve to boolean!");
    }*/
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
    if (node.varType == null) {
      String et = node.exp.getType();
      if (et.contains("<")) {
        node.varType = et.substring(et.indexOf("<"), et.indexOf(">"));
      }
    }
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
    node.currentRule = mem.getCurrentRule();
    node.condition.visit(this);
    /*if (!node.condition.getType().equals("boolean")) {
      rudi.handleTypeError("This is an if statement where the condition: "
              + node.conditionString + ", does not resolve to boolean!");
    }*/
    node.statblockIf.visit(this);
    if (node.statblockElse != null) {
      node.statblockElse.visit(this);
    }
  }

  @Override
  public void visitNode(StatImport node) {
    // nothing to test; what to do with the memory???
    mem.addImport(node.name);
  }

  @Override
  public void visitNode(StatListCreation node) {
    if (!(node.objects == null)) {
      for (RTExpression e : node.objects) {
        this.visitNode(e);
      }
      node.listType = "List<" + node.objects.get(0).getType() + ">";
      mem.addElement(node.variableName, node.listType, node.origin);
    } else {
      node.listType = "Object";
    }
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    mem.addFunction(node.name, node.return_type, node.partypes, node.position);
    mem.enterEnvironment();
    if (!node.parameters.isEmpty()) {
      for (int i = 0; i < node.parameters.size(); i++) {
        // add parameters to environment
        mem.addElement(node.parameters.get(i), node.partypes.get(i), node.position);
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
    /*if (!node.condition.getType().equals("boolean")) {
      rudi.handleTypeError("This is a while statement where the condition does not "
              + "resolve to boolean!");
    }*/
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
    if (!mem.existsFunction(node.representation, partypes)) {
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
      if (node.type == null) {
        // this variable wasnt declared, so it doesnt exist or is an rdf type
        if (RdfClass.classExists(node.representation, rudi.getClient())) {
          node.isRdfClass = true;
          return;
        }
      }
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

  private void conditionHandling(ExpBoolean node) {
    String t = node.left.getType();
    if (t == null) {
      // lets assume it is an unrecognized rdf object
      node.isTrue = " != null";
      return;
    }
    if (!t.equals("boolean")) {
      // tell the expression how it should handle its condition
      if (t.equals("int") || t.equals("float")) {
        node.isTrue = " != 0";
      } else if (mem.isRdf(node.fullexp)) {
        node.isTrue = ".has()?;\n";
      } else {
        node.isTrue = " != null";
        if (t.contains("List") || t.contains("Set") || t.contains("Map")) {
          node.testIsEmpty = true;
        }
      }
    } else {
      node.isTrue = "";
    }
  }
}
