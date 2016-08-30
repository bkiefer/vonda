/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.expressions;

import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author anna
 */
public class ABooleanExp implements AbstractExpression {

  private AbstractExpression left;
  private AbstractExpression right;
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
  public void generate(Writer out) throws IOException{
    String ret = "";
    if(this.not){
      out.append("!");
    }
    if(this.isSubsumed){
      out.append("isSubsumed(");
      left.generate(out);
      out.append(", ");
      right.generate(out);
      out.append(")");
    }
    if(this.right != null){
      out.append("(");
      this.left.generate(out);
      out.append(this.operator);
      this.right.generate(out);
      out.append(")");
      //GrammarMain.context.doLog(
      //        "\"" + ret.replace('"', ' ') +  " _ resulted to \" + " + ret);
      return;
    }
    this.left.generate(out);
    //GrammarMain.context.doLog("\"" + ret.replace('"', ' ') +  " resulted to \" + ("
    //        + ret + ")");
  }

  @Override
  public String getType() {
    if(this.right == null){
      return this.left.getType();
    }
    return "boolean";
  }
}
