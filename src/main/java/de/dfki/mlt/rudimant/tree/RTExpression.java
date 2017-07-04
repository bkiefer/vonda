/*
 * To change license header, choose License Headers in Project Properties.
 * To change template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.Type;

/**
 * a special kind of the RudiTree is an expression; expressions can have types
 *
 * @author Anna Welker
 */
public abstract class RTExpression extends RudiTree {

  public static final Logger logger = LoggerFactory.getLogger(RudiTree.class);

  protected Type type;

  public Type getType() {
    return type;
  }

  /**
   * visitor method
   */
  public abstract void visit(RTExpressionVisitor v);

  /**
   * duplicate the method from RudiTree to ensure String return
   * @param v
   */
  public String visitWithSComments(VGenerationVisitor v) {
    if(v.collectingCondition){
      return visitStringV(v);
    }
    String ret = "";
    int firstPos = positions[0];
    ret += checkComments(v, firstPos);
    ret += visitStringV(v);
    int endPos = positions[1];
    ret += checkComments(v, endPos);
    return ret;
  }

  /**
   * the visitMethod for the visitor that allows to return Strings ! only to be
   * used by expressions !
   */
  public abstract String visitStringV(RTStringVisitor v);

  /**
   * the visitMethod for the visitor that allows to return Strings ! for
   * everything except expressions, they should write to out !
   */
  public abstract void visitVoidV(VGenerationVisitor v);

  public boolean isComplexType() { return type != null && type.isCollection(); }

  public boolean isStringOrComplexType() {
    return type != null && (type.isString() || isComplexType());
  }

  public Type getInnerType() { return type.getInnerType(); }

  public RTExpression ensureBooleanBasic() {
    if (this instanceof ExpBoolean) {
      return this;
    }

    if (type != null && type.isBool()){
      if (this instanceof ExpUSingleValue){
        return this;
      }
      // if is a funccall with type boolean, we'd still like to have it as a
      // boolean, at least wrapped up; if it is a fieldaccess kind of funccall,
      // we have to go on testing for each part if it is null
      return fixFields(new ExpBoolean(this, null, null));
    }

    // is some other kind of expression, turn it into a comparison or
    // method call returning a boolean
    // TODO check if there's something missing for RDF types
    if (isStringOrComplexType()) {
      return fixFields(new ExpBoolean(this, null, "exists("));
    }

    RTExpression result = null;
    ExpUSingleValue right = null;

    if (type == null || type.isUnspecified()) {
      right = fixFields(new ExpUSingleValue("null", "Object"));
      result = fixFields(new ExpBoolean(this, right, "!="));
    } else {
      //Type cleanType = type; // .convertXsdType();
      //if (! type.equals(cleanType)) {
      //  result = fixFields(new ExpBoolean(this, null, "exists("));
      //} else {
      // TODO USE Type.isCollection, and possibly move to generation
        switch (type.toString()) {
        case "long":
        case "int":
        case "short":
        case "byte":
        case "char":
        case "float":
        case "double":
          right = fixFields(new ExpUSingleValue("0", type.toString()));
          break;
        default:
          right = fixFields(new ExpUSingleValue("null", "Object"));
          break;
        }
        result = fixFields(new ExpBoolean(this, right, "!="));
     // }
    }
    return result;
  }

  public RTExpression ensureBoolean() {
    if (this instanceof ExpUFieldAccess) {
      ExpUFieldAccess ufa = (ExpUFieldAccess)this;
      return ufa.ensureBooleanUFA();
    }
    return ensureBooleanBasic();
  }


  public abstract void propagateType(Type upperType);

}
