/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class UFieldAccess extends RTExpLeaf {

  List<RTExpression> parts;
  List<String> representation;
  boolean asked = false;

  public UFieldAccess(String fullexp, List<RTExpression> parts, List<String> representation) {
    this.parts = parts;
    this.representation = representation;
    this.fullexp = fullexp;
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

  public Iterable<? extends RudiTree> getDtrs() { return parts; }
}
