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
import java.io.IOException;
import java.io.Writer;

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
  private String position;

  public AAssignment(AbstractTree left, AbstractExpression right,
          boolean declaration, String position) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
    findType();
    this.actualType = this.type;
    this.position = position;
    // irgendwohin in generate:
//        Mem.addElement(ctx.getChild(1).getText(), ((AbstractExpression) right).getType(),
//                context.getCurrentRule());
  }

  public void findType() {
    if (declaration || !GrammarMain.checkTypes()) {
      this.type = ((AbstractExpression) right).getType();
    } else {
      this.type = ((AbstractExpression) left).getType();
      if (!(Mem.getVariableType(left.toString())
              .equals(((AbstractExpression) right).getType()))) {
        // TODO: do this without toString...
//        throw new UnsupportedOperationException("The following assignment has type mismatches:\n\t\t"
//                + this.generate(null) + "\nType " + Mem.getVariableType(left.generate(null))
//                + " does not match " + ((AbstractExpression) right).getType());
      }
    }
  }

  public AAssignment(String actualType, AbstractTree left, AbstractExpression right,
          boolean declaration, String position) {
    this.left = left;
    this.right = right;
    this.declaration = declaration;
    if (GrammarMain.checkTypes()) {
      findTypeDecl(actualType);
    }
    this.actualType = actualType;
    this.position = position;
  }

  public void findTypeDecl(String actualType) {
    if (declaration) {
      this.type = ((AbstractExpression) right).getType();
    } else {
      this.type = ((AbstractExpression) left).getType();
      try {
        if (!(((AbstractExpression) left).getType()).equals(((AbstractExpression) right).getType())) {
          // TODO: do this without toString...
//          throw new UnsupportedOperationException("The following assignment has type mismatches:\n\t\t"
//                  + this.generate(null) + "\nType " + ((AbstractExpression) left).getType()
//                  + " does not match " + ((AbstractExpression) right).getType());
        }
      } catch (NullPointerException e) {
        // TODO: do this without toString...
//        throw new UnsupportedOperationException("left: " + left.generate(null)
//                + " of type " + ((AbstractExpression) left).getType() + "; right: "
//                + right.generate(null) + " of type " + ((AbstractExpression) right).getType());
      }
    }
  }

  @Override
  public void testType() {
    // TODO: test, look into memory, differ between String s = "" and s = ""

  }

  @Override
  public void generate(Writer out) throws IOException {
    if (this.declaration) {
      if (!this.actualType.equals("")) {
        out.append(this.actualType + " ");
        this.left.generate(out);
        out.append(" = ");
        this.right.generate(out);
      }
      out.append(this.type + " ");
      this.left.generate(out);
      out.append(" = ");
      this.right.generate(out);
    }
    this.left.generate(out);
    out.append(" = ");
    this.right.generate(out);
  }

  @Override
  public String getType() {
    return this.type;
  }

}
