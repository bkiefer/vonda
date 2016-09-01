/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;
import java.io.IOException;
import java.io.Writer;

/**
 * this is either a variable declaration, or an assignment of a variable to a
 * new value.
 * Most of the type checking rudimant currently does happens here.
 * @author Anna Welker
 */
public class ExpAssignment implements RudiTree, RTExpression {

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

  public void testType(Writer out) throws IOException {
    // we don't know anything about an actualType, so let's look it up
    this.typeRight = ((RTExpression) right).getType();
    try {
      this.actualType = ((RTExpression) left).getType();
    } catch (NullPointerException e) {
      // the variable wasn't declared yet, so this is actually a declaration
      this.declaration = true;
      actualType = typeRight;
      return;
    }
    if (!(actualType.equals(typeRight))) {
      out.append("// an Exception occurred; closing the writer here\n");
      out.close();
      throw new UnsupportedOperationException("The assignment of the variable "
              + ((UVariable) left).toString() + "\t in rule " + position
              + " has type mismatches:\n\t\t" + actualType
              + " does not match " + typeRight);
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

  public void testTypeDecl(String actualType, Writer out) throws IOException {
    // an actualType exists, so we assume left is supposed to be actualType
    this.typeRight = ((RTExpression) right).getType();
    try {
      if (!(actualType.equals(((RTExpression) right).getType()))) {
      out.append("// an Exception occurred; closing the writer here\n");
        out.close();
        throw new UnsupportedOperationException("The declaration of the variable "
                + ((UVariable) left).toString() + " in rule " + position
                + " has type mismatches:\n\t\t" + actualType
                + " does not match " + ((RTExpression) right).getType());
        // TODO: do this without toString...
//          throw new UnsupportedOperationException("The following assignment has type mismatches:\n\t\t"
//                  + this.generate(null) + "\nType " + ((RTExpression) left).getType()
//                  + " does not match " + ((RTExpression) right).getType());
      }
    } catch (NullPointerException e) {
      out.append("// an Exception occurred; closing the writer here\n");
      out.close();
      throw new UnsupportedOperationException("The declaration of the variable "
              + ((UVariable) left).toString() + "\t in rule " + position
              + " has type mismatches:\n\t\t" + Mem.getVariableType(left.toString())
              + " does not match " + ((RTExpression) right).getType());
    }
  }

  @Override
  public void testType() {
    // see generate

  }

//  private void specialTest(Writer out){
//    if(((RTExpression) right).getType() instanceof ArrayList<String>.class){
//
//    } else{
//      throw new UnsupportedOperationException("The declaration of the variable "
//              + ((UVariable) left).toString() + "\t in rule " + position
//              + " has type mismatches:\n\t\t" + Mem.getSpecialVariableType(left.toString())
//              + " does not match " + ((RTExpression) right).getType());
//    }
//  }

  @Override
  public String getType() {
    return this.actualType;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
