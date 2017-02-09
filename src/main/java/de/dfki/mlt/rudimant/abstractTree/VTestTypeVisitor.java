/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;
import java.util.ArrayList;

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
  public void visitNode(RudiTree node) {
    node.visit(this);
  }

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
        // unknown type to the left
        if (null == node.right.type) {
          // unknown type on both branches
          rudi.typeError("Expression with unknown type: " + node.right, node);
        } else {
          // propagate type from the right branch to the left
          node.left.propagateType(node.right.type);
        }
      } else if (null == node.right.type) {
        //
        node.right.propagateType(node.left.type);
      } else {
        // check type compatibility
        String type = mem.unifyTypes(node.left.type, node.right.type);
        if (type == null) {
          rudi.typeError("Incompatible types in " + node + ": "
              + node.left.type + " vs. " + node.right.type, node);
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
    node.right.type = mem.convertXsdType(node.right.type);
    node.left.visit(this);
    node.left.type = mem.convertXsdType(node.left.type);

    // is this a variable declaration for an already existing variable?
    // When we get here, if node.declaration is true, then node.type has a
    // non-null value, and vice versa
    if (node.declaration) {
      node.type = mem.checkRdf(node.type);
      if (mem.variableExists(node.left.toString())) {
        rudi.typeError("Re-declaration of existing variable " + node.left, node);
      } else {
        node.left.type = node.type; // the type of the declaration is in this node
      }
      mem.addVariableDeclaration(((UVariable) node.left).content,
          node.left.type, mem.getClassName());
      if (node.right.type == null) {
        node.right.propagateType(node.type);
      }
    } else if ((node.left instanceof UVariable
        && !mem.variableExists(node.left.toString()))) {
      node.declaration = true;
      // node.type is null, and variable does not exist, so no type.
      // now consolidate types
      if (node.right.type == null) {
        // please, never assign null as a type, because that is no valid java type
        // and will crash for sure
        node.right.type = "Object";
        // TODO: ? node.right.propagateType(node.type);
      }
      node.type = node.left.type = node.right.type;
      mem.addVariableDeclaration(((UVariable) node.left).content,
          node.left.type, mem.getClassName());
    }
    if (node.right instanceof UVariable && !mem.variableExists(node.right.toString())) {
      rudi.typeError("Assignment of a value of a non-existing variable " + node.right + "to " + node.left, node);
    }


    // If the type on the left side is more specific that the one on the right,
    // a cast must be applied during generation. if it is less specific, no
    // special measures must be taken.
    // if unifyTypes returns null, the types are incompatible
    // if one of them is null, unifyTypes will return the other type
    String mergeType = mem.unifyTypes(node.left.type, node.right.type);
    if (mergeType == null) {
      mergeType = "Object";
      rudi.typeError("Incompatible types in assignment: "
          + node.left.type + " := " + node.right.type, node);
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
    node.rule = mem.getCurrentRule();
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);
      if(! Mem.isPODType(node.left.getType())
          && ! Mem.isPODType(node.right.getType())) {
        switch(node.operator){
          case "==":
            node.operator = "isEqual(";
            break;
          case "<":
            node.operator = "isSmaller(";
            break;
          case ">":
            node.operator = "isGreater(";
            break;
          case "<=":
            node.operator = "isSmallerEqual(";
            break;
          case ">=":
            node.operator = "isGreaterEqual(";
            break;
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
   * What we have to do is finding out which tokens are actual variables and
   * degrading all others to String (so we can infer how to represent them in
   * generation)
   */
  @Override
  public void visitNode(ExpDialogueAct node) {
    if (node.daType instanceof UVariable) {
      if (!mem.variableExists(((UVariable) node.daType).content)) {
        node.daType = mem.degradeToString((UVariable) node.daType);
      }
    }
    if (node.proposition instanceof UVariable) {
      if (!mem.variableExists(((UVariable) node.proposition).content)) {
        node.proposition = mem.degradeToString((UVariable) node.proposition);
      }
    }
    int i = 0;
    for (RTExpression e : node.exps) {
      if (e instanceof UVariable) {
        if (!mem.variableExists(((UVariable) e).content)) {
          node.exps.set(i, mem.degradeToString((UVariable) e));
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
    if (mem.unifyTypes(node.thenexp.getType(), node.elseexp.getType()) == null) {
      rudi.typeError(node.fullexp
          + " is a conditional expression where the else expression "
          + "does not have the same type as the right expression!\n("
          + "comparing types " + node.thenexp.getType() + " on left and "
          + node.elseexp.getType() + " on right)", node);
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
    mem.enterClass(rudi.getClassName());
    for (RudiTree t : node.rules) {
      t.visit(this);
    }
    if(mem.getToplevelCalls(rudi.getClassName()) != null){
      for (String s : mem.getToplevelCalls(rudi.getClassName())) {
        if(mem.getNeededClasses(s) != null){
          for (String n : mem.getNeededClasses(s)) {
            mem.needsClass(rudi.getClassName(), n);
          }
        }
      }
    }
    // do not leave the environment, we are still in it! (but remember to leave
    // it once the generation is done)
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
//    if (null != rudi.getConstructorArgs()
//            && !rudi.getConstructorArgs().isEmpty()) {
//      int i = 0;
//      for (String a : rudi.getConstructorArgs().split(",")) {
//        if (i > 0) {
//          conargs += ", ";
//        }
//        conargs += a.trim().split(" ")[1];
//        i++;
//      }
//    }
    mem.addImport(node.name, conargs);
    logger.info("Processing import " + node.content);
    rudi.processImport(node.content);
  }

  @Override
  public void visitNode(StatListCreation node) {
    if (!(node.objects == null)) {
      for (RTExpression e : node.objects) {
        this.visitNode(e);
      }
      String type = node.listType;
      if (type != null) {
        String elementType = type.substring(
            type.indexOf("<") + 1, type.indexOf(">"));
        if (mem.unifyTypes(elementType, node.objects.get(0).getType()) == null) {
          rudi.typeError("Found a list creation where the list type"
              + " doesn't fit its objects' type: " + elementType
              + " vs " + node.objects.get(0).getType(), node);
        }
        mem.addVariableDeclaration(node.variableName, type, node.origin);
      } else {
        node.listType = "List<" + node.objects.get(0).getType() + ">";
        mem.addVariableDeclaration(node.variableName, type, node.origin);
      }
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
    node.return_type = mem.checkRdf(node.return_type);
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

  // set this variable to tell error handling that we are in a function invoked
  // on a java object, so we probably do not wanna throw an error here
  boolean partOfFieldAccess = false;

  /** treat the case that an RDF is accessed with a label, which can result in
   *  a lot of different things:
   *  setValue, getValue, setSingleValue, getSingleValue, has
   * @param node
   * @param currentType
   * @param label
   */
  UPropertyAccess treatRdfPropertyAccess(UFieldAccess node, String currentType,
      UVariable var) {
    // only a literal: check if it is a property of clz, and update the
    // current type
    if ("String".equals(mem.getVariableType(var.content))) {
      // the literal represents a variable, so we can't determine the type of
      // the access
      // TODO: Maybe return "Object" as type, should be correct in most cases.
      return new UPropertyAccess(var, true, null, false);
    }
    if (DIALOGUE_ACT_TYPE.equals(currentType)) {
      // the return type will be string, this is a call to getSlot
      return new UPropertyAccess(var, false, "String", true);
    }
    RdfClass clz = mem.getProxy().getClass(currentType);
    String predUri = clz.fetchProperty(var.content);
    // warning / error if property not found
    if (predUri == null) {
      rudi.typeError("No property found for " + var.content, node);
      return new UPropertyAccess(var, false, null, false);
    }

    var.content = predUri; // replace plain name by URI
    int predType = clz.getPropertyType(predUri);
    // TODO: Set<Bla> vs Bla distinction, not that i know what to do with it.
    boolean isFunctional = (predType & RdfClass.FUNCTIONAL_PROPERTY) != 0;
    // TODO: CONVERT XSD TYPES TO JAVA, WHERE POSSIBLE
    currentType = clz.getPropertyRange(predUri);
    if (currentType == null) {
      // WARNING / error
      rudi.typeError("No range type defined for property "
          + predUri, node);
      if (var.getType() != null) {
        rudi.typeWarning("empty range: type defined instead: "
            + var.getType(), node);
      }
    }
    if (var.getType() != null && ! var.getType().equals(currentType)) {
      rudi.typeError("Overwriting type " + var.type +
          "  of partial field access for " + var.content + " to " +
          currentType, node);
    }
    // TODO: an access will always return sth of type Object, so to not get null
    // I'll set the type of this to Object by default
    return new UPropertyAccess(var, false, currentType, isFunctional);
  }

  /**
   * How this should work: We start from the leftmost element, stored in the
   * first element of the list, and work our way down.
   *
   * TODO CHECK FOR PROPERTIES RANGING OVER XSD DATATYPES, ETC. ALL FUZZY STUFF
   */
  @Override
  public void visitNode(UFieldAccess node) {
    String currentType = null;

    RudiTree currentNode = node.parts.get(0); // can not be empty
    currentNode.visit(this);
    // The type to which the next field access item is applied
    currentType = ((RTExpression)currentNode).type;
    // this is dangerous, and only works if this condition can not be
    // "interrupted"
    partOfFieldAccess = true;
    for(int i = 1; i < node.parts.size(); ++i) {
      currentNode = node.parts.get(i);
      currentNode.visit(this);
      if (Mem.isRdfType(currentType)) {
        if (currentNode instanceof UVariable) {
          // only a literal, delegate this because it's complicated
          UPropertyAccess acc =
              treatRdfPropertyAccess(node, currentType, (UVariable)currentNode);
          node.parts.set(i, acc);
          currentType = acc.getType();
        } else if (currentNode instanceof RTExpression) {
          // could also be a method application, possibly, what else?
          currentType = ((RTExpression)currentNode).type;
        } else {
          currentType = null;
        }
      } else { // unknown or Java type, let's try with the accessor type
        if (currentNode instanceof RTExpression) {
          currentType = ((RTExpression)currentNode).type;
        } else {
          currentType = null;
        }
      }
    }
    node.type = currentType == null? "Object" : currentType; // the final result type
    partOfFieldAccess = false;
  }

  /**
   * TODO: Are the parameter types of the function checked against the actual
   * types? Is it necessary?
   */
  @Override
  public void visitNode(UFuncCall node) {
    String o = mem.getFunctionOrigin(node.content);
    if (o != null && !rudi.getClassName().equals(o)) {
      mem.needsClass(mem.getCurrentTopRule(), o);
      node.realOrigin = o;
    }
    if (node.type == null) {
      node.type = mem.getFunctionRetType(node.content);
    }
    // test whether the given parameters are of the correct type
    ArrayList<String> partypes = new ArrayList<String>();
    for (RTExpression e : node.exps) {
      e.visit(this);
      partypes.add(e.getType());
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
    RdfClass cl = mem.getProxy().fetchClass(node.content);
    if (cl != null) {
      node.type = cl.toString();
      if(!mem.variableExists(node.content)){
        node.content = "\"" + node.content + "\"";
      }
    }

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
    if (!mem.isExistingRule(node.lit) && node.toRet != null){
      node.toRet.visit(this);
    }
  }

  @Override
  public void visitNode(StatSwitch node) {
    node.condition.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visitNode(ExpNew node) {
    if (node.construct == null) {
      // insert proper rdf type
      node.type = mem.checkRdf(node.type);
    } else {
      String f = node.fullexp;
      // the type is the java object created
      // TODO: shouldn't that be indexOf("w") + 1 ??
      node.type = f.substring(f.indexOf("w"), f.indexOf("("));
      node.construct.visit(this);
    }
  }
}
