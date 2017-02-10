/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;

/**
 * represents a single grammar rule located in the .rudi file; each rule will be
 * transformed in a separate .java class (i.e., a separate .java file)
 *
 * @author Anna Welker
 */
public class GrammarRule extends RudiTree {

  // comment label comment if_statement
  String label;
  StatIf ifstat;
  // remember whether you are toplevel
  boolean toplevel;

  public GrammarRule(String label, StatIf ifstat, boolean toplevel) {
    this.label = label;
    this.ifstat = ifstat;
    this.toplevel = toplevel;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { ifstat };
    return Arrays.asList(dtrs);
  }
}
