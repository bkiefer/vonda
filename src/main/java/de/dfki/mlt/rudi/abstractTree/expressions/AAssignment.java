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

/**
 *
 * @author anna
 */
public class AAssignment implements AbstractTree, AbstractExpression {

  private String type;
  private AbstractTree left;
  private AbstractExpression right;
  private boolean declaration;
  private String actualType;

  public AAssignment(AbstractTree left, AbstractExpression right,
          boolean declaration) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
    findType();
    this.actualType = this.type;
  }

  public void findType() {
    if (declaration || !GrammarMain.checkTypes()) {
      this.type = ((AbstractExpression) right).getType();
    } else {
      this.type = ((AbstractExpression) left).getType();
      if (!(Mem.getVariableType(left.toString())
              .equals(((AbstractExpression) right).getType()))) {
        throw new UnsupportedOperationException("The following assignment has type mismatches:\n\t\t"
                + this.toString() + "\nType " + Mem.getVariableType(left.toString())
                + " does not match " + ((AbstractExpression) right).getType());
      }
    }
  }

  public AAssignment(String actualType, AbstractTree left, AbstractExpression right,
          boolean declaration) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
    if (GrammarMain.checkTypes()) {
      findTypeDecl(actualType);
    }
    this.actualType = actualType;
  }

  public void findTypeDecl(String actualType) {
    if (declaration) {
      this.type = ((AbstractExpression) right).getType();
    } else {
      this.type = ((AbstractExpression) left).getType();
      try {
        if (!(((AbstractExpression) left).getType()).equals(((AbstractExpression) right).getType())) {
          throw new UnsupportedOperationException("The following assignment has type mismatches:\n\t\t"
                  + this.toString() + "\nType " + ((AbstractExpression) left).getType()
                  + " does not match " + ((AbstractExpression) right).getType());
        }
      } catch (NullPointerException e) {
        throw new UnsupportedOperationException("left: " + left.toString()
                + " of type " + ((AbstractExpression) left).getType() + "; right: "
                + right.toString() + " of type " + ((AbstractExpression) right).getType());
      }
    }
  }

  @Override
  public void testType() {
    // TODO: test, look into memory, differ between String s = "" and s = ""

  }

  @Override
  public String toString() {
    if (this.declaration) {
      if (!this.actualType.equals("")) {
        return this.actualType + " " + this.left.toString() + " = " + this.right;
      }
      return this.type + " " + this.left.toString() + " = " + this.right;
    }
    return this.left + " = " + this.right;
  }

  @Override
  public String getType() {
    return this.type;
  }

}
