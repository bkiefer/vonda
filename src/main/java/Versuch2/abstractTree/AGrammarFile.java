/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree;

/**
 *
 * @author anna
 */
public class AGrammarFile implements AbstractTree{
  private AbstractTree[] rules;

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
