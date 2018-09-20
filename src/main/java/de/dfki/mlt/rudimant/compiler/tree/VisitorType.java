/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.common.ErrorInfo.ErrorType.*;
import static de.dfki.mlt.rudimant.compiler.Type.*;
import static de.dfki.mlt.rudimant.compiler.Utils.isBooleanOperator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudimant.common.RuleInfo;
import de.dfki.mlt.rudimant.compiler.*;

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

  private TokenHandler _th;

  private String getFullText(RudiTree node) {
    return _th.getFullText(node);
  }

  /** use this to report a type checking error; it will be handled according to
   *  the set typeCheck parameter
   *
   * @param errorMessage
   * @param node the tree node where the error occured
   */
  public void typeError(String errorMessage, RudiTree node) {
    String newErrorMessage = node.getLocation() + " " + errorMessage;
    mem.registerError(errorMessage, node.getLocation(), ERROR);

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
   * @param warnMessage
   * @param node the tree node where the warning occured
   */
  public void typeWarning(String warnMessage, RudiTree node) {
    String newWarningMessage = node.getLocation() + " " + warnMessage;
    mem.registerError(warnMessage, node.getLocation(), WARNING);
    // just set a warning into the logger
    logger.warn(newWarningMessage);
  }

  public void percolateError(Type type, RudiTree node) {
    logger.error("Why didn't this type percolate up? "
        + getFullText(node) + " " + type);
  }

  public VisitorType(Mem m, boolean errorsFatal, TokenHandler th) {
    mem = m;
    typeErrorFatal = errorsFatal;
    _th = th;
  }

  public void visit(RudiTree node) {
    node.visit(this);
  }

  @SuppressWarnings("serial")
  /** This is a code conversion function which turns the value of an expression
   *  into a String
   * @param ex the expression to be converted
   * @return
   */
  private RTExpression convertToString(RTExpression ex) {
    ExpFuncCall result;
    String funName = ex.type.getStringConversionFunction();
    result = new ExpFuncCall(funName,
        new ArrayList<RTExpression>(){{add(ex);}}, false);
    result.type = new Type("String");
    // is this a method rather than a function call?
    if (funName.charAt(0) == '.') result.calledUpon = ex.type;
    ex.fixFields(result);
    return result;
  }


  public Type checkArithmeticTypes(ExpArithmetic node) {
    Type ltype = node.left.type;
    Type type = ltype;
    Type rtype = node.right.type;
    if (ltype.isUnspecified()) {
      // unknown type to the left
      if (rtype.isUnspecified()) {
        // unknown type on both branches
        typeError("Expression with unknown type: " + getFullText(node), node);
      } else {
        typeWarning("propagating " + rtype + " to unknown left part: "
            + getFullText(node), node);
        // propagate type from the right branch to the left
        node.left.propagateType(rtype, this);
        type = rtype;
      }
    } else if (rtype.isUnspecified()) {
      typeWarning("propagating " + ltype + " to unknown right part: "
          + getFullText(node), node);
      // propagate type from the left branch to the right
      node.right.propagateType(ltype, this);
      type = ltype;
    } else {
      // check type compatibility
      type = ltype.unifyTypes(rtype);
      if (type == null && "+".equals(node.operator)) {
        // cast to string if we think we now how
        if (ltype.isString() && rtype.isStringConvertible()) {
          type = ltype;
          node.right = convertToString(node.right);
        }
        if (rtype.isString() && ltype.isStringConvertible()) {
          type = rtype;
          node.left = convertToString(node.left);
        }
      }
      if (type == null) {
        typeError("Incompatible types in " + node + ": " + ltype +
            " vs. " + rtype, node);
        type = ltype;
      }
    }
    return type;
  }


  /**
   * If that is a binary expression, the resulting type should be the more
   * specific of both. If they are incompatible there should be a warning. Maybe
   * the type could be pushed down if there is only a non-empty type on one
   * branch
   */
  @Override
  public void visit(ExpArithmetic node) {
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);
      node.type = checkArithmeticTypes(node);
    } else {
      node.type = node.left.type;
    }
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
  public void visit(ExpAssignment node) {
    node.right.visit(this);
    if (node.right.type.isVoid())
      typeError("Void can not be assigned", node);
    node.left.visit(this);

    if (node.right instanceof ExpIdentifier
        && ! mem.variableExists(((ExpIdentifier)node.right).content)) {
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
      } else if ((node.left instanceof ExpFieldAccess) &&
          node.right instanceof ExpLiteral &&
          ((ExpLiteral)node.right).content.equals("null")) {
        // this is a "clear" operation, to be resolved later.
        mergeType = node.left.type;
      } else if (node.left.type.isString()
          && node.right.type.isStringConvertible()) {
        // cast to string if we think we now how
        node.right = convertToString(node.right);
        mergeType = node.left.type;
      } else {
        mergeType = getNoType();
        typeError("Incompatible types in assignment: "
            + node.left.type.getRep() + " := "
            + node.right.type.getRep(), node);
      }
    }
    if (mergeType.isUnspecified() && node.right instanceof ExpListLiteral) {
      List<Type> inner = new ArrayList<>();
      Type mergeInner = mergeType.getInnerType();
      inner.add(mergeInner != null ? mergeInner : getNoType());
    }
    node.type = mergeType;
    if (! node.right.type.equals(node.type)) {
      node.right.propagateType(node.type, this);
    }
    if (! node.left.type.equals(node.type)) {
      node.left.propagateType(node.type.copyNoCast(), this);
    }
  }


  /** This returns true if we are currently handling a boolean expression that
   *  is the condition of a rule. We treat this case in a special way since we
   *  have to collect the operators and the base terms of the expression for
   *  logging the live system
   *
   * @return true if we are handling the condition of a rule
   */
  private boolean collectTerms() {
    return (activeInfo != null && ! ruleIfSuspended);
  }

  /** If we are currently handling a boolean expression that is the condition
   *  of a rule, we collect the operators and the base terms of the expression
   *  for logging in the live system
   */
  private void handleRuleLogging(ExpBoolean node) {
    // handle case of ExpBoolean which is possibly a base term
    if (collectTerms()) {
      if (isBooleanOperator(node.operator)) {
        activeInfo.addOp(node.operator);
      } else {
        activeInfo.addBaseTerm(getFullText(node));
        ruleIfSuspended = true;
      }
    }
  }

  private void visitExpBoolChild(RudiTree node) {
    boolean oldSuspendState = ruleIfSuspended;
    if (collectTerms() && ! (node instanceof ExpBoolean)) {
      activeInfo.addBaseTerm(getFullText(node));
      ruleIfSuspended = true;
    }
    node.visit(this);
    ruleIfSuspended = oldSuspendState;
  }

  /** TODO: the following gives us #12 in full beauty.
   *  we don't do this, it seems conceptually wrong and generates weird code.
   */
  private void ensureBooleanGeneral(ExpBoolean node) {
    RTExpression check = null;
    if (node.right instanceof ExpFieldAccess)
      check = node.right.ensureBoolean();
    if (node.left instanceof ExpFieldAccess) {
      if (check != null)
        check = node.fixFields(new ExpBoolean(node.left.ensureBoolean(), check, "&&", true));
      else
        check = node.left.ensureBoolean();
    }
    if (check != null) {
      ExpBoolean newRight = node.fixFields(new ExpBoolean(node.left, node.right, node.operator, true));
      node.left = check;
      node.right = newRight;
      node.operator = "&&";
    }
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
  public void visit(ExpBoolean node) {
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
      } // else ensureBooleanGeneral(node);
    } else {
      // we do this always, because if the boolean expression has only one
      // side it may have no operator
      if (! node.synthetic) {
        node.left = node.left.ensureBoolean();
      }
    }
    ruleIfSuspended = oldSuspendState;
  }

  @Override
  public void visit(ExpCast node) {
    visit(node.expression);
    Type mergeType = node.type.unifyTypes(node.expression.type);
    if (mergeType == null) {
      typeError("Incompatible types : " + node.expression.type
          + " casted to " + node.type, node);
    }
    node.expression.type = node.type;
  }

  public RTExpression degradeToString(RTExpression expr, ExpDialogueAct node){
    RTExpression res = expr;
    if (expr instanceof ExpIdentifier) {
      ExpIdentifier variable = (ExpIdentifier) expr;
      if (!mem.variableExists(variable.content)) {
        res = new ExpLiteral(variable.content,
            //variable.toString(),
            "String");
        variable.fixFields(res);
      }
    }
    res.visit(this);
    if (! res.type.isString()) {
      if (res.type.isStringConvertible()) {
        // cast to string if we think we now how
        res = convertToString(res);
      } else {
        typeError(getFullText(node) + ": DialogueAct argument "
            + getFullText(res) + " not a string: " + res.type, node);
      }
    }
    return res;
  }

  /**
   * This should have type "DialogueAct" already, which should be a constant
   * What we have to do is finding out which tokens are actual variables and
   * degrading all others to String (so we can infer how to represent them in
   * generation)
   */
  @Override
  public void visit(ExpDialogueAct node) {
    node.daType = degradeToString(node.daType, node);
    node.proposition = degradeToString(node.proposition, node);
    int i = 0;
    for (RTExpression e : node.exps) {
      node.exps.set(i, degradeToString(e, node));
      i++;
    }
  }

  /**
   * This might push the boolean type downwards for the boolexp, but that's not
   * necessary because the bool expression already knows.
   */
  @Override
  public void visit(ExpConditional node) {
    node.boolexp.visit(this);
    node.boolexp = node.boolexp.ensureBoolean();
    node.thenexp.visit(this);
    node.elseexp.visit(this);
    Type unified = node.thenexp.getType().isUnspecified()
        ?	node.thenexp.getType().unifyTypes(node.elseexp.getType())
            : node.elseexp.getType();
    if (unified == null) {
      typeError(getFullText(node) + ": type of then and else differ: "
          + node.thenexp.getType().toString() + " vs. "
          + node.elseexp.getType().toString(), node);
      unified = getNoType();
    }
    node.type = unified;
  }

  @Override
  public void visit(ExpLambda node) {
    mem.enterEnvironment(node);
    // node.type is a Function type
    Iterator<Type> parTypes = node.type.getParameterTypes();
    Type returnType = node.type.getReturnType(); // return type;
    for(String arg : node.parameters){
      // set castRequired to true in all parTypes that are XSD/RDF, since we
      // have to pass Object for them
      Type parType = parTypes.next();
      if (parType.isXsdType() || parType.isStrictRdfType())
        parType.setCastRequired();
      // an arg always overwrites any defined var
      mem.addVariableDeclaration(arg, parType);
    }
    if (node.body instanceof RTExpression) {
      RTExpression exp = (RTExpression)node.body;
      visit(exp);
      returnType = exp.getType();
    } else {
      StatAbstractBlock block = (StatAbstractBlock)node.body;
      visit(block);
      // then the last statement of the block must be the return of some exp
      RudiTree last = block.statblock.get(block.statblock.size() - 1);
      if (last instanceof StatReturn) {
        returnType = ((StatReturn)last).returnExp.getType();
      } else {
        returnType = getNoType();
        typeError("Block used in functional expression doesn't end with a return", node);
      }
    }
    if (! returnType.equals(node.type.getReturnType())) {
      node.type.setReturnType(returnType);
    }
    mem.leaveEnvironment(node);
  }

  private boolean ruleIfSuspended = false;
  private RuleInfo activeInfo = null;

  @Override
  public void visit(StatGrammarRule node) {
    node.toplevel = mem.enterRule(node.label, node.getLocation());
    activeInfo = mem.getCurrentRuleInfo();
    node.ruleId = activeInfo.getId();
    // we step down into a new environment (later turned to a method) whose
    //  variables cannot be seen from the outside
    if (node.toplevel) {
      mem.enterEnvironment(node);
    }
    StatIf ifNode = node.ifstat;
    visitExpBoolChild(ifNode.condition);
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
  public void visit(StatAbstractBlock node) {
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
  public void visit(StatFor2 node) {
    mem.enterEnvironment(node);
    node.initialization.visit(this);
    Type innerIterableType = getNoType();
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
          logger.trace("{} Implicit casting of list Object to {} "
              + "in short for loop, be aware this might crash in Java "
              , node.getLocation(), node.varType.toString());
        } else {
          typeError("Incompatible types in short for loop: "
              + node.varType + " : " + innerIterableType, node);
          node.varType = innerIterableType;
        }
      }
    }
    mem.addVariableDeclaration(node.var.content, node.varType);
    node.statblock.visit(this);
    mem.leaveEnvironment(node);
  }

  /**
   * short for with decomposition : for ((a,b,c) : complex_iterable) {}
   */
  @Override
  public void visit(StatFor3 node) {
    // TODO: this is a bit more complicated; remember the types of the variables
    // that were declared in the condition
    for (String s : node.variables) {
      mem.addVariableDeclaration(s, new Type("Object"));
    }
  }

  @Override
  public void visit(ExpListLiteral node) {
    // TODO check if the elements are all subtypes of the contained type of the
    // collection
    Type inner = node.type.getInnerType();
    for (RTExpression e : node.objects) {
      visit(e);
      inner = inner.unifyTypes(e.getType());
      if (inner == null) {
        typeError("Elements of list literal have no common type", node);
        return;
      }
    }
    node.type.setInnerType(inner);
    for (RTExpression e : node.objects) {
      if (e.getType().isUnspecified()) {
        e.propagateType(inner, this);
      }
    }
  }

  @Override
  public void visit(StatSetOperation node) {
    // First call type checks for components, then perform the possible local
    // tests: The parameter type of the set should be compatible with what's
    // added.
    node.left.visit(this);
    node.right.visit(this);
    if (node.left.getType().isNumber() && node.right.getType().isNumber()) {
      // then this is a normal addition/subtraction, and should be turned into
      // an assignment to be treated properly
      ExpArithmetic s = node.fixFields(
          new ExpArithmetic(node.left, node.right, node.add? "+" : "-"));
      s.type = checkArithmeticTypes(s);
      node.left = node.fixFields(new ExpAssignment(node.left, s));
      node.left.type = s.type;
      node.right = null;
      return;
    }
    if (! node.left.getType().isCollection()) {
      typeError("Left side of a set operation is not a set, but "
          + node.left.getType(), node);
      return;
    }
    Type inner = node.left.getInnerType();
    if (inner != null) {
      Type res = inner.unifyTypes(node.right.type);
      if (res == null) {
        typeError("Incompatible types in set operation: "
            + node.left.getType() + " <> " + node.right.type, node);
      }
    }
  }

  /* **********************************************************************
   * Statements that add info to the binding table (var defs, fun defs)
   * ********************************************************************* */

  /** An explicit variable declaration, without assignment, just definition */
  @Override
  public void visit(StatVarDef node) {
    if (node.isDefinition) {
      if (mem.variableExistsLocally(node.variable)) {
        typeError("Re-defined variable " + node.variable
            + " from " + mem.getVariableType(node.variable).toString()
            + " to " + node.type.toString() +
            ", keeping the old type", node);
      } else {
        mem.addVariableDeclaration(node.variable, node.type);
      }
    }
    if (mem.variableExists(node.variable)) {
      if (node.type.isUnspecified())
        node.type = mem.getVariableType(node.variable);
    }

    if (node.toAssign != null) {
      ExpIdentifier var =
          node.fixFields(new ExpIdentifier(node.variable, node.type));
      node.toAssign = node.fixFields(new ExpAssignment(var, node.toAssign));
      node.toAssign.visit(this);
      Type mergeType = node.type.unifyTypes(node.toAssign.type);
      if (!node.type.equals(mergeType)) {
        node.type = mergeType;
      }
    }

    if (! mem.variableExists(node.variable)) {
      mem.addVariableDeclaration(node.variable, node.type);
      // mark as declaration
      node.isDefinition = true;
    }
  }

  @Override
  public void visit(StatMethodDeclaration node) {
    if (mem.functionDefined(node.name, node.function_type)) {
      typeError("redeclaring function " + node.name
          + " with new return type " + node.function_type.getReturnType()
          + ", was: " + mem.getFunction(node.name,
              node.function_type).getType().getReturnType(), node);
    } else {
      mem.addFunction(node.name, node.function_type);
    }
    Iterator<Type> parTypes = node.function_type.getParameterTypes();
    if (node.block != null) {
      // The following variables (function parameters) are local to the method
      // block we now step into; we don't want them to be reachable them from
      // outside
      mem.enterEnvironment(node);
      // add parameters to environment
      for (String par: node.parameters) {
        mem.addVariableDeclaration(par, parTypes.next());
      }
      node.block.visit(this);
      mem.leaveEnvironment(node);
    }
  }

  /** Top-level field (variable) definition */
  @Override
  public void visit(StatFieldDef node) {
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
  public void visit(StatIf node) {
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
  public void visit(StatWhile node) {
    node.condition.visit(this);
    node.block.visit(this);
    node.condition = node.condition.ensureBoolean();
  }

  /**
   * for (a;b;c) {}, only visit the sub-parts of this.
   */
  @Override
  public void visit(StatFor1 node) {
    mem.enterEnvironment(node);
    if (node.initialization != null) {
      // Koenig binding
      node.initialization.isDefinition = true;
      node.initialization.visit(this);
    }
    if (node.condition != null)
      node.condition.visit(this);
    if (node.increment != null)
      node.increment.visit(this);
    node.statblock.visit(this);
    node.condition = node.condition.ensureBoolean();
    mem.leaveEnvironment(node);
  }

  // set this variable to tell error handling that we are in a function invoked
  // on a java object, so we probably do not wanna throw an error here
  boolean partOfFieldAccess = false;

  /**
   * treat the case that an RDF is accessed with a label, which can result in a
   * lot of different things: setValue, getValue, setSingleValue,
   * getSingleValue, has
   *
   * TODO this is still ugly
   *
   * @param node
   * @param currentType
   * @param var
   */
  ExpPropertyAccess treatRdfPropertyAccess(ExpFieldAccess node, Type currentType,
          ExpIdentifier var) {
    // only a literal: check if it is a property of clz, and update the
    // current type
    if (mem.getVariableType(var.content) != null &&
        mem.getVariableType(var.content).isString()) {
      // the literal represents a variable, so we can't determine the type of
      // the access
      List<Type> subs = new ArrayList<>();
      subs.add(new Type("T"));
      Type paType = new Type("Set", subs); paType.setCastRequired();
      return new ExpPropertyAccess(var.content, var, true,
          paType, false);
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
      return new ExpPropertyAccess(var.content, var, false, getNoType(), false);
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
      currentType = getRdfComplexType("Set", currentType);
      currentType.setCastRequired();
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
  public void visit(ExpFieldAccess node) {
    RTExpression currentNode = node.parts.get(0); // can not be empty
    currentNode.visit(this);
    // The type to which the next field access item is applied
    Type currentType = ((RTExpression) currentNode).type;
    // this is dangerous, and only works if this condition can not be
    // "interrupted"
    partOfFieldAccess = true;
    for (int i = 1; i < node.parts.size(); ++i) {
      currentNode = node.parts.get(i);
      // if this is a funccall performed on anything, tell the function the type it was called on
      if(currentNode instanceof ExpFuncCall) {
        ((ExpFuncCall)currentNode).calledUpon = currentType;
      }
      currentNode.visit(this);
      if (currentType.isRdfType()) {
        if (currentNode instanceof ExpIdentifier) {
          // only a literal, delegate this because it's complicated
          ExpPropertyAccess acc = treatRdfPropertyAccess(node, currentType,
              (ExpIdentifier) currentNode);
          node.parts.set(i, acc);
          currentType = acc.getType();
        } else if (currentNode instanceof RTExpression) {
          // could also be a method application, possibly, what else?
          currentType = ((RTExpression) currentNode).type;
        } else {
          currentType = getNoType();
        }
      } else { // unknown or Java type, let's try with the accessor type
    	  // TODO: think about this. we definitely want this in case of
        //    currentNode being a function call, but if it is a variable we get
        //    either nothing or - worse - the type of some unrelated local variable;
    	  //		which other expressions need to be handled cautiously?
        if (currentNode instanceof RTExpression
            && !(currentNode instanceof ExpIdentifier)) {
          currentType = ((RTExpression) currentNode).type;
        } else {
          currentType = getNoType();
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
  public void visit(ExpFuncCall node) {

    // test whether the given parameters are of the correct type
    List<Type> partypes = new ArrayList<Type>();
    if(node.params != null) {
      for (RTExpression e : node.params) {
        e.visit(this);
        partypes.add(e.getType());
      }
    }
    // if this was used in a new Expression, handle it accordingly
    if(node.newexp){
      node.type = new Type(node.content);
      return;
    }
    // a type that represents the actual parameters (the call)
    // type variables must be made unique for every inner type of the function
    // type, otherwise, type variables can overlap in a wrong way
    Type callType = getFunctionCallType(node.type, node.calledUpon, partypes);

    Function f = mem.getFunction(node.content, callType);
    if (f != null) {
      String o = f.getOrigin();
      // no need to specify the function origin if it's from this class
      if (o != null && ! o.equals(mem.getClassName())) {
        node.realOrigin = o;
      }

      // Try to deduce "real" types for the type vars by matching the
      // function definition and actual parameter types
      Type resolved = null;
      List<String> clashes = new ArrayList<>();
      resolved = resolveTypeVars(f.getType(), callType, clashes);
      if (! clashes.isEmpty()) {
        for (String s : clashes) typeError(s, node);
      }

      // percolate all changed parameter types down
      if (node.calledUpon != null &&
          !node.calledUpon.equals(resolved.getClassOfMethod()))
        node.calledUpon = resolved.getClassOfMethod();

      Iterator<Type> restypes = resolved.getParameterTypes();
      Iterator<Type> ptypes = partypes.iterator();
      for(RTExpression e : node.params) {
        Type resType = restypes.next();
        if (!(ptypes.next().equals(resType))) {
          resType.setCastRequiredInner();
          e.propagateType(resType, this);
          e.visit(this);
        }
      }
      if (node.type == null || node.type.isUnspecified()) {
        node.type = resolved.getReturnType();
      }
    }
    if (node.type == null) {
      // TODO: MAYBE INTRODUCE A FLAG FOR STRICT CHECKING. FOR THE TIME BEING,
      // WE EXCLUDE ALL CALLS ON OBJECT THAT ARE ORDINARY JAVA CLASS INSTANCES
      if (node.calledUpon == null) {
        typeError("The function call to " + node.content
            + " refers to a function that wasn't declared", node);
      }
      node.type = getNoType();
    } else {
      // if the return type is an XSD or RDF type, we only know it's Object
      if (node.type.isXsdType())
        node.type.setCastRequired();
    }
  }

  @Override
  public void visit(ExpPropertyAccess node) {
    return;
  }

  /**
   * TODO: Do we have to check here if it's an RDF type?? or DialogueAct?? or is
   * this all clear? we might have to test whether Quiz is sth like that and
   * give the node a proper type; how to do that?
   */
  @Override
  public void visit(ExpLiteral node) {
    // nothing to test here
  }

  /**
   * We're trying to determine the type of a variable. a) If the variable is
   * already defined in another rudi file, it is fully specified, and all type
   * information is already stored with it. b)
   */
  @Override
  public void visit(ExpIdentifier node) {
    // get the type of the variable, if defined
    // TODO: is there a way to find out if we try to retrieve the value of an
    // undefined variable?
    Type t = mem.getVariableType(node.content);
    if (t == null) {
      if (node.type.isUnspecified()) {
        // we could have sth like Introduction, that is an undeclared rdf class
        RdfClass cl = mem.getProxy().fetchClass(node.content);
        if (cl != null) {
          node.type = new Type(cl.toString());
          if (!mem.variableExists(node.content)) {
            node.content = "\"" + node.content + "\"";
          }
        }
      }
    } else {
      node.type = t;
    }
  }

  @Override
  public void visit(ExpArrayAccess node) {
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
  public void visit(StatPropose node) {
    node.arg.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visit(StatTimeout node) {
    node.label.visit(this);
    Type ltype = node.label.getType();
    if (! ltype.isString() && ! ltype.isStringConvertible()
        && ! ltype.isDialogueAct()) {
      typeError("Argument of timeout must be string or dialogueact", node);
    }
    node.time.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visit(StatReturn node) {
    if (node.returnExp != null) {
      node.returnExp.visit(this);
    }
  }

  @Override
  public void visit(StatSwitch node) {
    node.condition.visit(this);
    node.block.visit(this);
  }

  @Override
  public void visit(ExpNew node) {
    if (node.params != null) {
      for (RTExpression param : node.params)
        param.visit(this);
    } else {
      if (node.type.getRdfClass() == null)
        typeError("new Rdf with unknown RDF class: " + node.type, node);
    }
  }

  @Override
  public void visit(StatExpression node) {
    node.expression.visit(this);
  }

}
