/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Utils.isBooleanOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.compiler.Mem;
import de.dfki.mlt.rudimant.compiler.RudimantCompiler;
import de.dfki.mlt.rudimant.compiler.Type;
import de.dfki.mlt.rudimant.compiler.TypeException;

/**
 * this visitor calculates the types of nodes and checks whether the types are
 * okay
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VisitorType implements RudiVisitor {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private Mem mem;

  boolean typeErrorFatal;

  /** use this to report a type checking error; it will be handled according to
   *  the set typeCheck parameter
   *
   * @param errorMessage
   * @param node the tree node where the error occured
   */
  public void typeError(String errorMessage, RudiTree node) {
    String newErrorMessage = node.getLocation() + " " + errorMessage;
    mem.registerError(errorMessage, node.getLocation());

    if (typeErrorFatal) {
      // throw a real Exception
      throw new TypeException(newErrorMessage);
    } else {
      // just set a warning into the logger
      logger.error(newErrorMessage);
    }
  }

  /** use this to report a type checking warning
   *
   * @param errorMessage
   * @param node the tree node where the error occured
   */
  public static void typeWarning(String errorMessage, RudiTree node) {
    // just set a warning into the logger
    logger.warn(node.getLocation() + " " + errorMessage);
  }

  public VisitorType(Mem m, boolean errorsFatal) {
    mem = m;
    typeErrorFatal = errorsFatal;
  }

  public void visitNode(RudiTree node) {
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
          typeError("Expression with unknown type: " + node.fullexp, node);
        } else {
          typeWarning("propagating " + node.right.type + " to unknown left part: "
              + node.fullexp, node);
          // propagate type from the right branch to the left
          node.left.propagateType(node.right.type);
        }
      } else if (node.right.type.isUnspecified()) {
        typeWarning("propagating " + node.left.type + " to unknown right part: "
            + node.fullexp, node);
        // propagate type from the left branch to the right
        node.right.propagateType(node.left.type);
      } else {
        // check type compatibility
        Type type = node.left.type.unifyTypes(node.right.type);
        if (type == null) {
          typeError("Incompatible types in " + node + ": "
                  + node.left.type + " vs. " + node.right.type, node);
        }
      }
    }
    // may be wrong if type unification failed
    node.type = node.left.type;
  }

  /**
   * This has various aspects.
   * a) if there is a declaration, and the variable is already defined, that
   *    should be either a warning or an error.
   * b) if the right side has a non-empty type
   *   1) if the variable is defined, has it the right type? If it has no
   *      type, set it to the type of the right side.
   *   2) if it is not defined, define it and set type
   * c) if the variable has a type, and the right side has not, push it down
   *    the right branch.
   *
   * TODO: GIVEN MY LAST WORK ON TYPE COMPATIBILITY IN ASSIGNMENT, I DOUBT THAT
   * UNIFYTYPES IS THE RIGHT THING TO DO. NEEDS A LOT OF UNIT TESTS FIRST
   *
   * TODO: add the case where the assigned expression is a collection literal
   * This means that as well the outer as the inner types of the variable and
   * expression have to match.
   */
  @Override
  public void visitNode(ExpAssignment node) {
    node.right.visit(this);
    if (node.right.type.isVoid())
      typeError("Void can not be assigned", node);
    node.left.visit(this);

    if (node.right instanceof ExpVariable
        && ! mem.variableExists(node.right.toString())) {
      typeError("assigning the value of a non-existing variable "
          + node.right + "to " + node.left, node);
    }

    // If the type on the left side is more specific than the one on the right,
    // a cast must be applied during generation. if it is less specific, no
    // special measures must be taken.
    // if unifyTypes returns null, the types are incompatible
    // if one of them is null, unifyTypes will return the other type
    Type mergeType = node.left.type.unifyTypes(node.right.type);
    if (mergeType == null) {
      if (node.left.type.isBool()) {
        // in that case, we assume that this should be a test for existence
        mergeType = new Type("boolean");
        node.right = node.right.ensureBoolean();
      } else {
        if ((node.left instanceof ExpFieldAccess) &&
            node.right instanceof ExpSingleValue &&
            ((ExpSingleValue)node.right).content.equals("null")) {
          // this is a "clear" operation, to be resolved later.
          mergeType = node.left.type;
        } else {
          mergeType = Type.getNoType();
          typeError("Incompatible types in assignment: "
              + node.left.type.getRep() + " := "
              + node.right.type.getRep(), node);
        }
      }
    }
    node.type = mergeType;
    if (node.right.type.isUnspecified()) {
      node.right.propagateType(node.type);
    }
    if (node.left.type.isUnspecified()) {
      node.left.propagateType(node.type);
    }
  }


  private boolean collectTerms() {
    return (activeInfo != null && ! ruleIfSuspended);
  }

  private void handleRuleLogging(ExpBoolean node) {
    // handle case of ExpBoolean which is possibly a base term
    if (collectTerms()) {
      if (isBooleanOperator(node.operator)) {
        activeInfo.addOp(node.operator);
      } else {
        activeInfo.addBaseTerm(node.fullexp);
        ruleIfSuspended = true;
      }
    }
  }

  private void visitExpBoolChild(RudiTree node) {
    boolean oldSuspendState = ruleIfSuspended;
    if (collectTerms() && ! (node instanceof ExpBoolean)) {
      activeInfo.addBaseTerm(node.fullexp);
      ruleIfSuspended = true;
    }
    node.visit(this);
    ruleIfSuspended = oldSuspendState;
  }

  /*
   * In principle the same as ExpArithmetic, with boolean only. The one
   * difference is that there are unary expressions which serve as boolean
   * expressions and later have to be turned into proper boolean expressions,
   * either by calling the right 0-ary method, or comparing with zero or null.
   */
  /** In case activeInfo is not null and ruleIfSuspended is false, we have to
   *  check if the operator is one of &&, || or !, in which case we add the
   *  operator to the infix representation in the rule info and proceed
   *  collecting base terms.
   *
   *  Otherwise, we have to do some things:
   *   1. add the base term to the rule info
   *   2. set ruleIfSuspended to true before descending
   */
  @Override
  public void visitNode(ExpBoolean node) {
    boolean oldSuspendState = ruleIfSuspended;
    // first handle case of ExpBoolean which is a base term
    handleRuleLogging(node);

    // the operator is one of &&, ||, !, if one of the children is not an
    // ExpBoolean, term collection has to be suspended and the term added
    visitExpBoolChild(node.left);
    if (node.right != null) {
      visitExpBoolChild(node.right);
      if (isBooleanOperator(node.operator)) {
        node.right = node.right.ensureBoolean();
        node.left = node.left.ensureBoolean();
      }
    } else {
      // we do this always, because if the boolean expression has only one
      // side it may have no operator
      node.left = node.left.ensureBoolean();
    }
    ruleIfSuspended = oldSuspendState;
  }

  @Override
  public void visitNode(ExpCast node) {
    visitNode(node.expression);
    Type mergeType = node.type.unifyTypes(node.expression.type);
    if (mergeType == null) {
      typeError("Incompatible types : " + node.expression.type
          + " casted to " + node.type, node);
    }
    node.expression.type = node.type;
  }

  public static ExpSingleValue degradeToString(ExpVariable variable){
    return variable.fixFields(new ExpSingleValue(variable.toString(), "String"));
  }

  /**
   * This should have type "DialogueAct" already, which should be a constant
   * What we have to do is finding out which tokens are actual variables and
   * degrading all others to String (so we can infer how to represent them in
   * generation)
   */
  @Override
  public void visitNode(ExpDialogueAct node) {
    if (node.daType instanceof ExpVariable) {
      if (!mem.variableExists(((ExpVariable) node.daType).content)) {
        node.daType = degradeToString((ExpVariable) node.daType);
      }
    }
    node.daType.visit(this);
    if (node.proposition instanceof ExpVariable) {
      if (!mem.variableExists(((ExpVariable) node.proposition).content)) {
        node.proposition = degradeToString((ExpVariable) node.proposition);
      }
    }
    node.proposition.visit(this);
    int i = 0;
    for (RTExpression e : node.exps) {
      if (e instanceof ExpVariable) {
        if (!mem.variableExists(((ExpVariable) e).content)) {
          node.exps.set(i, degradeToString((ExpVariable) e));
        }
      }
      e.visit(this);
      i++;
    }
  }

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
    Type unified = node.thenexp.getType().isUnspecified()
        ?	node.thenexp.getType().unifyTypes(node.elseexp.getType())
            : node.elseexp.getType();
    if (unified == null) {
      typeError(node.fullexp + ": type of then and else differ: "
          + node.thenexp.getType().toString() + " vs. "
          + node.elseexp.getType().toString(), node);
      unified = Type.getNoType();
    }
    node.type = unified;
  }

  @Override
  public void visitNode(ExpLambda node) {
    mem.enterEnvironment(node);
    Type[] parTypes = new Type[node.parameters.size()+ 1];
    int i = 1;
    for(String arg : node.parameters){
      // TODO: might be necessary to set _castRequired to true in all parTypes
      mem.addVariableDeclaration(arg, node.parType);
      parTypes[i++] = node.parType;
    }
    if (node.body instanceof RTExpression) {
      RTExpression exp = (RTExpression)node.body;
      visitNode(exp);
      parTypes[0] = exp.getType();
      node.type = Type.getComplexType("Function", parTypes);
    } else {
      visitNode((StatAbstractBlock)node.body);
    }
    mem.leaveEnvironment(node);
  }

  private boolean ruleIfSuspended = false;
  private RuleInfo activeInfo = null;

  @Override
  public void visitNode(StatGrammarRule node) {
    node.toplevel = mem.enterRule(node.label, node.getLocation());
    activeInfo = mem.getCurrentRuleInfo();
    node.ruleId = activeInfo.getId();
    // we step down into a new environment (later turned to a method) whose
    //  variables cannot be seen from the outside
    if (node.toplevel) {
      mem.enterEnvironment(node);
    }
    StatIf ifNode = node.ifstat;
    ifNode.condition.visit(this);
    activeInfo = null;
    ifNode.statblockIf.visit(this);
    if (ifNode.statblockElse != null) {
      ifNode.statblockElse.visit(this);
    }
    ifNode.condition = ifNode.condition.ensureBoolean();
    if (node.toplevel) {
      mem.leaveEnvironment(node);
    }

    mem.leaveRule();
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    // we step down into a new environment (a block, possibly method block)
    // whose variables cannot be seen from the outside
    if (node.braces) {
      mem.enterEnvironment(node);
    }
    for (RTStatement t : node.statblock) {
      t.visit(this);
    }
    if (node.braces) {
      mem.leaveEnvironment(node);
    }
  }

  /**
   * Short version of for, standard type: for(type a : iterable<varType>) {}
   */
  @Override
  public void visitNode(StatFor2 node) {
    node.initialization.visit(this);
    Type innerIterableType = Type.getNoType();
    if (! node.initialization.type.isUnspecified()) {
      innerIterableType = node.initialization.getInnerType();
    }
    if (innerIterableType.isUnspecified()) {
      typeError("Iterable for loop type is unknown or not generic, but: "
          + node.initialization.getType().toString(), node);
      innerIterableType = new Type("Object");
    }
    if (node.varType.isUnspecified()) {
      node.varType = innerIterableType;
    } else {
      Type mergeType = node.varType.unifyTypes(innerIterableType);
      if (mergeType == null) {
        if (innerIterableType.equals(new Type("Object"))) {
          // Then handle this as an implicit cast (but warn the user that it might crash)
          logger.trace(node.getLocation() + "  Implicit casting of list Object to "
              + node.varType.toString()
              + " in short for loop, be aware this might crash in Java ");
          /* typeWarning("Implicit casting of list Object to "
              + node.varType.toString()
              + " in short for loop, be aware this might crash in Java ", node); */
        } else {
          typeError("Incompatible types in short for loop: "
              + node.varType + " : " + innerIterableType, node);
          node.varType = innerIterableType;
        }
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
  public void visitNode(ExpListLiteral node) {
    // TODO check if the elements are all subtypes of the contained type of the
    // collection
    Type inner = node.type.getInnerType();
    for (RTExpression e : node.objects) {
      visitNode(e);
      inner = inner.unifyTypes(e.getType());
      if (inner == null) {
        typeError("Elements of list literal have no common type", node);
        return;
      }
    }
    node.type.setInnerType(inner);
    for (RTExpression e : node.objects) {
      if (e.getType().isUnspecified()) {
        e.propagateType(inner);
      }
    }
  }

  @Override
  public void visitNode(StatSetOperation node) {
    // First call type checks for components, then perform the possible local
    // tests: The parameter type of the set should be compatible with what's
    // added.
    node.left.visit(this);
    node.right.visit(this);
    if (! node.left.getType().isCollection()) {
      typeError("Left side of a set operation is not a set, but "
          + node.left.getType(), node);
      return;
    }
    Type inner = node.left.getInnerType();
    Type res = inner.unifyTypes(node.right.type);
    if (res == null) {
      typeError("Incompatible types in set operation: "
          + node.left.getType() + " <> " + node.right.type, node);
    }
  }

  /* **********************************************************************
   * Statements that add info to the binding table (var defs, fun defs)
   * ********************************************************************* */

  /** An explicit variable declaration, without assignment, just definition */
  @Override
  public void visitNode(StatVarDef node) {
    if (node.isDefinition && mem.variableExists(node.variable)) {
      typeError("Re-defined variable " + node.variable
          + " from " + mem.getVariableType(node.variable).toString()
          + " to " + node.type.toString() +
          ", keeping the old type", node);
    }
    if (mem.variableExists(node.variable)) {
      if (node.type.isUnspecified())
        node.type = mem.getVariableType(node.variable);
    } else {
      if (! node.type.isUnspecified())
        mem.addVariableDeclaration(node.variable, node.type);
      // mark as declaration
      node.isDefinition = true;
    }
    if (node.toAssign != null) {
      ExpVariable var =
          node.fixFields(new ExpVariable(node.variable, node.type));
      node.toAssign = node.fixFields(new ExpAssignment(var, node.toAssign));
      node.toAssign.visit(this);
      if (node.type.isUnspecified()) {
        node.type = node.toAssign.type;
      }
      if (! mem.variableExists(node.variable)) {
        mem.addVariableDeclaration(node.variable, var.type);
        // mark as declaration
        node.isDefinition = true;
      }
    }
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    mem.addFunction(node.name, node.return_type, node.calledUpon, node.partypes);
    if (node.block != null) {
      // The following variables (function parameters) are local to the method
      // block we now step into; we don't want them to be reachable them from
      // outside
      mem.enterEnvironment(node);
      for (int i = 0; i < node.parameters.size(); i++) {
        // add parameters to environment
        mem.addVariableDeclaration(node.parameters.get(i), node.partypes.get(i));
      }
      node.block.visit(this);
      mem.leaveEnvironment(node);
    }
  }

  /** Top-level field (variable) definition */
  @Override
  public void visitNode(StatFieldDef node) {
    node.varDef.visit(this);
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
   * @param var
   */
  ExpPropertyAccess treatRdfPropertyAccess(ExpFieldAccess node, Type currentType,
          ExpVariable var) {
    // only a literal: check if it is a property of clz, and update the
    // current type
    if (mem.getVariableType(var.content) != null &&
        mem.getVariableType(var.content).isString()) {
      // the literal represents a variable, so we can't determine the type of
      // the access
      return new ExpPropertyAccess(var.content, var, true, Type.getNoType(), false);
    }
    if (currentType.isDialogueAct()) {
      // the return type will be string, this is a call to getSlot
      return new ExpPropertyAccess(var.content, var, false, new Type("String"), true);
    }
    RdfClass clz = currentType.getRdfClass();
    String predUri = null;
    if (clz != null) {
      predUri = clz.fetchProperty(var.content);
    }
    // warning / error if property not found
    if (predUri == null) {
      typeError("No property found for " + var.content, node);
      return new ExpPropertyAccess(var.content, var, false, Type.getNoType(), false);
    }

    var.content = predUri; // replace plain name by URI
    int predType = clz.getPropertyType(predUri);
    // TODO: CONVERT XSD TYPES TO JAVA, WHERE POSSIBLE
    String typeName = null;
    Set<String> t = clz.getPropertyRange(predUri);
    if (t.isEmpty()) {
      // WARNING / error
      typeError("No range type defined for property " + predUri, node);
      if (var.getType() != null) {
        typeWarning("empty range: type defined instead: " + var.getType(),
            node);
      }
    } else {
      typeName = t.iterator().next();
      if (t.size() > 1) {
        // WARNING / error
        typeError("Multiple range types defined for property "
            + predUri + ", taking " + typeName, node);
      }
    }
    currentType = new Type(typeName);
    // TODO: this resulted in weird warnings if the property has the same name
    // 		as a local variable; this should be a "parameterized" property
    // access, where the name of the property is stored in the variable,
    // which seems too complicated anyway.
    /*
    if (var.getType() != null && !var.getType().equals(currentType)) {
      typeError("Overwriting type " + var.type
              + "  of partial field access for " + var.content + " to "
              + currentType, node);
    }*/

    // Set<Bla> vs Bla distinction, unfortunately, Java can not reason about
    // parameter types, so this must be Object
    boolean isFunctional = (predType & RdfClass.FUNCTIONAL_PROPERTY) != 0;
    if (! isFunctional) {
      //currentType = "Set<"+currentType+">";
      //currentType = new Type("Set<Object>");
      currentType = Type.getRdfComplexType("Set", currentType);
    }
    // the type of this is set to Object by default (not null)
    return new ExpPropertyAccess(var.content, var, false, currentType, isFunctional);
  }

  /**
   * How this should work: We start from the leftmost element, stored in the
   * first element of the list, and work our way down.
   *
   * TODO CHECK FOR PROPERTIES RANGING OVER XSD DATATYPES, ETC. ALL FUZZY STUFF
   */
  @Override
  public void visitNode(ExpFieldAccess node) {
    RTExpression currentNode = node.parts.get(0); // can not be empty
    currentNode.visit(this);
    // The type to which the next field access item is applied
    Type currentType = ((RTExpression) currentNode).type;
    // this is dangerous, and only works if this condition can not be
    // "interrupted"
    partOfFieldAccess = true;
    for (int i = 1; i < node.parts.size(); ++i) {
      currentNode = node.parts.get(i);
      if (currentType.isCollection()
          && currentNode instanceof ExpFuncCall
          && ! ((ExpFuncCall)currentNode).params.isEmpty()
          && ((ExpFuncCall)currentNode).params.get(0) instanceof ExpLambda) {
        ((ExpLambda)((ExpFuncCall)currentNode).params.get(0)).parType =
        		currentType.getInnerType();
      }
      // if this is a funccall performed on anything, tell the function the type it was called on
      if(currentNode instanceof ExpFuncCall) {
        ((ExpFuncCall)currentNode).calledUpon = currentType;
      }
      currentNode.visit(this);
      if (currentType.isRdfType()) {
        if (currentNode instanceof ExpVariable) {
          // only a literal, delegate this because it's complicated
          ExpPropertyAccess acc = treatRdfPropertyAccess(node, currentType,
              (ExpVariable) currentNode);
          node.parts.set(i, acc);
          currentType = acc.getType();
        } else if (currentNode instanceof RTExpression) {
          // could also be a method application, possibly, what else?
          currentType = ((RTExpression) currentNode).type;
        } else {
          currentType = Type.getNoType();
        }
      } else { // unknown or Java type, let's try with the accessor type
    	  // TODO: think about this. we definitely want this in case of
        //    currentNode being a function call, but if it is a variable we get
        //    either nothing or - worse - the type of some unrelated local variable;
    	  //		which other expressions need to be handled cautiously?
        if (currentNode instanceof RTExpression
            && !(currentNode instanceof ExpVariable)) {
          currentType = ((RTExpression) currentNode).type;
        } else {
          currentType = Type.getNoType();
        }
      }
      ((RTExpression) currentNode).type = currentType;
    }
    node.type = currentType; // the final result type
    partOfFieldAccess = false;
  }

  /**
   * TODO: Are the parameter types of the function checked against the actual
   * types? Is it necessary?
   */
  @Override
  public void visitNode(ExpFuncCall node) {
    // if there are generics involved in the computation of return type or parameter
    // types for this function, we need to resolve them first
    mem.resolveGenericTypes(node, this);

    // test whether the given parameters are of the correct type
    List<Type> partypes = new ArrayList<Type>();
    if(node.params != null) {
      if (node.params.size() == 2 && node.params.get(1) instanceof ExpLambda) {
        // Trick to explicitely find filter, contains, etc
        node.params.get(0).visit(this);
        partypes.add(node.params.get(0).getType());
        ((ExpLambda)node.params.get(1)).parType = node.params.get(0).getType().getInnerType();
        node.params.get(1).visit(this);
        partypes.add(node.params.get(1).getType());
      } else {
        for (RTExpression e : node.params) {
          e.visit(this);
          partypes.add(e.getType());
        }
      }
    }
    // if this was used in a new Expression, handle it accordingly
    if(node.newexp){
      node.type = new Type(node.content);
      return;
    }
    String o = mem.getFunctionOrigin(node.content, partypes);
    if (o != null) {
      node.realOrigin = o;
    }
    if (node.type == null) {
      // this returns null if there is no such function, and an unspecified type
      // if the return type is unknown.
      node.type = mem.getFunctionRetType(node.content, node.calledUpon, partypes);
    }
    if (node.type == null) {
      // TODO: MAYBE INTRODUCE A FLAG FOR STRICT CHECKING. FOR THE TIME BEING,
      // WE EXCLUDE ALL CALLS ON OBJECT THAT ARE ORDINARY JAVA CLASS INSTANCES
      if (node.calledUpon == null) {
        typeError("The function call to " + node.content
            + " refers to a function that wasn't declared", node);
      }
      node.type = Type.getNoType();
    }
  }

  /**
   * TODO: Do we have to check here if it's an RDF type?? or DialogueAct?? or is
   * this all clear? we might have to test whether Quiz is sth like that and
   * give the node a proper type; how to do that?
   */
  @Override
  public void visitNode(ExpSingleValue node) {
    // nothing to test here
    if (node.type == null)
      // TODO: can this ever happen? SingleValues are things like Strings, chars
      //       ints, floats, ... recognized = given a type by our parser
      node.type = Type.getNoType();
  }

  /**
   * We're trying to determine the type of a variable. a) If the variable is
   * already defined in another rudi file, it is fully specified, and all type
   * information is already stored with it. b)
   */
  @Override
  public void visitNode(ExpVariable node) {
    // get the type of the variable, if defined
    // TODO: is there a way to find out if we try to retrieve the value of an
    // undefined variable?
    node.type = mem.getVariableType(node.content);
    if (node.type == null) {
      // we could have sth like Introduction, that is an undeclared rdf class
      RdfClass cl = mem.getProxy().fetchClass(node.content);
      if (cl != null) {
        node.type = new Type(cl.toString());
        if (!mem.variableExists(node.content)) {
          node.content = "\"" + node.content + "\"";
        }
      } else {
        node.type = Type.getNoType();
      }
    }
  }

  @Override
  public void visitNode(ExpArrayAccess node) {
    node.index.visit(this);
    if (!new Type("int").equals(node.index.type)) {
      typeError("Array access with non-Integer", node);
    }
    node.array.visit(this);
    if (! node.array.type.isArray()) {
      typeError("Array access on non-array type", node);
    } else {
      node.type = node.array.type.getInnerType();
      if (node.type == null) {
        typeError("Type of array elements not specified", node);
      }
    }
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
  public void visitNode(StatTimeout node) {
    if (node.label != null) node.label.visit(this);
    node.time.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visitNode(StatReturn node) {
    if (node.returnExp != null) {
      node.returnExp.visit(this);
    }
  }

  @Override
  public void visitNode(StatSwitch node) {
    node.condition.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visitNode(ExpNew node) {
    if (node.params != null) {
      for (RTExpression param : node.params)
        param.visit(this);
    } else {
      if (node.type.getRdfClass() == null)
        typeError("new Rdf with unknown RDF class: " + node.type, node);
    }
  }

  @Override
  public void visitNode(StatExpression node) {
    node.expression.visit(this);
  }

}
