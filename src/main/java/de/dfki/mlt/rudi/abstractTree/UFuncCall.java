/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;
import java.util.List;

import java.util.ArrayList;

/**
 * this is a call to a function; as field accesses are considered to be retrieving
 * sth from the ontology, we define that there will never be functions used in
 * a .rudi file that would need to be accessed via another class
 *
 * @author Anna Welker
 */
public class UFuncCall extends RTLeaf{

  String type;
  String representation;
  List<RTExpression> exps;

  public UFuncCall(String representation, List<RTExpression> exps) {
    this.representation = representation;
    this.exps = exps;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
