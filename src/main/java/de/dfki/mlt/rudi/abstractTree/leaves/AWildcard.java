/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.abstractTree.*;

/**
 *
 * @author anna
 */
public class AWildcard  implements AbstractTree, AbstractExpression, AbstractLeaf{

  @Override
  public void testType() {
    // do nothing
  }
  
  @Override
  public String toString(){
    return "this.wildcard";   // wildcard is a local variable in resulting class
  }

  @Override
  public String getType() {
    return "Object"; // ???
  }
}
