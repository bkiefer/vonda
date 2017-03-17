/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represents dialogue acts
 *
 * @author Anna Welker
 */
public class ExpDialogueAct extends RTExpression {

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment
  RTExpression daType;
  RTExpression proposition;
  List<RTExpression> exps;

  public ExpDialogueAct(RTExpression daType,
      RTExpression proposition, List<RTExpression> exps) {
    this.daType = daType;
    this.proposition = proposition;
    this.exps = exps;
    this.type = DIALOGUE_ACT_TYPE;
  }

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

  public Iterable<? extends RudiTree> getDtrs() {
    List<RudiTree> dtrs = new ArrayList<>();
    dtrs.add(daType);
    dtrs.add(proposition);
    dtrs.addAll(exps);
    return dtrs;
  }

  public void propagateType(String upperType) {
    logger.error("Why didn't this type percolate up? " + fullexp + " " + type);
  }
}
