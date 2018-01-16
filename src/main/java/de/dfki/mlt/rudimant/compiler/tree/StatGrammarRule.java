/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Arrays;

import de.dfki.mlt.rudimant.compiler.Environment;

/**
 * represents a single grammar rule located in the .rudi file; each rule will be
 * transformed into a separate java method
 *
 * @author Anna Welker
 */
public class StatGrammarRule extends RTStatement implements RTBlockNode {

  // comment label comment if_statement
  String label;
  StatIf ifstat;
  // remember whether you are toplevel
  boolean toplevel;
  // remember your rule id
  int ruleId;

  public StatGrammarRule(String label, StatIf ifstat) {
    this.label = label;
    this.ifstat = ifstat;
  }

  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { ifstat };
    return Arrays.asList(dtrs);
  }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings;

  public Environment getParentBindings() { return _localBindings.getParent(); }

  public Environment enterEnvironment(Environment parent) {
    return _localBindings != null ? _localBindings
        : (_localBindings = Environment.getEnvironment(parent));
  }
}
