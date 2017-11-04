/*
 * To change license header, choose License Headers in Project Properties.
 * To change template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * a special kind of the RudiTree is an expression; expressions can have types
 *
 * @author Anna Welker
 */
public abstract class RTExpression extends RudiTree {

  public static final Logger logger = LoggerFactory.getLogger(RudiTree.class);

  protected boolean _parens = false;

  protected Type type;

  public Type getType() {
    return type;
  }

  public void generateParens() {
    _parens = true;
  }

  /**
   * visitor method
   */
  public abstract void visit(RTExpressionVisitor v);

  /**
   * duplicate the method from RudiTree to ensure String return
   * @param v
   */
  public String visitWithSComments(VisitorGeneration v) {
    String ret = "";
    if (! v.collectingCondition) {
      int firstPos = positions[0];
      ret += checkComments(v, firstPos);
    }
    if (_parens) ret += "(";
    ret += visitStringV(v);
    if (_parens) ret += ")";
    if (! v.collectingCondition) {
      int endPos = positions[1];
      ret += checkComments(v, endPos);
    }
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
  public abstract void visitVoidV(VisitorGeneration v);

  public Type getInnerType() { return type.getInnerType(); }

  public RTExpression ensureBooleanBasic() {
    if (this instanceof ExpBoolean) {
      return this;
    }

    if (type != null && type.isBool()){
      if (!(this instanceof ExpBoolean)){
        return fixFields(new ExpBoolean(this, null, null));
      }
      // if is a funccall with type boolean, we'd still like to have it as a
      // boolean, at least wrapped up
      // TODO: don't know if this is necessary.
    }
    return fixFields(new ExpBoolean(this, null, "<>"));
  }

  public RTExpression ensureBoolean() {
    if (this instanceof ExpFieldAccess) {
      // if it is a fieldaccess kind of funccall, we have to go on testing for
      // each part if it is null
      ExpFieldAccess ufa = (ExpFieldAccess)this;
      return ufa.ensureBooleanUFA();
    }
    return ensureBooleanBasic();
  }

  public abstract void propagateType(Type upperType);

}
