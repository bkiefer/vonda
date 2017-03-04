/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * a special kind of the RudiTree is an expression; expressions can have types
 *
 * @author Anna Welker
 */
public abstract class RTExpression extends RudiTree {

  public static final Logger logger = LoggerFactory.getLogger(RudiTree.class);

  protected String type;

  String fullexp;

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
  public boolean isRdfType() {
    return isRdfType(type);
  }

  // easier for recursion if we can also give the method a type
  public boolean isRdfType(String type) {
    return type != null && type.charAt(0) == '<';
  }

  public boolean isComplexType() {
    if (type == null) return false;
    return ("String".equals(type)
        || type.startsWith("Map")
        || type.startsWith("Set")
        || type.startsWith("RdfSet")
        || type.startsWith("RdfList")
        || (type.endsWith(">") && ! isRdfType()));
  }

  /** Return the "inner" type of a complex type expression */
  public String getInnerType() {
    int left = type.indexOf('<');
    int right = type.lastIndexOf('>');
    return type.substring(left + 1, right);
  }

  public RTExpression ensureBoolean() {
    ExpBoolean result = null;
    USingleValue right = null;

    if (this instanceof ExpBoolean) {
      return this;
    } else if ("boolean".equals(type)){
      if(this instanceof USingleValue){
        return this;
      } else {
        // if this is a funccall with type boolean, we'd still like to have it
        // as a boolean, at least wrapped up
        result = new ExpBoolean(fullexp, this, null, null);
      result.positions = positions;
      return result;
      }
    }
    // this is some other kind of expression, turn it into a comparison or
    // method call returning a boolean
    // TODO check if there's something missing for RDF types
    if (isComplexType()) {
      result = new ExpBoolean(fullexp, this, null, ".isEmpty()");
      result.positions = positions;
      return result;
    }
    if (type == null) {
      right = new USingleValue("null", "Object");
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
    }
    // we assume it's just an Object
    result = new ExpBoolean(fullexp, this, right, "!=");
    result.positions = positions;
    if (right != null) {
      int lastpos = positions[positions.length - 1];
      right.positions = new int[]{ lastpos, lastpos };
    }
    return result;
  }

  public abstract void propagateType(String upperType);

}
