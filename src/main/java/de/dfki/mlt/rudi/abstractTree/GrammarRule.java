/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * represents a single grammar rule located in the .rudi file; each rule
 * will be transformed in a separate .java class (i.e., a separate .java file)
 * @author Anna Welker
 */
public class GrammarRule implements RudiTree{

  // label comment if_statement

  String label;
  UCommentBlock comment;
  StatIf ifstat;
  // a help to mark this rule for return managing
  int number;

  public GrammarRule(String label, UCommentBlock comment,StatIf ifstat) {
    this.label = label;
    this.ifstat = ifstat;
    this.comment = comment;
  }

  /**
   * this is the number-t subrule of the superrule
   * @param number
   */
  void setNumber(int number){
    this.number = number;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
