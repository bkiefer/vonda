/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.*;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this visitor calculates the types of nodes and checks whether the types are
 * okay
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VTestTypeVisitor implements RudiVisitor {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private RudimantCompiler rudi;
  private Mem mem;

  @Override
  public void visitNode(RTExpression node) {
    node.visit(this);
  }

  public VTestTypeVisitor(RudimantCompiler rudi) {
    this.rudi = rudi;
    this.mem = rudi.getMem();
  }

  /**
   * If that is a binary expression, the resulting type should be the more
   * specific of both. If they are incompatible there should be a warning. Maybe
   * the type could be pushed down if there is only a non-empty type on one
   * branch
   */
  @Override
  public void visitNode(ExpArithmetic node) {
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);

      if (null == node.left.type) {
        // unkown type to the left
        if (null == node.right.type) {
          // unknown type on both branches
          logger.warn("Expression with unknown type: {}", node);
        } else {
          // propagate type from the right branch to the left
          node.left.propagateType(node.right.type);
        }
      } else if (null == node.right.type) {
        //
        node.right.propagateType(node.left.type);
      } else {
        // check type compatibility
        String type = mem.mergeTypes(node.left.type, node.right.type);
        if (type == null) {
          logger.error("Incompatible types in {}: {} vs. {}", node,
                  node.left.type, node.right.type);
        }
      }
    }
  }

  /**
   * This has various aspects. a) if there is a declaration, and the variable is
   * already defined, that should be either a warning or an error. b) if the
   * right side has a non-empty type 1) if the variable is defined, has it the
   * right type? If it has no type, set it to the type of the right side. 2) if
   * it is not defined, define it and set type c) if the variable has a type,
   * and the right side has not, push it down the right branch.
   */
  @Override
  public void visitNode(ExpAssignment node) {
    logger.trace("Testing an assignment");
    node.right.visit(this);
    // TODO: if this is a variable initialization, shouldn't we provide the type
    // to left if it was given? As the type is not yet in the memory, we won't
    // find it while visiting left and therefore get at least tons of warnings
    // Otherwise: infer type from right
    node.left.visit(this);

    if (node.actualType == null) {
      node.actualType = node.type = node.right.type;
    } else {
      node.actualType = mem.checkRdf(node.actualType);
      if (node.right.type == null) {
        node.right.propagateType(node.actualType);
        node.type = node.actualType;
      } else {
        if ((node.type = mem.mergeTypes(node.actualType, node.right.type))
            == null) {
          rudi.handleTypeError("Declared type incompatible with expression: "
                  + node.actualType + ", " + node.right.type);
        }
      }
    }

    if (node.left instanceof UVariable) {
      if (!mem.variableExists(node.left.toString())) {
        node.declaration = true;
        mem.addVariableDeclaration(((UVariable)node.left).content,
                node.type, mem.getClassName());
        if (node.type == null) {
          rudi.handleTypeError("Type of variable unkown: "
                  + node.left + " in " + node);
        }
      } else {
        // is this a variable declaration for an already existing variable?
        if (node.declaration) {
          rudi.handleTypeError("Re-declaration of existing variable " + node.left);
        }
      }
    }
  }

  private boolean isBooleanOperator(String operator) {
    return operator.equals("&&") || operator.equals("||")
            || operator.equals("!");
  }

  private boolean isComparisonOperator(String operator) {
    if ("<>=!".indexOf(operator.charAt(0)) < 0) {
      return false;
    }
    return (operator.length() == 2 && operator.charAt(1) == '=')
            || ("<>".indexOf(operator.charAt(0)) >= 0 && operator.length() == 1);
  }

  /**
   * In principle the same as ExpArithmetic, with boolean only. The one
   * difference is that there are unary expressions which serve as boolean
   * expressions and later have to be turned into proper boolean expressions,
   * either by calling the right 0-ary method, or comparing with zero or null.
   */
  @Override
  public void visitNode(ExpBoolean node) {
    // System.out.println(node.fullexp);
    node.rule = mem.getCurrentRule();
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);
      if (isComparisonOperator(node.operator)) {
        // if one of the operands is an RDF type, or a DialogueAct, the operator has
        // to be turned into a subsumption call
        // TODO: this crashes if there is Introduction or Quiz on the right; they
        // are not assigned to a type when visited...
        if ((node.right.type != null && node.right.type.equals(DIALOGUE_ACT_TYPE))
                || (node.left.type != null && node.left.type.equals(DIALOGUE_ACT_TYPE))) {
          if (node.operator.equals("<=")) {
            node.operator = ".isSubsumed(";
          } else if (node.operator.equals("=>")) {
            node.operator = ".subsumes(";
          }
          return;
        }
        // TODO THIS MIGHT NOT BE ENOUGH, E.G., IF ONE IS A RDF OBJECT AND THE OTHER
        // IS A TYPE, WE MAYBE NEED ANOTHER FUNCTION CALL HERE
        if (node.right.isRdfType() || node.left.isRdfType()) {
          if (node.operator.equals("<=")) {
            node.operator = ".isSubClassOf(";
          } else if (node.operator.equals("=>")) {
            node.operator = ".isSuperClassOf(";
          }
          return;
        }
      }
      if (isBooleanOperator(node.operator)) {
        node.right = node.right.ensureBoolean();
      }
    }
    if (isBooleanOperator(node.operator)) {
      node.left = node.left.ensureBoolean();
    }
  }

  /**
   * This should have type "DialogueAct" already, which should be a constant
   */
  @Override
  public void visitNode(ExpDialogueAct node) {
    // no type testing needed.
  }

  /**
   * This should get the return type of the method
   *
   * @Override public void visitNode(ExpFuncOnObject node) {
   * node.on.visit(this); node.funccall.visit(this);
  }
   */
  /**
   * This might push the boolean type downwards for the boolexp, but that's not
   * necessary because the bool expression already knows.
   */
  @Override
  public void visitNode(ExpIf node) {
    node.boolexp.visit(this);
    node.boolexp = node.boolexp.ensureBoolean();
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
    node.type = node.thenexp.getType();
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
    // do not leave the environment, we are still in it! (but remember to leave it
    // once the generation is done)
//    mem.leaveEnvironment();
    mem.leaveClass(oldname, oldrule, oldTrule);
  }

  @Override
  public void visitNode(GrammarRule node) {
    mem.addRule(node.label, node.toplevel);
    // we step down into a new environment (later turned to a method) whose
    //  variables cannot be seen from the outside
    if (node.toplevel) {
      mem.enterEnvironment();
    }
    node.ifstat.visit(this);
    if (node.toplevel) {
      mem.leaveEnvironment();
    }
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    // we step down into a new environment (a block, possibly method block)
    // whose variables cannot be seen from the outside
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

  /**
   * Short version of for, standard type: for(type a : iterable<varType>) {}
   */
  @Override
  public void visitNode(StatFor2 node) {
    node.exp.visit(this);
    if (node.varType == null) {
      String et = node.exp.getType();
      if (et.contains("<")) {
        node.varType = et.substring(et.indexOf("<"), et.indexOf(">"));
      }
    }
    mem.addVariableDeclaration(node.var.toString(), node.varType, node.position);
    node.statblock.visit(this);
  }

  /**
   * short for with decomposition : for ((a,b,c) : complex_iterable) {}
   */
  @Override
  public void visitNode(StatFor3 node) {
    // TODO: this is a bit more complicated; remember the types of the variables
    // that were declared in the condition
    for (String s : node.variables) {
      mem.addVariableDeclaration(s, "Object", node.position);
    }
  }

  /**
   * TODO: WHAT HAPPENS HERE? IS IT STILL NEEDED?
   */
  @Override
  public void visitNode(StatImport node) {
    String conargs = "";
    if (null != rudi.getConstructorArgs()
            && !rudi.getConstructorArgs().isEmpty()) {
      int i = 0;
      for (String a : rudi.getConstructorArgs().split(",")) {
        if (i > 0) {
          conargs += ", ";
        }
        conargs += a.trim().split(" ")[1];
        i++;
      }
    }
    mem.addImport(node.name, conargs);
  }

  @Override
  public void visitNode(StatListCreation node) {
    if (!(node.objects == null)) {
      for (RTExpression e : node.objects) {
        this.visitNode(e);
      }
      if (node.listType != null) {
        if (!(node.listType.substring(node.listType.indexOf("<"),
                node.listType.indexOf(">")).equals(node.objects.get(0)))) {
          rudi.handleTypeError("Found a list creation where the list type doesn't fit"
                  + " its objects' type");
        }
        mem.addVariableDeclaration(node.variableName, node.listType, node.origin);
        return;
      }
      node.listType = "List<" + node.objects.get(0).getType() + ">";
      mem.addVariableDeclaration(node.variableName, node.listType, node.origin);
    } else if (node.listType == null) {
      node.listType = "List<Object>";
    }
  }

  @Override
  public void visitNode(StatSetOperation node) {
    // TODO: test whether the set accepts variables of this type??
  }

  /* **********************************************************************
   * Statements that add info to the binding table (var defs, fun defs)
   * ********************************************************************* */
  @Override
  public void visitNode(StatVarDef node) {
    mem.addVariableDeclaration(node.variable, node.type, node.position);
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    mem.addFunction(node.name, node.return_type, node.partypes, node.position);
    if (node.block != null) {
      // The following variables (function parameters) are local to the method
      // block we now step into; we don't want them to be reachable them from
      // outside
      mem.enterEnvironment();
      for (int i = 0; i < node.parameters.size(); i++) {
        // add parameters to environment
        mem.addVariableDeclaration(node.parameters.get(i), node.partypes.get(i),
                node.position);
      }
      node.block.visit(this);
      mem.leaveEnvironment();
    }
  }


  /* **********************************************************************
   * Statements where one part must be a bool exp (if, do, while, for)
   * where bool exp must be guaranteed
   * ********************************************************************* */
  /**
   * visit children, ensure boolean exp
   */
  @Override
  public void visitNode(StatIf node) {
    node.currentRule = mem.getCurrentRule();
    node.condition.visit(this);
    node.statblockIf.visit(this);
    if (node.statblockElse != null) {
      node.statblockElse.visit(this);
    }
    node.condition = node.condition.ensureBoolean();
  }

  /**
   * visit children, ensure boolean exp
   */
  @Override
  public void visitNode(StatWhile node) {
    node.condition.visit(this);
    node.block.visit(this);
    node.condition = node.condition.ensureBoolean();
  }

  /**
   * condition is a boolean exp anyway, and has the right type
   */
  @Override
  public void visitNode(StatDoWhile node) {
    node.condition.visit(this);
    node.block.visit(this);
    node.condition = node.condition.ensureBoolean();
  }

  /**
   * for (a;b;c) {}, only visit the sub-parts of this.
   */
  @Override
  public void visitNode(StatFor1 node) {
    node.assignment.visit(this);
    node.condition.visit(this);
    node.arithmetic.visit(this);
    node.statblock.visit(this);
    node.condition = node.condition.ensureBoolean();
  }

  @Override
  public void visitNode(UFieldAccess node) {
    try {
      node.type = node.getPredicateType(mem.getProxy(), mem, node.representation);
    } catch (TException ex) {
      logger.error(ex.toString());
    }
    /*if(node.type == null){
     // then this is not an rdf node, but some composed funccall
     node.parts.get(node.parts.size() - 1).visit(this);
     node.type = ((RTExpression)node.parts.get(node.parts.size() - 1)).getType();
     return;
     }*/
    for (int i = 0; i < node.representation.size(); i++) {
      node.parts.get(i).visit(this);
//      if (node.representation.get(i).contains("(")) {
//        continue;
//      } else if (!mem.variableExists(node.representation.get(i))) {
//        node.representation.set(i, "\"" + node.representation.get(i) + "\"");
//      }
    }
  }

  /**
   * TODO: Are the parameter types of the function checked against the actual
   * types? Is it necessary?
   */
  @Override
  public void visitNode(UFuncCall node) {
    if (node.type == null) {
      node.type = mem.getFunctionRetType(node.content);
    }
    // test whether the given parameters are of the correct type
    ArrayList<String> partypes = new ArrayList<String>();
    for (RTExpression e : node.exps) {
      partypes.add(e.getType());
    }
    if (!mem.existsFunction(node.content, partypes)) {
      rudi.handleTypeError("The function call to " + node.content
              + " refers to a function that wasn't declared");
    }
  }

  /**
   * TODO: Do we have to check here if it's an RDF type?? or DialogueAct?? or is
   * this all clear? we might have to test whether Quiz is sth like that and
   * give the node a proper type; how to do that?
   */
  @Override
  public void visitNode(USingleValue node) {
    // nothing to test here
  }

  /**
   * We're trying to determine the type of a variable. a) If the variable is
   * already defined in another rudi file, it is fully specified, and all type
   * information is already stored with it. b)
   */
  @Override
  public void visitNode(UVariable node) {
    if (node.type != null && mem.getVariableType(node.content) == null) {
      mem.addVariableDeclaration(node.fullexp, node.type, node.originClass);
      return;
    }
    node.type = mem.getVariableType(node.fullexp);
    String o = mem.getVariableOriginClass(node.fullexp);
    // we could have sth like Introduction, that is an undeclared rdf class
    try {
      RdfClass cl = mem.getProxy().fetchClass(node.content);
      if (cl != null) {
        node.type = cl.toString();
      }
    } catch (TException ex) {
      logger.error(ex.toString());
    }

    /*
     if (o == null) {
     // the variable does not originate in another file

     // is the variable an rdf type?
     try {
     if (rudi.getProxy().fetchRdfClass(node.type) != null) {
     node.isRdfClass = true;
     return;
     } else // TODO: is this correct????
     // it could still be sth like Introduction
     if (rudi.getProxy().fetchRdfClass(node.representation) != null) {
     node.isRdfClass = true;
     return;
     }
     } catch (TException e) {
     logger.error("Problem accessing database : {}", e.getMessage());
     throw new RuntimeException(e);
     }

     // if not, mem either found a type or this variable wasn't declared
     if (node.type == null) {
     rudi.handleTypeError("The variable " + node.representation
     + " is used but was not declared");
     node.type = "Object";
     return;
     }
     }
     */
    if (o != null && !node.originClass.equals(o)) {
      mem.needsClass(mem.getCurrentTopRule(), o);
      node.realOrigin = o;
    }
  }

  /* **********************************************************************
   * Nodes without special treatment
   *
   * ********************************************************************* */
  @Override
  public void visitNode(UWildcard node) {
    // nothing to do
  }

  @Override
  public void visitNode(StatPropose node) {
    node.arg.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visitNode(StatReturn node) {
    // nothing to do (?)
  }

  @Override
  public void visitNode(StatSwitch node) {
    node.condition.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visitNode(ExpNew node) {
    if (node.toCreate != null) {
      // TODO: insert proper rdf type
      node.type = node.toCreate;
    } else {
      String f = node.fullexp;
      // the type is the java object created
      node.type = f.substring(f.indexOf("w"), f.indexOf("("));
      node.construct.visit(this);
    }
  }

  /**
   * TODO: this is generation, not type computation. It does not belong here *
   * private void conditionHandling(ExpBoolean node) { String t =
   * node.left.getType(); if (t == null) { // lets assume it is an unrecognized
   * rdf object node.isTrue = " != null"; return; } if (!t.equals("boolean")) {
   * // tell the expression how it should handle its condition if
   * (t.equals("int") || t.equals("float")) { node.isTrue = " != 0"; } else if
   * (mem.isRdf(node.fullexp)) { node.isTrue = ".has()?;\n"; } else {
   * node.isTrue = " != null"; if (t.contains("List") || t.contains("Set") ||
   * t.contains("Map")) { node.testIsEmpty = true; } }
   * node.left.setType("boolean"); } else { node.isTrue = ""; } }
   */
}
