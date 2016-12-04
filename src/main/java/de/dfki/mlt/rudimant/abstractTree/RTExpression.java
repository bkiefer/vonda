/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.*;

import java.util.HashMap;
import java.util.Map;

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

  // Return true if this is represents an RDF type or a DialogueAct
  // TODO: maybe has to be split up.
  public boolean isRdfType() {
    return type != null && type.charAt(0) == '<';
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

  static Map<String, Integer> typeCodes = new HashMap<>();

  static {
    typeCodes.put("Object", 0x1111);
    typeCodes.put("String", 0x1);
    typeCodes.put("double", 0x1110000);
    typeCodes.put("float", 0x110000);
    typeCodes.put("int", 0x10000);
  }

  public static String mergeTypes(String left, String right) {
    // TODO:
    // we also have to check if these are RDF types and are in a type relation.

    // this should return the more specific of the two, or null if they are
    // incompatible
    Integer leftCode = typeCodes.get(left);
    Integer rightCode = typeCodes.get(right);
    if (leftCode == null || rightCode == null) return null;
    int common = leftCode | rightCode;
    if (common == leftCode) {
      return left;
    }
    if (common == rightCode) {
      return right;
    }
    return null;
  }

  public ExpBoolean ensureBoolean() {
    if (this instanceof ExpBoolean) {
      return (ExpBoolean) this;
    }

    ExpBoolean result = null;
    USingleValue right = null;
    // this is some other kind of expression, turn it into a comparison or
    // method call returning a boolean
    // TODO check if there's something missing for RDF types
    if (isComplexType()) {
      result = new ExpBoolean(fullexp + ".isEmpty()", this, null, ".isEmpty()");
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
    result = new ExpBoolean(fullexp + " != " + right, this, right, "!=");
    result.positions = positions;
    if (right != null) {
      int lastpos = positions[positions.length - 1];
      right.positions = new int[]{ lastpos, lastpos };
    }
    return result;
  }

  public abstract void propagateType(String upperType);

}
