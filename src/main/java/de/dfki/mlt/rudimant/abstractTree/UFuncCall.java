/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;

/**
 * this is a call to a function; as field accesses are considered to be
 * retrieving sth from the ontology, we define that there will never be
 * functions used in a .rudi file that would need to be accessed via multiple
 * other classes
 *
 * @author Anna Welker
 */
public class UFuncCall extends RTExpLeaf {

  List<RTExpression> exps;
  String realOrigin;

  public UFuncCall(String full, String representation, List<RTExpression> exps) {
    this.fullexp = full;
    this.content = representation;
    this.exps = exps;
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

  public Iterable<? extends RudiTree> getDtrs() { return exps; }
}
