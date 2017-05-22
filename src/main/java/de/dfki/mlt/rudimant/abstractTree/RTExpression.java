/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

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

  protected String type;

  public String getType() {
    return type;
  }

  public void setType(String to) {
    this.type = to;
  }

  /**
   * duplicate the method from RudiTree to ensure String return
   * @param v
   */
  public String visitWithSComments(VGenerationVisitor v) {
    if(v.collectingCondition){
      return this.visitStringV(v);
    }
    String ret = "";
    int firstPos = this.positions[0];
    ret += checkComments(v, firstPos);
    ret += this.visitStringV(v);
    int endPos = this.positions[1];
    ret += checkComments(v, endPos);
    return ret;
  }

  // Return true if this is represents an RDF type or a DialogueAct
  // TODO: maybe has to be split up.
  public boolean isRdfType() { return Type.isRdfType(type); }

  public boolean isComplexType() { return Type.isComplexType(type); }

  public boolean isStringOrComplexType() {
    return "String".equals(type) || isComplexType();
  }

  public String getInnerType() { return Type.getInnerType(type); }

  public RTExpression fixFields(RTExpression b) {
    b.positions = positions;
    b.fullexp = fullexp;
    return b;
  }

  public RTExpression ensureBooleanBasic() {
    if (this instanceof ExpBoolean) {
      return this;
    }

    if ("boolean".equals(type)){
      if(this instanceof USingleValue){
        return this;
      } else {
        // if this is a funccall with type boolean, we'd still like to have it
        // as a boolean, at least wrapped up; if it is a fieldaccess kind of funccall, we have
        // to go on testing for each part if it is null
        return fixFields(new ExpBoolean(this, null, null));
      }
    }

    // this is some other kind of expression, turn it into a comparison or
    // method call returning a boolean
    // TODO check if there's something missing for RDF types
    if (isStringOrComplexType()) {
      return fixFields(new ExpBoolean(this, null, "exists("));
    }

    RTExpression result = null;
    USingleValue right = null;

    if (type == null) {
      right = new USingleValue("null", "Object");
      right.positions = positions;
      right.fullexp = fullexp;
      result = fixFields(new ExpBoolean(this, right, "!="));
    } else {
      String cleanType = Type.convertXsdType(type);
      if (! type.equals(cleanType)) {
        result = fixFields(new ExpBoolean(this, null, "exists("));
      } else {
        switch (type) {
        case "int":
        case "float":
        case "double":
          right = new USingleValue("0", type);
          break;
        default:
          right = new USingleValue("null", "Object");
          break;
        }
        right.positions = positions;
        right.fullexp = fullexp;
        result = fixFields(new ExpBoolean(this, right, "!="));
      }
    }
    return result;
  }

  public RTExpression ensureBoolean() {
    if (this instanceof UFieldAccess) {
      UFieldAccess ufa = (UFieldAccess)this;
      return ufa.ensureBooleanUFA();
    }
    return ensureBooleanBasic();
  }


  public abstract void propagateType(String upperType);

}
