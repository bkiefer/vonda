/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;
import java.util.List;

/**
 * FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR
 * loop_statement_block = a special for statement allowing tuples of arbitrary
 * size
 *
 * @author Anna Welker
 */
public class StatFor3 extends RTStatement {

  List<String> variables;
  RudiTree exp;
  RTStatement statblock;
  String position;

  public StatFor3(List<String> variables, RudiTree exp,
      RTStatement block, String position) {
    this.variables = variables;
    this.exp = exp;
    this.statblock = block;
    this.position = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public String visitStringV(VGenerationVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public void visitCondPart(VRuleConditionVisitor v){
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { exp, statblock };
    return Arrays.asList(dtrs);
  }
}
