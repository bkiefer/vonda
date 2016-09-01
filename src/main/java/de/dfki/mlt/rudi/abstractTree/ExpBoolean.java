/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RTExpression;

/**
 * this is a boolean expression; might also be a subsumes relation (but will
 * in sum always be a boolean)
 *
 * @author Anna Welker
 */
public class ExpBoolean implements RTExpression {

  RTExpression left;
  RTExpression right;
  boolean not;
  boolean isSubsumed = false;   // <- magic part!!!
  boolean doesSubsume = false;   // <- magic part!!!
  String operator;
  String type;

  /**
   * if the expression consists of only one part, set right and operator to null
   *
   * @param left left part
   * @param right right part
   * @param operator operator in between
   * @param not set true if there is a ! in front of the expression
   */
  public ExpBoolean(RTExpression left,
          RTExpression right, String operator, boolean not) {
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.not = not;
    this.type = null;
  }

  @Override
  public void testType() {
    // the getType method is testing, too
  }

  @Override
  public void generate(Writer out) throws IOException {
    if (this.type == null) {
      getType();
    }
    String ret = "";
    if (this.not) {
      out.append("!");
    }
    if (this.isSubsumed) {
      out.append("isSubsumed(");
      left.generate(out);
      out.append(", ");
      right.generate(out);
      out.append(")");
    } else if (this.doesSubsume) {
      out.append("isSubsumed(");
      right.generate(out);
      out.append(", ");
      left.generate(out);
      out.append(")");
    }
    if (this.right != null) {
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
    if (this.type == null) {
      if (operator != null && left.getType().equals("rdf")) {
        if (left.getType().equals(right.getType())) {
          if (operator.equals("<=")) {
            this.isSubsumed = true;
          } else if (operator.equals("=>")) {
            this.doesSubsume = true;
          }
        }
      }
    }
    this.type = "boolean";
    return type;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
