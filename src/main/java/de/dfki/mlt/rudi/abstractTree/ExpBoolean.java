/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.Objects;

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

  String rule;

  String fullexp;
  // if the boolexp is no boolexp, type visitor should set this to the correct
  // way of testing the existance of boolexp
  String isTrue = "";
  boolean testIsEmpty = false;

  /**
   * if the expression consists of only one part, set right and operator to null
   *
   * @param fullexp the String representation of the whole expression
   * @param left left part
   * @param right right part
   * @param operator operator in between
   * @param not set true if there is a ! in front of the expression
   */
  public ExpBoolean(String fullexp, RTExpression left,
          RTExpression right, String operator, boolean not) {
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.not = not;
    this.type = "boolean";
    this.fullexp = fullexp;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

    @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.fullexp);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ExpBoolean other = (ExpBoolean) obj;
    if (!Objects.equals(this.fullexp, other.fullexp)) {
      return false;
    }
    return true;
  }
}
