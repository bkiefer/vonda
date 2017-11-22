/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.List;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class ExpFieldAccess extends RTExpLeaf {

  List<RTExpression> parts;
  List<String> representation;

  public ExpFieldAccess(List<RTExpression> parts, List<String> representation) {
    this.parts = parts;
    this.representation = representation;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() { return parts; }

  public RTExpression ensureBooleanUFA() {
    int s = parts.size() - 1;
    if (s == 0) {
      return parts.get(s).ensureBooleanBasic();
    }

    List<RTExpression> smaller = parts.subList(0, s);
    List<String> smallerRep = representation.subList(0, s);
    ExpFieldAccess first = fixFields(new ExpFieldAccess(smaller, smallerRep));
    first.type = parts.get(s - 1).type;
    RTExpression right = this.ensureBooleanBasic();
    return fixFields(new ExpBoolean(first.ensureBooleanUFA(), right, "&&", true));
  }
}
