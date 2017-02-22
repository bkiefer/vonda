/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * representation of a variable
 *
 * @author Anna Welker
 */
public class UVariable extends RTExpLeaf {

  String originClass;
  // set to the class whose attribute this variable is; null if the variable
  // is declared in originClass
  String realOrigin;

  public UVariable(String type, String representation, String originClass) {
    this(representation, originClass);
    this.type = type;
  }

  public UVariable(String representation, String originClass) {
    content = fullexp = representation;
    this.originClass = originClass;
  }

  @Override
  public String toString() {
    return this.fullexp;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  /**
   * if we are an expression but this method is called, we should write to out;
   * it means that the instance calling us must be a statement
   * @param v
   */
  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.out.append(v.visitNode(this));
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }

  public void propagateType(String upperType) {
    // it's not an error if the type is null
    type = upperType;
  }
}
