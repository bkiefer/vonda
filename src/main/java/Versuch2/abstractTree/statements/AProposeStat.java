/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;

/**
 *
 * @author anna
 */
public class AProposeStat extends AbstractStatement implements AbstractTree{
  
  private AbstractExpression arg;
  private AbstractStatement[] block;

  public AProposeStat(AbstractExpression arg, AbstractStatement[] block) {
    this.arg = arg;
    this.block = block;
  }

  @Override
  public void testType() {
    // TODO: arg should be a string expression
  }
  
  @Override
  public String toString(){
    return "propose(" + arg + ", new Proposal() {\n public void run() {\n"
            + printStatBlock(block) + "}\n" + "});\n";
  }
}
