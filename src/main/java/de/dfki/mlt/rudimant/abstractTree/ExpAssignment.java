/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/**
 * this is either a variable declaration, or an assignment of a variable to a
 * new value. Most of the type checking rudimant currently does happens here.
 *
 * @author Anna Welker
 */
public class ExpAssignment extends RTExpression {

  String typeRight;
  RudiTree left;
  RTExpression right;
  boolean declaration;
  String actualType;
  String position;

  public ExpAssignment(RudiTree left, RTExpression right,
          boolean declaration, String position) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
    this.position = position;
    // irgendwohin in generate:
//        Mem.addElement(ctx.getChild(1).getText(), ((RTExpression) right).getType(),
//                context.getCurrentRule());
  }

  public void testType(RudimantCompiler out) {
    // we don't know anything about an actualType, so let's look it up
    this.typeRight = right.getType();
    try {
      this.actualType = ((RTExpression) left).getType();
    } catch (NullPointerException e) {
      // the variable wasn't declared yet, so this is actually a declaration
      this.declaration = true;
      actualType = typeRight;
      return;
    }
    try {
      if (!(actualType.equals(typeRight))) {
        out.handleTypeError("The assignment of the variable "
                + left.toString() + "\t in rule " + position
                + " has type mismatches:\n\t\t" + actualType
                + " does not match " + typeRight);
      }
    } catch (NullPointerException e) {
      out.handleTypeError("The assignment of the variable "
              + left.toString() + "\t in rule " + position
              + " cannot work; it was not defined!!");
    }
  }

  public ExpAssignment(String actualType, RudiTree left, RTExpression right,
          boolean declaration, String position) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
    this.actualType = actualType;
    this.position = position;
  }

  public void testTypeDecl(RudimantCompiler out) {
    // an actualType exists, so we assume left is supposed to be actualType
    this.typeRight = right.getType();
    try {
      if (!(actualType.equals(typeRight))) {
        out.handleTypeError("The declaration of the variable "
                + left.toString() + " in rule " + position
                + " has type mismatches:\n\t\t" + actualType
                + " does not match " + right.getType());
        // TODO: do this without toString...
//          throw new UnsupportedOperationException("The following assignment has type mismatches:\n\t\t"
//                  + this.generate(null) + "\nType " + ((RTExpression) left).getType()
//                  + " does not match " + ((RTExpression) right).getType());
      }
    } catch (NullPointerException e) {
      out.handleTypeError("The declaration of the variable "
              + left.toString() + "\t in rule " + position
              + " has type mismatches:\n\t\t" + out.getMem().getVariableType(left.toString())
              + " does not match " + right.getType());
    }
  }

@Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 79 * hash + Objects.hashCode(this.left);
    hash = 79 * hash + Objects.hashCode(this.right);
    hash = 79 * hash + (this.declaration ? 1 : 0);
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
    final ExpAssignment other = (ExpAssignment) obj;
    if (this.declaration != other.declaration) {
      return false;
    }
    if (!Objects.equals(this.left, other.left)) {
      return false;
    }
    if (!Objects.equals(this.right, other.right)) {
      return false;
    }
    return true;
  }

}
