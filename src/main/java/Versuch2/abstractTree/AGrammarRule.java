/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree;

import Versuch2.abstractTree.statements.AIfStatement;

/**
 *
 * @author anna
 */
public class AGrammarRule implements AbstractTree{
  
  private String label;
  private AIfStatement ifstat;
  
  @Override
  public String toString(){
    return "execute(\"" + label + "\");\n" + ifstat + "\n\n";
  }

  @Override
  public void testType() {
  }
  
}
