/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.List;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * this is a call to a function; as field accesses are considered to be
 * retrieving sth from the ontology, we define that there will never be
 * functions used in a .rudi file that would need to be accessed via multiple
 * other classes
 *
 * @author Anna Welker
 */
public class ExpFuncCall extends RTExpLeaf {

  List<RTExpression> params;
  String realOrigin;
  // remember whether this is used by new
  boolean newexp;
  // the type of object this function was called upon (null if nothing)
  Type calledUpon;

  public ExpFuncCall(String representation, List<RTExpression> expressions,
      boolean newexpression) {
    content = representation;
    params = expressions;
    newexp = newexpression;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() { return params; }
}
