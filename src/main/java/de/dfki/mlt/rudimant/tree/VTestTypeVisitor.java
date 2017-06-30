/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.Type;

/**
 * this visitor calculates the types of nodes and checks whether the types are
 * okay
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VTestTypeVisitor implements RTExpressionVisitor, RTStatementVisitor {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private RudimantCompiler rudi;
  private Mem mem;

  public VTestTypeVisitor(RudimantCompiler comp) {
    rudi = comp;
    mem = rudi.getMem();
  }

  public void visitNode(RTExpression node) {
    node.visit(this);
  }

  public void visitNode(RTStatement node) {
    node.visit(this);
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

      if (node.left.type.isUnspecified()) {
        // unknown type to the left
        if (node.right.type.isUnspecified()) {
          // unknown type on both branches
          rudi.typeError("Expression with unknown type: " + node.right, node);
        } else {
          // propagate type from the right branch to the left
          node.left.propagateType(node.right.type);
        }
      } else if (node.right.type.isUnspecified()) {
        //
        node.right.propagateType(node.left.type);
      } else {
        // check type compatibility
        Type type = node.left.type.unifyTypes(node.right.type);
        if (type == null) {
          rudi.typeError("Incompatible types in " + node + ": "
                  + node.left.type + " vs. " + node.right.type, node);
        } else {
          node.type = type;
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
    node.right.visit(this);
    // make sure they become Java POD types, if xsd type
    //node.right.type = node.right.type != null? node.right.type.convertXsdType() : null;
    node.left.visit(this);
    //node.left.type = node.left.type != null? node.left.type.convertXsdType() : null;

    // is this a variable declaration for an already existing variable?
    // When we get here, if node.declaration is true, then node.type has a
    // non-null value, and vice versa
    if (node.declaration) {
      if (mem.variableExists(node.left.toString())) {
        rudi.typeError("Re-declaration of existing variable " + node.left, node);
      } else {
    	if(mem.isActiveRule(node.left.fullexp)){
          rudi.typeError("Declaring a variable " + node.left + " what also is a rule name!", node);
    	}
        node.left.type = node.type; // the type of the declaration is in this node
      }
      mem.addVariableDeclaration(((ExpUVariable) node.left).content,
              node.left.type);
      if (node.right.type == null) {
        node.right.propagateType(node.type);
      }
    } else if ((node.left instanceof ExpUVariable
            && !mem.variableExists(node.left.toString()))) {
      node.declaration = true;
      // node.type is null, and variable does not exist, so no type.
      // now consolidate types
      if (node.right.type == null) {
        // please, never assign null as a type, because that is no valid java type
        // and will crash for sure
        node.right.type = new Type("Object");
        // TODO: ? node.right.propagateType(node.type);
      }
      node.type = node.left.type = node.right.type;
      mem.addVariableDeclaration(((ExpUVariable) node.left).content,
              node.left.type);
    }
    if (node.right instanceof ExpUVariable
        && !mem.variableExists(node.right.toString())) {
      rudi.typeError("assigning the value of a non-existing variable "
          + node.right + "to " + node.left, node);
    }

    // If the type on the left side is more specific that the one on the right,
    // a cast must be applied during generation. if it is less specific, no
    // special measures must be taken.
    // if unifyTypes returns null, the types are incompatible
    // if one of them is null, unifyTypes will return the other type
    Type mergeType = node.left.type.unifyTypes(node.right.type);
    if (mergeType == null) {
      if (node.left.type.isBool()) {
        // in that case, we assume that this should be a test for existance
        mergeType = new Type("boolean");
        node.right = node.right.ensureBoolean();
      } else {
        if ((node.left instanceof ExpUFieldAccess) &&
            node.right instanceof ExpUSingleValue &&
            ((ExpUSingleValue)node.right).content.equals("null")) {
          // this is a "clear" operation, to be resolved later.
          mergeType = node.left.type;
        } else {
          mergeType = Type.getNoType();
          rudi.typeError("Incompatible types in assignment: "
              + node.left.type + " := " + node.right.type, node);
        }
      }
    }
    node.type = mergeType;
    if (node.right.type == null) {
      node.right.propagateType(node.type);
    }
    if (node.left.type == null) {
      node.left.propagateType(node.type);
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
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);
      if (node.left.getType() != null && !node.left.getType().isPODType()
          && node.right.getType() != null && !node.right.getType().isPODType()) {
        String or = "";
        switch (node.operator) {
          case "==":
            node.operator = or + "isEqual(";
            break;
          case "<":
            node.operator = or + "isSmaller(";
            break;
          case ">":
            node.operator = or + "isGreater(";
            break;
          case "<=":
            node.operator = or + "isSmallerEqual(";
            break;
          case ">=":
            node.operator = or + "isGreaterEqual(";
            break;
        }
      }
      if (isBooleanOperator(node.operator)) {
        node.right = node.right.ensureBoolean();
        node.left = node.left.ensureBoolean();
      }
    } else {
      // we should do this always, because if the boolean expression has only one
      // side it may have no operator!!!!
      node.left = node.left.ensureBoolean();
    }
  }

  @Override
  public void visitNode(ExpCast node) {
    visitNode(node.expression);
    Type mergeType = node.type.unifyTypes(node.expression.type);
    if (mergeType == null) {
      rudi.typeError("Incompatible types : " + node.expression.type
          + " casted to " + node.type, node);
    }
    node.expression.type = node.type;
  }

  /**
   * This should have type "DialogueAct" already, which should be a constant
   * What we have to do is finding out which tokens are actual variables and
   * degrading all others to String (so we can infer how to represent them in
   * generation)
   */
  @Override
  public void visitNode(ExpDialogueAct node) {
    if (node.daType instanceof ExpUVariable) {
      if (!mem.variableExists(((ExpUVariable) node.daType).content)) {
        node.daType = mem.degradeToString((ExpUVariable) node.daType);
      }
    }
    if (node.proposition instanceof ExpUVariable) {
      if (!mem.variableExists(((ExpUVariable) node.proposition).content)) {
        node.proposition = mem.degradeToString((ExpUVariable) node.proposition);
      }
    }
    int i = 0;
    for (RTExpression e : node.exps) {
      if (e instanceof ExpUVariable) {
        if (!mem.variableExists(((ExpUVariable) e).content)) {
          node.exps.set(i, mem.degradeToString((ExpUVariable) e));
        }
      } else {
        e.visit(this);
      }
      i++;
    }
  }

  /**
   * This should get the return type of the method
   *
   * @Override public void visitNode(ExpFuncOnObject node) {
   * node.on.visit(this); node.funccall.visit(this); }
   */
  /**
   * This might push the boolean type downwards for the boolexp, but that's not
   * necessary because the bool expression already knows.
   */
  @Override
  public void visitNode(ExpConditional node) {
    node.boolexp.visit(this);
    node.boolexp = node.boolexp.ensureBoolean();
    node.thenexp.visit(this);
    node.elseexp.visit(this);
    Type unified = node.thenexp.getType() != null?
    	node.thenexp.getType().unifyTypes(node.elseexp.getType())
    	: node.elseexp.getType();
    if (unified == null) {
      rudi.typeError(node.fullexp
              + " is a conditional expression where the else expression "
              + "does not have the same type as the right expression!\n("
              + "comparing types " + node.thenexp.getType() + " on left and "
              + node.elseexp.getType() + " on right)", node);
    }
    node.type = unified;
  }

  @Override
  public void visitNode(ExpLambda node) {
    mem.enterEnvironment();
    for(String arg : node.parameters){
      mem.addVariableDeclaration(arg, node.parType);
    }
    if (node.body instanceof RTExpression) {
      RTExpression exp = (RTExpression)node.body;
      visitNode(exp);
      node.type = exp.getType();
    } else {
      visitNode((StatAbstractBlock)node.body);
    }
    mem.leaveEnvironment();
  }


  @Override
  public void visitNode(StatGrammarRule node) {
    node.toplevel = mem.enterRule(node.label);
    // we step down into a new environment (later turned to a method) whose
    //  variables cannot be seen from the outside
    if (node.toplevel) {
      mem.enterEnvironment();
    }
    node.ifstat.visit(this);
    if (node.toplevel) {
      mem.leaveEnvironment();
    }
    mem.leaveRule();
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    // we step down into a new environment (a block, possibly method block)
    // whose variables cannot be seen from the outside
    if (node.braces) {
      mem.enterEnvironment();
    }
    for (RTStatement t : node.statblock) {
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
    node.initialization.visit(this);
    Type innerIterableType = null;
    if (node.initialization.type != null) {
      innerIterableType = node.initialization.getInnerType();
    }
    if (innerIterableType == null) {
      rudi.typeError("Iterable for loop type is unknown or not generic, but: "
          + node.initialization.getType(), node);
      innerIterableType= new Type("Object");
    }
    if (node.varType == null) {
      node.varType = innerIterableType;
    } else {
      Type mergeType = node.varType.unifyTypes(innerIterableType);
      if (mergeType == null) {
        rudi.typeError("Incompatible types in short for loop: "
            + node.varType + " : " + innerIterableType, node);
        node.varType = innerIterableType;
      }
    }
    mem.addVariableDeclaration(node.var.toString(), node.varType);
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
      mem.addVariableDeclaration(s, new Type("Object"));
    }
  }

  @Override
  public void visitNode(StatListCreation node) {
    if (!(node.objects.isEmpty())) {
      for (RTExpression e : node.objects) {
        visitNode(e);
      }
      Type type = node.listType;
      if (type != null) {
        Type elementType = type.getInnerType();
        if (elementType.unifyTypes(node.objects.get(0).getType()) == null) {
          rudi.typeError("Found a list creation where the list type"
                  + " doesn't fit its objects' type: " + elementType
                  + " vs " + node.objects.get(0).getType(), node);
        }
        mem.addVariableDeclaration(node.variableName, type);
      } else {
        node.listType = new Type("List<" + node.objects.get(0).getType() + ">");
        mem.addVariableDeclaration(node.variableName, type);
      }
    } else if (node.listType == null) {
      node.listType = new Type("List<Object>");
    }
  }

  @Override
  public void visitNode(StatSetOperation node) {
    // First call type checks for components, then perform the possible local
    // tests: The parameter type of the set should be compatible with what's
    // added.
    node.left.visit(this);
    node.right.visit(this);
    if (! node.left.isComplexType()) {
      rudi.typeError("Left side of a set operation is not a set, but "
          + node.left.getType(), node);
      return;
    }
    Type inner = node.left.getInnerType();
    Type res = inner.unifyTypes(node.right.type);
    if (res == null) {
      rudi.typeError("Incompatible types in set operation: "
          + node.left.getType() + " <> " + node.right.type, node);
    }
  }

  /* **********************************************************************
   * Statements that add info to the binding table (var defs, fun defs)
   * ********************************************************************* */
  @Override
  public void visitNode(StatVarDef node) {
    mem.addVariableDeclaration(node.variable, node.type);
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {

    mem.addFunction(node.name, node.return_type, node.calledUpon, node.partypes);
    if (node.block != null) {
      // The following variables (function parameters) are local to the method
      // block we now step into; we don't want them to be reachable them from
      // outside
      mem.enterEnvironment();
      for (int i = 0; i < node.parameters.size(); i++) {
        // add parameters to environment
        mem.addVariableDeclaration(node.parameters.get(i), node.partypes.get(i));
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
   * for (a;b;c) {}, only visit the sub-parts of this.
   */
  @Override
  public void visitNode(StatFor1 node) {
    node.initialization.visit(this);
    node.condition.visit(this);
    node.increment.visit(this);
    node.statblock.visit(this);
    node.condition = node.condition.ensureBoolean();
  }

  // set this variable to tell error handling that we are in a function invoked
  // on a java object, so we probably do not wanna throw an error here
  boolean partOfFieldAccess = false;

  /**
   * treat the case that an RDF is accessed with a label, which can result in a
   * lot of different things: setValue, getValue, setSingleValue,
   * getSingleValue, has
   *
   * @param node
   * @param currentType
   * @param label
   */
  ExpUPropertyAccess treatRdfPropertyAccess(ExpUFieldAccess node, Type currentType,
          ExpUVariable var) {
    // only a literal: check if it is a property of clz, and update the
    // current type
    if (mem.getVariableType(var.content) != null &&
        mem.getVariableType(var.content).isString()) {
      // the literal represents a variable, so we can't determine the type of
      // the access
      // TODO: Maybe return "Object" as type, should be correct in most cases.
      return new ExpUPropertyAccess(var.fullexp, var, true, Type.getNoType(), false);
    }
    if (currentType.isDialogueAct()) {
      // the return type will be string, this is a call to getSlot
      return new ExpUPropertyAccess(var.fullexp, var, false, new Type("String"), true);
    }
    RdfClass clz = currentType.getRdfClass();
    String predUri = null;
    if (clz != null) {
      predUri = clz.fetchProperty(var.content);
    }
    // warning / error if property not found
    if (predUri == null) {
      rudi.typeError("No property found for " + var.content, node);
      return new ExpUPropertyAccess(var.fullexp, var, false, null, false);
    }

    var.content = predUri; // replace plain name by URI
    int predType = clz.getPropertyType(predUri);
    // TODO: CONVERT XSD TYPES TO JAVA, WHERE POSSIBLE
    String t = clz.getPropertyRange(predUri);
    currentType = new Type(t);
    if (t == null) {
      // WARNING / error
      rudi.typeError("No range type defined for property "
              + predUri, node);
      if (var.getType() != null) {
        rudi.typeWarning("empty range: type defined instead: "
                + var.getType(), node);
      }
    }
    // TODO: this resulted in weird warnings if the property has the same name
    // 		as a local variable; this should be a "parameterized" property
    // access, where the name of the property is stored in the variable,
    // which seems too complicated anyway.
    /*
    if (var.getType() != null && !var.getType().equals(currentType)) {
      rudi.typeError("Overwriting type " + var.type
              + "  of partial field access for " + var.content + " to "
              + currentType, node);
    }*/

    // Set<Bla> vs Bla distinction, unfortunately, Java can not reason about
    // parameter types, so this must be Object
    boolean isFunctional = (predType & RdfClass.FUNCTIONAL_PROPERTY) != 0;
    if (! isFunctional) {
      //currentType = "Set<"+currentType+">";
      currentType = new Type("Set<Object>");
    }
    // the type of this is set to Object by default (not null)
    return new ExpUPropertyAccess(var.fullexp, var, false, currentType, isFunctional);
  }

  /**
   * How this should work: We start from the leftmost element, stored in the
   * first element of the list, and work our way down.
   *
   * TODO CHECK FOR PROPERTIES RANGING OVER XSD DATATYPES, ETC. ALL FUZZY STUFF
   */
  @Override
  public void visitNode(ExpUFieldAccess node) {
    Type currentType = null;
    RTExpression currentNode = node.parts.get(0); // can not be empty
    currentNode.visit(this);
    // The type to which the next field access item is applied
    currentType = ((RTExpression) currentNode).type;
    // this is dangerous, and only works if this condition can not be
    // "interrupted"
    partOfFieldAccess = true;
    for (int i = 1; i < node.parts.size(); ++i) {
      currentNode = node.parts.get(i);
      if (currentType != null && currentType.isComplexType()
          && currentNode instanceof ExpUFuncCall
          && ! ((ExpUFuncCall)currentNode).exps.isEmpty()
          && ((ExpUFuncCall)currentNode).exps.get(0) instanceof ExpLambda) {
        ((ExpLambda)((ExpUFuncCall)currentNode).exps.get(0)).parType =
        		currentType.getInnerType();
      }
      // if this is a funccall performed on anything, tell the function the type it was called on
      if(currentNode instanceof ExpUFuncCall && currentType != null){
    	  ((ExpUFuncCall)currentNode).calledUpon = currentType;
      }
      currentNode.visit(this);
      if (currentType != null && currentType.isRdfType()) {
        if (currentNode instanceof ExpUVariable) {
          // only a literal, delegate this because it's complicated
          ExpUPropertyAccess acc
                  = treatRdfPropertyAccess(node, currentType, (ExpUVariable) currentNode);
          node.parts.set(i, acc);
          currentType = acc.getType();
        } else if (currentNode instanceof RTExpression) {
          // could also be a method application, possibly, what else?
          currentType = ((RTExpression) currentNode).type;
        } else {
          currentType = null;
        }
      } else { // unknown or Java type, let's try with the accessor type
    	  // TODO: think about this. we definitely want this in case of
        //    currentNode being a function call, but if it is a variable we get
        //    either nothing or - worse - the type of some unrelated local variable;
    	  //		which other expressions need to be handled cautiously?
        if (currentNode instanceof RTExpression
            && !(currentNode instanceof ExpUVariable)) {
          currentType = ((RTExpression) currentNode).type;
        } else {
          currentType = null;
        }
      }
      ((RTExpression) currentNode).type = currentType;
    }
    node.type = currentType == null ? new Type("Object") : currentType; // the final result type
    partOfFieldAccess = false;
  }

  /**
   * TODO: Are the parameter types of the function checked against the actual
   * types? Is it necessary?
   */
  @Override
  public void visitNode(ExpUFuncCall node) {
    // if this was used in a new Expression, handle it accordingly
    if(node.newexp){
      node.type = new Type(node.content);
      return;
    }
    // test whether the given parameters are of the correct type
    List<Type> partypes = new ArrayList<Type>();
    for (RTExpression e : node.exps) {
      e.visit(this);
      partypes.add(e.getType());
    }
    String o = mem.getFunctionOrigin(node.content, partypes);
    if (o != null) {
      node.realOrigin = o;
    }
    if (node.type == null) {
      node.type = mem.getFunctionRetType(node.content, node.calledUpon, partypes);
    }
    if (!mem.existsFunction(node.content, partypes)) {
      if (partOfFieldAccess) {
        // TODO: adapt this if you still want to throw an error
        return;
      }
      rudi.typeError("The function call to " + node.content
              + " refers to a function that wasn't declared", node);
    }
  }

  /**
   * TODO: Do we have to check here if it's an RDF type?? or DialogueAct?? or is
   * this all clear? we might have to test whether Quiz is sth like that and
   * give the node a proper type; how to do that?
   */
  @Override
  public void visitNode(ExpUSingleValue node) {
    // nothing to test here
  }

  /**
   * We're trying to determine the type of a variable. a) If the variable is
   * already defined in another rudi file, it is fully specified, and all type
   * information is already stored with it. b)
   */
  @Override
  public void visitNode(ExpUVariable node) {
    if (node.type != null && ! node.type.isUnspecified()
        && mem.getVariableType(node.content) == null) {
      mem.addVariableDeclaration(node.fullexp, node.type);
      return;
    }
    node.type = mem.getVariableType(node.fullexp);
    // we could have sth like Introduction, that is an undeclared rdf class
    RdfClass cl = mem.getProxy().fetchClass(node.content);
    if (cl != null) {
      node.type = new Type(cl.toString());
      if (!mem.variableExists(node.content)) {
        node.content = "\"" + node.content + "\"";
      }
    }
    // Make sure an external class requirement is registered if variable is
    // not defined in this class
    mem.getVariableOriginClass(node.fullexp);
  }

  /* **********************************************************************
   * Nodes without special treatment
   * ********************************************************************* */

  @Override
  public void visitNode(StatPropose node) {
    node.arg.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visitNode(StatReturn node) {
    if (node.returnExp != null) {
      node.returnExp.visit(this);
      if (mem.variableExists(node.returnExp.fullexp)){
        rudi.typeError("Return used with a rule name that also is a variable, I will use the rule functionality.", node);
      }
    }
  }

  @Override
  public void visitNode(StatSwitch node) {
    node.condition.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visitNode(ExpNew node) {
    if (node.construct != null) {
      if(node.construct instanceof ExpUFuncCall){
        ((ExpUFuncCall)node.construct).newexp = true;
      }
      node.construct.visit(this);
      // the type is the (java) object created
      node.type = node.construct.type;
    }
  }

  @Override
  public void visitNode(StatExpression node) {
    node.expression.visit(this);
  }

}
