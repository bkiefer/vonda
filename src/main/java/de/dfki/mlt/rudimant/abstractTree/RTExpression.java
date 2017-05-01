/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.Constants;
import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.agent.DialogueAct;

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
  public boolean isRdfType() { return Mem.isRdfType(type); }

  public boolean isComplexType() { return Mem.isComplexType(type); }

  public boolean isStringOrComplexType() {
    return "String".equals(type) || isComplexType();
  }

  public String getInnerType() { return Mem.getInnerType(type); }

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
        // as a boolean, at least wrapped up; if it is a fieldaccess kind of funccall, we have
    	// to go on testing for each part if it is null
        result = new ExpBoolean(this, null, null);
        if(!(this instanceof UFieldAccess)){
          result.positions = positions;
          result.fullexp = fullexp;
          return result;
        }
      }
    }
    // this is some other kind of expression, turn it into a comparison or
    // method call returning a boolean
    // TODO check if there's something missing for RDF types
    if (isStringOrComplexType()) {
      /* TODO: MAKE THIS WORK PROPERLY. If it's a function call, it must
       * be treated differently
      USingleValue newSingle = new USingleValue("null", "Object");
      ExpBoolean newLeft =
          new ExpBoolean(fullexp, this, newSingle , "!=");
      ExpBoolean newRight =
          new ExpBoolean(fullexp, this, null, ".isEmpty() == false");
      result = new ExpBoolean(fullexp, newLeft, newRight, "&&");
      int lastpos = positions[positions.length - 1];
      newSingle.positions = newLeft.positions = newRight.positions
          = new int[]{ lastpos, lastpos };
      */
      result = new ExpBoolean(this, null, "exists(");
    } else {
      if (type == null) {
        right = new USingleValue("null", "Object");
        right.positions = positions;
        right.fullexp = fullexp;
        result = new ExpBoolean(this, right, "!=");
      } else {
        String cleanType = Mem.convertXsdType(type);
        if (! type.equals(cleanType)) {
          result = new ExpBoolean(this, null, "exists(");
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
          result = new ExpBoolean(this, right, "!=");
        }
      }
    }
    if(this instanceof UFieldAccess){
        int s = ((UFieldAccess)this).parts.size();
        if(((UFieldAccess)this).parts.get(s - 2).type != null && 
      		  ((UFieldAccess)this).parts.get(s - 2).type.equals(Constants.DIALOGUE_ACT_TYPE)){
    	    result = new ExpBoolean(((UFieldAccess)this).parts.get(s - 2), new USingleValue("\"" + 
      		    		  	((UFieldAccess)this).parts.get(s - 1).fullexp + "\"", "String"), "hasSlot(");
        }
        result.positions = positions;
        result.fullexp = fullexp;
        if(s > 2){
  	      // to avoid NullPointerexceptions, test on null on all levels
  	      List<RTExpression> smaller = new ArrayList<>();
  	      smaller.addAll(((UFieldAccess)this).parts);
  	      smaller.remove(s - 1);
  	      List<String> smallerRep = new ArrayList<>();
  	      smallerRep.addAll(((UFieldAccess)this).representation);
  	      smallerRep.remove(s - 1);
  	      UFieldAccess partAccess = new UFieldAccess(smaller, smallerRep);
  	      partAccess.positions = positions;
  	      partAccess.fullexp = fullexp;
  	      result = new ExpBoolean(partAccess.ensureBoolean(), result, "&&");
        } else {
          RTExpression first = ((UFieldAccess)this).parts.get(0);
  	      first.positions = positions;
  	      first.fullexp = fullexp;
  	      result = new ExpBoolean(first.ensureBoolean(), result, "&&");
        }
    }
    result.positions = positions;
    result.fullexp = fullexp;
    return result;
  }

  public abstract void propagateType(String upperType);

}
