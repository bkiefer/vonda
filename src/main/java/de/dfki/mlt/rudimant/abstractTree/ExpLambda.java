/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * just to be able to deal with lambda expressions if someone should use them,
 * but there is nothing like type checking implemented yet
 *
 * @author Anna Welker
 */
public class ExpLambda extends RTExpLeaf {

  public ExpLambda(String exp) {
    content = fullexp = exp;
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
  public String visitStringV(VGenerationVisitor v){
    return v.visitNode(this);
  }
  
  @Override
  public void visitCondPart(VRuleConditionVisitor v){
    v.visitNode(this);
  }
}
