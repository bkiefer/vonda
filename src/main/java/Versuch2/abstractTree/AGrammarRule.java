/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree;

import Versuch2.abstractTree.leaves.ACommentBlock;
import Versuch2.abstractTree.statements.AIfStatement;

/**
 *
 * @author anna
 */
public class AGrammarRule implements AbstractTree{
  
  // label comment if_statement
  
  private String label;
  private ACommentBlock comment;
  private AIfStatement ifstat;

  public AGrammarRule(String label, ACommentBlock comment,AIfStatement ifstat) {
    this.label = label;
    this.ifstat = ifstat;
    this.comment = comment;
  }
  
  @Override
  public String toString(){
    return "execute(\"" + label + "\");\n" + comment + "\n"+ ifstat + "\n\n";
  }

  @Override
  public void testType() {
    this.ifstat.testType();
  }
  
}
