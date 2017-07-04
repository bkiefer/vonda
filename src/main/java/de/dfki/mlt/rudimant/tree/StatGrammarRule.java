/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.Arrays;

import de.dfki.mlt.rudimant.Environment;

/**
 * represents a single grammar rule located in the .rudi file; each rule will be
 * transformed into a separate java method
 *
 * @author Anna Welker
 */
public class StatGrammarRule extends RTStatement {

  // comment label comment if_statement
  String label;
  StatIf ifstat;
  // remember whether you are toplevel
  // TODO: EVENTUALLY REMOVE, CAN BE DETERMINED OTHERWISE (ClassEnv)
  boolean toplevel;

  private Environment _localBindings;

  public StatGrammarRule(String label, StatIf ifstat) {
    this.label = label;
    this.ifstat = ifstat;
  }

  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VisitorGeneration v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { ifstat };
    return Arrays.asList(dtrs);
  }

  public void setBindings(Environment local) {
    _localBindings = local;
  }

  public Environment getBindings() { return _localBindings; }
}
