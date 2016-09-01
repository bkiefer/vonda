/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.Writer;

/**
 * represents a single grammar rule located in the .rudi file; each rule
 * will be transformed in a separate .java class (i.e., a separate .java file)
 * @author Anna Welker
 */
public class GrammarRule implements RudiTree{

  // label comment if_statement

  String label;
  String classname;
  UCommentBlock comment;
  StatIf ifstat;
  static Writer out;

  public GrammarRule(String label, UCommentBlock comment,StatIf ifstat) {
    this.label = label;
    this.ifstat = ifstat;
    this.comment = comment;
    this.classname = label.substring(0, 1).toUpperCase() + label.substring(1);
  }

  @Override
  public void testType() {
    this.ifstat.testType();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
