/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Arrays;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * Either used to encode a normal java creation using new or to create a new
 * rdf object
 * @author pal
 */
public class ExpNew extends RTExpression {

  // the java object creation as a function call
  RTExpression construct;

  /**
   *
   * @param toCreate set to the rdf object class whose type should be created
   *                  or to null if this is a java creation
   * @param construct set to the function call that represents the java creation
   *                  (to null if no java creation)
   */
  public ExpNew(String toCreate, RTExpression constr){
    type = new Type(toCreate);
    construct = constr;
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
    RudiTree[] dtrs = { construct };
    return Arrays.asList(dtrs);
  }


}
