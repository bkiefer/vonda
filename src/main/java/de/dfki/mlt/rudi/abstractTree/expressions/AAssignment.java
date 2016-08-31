/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.expressions;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudi.abstractTree.leaves.AVariable;
import java.io.IOException;
import java.io.Writer;

/**
 * this is either a variable declaration, or an assignment of a variable to a
 * new value.
 * Most of the type checking rudimant currently does happens here.
 * @author Anna Welker
 */
public class AAssignment implements AbstractTree, AbstractExpression {

  private String typeRight;
  private AbstractTree left;
  private AbstractExpression right;
  private boolean declaration;
  private String actualType;
  private String position;

  public AAssignment(AbstractTree left, AbstractExpression right,
          boolean declaration, String position) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
    this.position = position;
    // irgendwohin in generate:
//        Mem.addElement(ctx.getChild(1).getText(), ((AbstractExpression) right).getType(),
//                context.getCurrentRule());
  }

  public void testType(Writer out) throws IOException {
    // we don't know anything about an actualType, so let's look it up
    this.typeRight = ((AbstractExpression) right).getType();
    try {
      this.actualType = ((AbstractExpression) left).getType();
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
              + ((AVariable) left).toString() + "\t in rule " + position
              + " has type mismatches:\n\t\t" + actualType
              + " does not match " + typeRight);
    }
  }

  public AAssignment(String actualType, AbstractTree left, AbstractExpression right,
          boolean declaration, String position) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
    this.actualType = actualType;
    this.position = position;
  }

  public void testTypeDecl(String actualType, Writer out) throws IOException {
    // an actualType exists, so we assume left is supposed to be actualType
    this.typeRight = ((AbstractExpression) right).getType();
    try {
      if (!(actualType.equals(((AbstractExpression) right).getType()))) {
      out.append("// an Exception occurred; closing the writer here\n");
        out.close();
        throw new UnsupportedOperationException("The declaration of the variable "
                + ((AVariable) left).toString() + " in rule " + position
                + " has type mismatches:\n\t\t" + actualType
                + " does not match " + ((AbstractExpression) right).getType());
        // TODO: do this without toString...
//          throw new UnsupportedOperationException("The following assignment has type mismatches:\n\t\t"
//                  + this.generate(null) + "\nType " + ((AbstractExpression) left).getType()
//                  + " does not match " + ((AbstractExpression) right).getType());
      }
    } catch (NullPointerException e) {
      out.append("// an Exception occurred; closing the writer here\n");
      out.close();
      throw new UnsupportedOperationException("The declaration of the variable "
              + ((AVariable) left).toString() + "\t in rule " + position
              + " has type mismatches:\n\t\t" + Mem.getVariableType(left.toString())
              + " does not match " + ((AbstractExpression) right).getType());
    }
  }

  @Override
  public void testType() {
    // see generate

  }

//  private void specialTest(Writer out){
//    if(((AbstractExpression) right).getType() instanceof ArrayList<String>.class){
//
//    } else{
//      throw new UnsupportedOperationException("The declaration of the variable "
//              + ((AVariable) left).toString() + "\t in rule " + position
//              + " has type mismatches:\n\t\t" + Mem.getSpecialVariableType(left.toString())
//              + " does not match " + ((AbstractExpression) right).getType());
//    }
//  }

  @Override
  public void generate(Writer out) throws IOException {
    //System.out.println(((AVariable) left).toString());
    if (GrammarMain.checkTypes()) {
      if (this.actualType != null) {
        testTypeDecl(actualType, out);
      } else {
        testType(out);
      }
    }
    if (this.declaration) {
      Mem.addElement(((AVariable) left).toString(), actualType, position);
      out.append(this.actualType + " " + ((AVariable) left).toString() + " = ");
      this.right.generate(out);
      return;
    }
    out.append(((AVariable) left).toString() + " = ");
    this.right.generate(out);
  }

  @Override
  public String getType() {
    return this.actualType;
  }

  @Override
  public void returnManaging() {
    // nothing to do
  }

}
