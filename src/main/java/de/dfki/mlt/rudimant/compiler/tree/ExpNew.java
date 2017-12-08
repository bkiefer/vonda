/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.List;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * Either used to encode a normal java creation using new or to create a new
 * rdf object
 * @author pal
 */
public class ExpNew extends RTExpression {

  // the java object creation as a function call
  List<RTExpression> params;

  /**
   * @param toCreate the class of the object to create
   * @param params the parameters to the constructor call
   */
  public ExpNew(String toCreate, List<RTExpression> params){
    type = new Type(toCreate);
    this.params = params;
  }

  public ExpNew(String toCreate){
    this(toCreate, null);
  }

  @Override
  public void propagateType(Type upperType) {
    // TODO: does this make sense here????
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return params;
  }


}
