/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree;

import java.util.List;

/**
 *
 * @author anna
 */
public class AGrammarFile implements AbstractTree{
  
  // (comment grammar_rule)* comment
  
  private List<AbstractTree> rules;

  public AGrammarFile(List<AbstractTree> rules) {
    this.rules = rules;
  }

  @Override
  public String toString(){
    String ret = "";
    for (AbstractTree r : rules){
      ret += r.toString();
    }
    return ret;
  }
  
  @Override
  public void testType() {
  }
}
