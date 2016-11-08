/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;
import java.util.Objects;

/**
 * this is a call to a function; as field accesses are considered to be
 * retrieving sth from the ontology, we define that there will never be
 * functions used in a .rudi file that would need to be accessed via multiple
 * other classes
 *
 * @author Anna Welker
 */
public class UFuncCall extends RTLeaf {

  String representation;
  List<RTExpression> exps;

  public UFuncCall(String representation, List<RTExpression> exps) {
    this.representation = representation;
    this.exps = exps;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 23 * hash + Objects.hashCode(this.representation);
    hash = 23 * hash + Objects.hashCode(this.exps);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final UFuncCall other = (UFuncCall) obj;
    if (!Objects.equals(this.representation, other.representation)) {
      return false;
    }
    if (!Objects.equals(this.exps, other.exps)) {
      return false;
    }
    return true;
  }
}
