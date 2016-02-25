/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.leaves;

import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractLeaf;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.AbstractType;
import java.util.List;

/**
 *
 * @author anna
 */
public class AFunctAccess  implements AbstractTree, AbstractExpression, AbstractLeaf{
  
  private AbstractType type;
  private String representation;
  private List<AbstractExpression> exps;

  public AFunctAccess(AbstractType type, String representation, List<AbstractExpression> exps) {
    this.type = type;
    this.representation = representation;
    this.exps = exps;
  }

  @Override
  public void testType() {
    // nothing to do
  }
  
  @Override
  public String toString(){
    String args = "";
    for(int i = 0; i < this.exps.size(); i++){
      args += this.exps.get(i);
      if(i != this.exps.size() -1){
        args += ", ";
      }
    }
    return this.representation + "(" + args + ")";
  }

  @Override
  public AbstractType getType() {
    return this.type;
  }
}
