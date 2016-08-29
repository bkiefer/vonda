/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.expressions;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.abstractTree.AbstractBinaryExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractExpression;

/**
 *
 * @author anna
 */
public class ABooleanExp extends AbstractBinaryExpression {

  private boolean not;
  private boolean isSubsumed = false;   // <- magic part!!!
  private String operator;

  /**
   * if the expression consists of only one part, set right and operator to null
   * @param left left part
   * @param right right part
   * @param operator  operator in between
   * @param not set true if there is a ! in front of the expression
   */
  public ABooleanExp(AbstractExpression left,
          AbstractExpression right, String operator, boolean not) {
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.not = not;
    if(operator != null && operator.equals("<=")){
      if(left.getType().equals(right.getType())){
        if(left.getType().equals("magic")){
          this.isSubsumed = true;
        }
      }
    }
  }

  @Override
  public void testType() {
    // TODO: test, test...
  }

  @Override
  public String generate(Writer out){
    String ret = "";
    if(this.not){
      ret += "!";
    }
    if(this.isSubsumed){
      return ret + "isSubsumed(" + left + ", " + right + ")";
    }
    if(this.right != null){
      ret += "(" + this.left + this.operator + this.right + ")";
      GrammarMain.context.doLog(
              "\"" + ret.replace('"', ' ') +  " _ resulted to \" + " + ret);
      return ret;
    }
    ret += this.left.generate(null);
    GrammarMain.context.doLog("\"" + ret.replace('"', ' ') +  " resulted to \" + ("
            + ret + ")");
    return ret;
  }

  @Override
  public String getType() {
    if(this.right == null){
      return this.left.getType();
    }
    return "boolean";
  }
}
